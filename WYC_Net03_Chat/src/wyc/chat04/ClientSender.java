package wyc.chat04;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 使用多线程封装：客户端发送端 <br>
 * 1、发送消息 <br>
 * 2、从控制台获取消息 <br>
 * 3、释放资源 <br>
 * 4、重写run <br>
 * @author 王以诚
 */
public class ClientSender implements Runnable {
	
	private BufferedReader console;
	private DataOutputStream dos;
	private Socket client;
	private boolean isRunning;
	private String name;

	/**
	 * 客户端发送端初始化
	 * @param client
	 * @param name
	 */
	public ClientSender(Socket client, String name) {
		this.client = client;
		console = new BufferedReader(new InputStreamReader(System.in));
		this.isRunning = true;
		this.name = name;
		try {
			dos = new DataOutputStream(client.getOutputStream());
			this.send(name);
		} catch (IOException e) {
			System.out.println("==1==");
			this.release();
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			String msg = getStrFromConsole();
			if (!msg.equals("")) {
				send(msg);
			}
		}
	}

	/**
	 * 发送消息
	 * @param msg
	 */
	private void send(String msg) {
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("===3==");
			release();
		}
	}

	/**
	 * 从控制台获取消息
	 * @return
	 */
	private String getStrFromConsole() {
		try {
			return console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 释放资源
	 */
	private void release() {
		this.isRunning = false;
		ChatUtils.close(dos, client);
	}

}
