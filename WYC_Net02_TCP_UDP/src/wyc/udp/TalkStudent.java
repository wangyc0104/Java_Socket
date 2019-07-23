package wyc.udp;

/**
 * 加入多线程，实现双向交流，模拟在线聊天
 * 学生端
 * @author 王以诚
 */
public class TalkStudent {
	public static void main(String[] args) {
		// 从本机7777端口发送至对方9999端口
		new Thread(new TalkSend(7777, "localhost", 9999)).start();
		// 在本机8888端口接收对方的信息
		new Thread(new TalkReceive(8888, "导师")).start();
	}
}
