package me.serliunx.chatfilters;

import me.serliunx.chatfilters.events.AsyncPlayerChat;
import me.serliunx.chatfilters.files.FiltersFile;
import me.serliunx.chatfilters.files.LanguageFile;
import me.serliunx.chatfilters.sql.Mysql;
import me.serliunx.chatfilters.utils.FiltersManager;
import me.serliunx.chatfilters.utils.Language;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Objects;

public final class ChatFilters extends JavaPlugin {
    public FiltersFile filtersFile;
    public FiltersManager filtersManager;
    public LanguageFile langFile;
    public Language lang;
    public Mysql filterSQL;
    public boolean useMysql = false;
    public boolean filterEdited = false;
//    public boolean isOffline = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.register();
        this.builder();

//        getLogger().info(langFile.getConfiguration().getKeys(false).toString());
        if(useMysql){
            try{
                filterSQL.conSql();
            }catch(SQLException e){
                this.getLogger().info(e.toString());
                useMysql = false;
            }
        }
    }

    @Override
    public void onDisable(){
        if (useMysql){
            try{
                filterSQL.disconnectSQL();
            }catch(Exception e){
                this.getLogger().info(e.toString());
            }
        }
    }

    public void reload(){
        this.reloadConfig();
        this.filtersManager.reload();
        this.langFile.reloadConfig(true);
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
        if(useMysql)
            filterSQL = new Mysql(this);
    }

    private void register(){
        this.getServer().getPluginManager().registerEvents(new AsyncPlayerChat(this),this);
        Objects.requireNonNull(this.getCommand("cf")).setExecutor(new Commands(this));

    }
}
