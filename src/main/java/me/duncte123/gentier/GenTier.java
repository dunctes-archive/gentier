package me.duncte123.gentier;

import org.bukkit.plugin.java.JavaPlugin;

public class GenTier extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");

        this.saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}