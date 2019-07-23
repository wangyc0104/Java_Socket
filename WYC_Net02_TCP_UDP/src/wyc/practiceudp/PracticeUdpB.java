package wyc.practiceudp;

public class PracticeUdpB {
	public static void main(String[] args) {
		new Thread(new PracticeTalkReceive(7777), "B").start();
		new Thread(new PracticeTalkSend(9999, "localhost", 6666)).start();
	}
}
