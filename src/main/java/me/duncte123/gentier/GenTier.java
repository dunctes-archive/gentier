package me.duncte123.gentier;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenTier extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        getLogger().info("Loading config");

        FileConfiguration config = this.getConfig();
        String defaultTier = config.getString("defaultTier");
        String message = config.getString("message");

        List<Map<?, ?>> permsList = config.getMapList("permissions");

        /*
         * Map(perm_name, tier)
         */
        Map<String, String> tierMap = new HashMap<>();

        for (Map<?, ?> permMap : permsList) {
            if (!permMap.containsKey("perm")) {
                getLogger().warning("Found tier without perm in list, skipping");
                continue;
            }

            if (!permMap.containsKey("tier")) {
                getLogger().warning("Found perm without tier in list, skipping");
                continue;
            }

            String perm = String.valueOf(permMap.get("perm"));
            String tier = String.valueOf(permMap.get("tier"));

            tierMap.put(perm, tier);
        }

        getLogger().info("Loaded all tiers");

        this.getCommand("gentier").setExecutor(new GenTierCommand(tierMap, defaultTier, message));
    }
}
