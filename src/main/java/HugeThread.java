public class HugeThread {

	public static void main(String[] args) {
		
		final Data data = new Data();
		for(int i = 0; i < 2; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j = 0; j < 100; j ++){
						data.add();
					}
				}
			}).start();
		}
		
		for(int i = 0; i < 2; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j = 0; j < 100; j ++){
						data.sub();
					}
				}
			}).start();
		}
	}
}

class Data {

	private int j = 0;

	public synchronized void add() {
		j++;
		System.out.println(Thread.currentThread().getName() + ": after add the value of j is " + j);
	}

	public synchronized void sub() {
		j--;
		System.out.println(Thread.currentThread().getName() + ": after sub the value of j is " + j);
	}

}
