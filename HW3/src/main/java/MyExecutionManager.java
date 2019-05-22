public class MyExecutionManager implements ExecutionManager {

	private final MyThreadPool threadPool;

	MyExecutionManager(int numThreads) {
		this.threadPool = new MyThreadPool(numThreads);
		threadPool.start();
	}

	public Context execute(Runnable callback, Runnable... tasks) {

		for (Runnable task : tasks) {
			threadPool.execute(task);
		}

		while (threadPool.getCompletedTasks() + threadPool.getFailedTasks() < tasks.length) {
			Thread.yield();
		}
		threadPool.execute(callback);


		return new Context() {

			public int getCompletedTaskCount() {
				return threadPool.getCompletedTasks();
			}

			public int getFailedTaskCount() {
				return threadPool.getFailedTasks();
			}

			public int getInterruptedTaskCount() {
				return threadPool.getQueueSize();
			}

			public void interrupt() {
				threadPool.stopRun();
			}

			public boolean isFinished() {
				return threadPool.getQueueSize() == 0;
			}
		};



	}
}