package wyc.practiceudp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class PracticeTalkSend implements Runnable {

	DatagramSocket sendSocket;
	String toAddress;
	int toPort;
	boolean isRunning = true;
	BufferedReader reader;

	public PracticeTalkSend(int port, String toAddress, int toPort) {
		try {
			this.sendSocket = new DatagramSocket(port);
			this.toAddress = toAddress;
			this.toPort = toPort;
			this.reader = new BufferedReader(new InputStreamReader(System.in));
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			String data;
			try {
				// 1.准备数据发送的包裹，要指定接收端的地址和端口
				data = reader.readLine();
				if (data.equalsIgnoreCase("bye")) {
					System.out.println("Send退出！");
					isRunning = false;
				}
				byte[] datas = data.getBytes();
				DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
						new InetSocketAddress(toAddress, toPort));
				// 2.发送数据
				sendSocket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 3.关闭资源
		sendSocket.close();
	}

}
