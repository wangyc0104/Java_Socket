package wyc.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器存储文件<br>
 * 创建服务器<br>
 * 1.指定端口：使用ServerSocket创建服务器<br>
 * 2.阻塞式等待连接accept<br>
 * 3.操作：输入输出流操作<br>
 * 4.关闭资源<br>
 * 
 * @author 王以诚
 */
public class FileServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1.指定端口：使用ServerSocket创建服务器<br>
		ServerSocket server = new ServerSocket(8888);
		// 2.阻塞式等待连接accept<br>
		Socket client = server.accept();
		System.out.println("一个客户端建立了连接");
		// 3.操作：输入输出流操作
		InputStream is = new BufferedInputStream(client.getInputStream());
		OutputStream os = new BufferedOutputStream(new FileOutputStream("src/tcpndl.png"));
		byte[] flush = new byte[1024];
		int len = -1;
		while ((len = is.read(flush)) != -1) {
			os.write(flush, 0, len);
		}
		os.flush();
		// 4.关闭资源
		is.close();
		os.close();
		client.close();
		server.close();
	}
}
