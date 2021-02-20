package de.blocki.advancednte.main.utils;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class TagManager {

    public static void setTag(Player player, String prefix, String suffix) {
        //System.out.println("setTag");
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = board.getTeam(player.getName());
        //System.out.println("team = " + team);
        //System.out.println("team.getPrefix() = " + team.getPrefix());

        if(Objects.nonNull(team)) team.removePlayer(player);

        LuckPermsProvider.get().getGroupManager().loadAllGroups();

        Optional<Group> optionalInteger = LuckPermsProvider.get().getGroupManager().getLoadedGroups().stream().filter(g -> g.getName().equals(
                LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrimaryGroup()
        )).findFirst();

        //System.out.println("optionalInteger.isPresent() = " + optionalInteger.isPresent());
        //System.out.println("i = " + optionalInteger.get().getWeight().getAsInt());
        int weight = (optionalInteger.isPresent() ? (optionalInteger.get().getWeight().isPresent() ? optionalInteger.get().getWeight().getAsInt() : 0) : 0);
        String teamName = "000" + weight + player.getUniqueId().toString().split("-")[0] + ThreadLocalRandom.current().nextInt(10);

        team = Objects.isNull(board.getTeam(teamName)) ? board.registerNewTeam(teamName) : board.getTeam(teamName);

        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addPlayer(player);
        player.setScoreboard(board);
    }

}
