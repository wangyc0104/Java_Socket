package wyc.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * - 多次交流：发送端 <br>
 * 1.使用DatagramSocket 指定端口 创建发送端 <br>
 * 2.准备数据，转成字节数组 <br>
 * 3.封装成DatagramPacket包裹，指定目的地 <br>
 * 4.发送包裹 <br>
 * 5.释放资源 <br>
 * @author 王以诚
 */
public class UdpTalkClient {
	public static void main(String[] args) throws Exception {
		System.out.println("发送方启动中……");
		// 1.使用DatagramSocket 指定端口 创建发送端 <br>
		DatagramSocket client = new DatagramSocket(8888);
		// 2.准备数据，转成字节数组 <br>
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String data = reader.readLine();
			byte[] datas = data.getBytes();
			// 3.封装成DatagramPacket包裹，指定目的地 <br>
			DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
					new InetSocketAddress("localhost", 9999));
			// 4.发送包裹 <br>
			client.send(packet);
			if (data.equalsIgnoreCase("bye")) {
				break;
			}
		}
		// 5.释放资源 <br>
		client.close();
	}
}
