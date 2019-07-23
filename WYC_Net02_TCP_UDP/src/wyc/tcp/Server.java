package wyc.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：熟悉流程<br>
 * 创建服务器<br>
 * 1.指定端口：使用ServerSocket创建服务器<br>
 * 2.阻塞式等待连接accept<br>
 * 3.操作：输入输出流操作<br>
 * 4.关闭资源<br>
 * 
 * @author 王以诚
 */
public class Server {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1.指定端口：使用ServerSocket创建服务器<br>
		ServerSocket server = new ServerSocket(8888);
		// 2.阻塞式等待连接accept<br>
		Socket client = server.accept();
		System.out.println("一个客户端建立了连接");
		// 3.操作：输入输出流操作
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String data = dis.readUTF();
		System.out.println(data);
		// 4.关闭资源
		dis.close();
		client.close();
		server.close();
	}
}
