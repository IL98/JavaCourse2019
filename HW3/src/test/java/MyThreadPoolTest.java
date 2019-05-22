import org.junit.Assert.*;
import org.junit.Test;

public class MyThreadPoolTest {
	@Test
	public void Test1() {
		MyThreadPool myThreadPool = new MyThreadPool(2);

		myThreadPool.start();


		for (int i=0; i < 10; i++) {
			myThreadPool.execute(() -> {
				int j = 0;
				while (j < 1_000_000)
					j++;

				System.out.println("I'm done : " +  Thread.currentThread().getName());
			});
		}

		myThreadPool.stopRun();

	}


}