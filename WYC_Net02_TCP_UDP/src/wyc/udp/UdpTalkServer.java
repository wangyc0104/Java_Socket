package wyc.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * - 多次交流：接收端 <br>
 * 1.使用DatagramSocket 指定端口 创建接收端 <br>
 * 2.准备容器 封装成DatagramPacket 包裹 <br>
 * 3.阻塞式接收包裹receive(DatagramPacket p) <br>
 * 4.分析数据 <br>
 * - getData() <br>
 * - getLength() <br>
 * 5.释放资源 <br>
 * @author 王以诚
 */
public class UdpTalkServer {
	public static void main(String[] args) throws Exception {
		System.out.println("接收方启动中……");
		// 1.使用DatagramSocket 指定端口 创建接收端 <br>
		DatagramSocket server = new DatagramSocket(9999);
		while (true) {
			// 2.准备容器 封装成DatagramPacket 包裹 <br>
			byte[] container = new byte[1024 * 60];
			DatagramPacket packet = new DatagramPacket(container, 0, container.length);
			// 3.阻塞式接收包裹receive(DatagramPacket p) <br>
			server.receive(packet); // 阻塞式
			// 4.分析数据 <br>
			byte[] datas = packet.getData();
			int len = packet.getLength();
			String data = new String(datas, 0, len);
			System.out.println(data);
			if (data.equalsIgnoreCase("bye")) {
				break;
			}
		}
		// 5.释放资源 <br>
		server.close();
	}
}
