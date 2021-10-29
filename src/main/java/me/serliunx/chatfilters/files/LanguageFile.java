package me.serliunx.chatfilters.files;

import me.serliunx.chatfilters.ChatFilters;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguageFile implements IFiles{
    private final ChatFilters plugin;
    private FileConfiguration langConfig = null;
    private File langConfigFile = null;

    public LanguageFile(ChatFilters plugin){
        this.plugin = plugin;
        saveDefaultConfig();
    }

    /*
    首次载入或重载配置文件
     */
    @Override
    public void reloadConfig(boolean onReload) {
        if(langConfigFile == null || onReload)
            this.langConfigFile = new File(this.plugin.getDataFolder(),"lang.yml");
        this.langConfig = YamlConfiguration.loadConfiguration(langConfigFile);
    }

    @Override
    public void saveDefaultConfig() {
        this.langConfigFile = new File(this.plugin.getDataFolder(),"lang.yml");
        if(!this.langConfigFile.exists())
            this.plugin.saveResource("lang.yml",false);
    }

    @Override
    public FileConfiguration getConfiguration() {
        if(langConfig == null)
            reloadConfig(false);
        return this.langConfig;
    }
}
