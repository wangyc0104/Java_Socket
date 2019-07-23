package wyc.location;

import java.net.InetSocketAddress;

/**
 * 端口 <br>
 * 1. 区分软件 <br>
 * 2. 2个字节 0-65535 <br>
 * 3. 同一个协议端口不能冲突 <br>
 * 4. 定义端口越大越好 <br>
 * 
 * InetSocketAddress <br>
 * 1. 构造器 new InetSocketAddress(地址|域名|端口) <br>
 * 2. 方法 getAddress() getPort() getHostName() <br>
 * 
 * @author 王以诚
 */
public class PortTest {
	public static void main(String[] args) {
		// 包含端口
		InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);
		InetSocketAddress socketAddress2 = new InetSocketAddress("localhost", 9000);
		System.out.println(socketAddress.getHostName());
		System.out.println(socketAddress.getAddress());
		System.out.println(socketAddress.getPort());
		System.out.println();
		System.out.println(socketAddress2.getHostName());
		System.out.println(socketAddress2.getAddress());
		System.out.println(socketAddress2.getPort());
	}
}
