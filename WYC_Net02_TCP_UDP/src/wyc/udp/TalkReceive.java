package wyc.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端<br>
 * @author 王以诚
 */
public class TalkReceive implements Runnable {

	DatagramSocket server;
	private String from;

	public TalkReceive(int port, String from) {
		this.from = from;
		try {
			server = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				// 2.准备容器 封装成DatagramPacket 包裹 <br>
				byte[] container = new byte[1024 * 60];
				DatagramPacket packet = new DatagramPacket(container, 0, container.length);
				// 3.阻塞式接收包裹receive(DatagramPacket p) <br>
				server.receive(packet); // 阻塞式
				// 4.分析数据 <br>
				byte[] datas = packet.getData();
				int len = packet.getLength();
				String data = new String(datas, 0, len);
				System.out.println(from + "：" + data);
				if (data.equalsIgnoreCase("bye")) {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
