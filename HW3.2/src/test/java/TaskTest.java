import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import org.junit.Test;

public class TaskTest {
 	@Test
	public void Test1() {
 		final Task<String> task = new Task<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "I'm done first :" + Thread.currentThread().getName();
			}
		});

		new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(task.get());
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				System.out.println(task.get());
			}
		}).start();

	}
}