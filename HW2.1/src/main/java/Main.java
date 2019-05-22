import ru.edhunter.Plugin;
import ru.edhunter.PluginManager;

public class Main {

	public static void main(String[] args) throws Exception {

//		Main.class.getClassLoader().loadClass("ru.edhunter.Plugin");

		PluginManager pluginManager = new PluginManager(
				"target");

//	FileReader file = new FileReader("/home/ilya/IdeaProjects/SberTech/Homeworks/my_plugins/out/production/my_plugins/MyPlugin.class");

		Plugin plugin =  pluginManager.load("test-classes", "MyPlugin");
		plugin.doUseFull();

	}
}