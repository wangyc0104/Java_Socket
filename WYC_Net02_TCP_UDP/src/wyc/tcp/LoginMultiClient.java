package wyc.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 模拟登录：双向<br>
 * 创建客户端<br>
 * 1.建立连接：使用Socket创建客户端+服务器地址和端口<br>
 * 2.操作：输入输出流操作<br>
 * 3.释放资源<br>
 * 
 * @author 王以诚
 */
public class LoginMultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("-----Client-----");
		// 1.建立连接：使用Socket创建客户端+服务器地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2.操作：输入输出流操作
		new Send(client).send();
		new Receive(client).receive();
		// 3.释放资源
		client.close();
	}

	static class Send {
		private Socket client;
		private DataOutputStream dos;
		private BufferedReader console;
		private String msg;

		public Send(Socket client) {
			this.client = client;
			console = new BufferedReader(new InputStreamReader(System.in));
			msg = init();
			try {
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private String init() {
			try {
				System.out.println("请输入用户名：");
				String uname = console.readLine();
				System.out.println("请输入密码：");
				String upwd = console.readLine();
				return "uname=" + uname + "&" + "upwd=" + upwd;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}

		public void send() {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static class Receive {
		Socket client;
		DataInputStream dis;

		public Receive(Socket client) {
			try {
				this.client = client;
				dis = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void receive() {
			String result;
			try {
				result = dis.readUTF();
				System.out.println(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
