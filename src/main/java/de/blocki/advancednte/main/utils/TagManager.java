package de.blocki.advancednte.main.utils;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class TagManager {

    public static void setTag(Player player, String prefix, String suffix) {
        //get board
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

        //get weight
        LuckPermsProvider.get().getGroupManager().loadAllGroups();
        Optional<Group> lpWeight = LuckPermsProvider.get().getGroupManager().getLoadedGroups().stream().filter(g -> g.getName().equals(
                LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrimaryGroup()
        )).findFirst();
        int weightInt = (lpWeight.filter(group -> group.getWeight().isPresent()).map(group -> group.getWeight().getAsInt()).orElse(0));

        //get team
        String teamName = "000" + weightInt + player.getUniqueId().toString().split("-")[0] + ThreadLocalRandom.current().nextInt(10);
        Team team = board.getTeam(teamName);
        if(team == null) team = board.registerNewTeam(teamName);

        //prefix
        team.setPrefix(prefix);
        //suffix
        team.setSuffix(suffix);
        //color
        ChatColor color = null;
        System.out.println("prefix"+prefix);
        String lastColors = ChatColor.getLastColors(prefix).replace("§", "");
        System.out.println("lastColors="+lastColors);
        for (String colorCodeString : lastColors.split("")) {
            color = ChatColor.getByChar(colorCodeString);
            if(color != null && color.isColor()) break;
        }
        if(color == null || !color.isColor()) color = ChatColor.AQUA; //wenn keien valide farbe bei last colors gefunden wurde, haben wir Aqua als farbe
        team.setColor(color); //tests mal!!!

        //set player
        team.addPlayer(player);
        player.setScoreboard(board);
    }

}
