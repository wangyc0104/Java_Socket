package wyc.practicetcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class PracticeServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8888);
		Socket client = server.accept();
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String str;
		while((str=reader.readLine())!=null) {
			System.out.println(str);
		}
		client.close();
		server.close();
	}
}
