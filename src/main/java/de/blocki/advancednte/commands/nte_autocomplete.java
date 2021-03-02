package de.blocki.advancednte.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class nte_autocomplete implements TabCompleter {

    List<String> mainCMD = new ArrayList<String>();
   public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(mainCMD.isEmpty()){
            mainCMD.add("reload");
        }

       List<String> result = new ArrayList<String>();
       if(args.length == 1){
           for(String a : mainCMD){
               if(a.toLowerCase().startsWith(args[0].toLowerCase())){
                   result.add(a);
               }
           }
           return result;
       }
        return null;
    }

}
