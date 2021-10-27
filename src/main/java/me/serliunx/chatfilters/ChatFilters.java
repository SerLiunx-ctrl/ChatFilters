package me.serliunx.chatfilters;

import me.serliunx.chatfilters.events.AsyncPlayerChat;
import me.serliunx.chatfilters.files.FiltersFile;
import me.serliunx.chatfilters.files.LanguageFile;
import me.serliunx.chatfilters.utils.FiltersManager;

import me.serliunx.chatfilters.utils.Language;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ChatFilters extends JavaPlugin {
    public FiltersFile filtersFile;
    public FiltersManager filtersManager;
    public LanguageFile langFile;
    public Language lang;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.register();
        this.builder();
    }

    public void reload(){
        this.reloadConfig();
        filtersManager.loadFromFile();

    }

    public FiltersManager getFilters(){
        return this.filtersManager;
    }

    public Language getLang(){
        return this.lang;
    }

    private void builder(){
        filtersFile = new FiltersFile(this);
        filtersManager = new FiltersManager(this,filtersFile);
        langFile = new LanguageFile(this);
        lang = new Language(this,langFile);
    }

    private void register(){
        this.getServer().getPluginManager().registerEvents(new AsyncPlayerChat(this),this);
        Objects.requireNonNull(this.getCommand("cf")).setExecutor(new Commands(this));
    }
}
