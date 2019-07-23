package wyc.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * - 发送端 <br>
 * 1.使用DatagramSocket 指定端口 创建发送端 <br>
 * 2.准备数据：将基本类型转成字节数组 <br>
 * 3.封装成DatagramPacket包裹，指定目的地 <br>
 * 4.发送包裹 <br>
 * 5.释放资源 <br>
 * @author 王以诚
 */
public class UdpTypeClient {
	public static void main(String[] args) throws Exception {
		System.out.println("发送方启动中……");
		// 1.使用DatagramSocket 指定端口 创建发送端 <br>
		DatagramSocket client = new DatagramSocket(8888);
		// 2.准备数据：将基本类型转成字节数组 <br>
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
		// 操作数据类型 +数据
		dos.writeUTF("编码辛酸泪");
		dos.writeInt(18);
		dos.writeBoolean(false);
		dos.writeChar('a');
		dos.flush();
		byte[] datas = baos.toByteArray();
		// 3.封装成DatagramPacket包裹，指定目的地 <br>
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 9999));
		// 4.发送包裹 <br>
		client.send(packet);
		// 5.释放资源 <br>
		client.close();
	}
}
