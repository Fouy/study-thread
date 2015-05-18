public class A {
	JManager j = new JManager();

	public static void main(String[] args) {
		new A().call();
	}

	void call() {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						j.accumulate();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						j.sub();
					}
				}
			}).start();
		}
	}
}

class JManager {
	private int j = 0;

	public synchronized void sub() {
		j--;
	}

	public synchronized void accumulate() {
		j++;
	}

}
