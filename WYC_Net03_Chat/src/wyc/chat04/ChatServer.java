package wyc.chat04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室: 服务器 <br>
 * 目标: 封装使用多线程实现多个客户可以正常收发多条消息 <br>
 * @author 王以诚
 */
public class ChatServer {
	
	/**
	 * 同步容器（存放所有客户端频道的List）
	 */
	private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<Channel>();

	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		boolean isRunning = true;
		while (isRunning) {
			Socket client = server.accept();
			System.out.println("一个客户端建立了连接");
			Channel c = new Channel(client);
			all.add(c); // 管理所有的成员
			new Thread(c).start();
		}
		server.close();
	}

	/**
	 * 客户端频道类
	 * @author 王以诚
	 */
	static class Channel implements Runnable {
		private DataInputStream dis;
		private DataOutputStream dos;
		private Socket client;
		private boolean isRunning;
		private String name;
		/**
		 * 客户端初始化
		 * @param client
		 */
		public Channel(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				isRunning = true;
				// 获取名称
				this.name = receive(); // 收取Send构造方法中发送的name名称
				// 欢迎你的到来
				this.send("欢迎你的到来！");
				this.sendOthers(this.name + "来到了聊天室", true);
			} catch (IOException e) {
				System.out.println("---1------");
				release();
			}
		}

		/**
		 * 客户端接收消息
		 * @return
		 */
		private String receive() {
			String msg = "";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				System.out.println("---2------");
				release();
			}
			return msg;
		}

		/**
		 * 客户端发送消息
		 * @param msg
		 */
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				System.out.println("---3------");
				release();
			}
		}

		/**
		 * 群聊：获取自己的消息，发给他人 <br>
		 * 私聊：约定数据格式：@xxx:输入的内容 <br>
		 */
		private void sendOthers(String msg, boolean isSys) {
			boolean isPrivate = msg.startsWith("@");
			if (isPrivate) { // 私聊
				// 获取目标和数据
				String targetName = msg.substring(1, msg.indexOf(":"));
				msg = msg.substring(msg.indexOf(":") + 1);
				for (Channel other : all) {
					if (other.name.equals(targetName)) {
						other.send(this.name + "悄悄地对你说: " + msg);
					}
				}
			} else {
				for (Channel other : all) {
					if (other == this) {
						continue;
					} else {
						if (!isSys) {
							other.send(this.name + "对所有人说：" + msg); // 群聊消息
						} else {
							other.send(msg); // 系统消息
						}
					}
				}
			}
		}

		/**
		 * 释放资源
		 */
		private void release() {
			this.isRunning = false;
			ChatUtils.close(dis, dos, client);
			// 退出
			all.remove(this);
			sendOthers(this.name + "离开了聊天室", true);
		}

		@Override
		public void run() {
			while (isRunning) {
				String msg = receive();
				if (!msg.equals("")) {
					sendOthers(msg, false);
				}
			}
		}

	}
	
}
