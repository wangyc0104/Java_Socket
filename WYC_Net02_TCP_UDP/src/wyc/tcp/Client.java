package wyc.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 目标：熟悉流程<br>
 * 创建客户端<br>
 * 1.建立连接：使用Socket创建客户端+服务器地址和端口<br>
 * 2.操作：输入输出流操作<br>
 * 3.释放资源<br>
 * 
 * @author 王以诚
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("-----Client-----");
		// 1.建立连接：使用Socket创建客户端+服务器地址和端口
		Socket client = new Socket("localhost",8888);
		// 2.操作：输入输出流操作
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		String data = "Hello";
		dos.writeUTF(data);
		dos.flush();
		// 3.释放资源
		dos.close();
		client.close();
	}
}
