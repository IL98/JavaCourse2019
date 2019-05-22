import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainTest {
	public static void main(String[] args)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException, InvocationTargetException {

		EncryptedClassLoader classLoader = new EncryptedClassLoader("asa",
				new File("target/test-classes"),
				ClassLoader.getSystemClassLoader());


		Method method = classLoader.findClass("СompiledClassTest").getMethod("success");

		method.invoke(classLoader.findClass("СompiledClassTest").newInstance(), null);

	}
}
