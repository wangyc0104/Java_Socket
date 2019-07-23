package wyc.chat02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 在线聊天室：客户端<br>
 * 目标：使用多线程实现多个客户可以正常收发多条消息<br>
 * 问题解决：其他客户必须等待之前的客户退出，才能继续进行 <br>
 * @author 王以诚
 */
public class MultiThreadMultiChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("-----Client-----");
		// 1.建立连接：使用Socket创建客户端+服务器地址和端口
		Socket client = new Socket("localhost", 8888);
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());
		boolean isRunning = true;
		while (isRunning) {
			// 2.客户端发送消息
			String msg = console.readLine();
			dos.writeUTF(msg);
			dos.flush();
			// 3.获取消息
			msg = dis.readUTF();
			System.out.println(msg);
		}
		// 4.释放资源
		dis.close();
		dos.close();
		client.close();
	}
}
