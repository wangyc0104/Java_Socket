package wyc.practiceudp;

public class PracticeUdpA {
	public static void main(String[] args) {
		new Thread(new PracticeTalkReceive(6666), "A").start();
		new Thread(new PracticeTalkSend(8888, "localhost", 7777)).start();
	}
}
