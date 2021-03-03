package de.blocki.advancednte.essentials.listener;

import de.blocki.advancednte.main.Main;
import net.ess3.api.IUser;
import net.ess3.api.events.VanishStatusChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerVanish implements Listener {

    @EventHandler
    public void onVanishStatusChange(VanishStatusChangeEvent e){
        IUser user = e.getAffected();
        Player p = Bukkit.getPlayer(user.getName());
        for(Player player : Bukkit.getOnlinePlayers()){
            if(!player.isOp()){
                player.hidePlayer(Main.plugin, p);
            }else {
                player.showPlayer(Main.plugin, p);
            }
        }
    }

}
