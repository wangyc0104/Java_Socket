package wyc.chat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 在线聊天室: 客户端 <br>
 * 目标: 封装使用多线程实现多个客户可以正常收发多条消息 <br>
 * @author 王以诚
 */
public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("-----Client-----");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入用户名：");
//		!!!不可以这样使用，因为br关闭后，会把所有涉及的资源（包括System.in）也关闭，导致后面的资源无法调用System.in
//		String name = "";
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//			name = br.readLine();
//		}
		String name = br.readLine();
		// 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2、客户端发送消息
		new Thread(new ClientSender(client, name)).start();
		new Thread(new ClientReceiver(client)).start();
	}
}
