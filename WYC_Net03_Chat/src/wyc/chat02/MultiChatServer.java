package wyc.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端 <br>
 * 目标：实现多个客户可以正常收发多条消息<br>
 * 问题：其他客户必须等待之前的客户退出，才能继续进行 <br>
 * @author 王以诚
 */
public class MultiChatServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1.指定端口：使用ServerSocket创建服务器<br>
		ServerSocket server = new ServerSocket(8888);
		while (true) {
			// 2.阻塞式等待连接accept<br>
			Socket client = server.accept();
			System.out.println("一个客户端建立了连接");
			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			boolean isRunning = true;
			while (isRunning) {
				// 3.接收消息
				String msg = dis.readUTF();
				// 4.返回消息
				dos.writeUTF(msg);
				dos.flush();
			}
			// 5.释放资源
			dos.close();
			dis.close();
			client.close();
		}
	}
}
