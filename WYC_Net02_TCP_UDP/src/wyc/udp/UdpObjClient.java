package wyc.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * - 引用类型：发送端 <br>
 * 1.使用DatagramSocket 指定端口 创建发送端 <br>
 * 2.准备数据：将引用类型转成字节数组 <br>
 * 3.封装成DatagramPacket包裹，指定目的地 <br>
 * 4.发送包裹 <br>
 * 5.释放资源 <br>
 * @author 王以诚
 */
public class UdpObjClient {
	public static void main(String[] args) throws Exception {
		System.out.println("发送方启动中……");
		// 1.使用DatagramSocket 指定端口 创建发送端 <br>
		DatagramSocket client = new DatagramSocket(8888);
		// 2.准备数据：将基本类型转成字节数组 <br>
		// 写出 -->序列化
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos));
		// 操作数据类型 +数据
		oos.writeUTF("编码辛酸泪");
		oos.writeInt(18);
		oos.writeBoolean(false);
		oos.writeChar('a');
		// 对象
		oos.writeObject("谁解其中味");
		oos.writeObject(new Date());
		Employee emp = new Employee("马云", 400);
		oos.writeObject(emp);
		oos.flush();
		byte[] datas = baos.toByteArray();
		System.out.println("发送数据包大小：" + datas.length);

		// 3.封装成DatagramPacket包裹，指定目的地 <br>
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 9999));
		// 4.发送包裹 <br>
		client.send(packet);
		// 5.释放资源 <br>
		client.close();
	}
}
