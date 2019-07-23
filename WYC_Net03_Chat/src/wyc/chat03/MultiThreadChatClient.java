package wyc.chat03;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 在线聊天室: 客户端 <br>
 * 目标: 封装使用多线程实现多个客户可以正常收发多条消息 <br>
 * @author 王以诚
 */
public class MultiThreadChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("-----Client-----");
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client =new Socket("localhost",8888);
		//2、客户端发送消息
		new Thread(new ClientSender(client)).start();  
		new Thread(new ClientReceiver(client)).start();
	}
}
