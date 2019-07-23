package wyc.udp;

/**
 * 加入多线程，实现双向交流，模拟在线咨询
 * 导师端
 * @author 王以诚
 */
public class TalkTeacher {
	public static void main(String[] args) {
		// 在本机9999端口接收对方的信息
		new Thread(new TalkReceive(9999, "学生")).start();
		// 从本机6666端口发送至对方8888端口
		new Thread(new TalkSend(6666, "localhost", 8888)).start();
	}
}
