import java.util.concurrent.Callable;


public class Task<T> {

	private final Callable<? extends T> callable;
	private volatile RuntimeException MyException;
	private volatile T result;

	public Task(Callable<? extends T> callable) {
		this.callable = callable;
	}


	private T check() {
		if (MyException != null) {
			throw MyException;
		}
		if (result != null) {
			return result;
		}

		return null;
	}

	public T get() {
		T probableResult = check();

		if (probableResult == null) {
			doAct();
		}

		return result;
	}

	private synchronized void doAct() {
		T probableResult = check();

		if (probableResult == null) {
			try {
				result = callable.call();
			} catch (Exception e) {
				MyException = new RuntimeException("Exception in callable.call()");
				throw MyException;
			}
		}
	}

}