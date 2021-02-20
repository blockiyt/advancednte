package de.blocki.advancednte.main;

import de.blocki.advancednte.luckperms.listener.PlayerNodeChangeListener;
import de.blocki.advancednte.luckperms.listener.Listeners;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    //Sets the Plugin Variable Public
    public static Plugin plugin;
    //Sets the Plugin Variable Public
    public static LuckPerms lpApi;
    //sets the prefix
    public static String prefix;

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("              _                               _ _   _ _______ ______ ");
        System.out.println("     /\\      | |                             | | \\ | |__   __|  ____|");
        System.out.println("    /  \\   __| |_   ____ _ _ __   ___ ___  __| |  \\| |  | |  | |__   ");
        System.out.println("   / /\\ \\ / _` \\ \\ / / _` | '_ \\ / __/ _ \\/ _` | . ` |  | |  |  __|  ");
        System.out.println("  / ____ \\ (_| |\\ V / (_| | | | | (_|  __/ (_| | |\\  |  | |  | |____ ");
        System.out.println(" /_/    \\_\\__,_| \\_/ \\__,_|_| |_|\\___\\___|\\__,_|_| \\_|  |_|  |______|");
        System.out.println("");
        System.out.println("AdvancedNTE by blocki");
        System.out.println("");

        PluginManager pm = this.getServer().getPluginManager();
        plugin = this;

        if(getServer().getPluginManager().getPlugin("LuckPerms").isEnabled()){
            System.out.println("[AdvancedNTE] Das Plugin LuckPerms wurde gefunden");
            LuckPerms api = LuckPermsProvider.get();
            lpApi = api;
            pm.registerEvents(new Listeners(), this);
            new PlayerNodeChangeListener(this, api).register();
            //new GroupPrefixChangedListener(this, api).register();
            setDefaultConfigLuckperms();
        }

        setDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setDefaultConfigLuckperms() {
        if(ConfigManager.get("MessageGroupAdd") == null){ ConfigManager.set("MessageGroupAdd", "You were added to the %GROUPNAME% group!"); }
        if(ConfigManager.get("MessagePrefixAdd") == null){ ConfigManager.set("MessagePrefixUpdate", "You were given the %PREFIX% prefix!"); }
        if(ConfigManager.get("MessageSuffixAdd") == null){ ConfigManager.set("MessagePrefixUpdate", "You were given the %SUFFIX% suffix!"); }
        if(ConfigManager.get("MessageGroupRemove") == null){ ConfigManager.set("MessageGroupRemove", "You are no longer in the %GROUPNAME% group!"); }
        if(ConfigManager.get("MessagePrefixRemove") == null){ ConfigManager.set("MessagePrefixRemove", "You no longer have the %PREFIX% prefix!"); }
        if(ConfigManager.get("MessageSuffixRemove") == null){ ConfigManager.set("MessageSuffixRemove", "You no longer have the %SUFFIX% suffix!\""); }

    }

    private void setDefaultConfig(){
        if(ConfigManager.get("ChatLayout") == null){ ConfigManager.set("ChatLayout", "%PREFIX%%PLAYERNAME%%SUFFIX% §7:§r %MESSAGE%"); }
        if(ConfigManager.get("DisplayNameLayout") == null){ ConfigManager.set("DisplayNameLayout", "%PREFIX%%PLAYERNAME%%SUFFIX%");}
        if(ConfigManager.get("MessagePrefix") == null){ ConfigManager.set("MessagePrefix", "§7[§6LP-NTE§7]"); }
        prefix = ConfigManager.get("MessagePrefix");
    }
}
