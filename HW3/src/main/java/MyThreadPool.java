import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MyThreadPool implements ThreadPool{

	private final Queue<Runnable> waitFreeQueue = new ConcurrentLinkedQueue<Runnable>();
	private int numThread;

	private volatile boolean isRun = true;
	private int numComplete = 0;
	private int numFail = 0;


	public MyThreadPool(int numThread) {
		this.numThread = numThread;
	}


	public void stopRun() {
		isRun= false;
	}

	private final class Runner implements Runnable {

		public void run() {
			while (isRun) {
				Runnable task = waitFreeQueue.poll();
				if (task != null) {
					try {
						task.run();
						incNumComplete();
					} catch (Exception e) {
						e.printStackTrace();
						incNumFail();
					}
				}
			}
		}
	}


	private synchronized void incNumComplete() {
		++numComplete;
	}


	int getCompletedTasks() {
		return numComplete;
	}


	private synchronized void incNumFail() {
		++numFail;
	}


	int getFailedTasks() {
		return numFail;
	}

	int getQueueSize() {
		return waitFreeQueue.size();
	}


	public void start() {
		for (int i = 0; i < numThread; i++) {
			new Thread(new Runner()).start();
		}
	}


	public void execute(Runnable command) {
		if (isRun) {
			waitFreeQueue.offer(command);
		}
	}



}
