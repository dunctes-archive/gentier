package me.duncte123.gentier;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GenTierCommand implements CommandExecutor {
    private final Map<String, String> tierMap;
    private final String defaultTier;
    private final String message;
    private final List<String> permsList;

    GenTierCommand(Map<String, String> tierMap, String defaultTier, String message) {
        this.tierMap = tierMap;
        this.defaultTier = defaultTier;
        this.message = message;

        this.permsList = new ArrayList<>(tierMap.keySet());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        for (String perm : this.permsList) {
            if (player.hasPermission(perm)) {
                this.sendFormattedMessage(player, this.tierMap.get(perm));

                return true;
            }
        }

        this.sendFormattedMessage(player, this.defaultTier);

        return true;
    }

    private void sendFormattedMessage(Player player, String tier) {
        final String message = ChatColor.translateAlternateColorCodes('&',
                this.message.replaceAll("\\{tier}", tier)
                        .replaceAll("\\{name}", player.getDisplayName())
        );

        player.sendMessage(message);
    }
}
