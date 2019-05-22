import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws InterruptedException {
//		for (int i=0; i < 10; i++) {
//			MyThread myThread = new MyThread();
//			myThread.start();
//		}

		MyExecutionManager manager = new MyExecutionManager(4);

		ArrayList<Runnable> tasks = new ArrayList<>();
		tasks.add(new Runnable() {
			@Override
			public void run() {
				System.out.println("");
			}
		});


		Context context =  manager.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("callback!");
			}
		}, new Runnable() {
			@Override
			public void run() {
				System.out.println("1st Task : " + Thread.currentThread().getName() );
			}
		}, new Runnable() {
			@Override
			public void run() {
				System.out.println("2nd Task : " + Thread.currentThread().getName() );
			}
		}, new Runnable() {
			@Override
			public void run() {
				System.out.println("3rd Task : " + Thread.currentThread().getName() );
			}
		}, new Runnable() {
			@Override
			public void run() {
				System.out.println("4th Task : " + Thread.currentThread().getName() );
			}
		});

		System.out.println("context : " + context.getCompletedTaskCount());

		Thread.sleep(1000);

		System.out.println("context : " + context.getCompletedTaskCount());
		context.interrupt();

	}

}
