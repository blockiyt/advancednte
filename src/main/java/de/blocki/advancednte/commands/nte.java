package de.blocki.advancednte.commands;

import de.blocki.advancednte.main.Main;
import de.blocki.advancednte.main.utils.TagManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class nte implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        String name = p.getName();
        if(args[0].equalsIgnoreCase("reload")){

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

            for (Player player : Bukkit.getOnlinePlayers()) {
                TagManager.setTag(player, prefix, suffix);
            }

        }
        return false;
    }
}
