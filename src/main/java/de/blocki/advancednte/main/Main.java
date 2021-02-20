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

        System.out.println("  _                _    _____                        _   _ _______ ______        __   ___  ");
        System.out.println(" | |              | |  |  __ \\                      | \\ | |__   __|  ____|      /_ | / _ \\ ");
        System.out.println(" | |    _   _  ___| | _| |__) |__ _ __ _ __ ___  ___|  \\| |  | |  | |__    __   _| || | | |");
        System.out.println(" | |   | | | |/ __| |/ /  ___/ _ \\ '__| '_ ` _ \\/ __| . ` |  | |  |  __|   \\ \\ / / || | | |");
        System.out.println(" | |___| |_| | (__|   <| |  |  __/ |  | | | | | \\__ \\ |\\  |  | |  | |____   \\ V /| || |_| |");
        System.out.println(" |______\\__,_|\\___|_|\\_\\_|   \\___|_|  |_| |_| |_|___/_| \\_|  |_|  |______|   \\_/ |_(_)___/ ");
        System.out.println("");
        System.out.println("LuckPermsNTE by blocki");
        System.out.println("");

        LuckPerms api = LuckPermsProvider.get();
        lpApi = api;
        plugin = this;
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Listeners(), this);
        new PlayerNodeChangeListener(this, api).register();
        //new GroupPrefixChangedListener(this, api).register();
        setDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setDefaultConfig(){
        if(ConfigManager.get("ChatLayout") == null){ ConfigManager.set("ChatLayout", "%PREFIX%%PLAYERNAME%%SUFFIX% §7:§r %MESSAGE%"); }
        if(ConfigManager.get("DisplayNameLayout") == null){ ConfigManager.set("DisplayNameLayout", "%PREFIX%%PLAYERNAME%%SUFFIX%");}
        if(ConfigManager.get("MessagePrefix") == null){ ConfigManager.set("MessagePrefix", "§7[§6LP-NTE§7]"); }
        if(ConfigManager.get("MessageGroupAdd") == null){ ConfigManager.set("MessageGroupAdd", "You were added to the %GROUPNAME% group!"); }
        if(ConfigManager.get("MessagePrefixAdd") == null){ ConfigManager.set("MessagePrefixUpdate", "You were given the %PREFIX% prefix!"); }
        if(ConfigManager.get("MessageSuffixAdd") == null){ ConfigManager.set("MessagePrefixUpdate", "You were given the %SUFFIX% suffix!"); }
        if(ConfigManager.get("MessageGroupRemove") == null){ ConfigManager.set("MessageGroupRemove", "You are no longer in the %GROUPNAME% group!"); }
        if(ConfigManager.get("MessagePrefixRemove") == null){ ConfigManager.set("MessagePrefixRemove", "You no longer have the %PREFIX% prefix!"); }
        if(ConfigManager.get("MessageSuffixRemove") == null){ ConfigManager.set("MessageSuffixRemove", "You no longer have the %SUFFIX% suffix!\""); }
        prefix = ConfigManager.get("MessagePrefix");
    }
}
