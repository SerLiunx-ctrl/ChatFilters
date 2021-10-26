package me.serliunx.chatfilters.files;

import me.serliunx.chatfilters.ChatFilters;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class LanguageFile implements IFiles{
    private final ChatFilters plugin;
    private FileConfiguration langConfig = null;
    private File langConfigFile = null;

    public LanguageFile(ChatFilters plugin){
        this.plugin = plugin;
    }
    @Override
    public void reloadConfig(boolean onReload) {

    }

    @Override
    public void saveDefaultConfig() {

    }

    @Override
    public FileConfiguration getConfiguration() {
        return null;
    }
}
