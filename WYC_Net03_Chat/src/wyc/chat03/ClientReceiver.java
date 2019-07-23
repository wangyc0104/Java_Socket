package wyc.chat03;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 使用多线程封装：接收端 <br>
 * 1、接收消息 <br>
 * 2、释放资源 <br>
 * 3、重写run <br>
 * @author 王以诚
 */
public class ClientReceiver implements Runnable {
	private DataInputStream dis;
	private Socket client;
	private boolean isRunning;

	public ClientReceiver(Socket client) {
		this.client = client;
		this.isRunning = true;
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			System.err.println("====2=====");
			release();
		}
	}

	/**
	 * 接收消息
	 * @return
	 */
	private String receive() {
		String msg = "";
		try {
			msg = dis.readUTF();
		} catch (IOException e) {
			System.err.println("====4====");
			release();
		}
		return msg;
	}

	@Override
	public void run() {
		while (isRunning) {
			String msg = receive();
			if (!msg.equals("")) {
				System.err.println(msg);
			}
		}
	}

	/**
	 * 释放资源
	 */
	private void release() {
		this.isRunning = false;
		ChatUtils.close(dis, client);
	}

}
