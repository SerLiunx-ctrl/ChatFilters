package me.serliunx.chatfilters;

import me.serliunx.chatfilters.events.AsyncPlayerChat;
import me.serliunx.chatfilters.files.FiltersFile;
import me.serliunx.chatfilters.utils.FiltersManager;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ChatFilters extends JavaPlugin {
    public FiltersFile filtersFile;
    public FiltersManager filtersManager;

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("cf")).setExecutor(new Commands(this));
        this.getServer().getPluginManager().registerEvents(new AsyncPlayerChat(this),this);
        this.saveDefaultConfig();
        this.builder();

        for (String s : filtersManager.getFilterGroup("another-group").getValues()){
            this.getLogger().info(s);
        }
    }

    public void reload(){
        this.reloadConfig();
    }

    public FiltersManager getFilters(){
        return this.filtersManager;
    }

    private void builder(){
        filtersFile = new FiltersFile(this);
        filtersManager = new FiltersManager(this,filtersFile);
    }
}
