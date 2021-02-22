package de.blocki.advancednte.luckperms.listener;

import de.blocki.advancednte.main.ConfigManager;
import de.blocki.advancednte.main.Main;
import de.blocki.advancednte.main.utils.TagManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.node.types.SuffixNode;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerNodeChangeListener {
    private final Main plugin;
    private final LuckPerms luckPerms;

    public PlayerNodeChangeListener(Main plugin, LuckPerms luckPerms) {
        this.plugin = plugin;
        this.luckPerms = luckPerms;
    }

    public void register() {
        EventBus eventBus = this.luckPerms.getEventBus();
        eventBus.subscribe(this.plugin, NodeAddEvent.class, this::onNodeAdd);
        eventBus.subscribe(this.plugin, NodeRemoveEvent.class, this::onNodeRemove);
    }

    private void onNodeAdd(NodeAddEvent e) {
        if (!e.isUser()) {
            return;
        }

        User target = (User) e.getTarget();
        Node node = e.getNode();

        // LuckPerms events are posted async, we want to process on the server thread!
        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> {
            Player player = this.plugin.getServer().getPlayer(target.getUniqueId());
            if (player == null) {
                return; // Player not online.
            }

            //get prefix
            String prefix = "";
            if(target.getCachedData().getMetaData().getPrefixes().size() > 0){
                prefix = ChatColor.translateAlternateColorCodes('&', target.getCachedData().getMetaData().getPrefix());
            }
            //get suffix
            String suffix = "";
            if(target.getCachedData().getMetaData().getSuffixes().size() > 0){
                suffix = ChatColor.translateAlternateColorCodes('&', target.getCachedData().getMetaData().getSuffix());
            }

            if (node instanceof InheritanceNode) {
                String groupName = ((InheritanceNode) node).getGroupName();
                player.sendMessage(Main.prefix + ConfigManager.get("MessageGroupAdd").replace("%GROUPNAME%", groupName));
                player.setDisplayName(ConfigManager.get("DisplayNameLayout").replace("%PREFIX%", prefix).replace("%PLAYERNAME%", target.getUsername()).replace("%SUFFIX%", suffix));
                TagManager.setTag(player, prefix, suffix);

            } else if (node instanceof PrefixNode) {
                String newPrefix = ((PrefixNode) node).getMetaValue();
                player.sendMessage(Main.prefix + ConfigManager.get("MessagePrefixAdd").replace("%PREFIX%", newPrefix));
                player.setDisplayName(ConfigManager.get("DisplayNameLayout").replace("%PREFIX%", newPrefix).replace("%PLAYERNAME%", target.getUsername()).replace("%SUFFIX%", suffix));
                TagManager.setTag(player, newPrefix, suffix);

            } else if (node instanceof SuffixNode) {
                String newSuffix = ((SuffixNode) node).getMetaValue();
                player.sendMessage(Main.prefix + ConfigManager.get("MessageSuffixAdd").replace("%SUFFIX%", newSuffix));
                player.setDisplayName(ConfigManager.get("DisplayNameLayout").replace("%PREFIX%", prefix).replace("%PLAYERNAME%", target.getUsername()).replace("%SUFFIX%", newSuffix));
                TagManager.setTag(player, prefix, newSuffix);
            }

        });
    }

    private void onNodeRemove(NodeRemoveEvent e) {
        if (!e.isUser()) {
            return;
        }

        User target = (User) e.getTarget();
        Node node = e.getNode();

        // LuckPerms events are posted async, we want to process on the server thread!
        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> {
            Player player = this.plugin.getServer().getPlayer(target.getUniqueId());
            if (player == null) {
                return; // Player not online.
            }

            //get prefix
            String prefix = "";
            if(target.getCachedData().getMetaData().getPrefixes().size() > 0){
                prefix = ChatColor.translateAlternateColorCodes('&', target.getCachedData().getMetaData().getPrefix());
            }
            //get suffix
            String suffix = "";
            if(target.getCachedData().getMetaData().getSuffixes().size() > 0){
                suffix = ChatColor.translateAlternateColorCodes('&', target.getCachedData().getMetaData().getSuffix());
            }

            if (node instanceof InheritanceNode) {
                String groupName = ((InheritanceNode) node).getGroupName();
                player.sendMessage(Main.prefix + ConfigManager.get("MessagePrefixRemove").replace("%GROUPNAME%", groupName));
                player.setDisplayName(ConfigManager.get("DisplayNameLayout").replace("%PREFIX%", prefix).replace("%PLAYERNAME%", target.getUsername()).replace("%SUFFIX%", suffix));
                TagManager.setTag(player, prefix, suffix);

            } else if (node instanceof PrefixNode) {
                String newPrefix = ((PrefixNode) node).getMetaValue();
                player.sendMessage(Main.prefix + ConfigManager.get("MessagePrefixRemove").replace("%PREFIX%", newPrefix));
                player.setDisplayName(ConfigManager.get("DisplayNameLayout").replace("%PREFIX%", newPrefix).replace("%PLAYERNAME%", target.getUsername()).replace("%SUFFIX%", suffix));
                TagManager.setTag(player, newPrefix, suffix);

            } else if (node instanceof SuffixNode) {
                String newSuffix = ((SuffixNode) node).getMetaValue();
                player.sendMessage(Main.prefix + ConfigManager.get("MessageSuffixRemove").replace("%SUFFIX%", newSuffix));
                player.setDisplayName(ConfigManager.get("DisplayNameLayout").replace("%PREFIX%", prefix).replace("%PLAYERNAME%", target.getUsername()).replace("%SUFFIX%", newSuffix));
                TagManager.setTag(player, prefix, newSuffix);
            }
        });
    }

}
