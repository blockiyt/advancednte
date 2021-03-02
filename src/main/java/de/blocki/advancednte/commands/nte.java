package de.blocki.advancednte.commands;

import de.blocki.advancednte.main.ConfigManager;
import de.blocki.advancednte.main.Main;
import de.blocki.advancednte.luckperms.controller.LPController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class nte implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length >= 1){
                if(args[0].equalsIgnoreCase("reload")){
                    Player p = (Player) sender;
                    LPController lpc = new LPController();
                    lpc.reloadAllPlayers();
                    p.sendMessage(Main.prefix + ConfigManager.get("MessageReloadConfirm"));
                }
            }
        }
        return false;
    }
}
