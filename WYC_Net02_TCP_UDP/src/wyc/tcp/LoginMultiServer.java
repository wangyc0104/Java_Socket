package wyc.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟登录：双向<br>
 * 创建客户端<br>
 * 1.建立连接：使用Socket创建客户端+服务器地址和端口<br>
 * 2.操作：输入输出流操作<br>
 * 3.释放资源<br>
 * 
 * @author 王以诚
 */
public class LoginMultiServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1.指定端口：使用ServerSocket创建服务器<br>
		ServerSocket server = new ServerSocket(8888);
		boolean isRunning = true;
		// 2.阻塞式等待连接accept<br>
		while (isRunning) {
			Socket client = server.accept();
			System.out.println("一个客户端建立了连接");
			new Thread(new Channel(client)).start();
		}
		server.close();
	}

	static class Channel implements Runnable {
		Socket client;
		// IO流
		private DataInputStream dis;
		private DataOutputStream dos;

		public Channel(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				release();
			}
		}

		// 接收数据
		private String receive() {
			String datas = "";
			try {
				datas = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return datas;
		}

		// 发送数据
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 释放资源
		private void release() {
			// 4.关闭资源
			try {
				if (null != dos) {
					dos.close();
				}
				if (null != dis) {
					dis.close();
				}
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// 3.操作：输入输出流操作
			String uname = "";
			String upwd = "";
			// 分析数据
			String[] dataArray = receive().split("&");
			for (String info : dataArray) {
				String[] userInfo = info.split("=");
				if (userInfo[0].equals("uname")) {
					System.out.println("你的用户名为：" + userInfo[1]);
					uname = userInfo[1];
				} else if (userInfo[0].equals("upwd")) {
					System.out.println("你的密码为：" + userInfo[1]);
					upwd = userInfo[1];
				}
			}
			if (uname.equals("wangyc") && upwd.equals("good")) {
				// 登录成功
				send("登录成功！");
			} else {
				// 登录失败
				send("登录失败！");
			}
			release();
		}
	}
}
