package de.blocki.advancednte.main.utils;

import de.blocki.advancednte.luckperms.controller.LPController;
import de.blocki.advancednte.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class TagManager2 {

    public static void setTag(Player player, String prefix, String suffix) {
        if(Main.isLuckPerms) {
            LPController lpc = new LPController();
            //get board
            Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

            //get weight
        /*LuckPermsProvider.get().getGroupManager().loadAllGroups();
        Optional<Group> lpWeight = LuckPermsProvider.get().getGroupManager().getLoadedGroups().stream().filter(g -> g.getName().equals(
                LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrimaryGroup()
        )).findFirst();
        //int weightInt = (lpWeight.filter(group -> group.getWeight().isPresent()).map(group -> group.getWeight().getAsInt()).orElse(0));
        int weightInt = (99-(lpWeight.isPresent() ? (lpWeight.get().getWeight().isPresent() ? lpWeight.get().getWeight().getAsInt() : 0) : 0));
        */
            int weightInt = lpc.getWeightFromPrimaryGroup(player);

            //get team
            String teamName = "000" + weightInt + player.getUniqueId().toString().split("-")[0] + ThreadLocalRandom.current().nextInt(10);
            Team team = board.getTeam(teamName);
            if (team == null) team = board.registerNewTeam(teamName);

            //prefix
            team.setPrefix(prefix);
            //suffix
            team.setSuffix(suffix);
            if (Main.is16) {
                //color
                ChatColor color = null;
                String lastColors = ChatColor.getLastColors(prefix).replace("§", "");
                for (String colorCodeString : lastColors.split("")) {
                    color = ChatColor.getByChar(colorCodeString);
                    if (color != null && color.isColor()) break;
                }
                if (color == null || !color.isColor())
                    color = ChatColor.GRAY; //wenn keien valide farbe bei last colors gefunden wurde, haben wir grey als farbe
                team.setColor(color);
            } else {
                return;
            }
            //set player
            team.addPlayer(player);
            player.setScoreboard(board);
        }

        if(Main.isVault){
            //get board
            Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

            //000+ whight + uuid
            String primary_group = Main.getPermissions().getPrimaryGroup(player);
            String gp = Main.getChat().getGroupPrefix(player.getWorld(), primary_group);
            String gs = Main.getChat().getGroupSuffix(player.getWorld(), primary_group);

            String teamName = "";

            Team team = board.getTeam(teamName);
            if (team == null) team = board.registerNewTeam(teamName);

            //prefix
            team.setPrefix(prefix);
            //suffix
            team.setSuffix(suffix);

            //set player
            team.addPlayer(player);
            player.setScoreboard(board);
        }
    }

}

