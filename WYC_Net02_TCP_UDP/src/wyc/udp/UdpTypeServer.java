package wyc.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * - 接收端 <br>
 * 1.使用DatagramSocket 指定端口 创建接收端 <br>
 * 2.准备容器 封装成DatagramPacket 包裹 <br>
 * 3.阻塞式接收包裹receive(DatagramPacket p) <br>
 * 4.分析数据：将字节数组还原为对应的类型 <br>
 * - getData() <br>
 * - getLength() <br>
 * 5.释放资源 <br>
 * @author 王以诚
 */
public class UdpTypeServer {
	public static void main(String[] args) throws Exception {
		System.out.println("接收方启动中……");
		// 1.使用DatagramSocket 指定端口 创建接收端 <br>
		DatagramSocket server = new DatagramSocket(9999);
		// 2.准备容器 封装成DatagramPacket 包裹 <br>
		byte[] container = new byte[1024 * 60];
		DatagramPacket packet = new DatagramPacket(container, 0, container.length);
		// 3.阻塞式接收包裹receive(DatagramPacket p) <br>
		server.receive(packet); // 阻塞式
		// 4.分析数据 <br>
		// -getData() <br>
		// -getLength() <br>
		byte[] datas = packet.getData();
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		// 顺序与写出一致
		String msg = dis.readUTF();
		int age = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		System.out.println(msg);
		System.out.println(age);
		System.out.println(flag);
		System.out.println(ch);
		// 5.释放资源 <br>
		server.close();
	}
}
