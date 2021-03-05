package de.blocki.advancednte.luckperms.controller;

import de.blocki.advancednte.main.Main;
import de.blocki.advancednte.main.utils.TagManager2;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;

public class LPController {

    public String getLpPrefix(Player player){
        LuckPerms api = LuckPermsProvider.get();
        User user = api.getUserManager().getUser(player.getName());
        String prefix = "";
        if(user.getCachedData().getMetaData().getPrefixes().size() > 0){
            prefix = ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getPrefix());
        }else {
            prefix = "§cNoPrefixSet §8| §c";
        }
        return prefix;
    }

    public String getLpSuffix(Player player){
        LuckPerms api = LuckPermsProvider.get();
        User user = api.getUserManager().getUser(player.getName());
        String suffix = "";
        if(user.getCachedData().getMetaData().getSuffixes().size() > 0){
            suffix = ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getSuffix());
        }
        return suffix;
    }

    public int getWeightFromPrimaryGroup(Player player){
        LuckPermsProvider.get().getGroupManager().loadAllGroups();
        Optional<Group> lpWeight = LuckPermsProvider.get().getGroupManager().getLoadedGroups().stream().filter(g -> g.getName().equals(
                LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrimaryGroup()
        )).findFirst();
        int weightInt = (99-(lpWeight.isPresent() ? (lpWeight.get().getWeight().isPresent() ? lpWeight.get().getWeight().getAsInt() : 0) : 0));
        return weightInt;
    }

    public void reloadAllPlayers(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            TagManager2.setTag(player, getLpPrefix(player), getLpSuffix(player));
            System.out.println("Reloaded all Players");
        }
    }

}
