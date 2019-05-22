import ru.edhunter.Plugin;
import ru.edhunter.PluginManager;

public class Main {

	public static void main(String[] args) throws Exception {

		Main.class.getClassLoader().loadClass("ru.edhunter.Plugin");

//		PluginManager pluginManager = new PluginManager(
//				"/home/ilya/IdeaProjects/SberTech/Homeworks/my_plugins/out/production");
//
////	FileReader file = new FileReader("/home/ilya/IdeaProjects/SberTech/Homeworks/my_plugins/out/production/my_plugins/MyPlugin.class");
//
//		Plugin plugin =  pluginManager.load("my_plugins", "MyPlugin");
//		plugin.doUseFull();

	}
}
