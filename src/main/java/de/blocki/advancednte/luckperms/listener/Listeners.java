package de.blocki.advancednte.luckperms.listener;

import de.blocki.advancednte.main.ConfigManager;
import de.blocki.advancednte.luckperms.controller.LPController;
import de.blocki.advancednte.main.utils.TagManager2;
import org.bukkit.ChatColor;
import org.bukkit.command.defaults.ReloadCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class Listeners implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        LPController lpc = new LPController();
        Player p = e.getPlayer();
        String name = p.getName();

        String prefix = lpc.getLpPrefix(p);
        String suffix = lpc.getLpSuffix(p);

        String nametaglayout = ConfigManager.get("DisplayNameLayout");
        p.setDisplayName(nametaglayout.replace("%PREFIX%", prefix).replace("%PLAYERNAME%", name).replace("%SUFFIX%", suffix));
        TagManager2.setTag(p, prefix, suffix);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        LPController lpc = new LPController();
        Player p = e.getPlayer();
        String name = p.getName();

        String prefix = lpc.getLpPrefix(p);
        String suffix = lpc.getLpSuffix(p);

        //get message
        String message = ChatColor.translateAlternateColorCodes('&', e.getMessage());

        String chatlayout = ChatColor.translateAlternateColorCodes('&', ConfigManager.get("ChatLayout"));
        e.setFormat(chatlayout.replace("%PREFIX%", prefix).replace("%PLAYERNAME%", name).replace("%MESSAGE%", message).replace("%SUFFIX%", suffix));
    }

}
