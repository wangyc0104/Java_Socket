package wyc.chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端 <br>
 * @author 王以诚
 */
public class ChatServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1.指定端口：使用ServerSocket创建服务器<br>
		ServerSocket server = new ServerSocket(8888);
		// 2.阻塞式等待连接accept<br>
		Socket client = server.accept();
		System.out.println("一个客户端建立了连接");
		// 3.接收消息
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String msg = dis.readUTF();
		// 4.返回消息
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();
		// 5.释放资源
		dos.close();
		dis.close();
		client.close();
	}
}
