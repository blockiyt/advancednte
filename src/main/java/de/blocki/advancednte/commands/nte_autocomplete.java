package de.blocki.advancednte.commands;

import net.luckperms.api.model.group.Group;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class nte_autocomplete implements TabCompleter {

    List<String> mainCMD = new ArrayList<String>();
   public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(mainCMD.isEmpty()){
            mainCMD.add("reload");
        }

        return null;
    }

}
