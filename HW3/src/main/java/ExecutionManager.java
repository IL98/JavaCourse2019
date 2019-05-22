public interface ExecutionManager {
	Context execute(Runnable callback, Runnable... tasks);
}

interface Context {
	int getCompletedTaskCount();
	int getFailedTaskCount();
	int getInterruptedTaskCount();
	void interrupt();
	boolean isFinished();
}
