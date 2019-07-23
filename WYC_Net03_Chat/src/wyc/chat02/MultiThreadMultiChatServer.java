package wyc.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端 <br>
 * 目标：使用多线程实现多个客户可以正常收发多条消息<br>
 * 问题解决：其他客户必须等待之前的客户退出，才能继续进行 <br>
 * 还有问题： <br>
 * 1.代码不好维护 <br>
 * 2.客户端读写没有分开，必须先写后读（还是面向过程） <br>
 * @author 王以诚
 */
public class MultiThreadMultiChatServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1.指定端口：使用ServerSocket创建服务器<br>
		ServerSocket server = new ServerSocket(8888);
		boolean isRunning = true;
		while (isRunning) {
			// 2.阻塞式等待连接accept<br>
			final Socket client = server.accept();
			System.out.println("一个客户端建立了连接");
			new Thread(new Runnable() {
				@Override
				public void run() {
					DataInputStream dis;
					DataOutputStream dos;
					try {
						dis = new DataInputStream(client.getInputStream());
						dos = new DataOutputStream(client.getOutputStream());
						boolean isRunning = true;
						while (isRunning) {
							// 3.接收消息
							String msg = dis.readUTF();
							// 4.返回消息
							dos.writeUTF(msg);
							dos.flush();
						}
						// 5.释放资源
						if (null != dos) {
							dos.close();
						}
						if (null != dis) {
							dis.close();
						}
						if (null != client) {
							client.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		server.close();
	}
}
