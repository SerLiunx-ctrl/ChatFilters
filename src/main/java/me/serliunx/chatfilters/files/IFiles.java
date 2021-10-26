package me.serliunx.chatfilters.files;

import org.bukkit.configuration.file.FileConfiguration;

public interface IFiles {
    void reloadConfig(boolean onReload);
    void saveDefaultConfig();
    FileConfiguration getConfiguration();
}
