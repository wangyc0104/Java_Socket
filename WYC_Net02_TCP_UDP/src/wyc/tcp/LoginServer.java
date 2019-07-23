package wyc.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟登录：单向<br>
 * 创建客户端<br>
 * 1.建立连接：使用Socket创建客户端+服务器地址和端口<br>
 * 2.操作：输入输出流操作<br>
 * 3.释放资源<br>
 * 
 * @author 王以诚
 */
public class LoginServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1.指定端口：使用ServerSocket创建服务器<br>
		ServerSocket server = new ServerSocket(8888);
		// 2.阻塞式等待连接accept<br>
		Socket client = server.accept();
		System.out.println("一个客户端建立了连接");
		// 3.操作：输入输出流操作
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String datas = dis.readUTF();
		// 分析数据
		String[] dataArray = datas.split("&");
		for (String info : dataArray) {
			String[] userInfo = info.split("=");
			if (userInfo[0].equals("uname")) {
				System.out.println("你的用户名为：" + userInfo[1]);
			} else if (userInfo[0].equals("upwd")) {
				System.out.println("你的密码为：" + userInfo[1]);
			}
		}
		// 4.关闭资源
		dis.close();
		client.close();
		server.close();
	}
}
