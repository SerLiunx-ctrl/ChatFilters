package me.serliunx.chatfilters.files;

import me.serliunx.chatfilters.ChatFilters;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FiltersFile implements IFiles{
    private final ChatFilters plugin;
    private FileConfiguration filtersConfig = null;
    private File filtersFile = null;

    public FiltersFile(ChatFilters plugin){
        this.plugin = plugin;
        saveDefaultConfig();
    }

    @Override
    public void reloadConfig(boolean onReload){
        if(filtersFile == null | onReload)
            this.filtersFile = new File(this.plugin.getDataFolder(),"filters.yml");
        this.filtersConfig = YamlConfiguration.loadConfiguration(this.filtersFile);
    }

    @Override
    public void saveDefaultConfig() {
        this.filtersFile = new File(this.plugin.getDataFolder(),"filters.yml");
        if(!this.filtersFile.exists())
            this.plugin.saveResource("filters.yml",false);
    }

    @Override
    public FileConfiguration getConfiguration() {
        if(filtersConfig == null)
            reloadConfig(false);
        return this.filtersConfig;
    }
}
