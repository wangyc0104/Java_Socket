package wyc.location;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP：定位一个节点：计算机、路由、通讯设备 <br>
 * 
 * 两个成员方法： <br>
 * 1.getLocalHost:本机 <br>
 * 2.getByName:根据DNS|IP地址 --> IP <br>
 * 
 * @author 王以诚
 */
public class IPTest {
	public static void main(String[] args) throws UnknownHostException {
		// 根据getLocalHost方法创建InetAddress对象（本机）
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println(addr.getHostAddress());
		System.out.println(addr.getHostName());
		// 根据域名得到InetAddress对象
		addr = InetAddress.getByName("www.163.com");
		System.out.println(addr.getHostAddress());
		System.out.println(addr.getHostName());
		// 根据ip得到InetAddress对象（得不到域名）
		addr = InetAddress.getByName("0.0.0.0");
		System.out.println(addr.getHostAddress());
		System.out.println(addr.getHostName());
	}
}
