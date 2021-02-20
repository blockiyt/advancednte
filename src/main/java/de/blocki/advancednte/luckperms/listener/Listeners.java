package de.blocki.advancednte.luckperms.listener;

import de.blocki.advancednte.main.ConfigManager;
import de.blocki.advancednte.main.utils.TagManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class Listeners implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String name = p.getName();

        LuckPerms api = LuckPermsProvider.get();
        User user = api.getUserManager().getUser(name);

        //get prefix
        String prefix = "";
        if(user.getCachedData().getMetaData().getPrefixes().size() > 0){
            prefix = ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getPrefix());
        }
        //get suffix
        String suffix = "";
        if(user.getCachedData().getMetaData().getSuffixes().size() > 0){
            suffix = ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getSuffix());
        }

        String nametaglayout = ConfigManager.get("DisplayNameLayout");
        p.setDisplayName(nametaglayout.replace("%PREFIX%", prefix).replace("%PLAYERNAME%", name).replace("%SUFFIX%", suffix));
        TagManager.setTag(p, prefix, suffix);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String name = p.getName();

        LuckPerms api = LuckPermsProvider.get();
        User user = api.getUserManager().getUser(name);

        //get prefix
        String prefix = "";
        if(user.getCachedData().getMetaData().getPrefixes().size() > 0){
            prefix = ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getPrefix());
        }
        //get suffix
        String suffix = "";
        if(user.getCachedData().getMetaData().getSuffixes().size() > 0){
            suffix = ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getSuffix());
        }

        //get message
        String message = ChatColor.translateAlternateColorCodes('&', e.getMessage());

        String chatlayout = ChatColor.translateAlternateColorCodes('&', ConfigManager.get("ChatLayout"));
        e.setFormat(chatlayout.replace("%PREFIX%", prefix).replace("%PLAYERNAME%", name).replace("%MESSAGE%", message).replace("%SUFFIX%", suffix));
    }

}
