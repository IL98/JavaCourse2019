import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class EncryptedClassLoader extends ClassLoader {
	private String key;

	private File dir;


	public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
		super(parent);
		this.key = key;
		this.dir = dir;
	}

	private void encrypte(byte[] bytes) {
		byte[] byteArray = key.getBytes();

		int sum = 0;

		for (byte b : byteArray) {
			sum += b;
		}

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) ((byte)(bytes[i] + sum) % 256 - 128);
		}
	}

	private void decrypte(byte[] bytes) {
		byte[] byteArray = key.getBytes();

		int sum = 0;

		for (byte b : byteArray) {
			sum += b;
		}

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) ((byte)(bytes[i] - sum) % 256 - 128);
		}
	}

	private byte[] getBytesFromFile(String s) {
		byte[] bytes = {};

		try {
			File file = new File(dir + "/" + s + ".class");

			bytes = new byte[(int) file.length()];
			InputStream is = new FileInputStream(file);
			is.read(bytes);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			is.close();
		}

		return bytes;
	}

	@Override
	protected Class<?> findClass(String s) throws ClassNotFoundException {

		byte[] bytes = getBytesFromFile(s);

		if (bytes.length == 0) {
			throw new ClassNotFoundException();
		}
		//decrypte(bytes);

		try {
			return defineClass(s, bytes, 0, bytes.length);
		} catch (Exception e) {
			return super.findClass(s);
		}
	}
}
