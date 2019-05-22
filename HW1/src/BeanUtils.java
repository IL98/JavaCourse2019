import java.lang.reflect.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


class A {
	int a = 5;

	public int getA() {
		return a;
	}

}

class B {
	int  a = 10;

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}
}


public class BeanUtils {

	public static void assign(Object to, Object from) throws Exception{

		HashSet<String> getterSet = addGettersToSet(from);

		Method[] setters = to.getClass().getMethods();
		for (Method setter : setters) {
			if (isSetter(setter)) {
				String getterName = "get" + setter.getName().substring(3);
				if (getterSet.contains(getterName)) {

					Method getter = from.getClass().getMethod(getterName, null);

					if (Compatible(getter, setter)) {
						setter.invoke(to, getter.invoke(from, null));
					}
				}
			}
		}

	}


	private static boolean Compatible(Method getter, Method setter) {
		Class parType = setter.getParameterTypes()[0];
		return (getter.getReturnType().equals(parType) ||
								getter.getReturnType().getSuperclass().equals(parType));
	}

	private static HashSet<String> addGettersToSet(Object from) {

		HashSet<String> hashSet = new HashSet<>();

		Method[] methods = from.getClass().getMethods();
		for (Method method : methods) {
			if (isGetter(method)) {
				hashSet.add(method.getName());
			}
		}

		return hashSet;
	}

	public static boolean isGetter(Method method){
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}

	public static boolean isSetter(Method method){
		if (!method.getName().startsWith("set"))
			return false;
		if (method.getParameterTypes().length != 1)
			return false;
		return true;
	}



	public static void main(String[] args) throws Exception{
		A a = new A();
		B b = new B();
		assign(b, a);
		System.out.println(b.getA());
	}
}
