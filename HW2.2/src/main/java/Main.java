import java.io.File;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {

		EncryptedClassLoader classLoader = new EncryptedClassLoader("asa",
					new File("target/test-classes"),
							ClassLoader.getSystemClassLoader());


		classLoader.loadClass("СompiledClassTest");
	}
}
