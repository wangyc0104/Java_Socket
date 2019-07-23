package wyc.practiceudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class PracticeTalkReceive implements Runnable {

	DatagramSocket receiveSocket;
	DatagramPacket packet;
	boolean isRunning = true;

	public PracticeTalkReceive(int port) {
		try {
			receiveSocket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
			receiveSocket.close();
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				// 1.准备接收包裹
				byte[] container = new byte[1024 * 60];
				packet = new DatagramPacket(container, 0, container.length);
				// 2.接收数据
				receiveSocket.receive(packet);
				// 3.分析处理数据
				byte[] datas = packet.getData();
				int len = packet.getLength();
				String data = new String(datas, 0, len);
				if (data.equalsIgnoreCase("bye")) {
					System.out.println("Receive退出！");
					isRunning = false;
				}
				System.out.println(Thread.currentThread().getName() + "收到: " + data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		receiveSocket.close();
	}
}
