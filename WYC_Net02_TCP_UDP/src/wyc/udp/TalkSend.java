package wyc.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送端<br>
 * @author 王以诚
 */
public class TalkSend implements Runnable {
	
	private DatagramSocket client;
	private BufferedReader reader;
	private String toIP;
	private int toPort;

	/**
	 * 发送端构造方法
	 * @param port 发送端口号
	 * @param toIP 接收端IP地址
	 * @param toPort 接收端口号
	 */
	public TalkSend(int port, String toIP, int toPort) {
		try {
			this.client = new DatagramSocket(port);
			this.toIP = toIP;
			this.toPort = toPort;
			this.reader = new BufferedReader(new InputStreamReader(System.in));
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			String data;
			try {
				data = reader.readLine();
				byte[] datas = data.getBytes();
				// 3.封装成DatagramPacket包裹，指定目的地 <br>
				DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress(this.toIP, this.toPort));
				// 4.发送包裹 <br>
				client.send(packet);
				if (data.equalsIgnoreCase("bye")) {
					break;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
