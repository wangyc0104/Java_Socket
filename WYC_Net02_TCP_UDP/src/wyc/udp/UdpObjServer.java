package wyc.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

/**
 * - 引用类型：接收端 <br>
 * 1.使用DatagramSocket 指定端口 创建接收端 <br>
 * 2.准备容器 封装成DatagramPacket 包裹 <br>
 * 3.阻塞式接收包裹receive(DatagramPacket p) <br>
 * 4.分析数据：将字节数组还原为对应的引用类型 <br>
 * -getData() <br>
 * -getLength() <br>
 * 5.释放资源 <br>
 * 
 * @author 王以诚
 */
public class UdpObjServer {
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
		// 读取 -->反序列化
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		// 顺序与写出一致
		String msg = ois.readUTF();
		int age = ois.readInt();
		boolean flag = ois.readBoolean();
		char ch = ois.readChar();
		System.out.println(msg);
		System.out.println(age);
		System.out.println(flag);
		System.out.println(ch);
		// 对象的数据还原
		Object str = ois.readObject();
		Object date = ois.readObject();
		Object employee = ois.readObject();
		if (str instanceof String) {
			String strObj = (String) str;
			System.out.println(strObj);
		}
		if (date instanceof Date) {
			Date dateObj = (Date) date;
			System.out.println(dateObj);
		}
		if (employee instanceof Employee) {
			Employee empObj = (Employee) employee;
			System.out.println(empObj.getName() + "-->" + empObj.getSalary());
		}
		// 5.释放资源 <br>
		server.close();
	}
}
