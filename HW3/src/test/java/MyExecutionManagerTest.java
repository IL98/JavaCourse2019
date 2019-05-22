import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class MyExecutionManagerTest {
	@Test
	public void Test() throws InterruptedException {
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
	}
}