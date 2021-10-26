package me.serliunx.chatfilters.utils;

import me.serliunx.chatfilters.ChatFilters;
import me.serliunx.chatfilters.files.FiltersFile;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class FiltersManager {
    private final ChatFilters plugin;
    private final FiltersFile file;
    private FileConfiguration config;
    public List<FilterGroup> filterGroups = new ArrayList<>();

    public FiltersManager(ChatFilters plugin, FiltersFile file){
        this.plugin = plugin;
        this.file = file;
        loadFromFile();
    }

    public void loadFromFile(){
       this.config = file.getConfiguration();
       try{
           for(String gs:config.getKeys(false)){
               filterGroups.add(new FilterGroup(
                       gs,config.getString(gs+".skip-permission"),
                       config.getBoolean(gs+".enable"),config.getString(gs+".replace-by"),
                       config.getStringList(gs+".values")
               ));
           }
       }catch(Exception ex){
           plugin.getLogger().info("过滤器载入失败!");
       }
    }

    public FilterGroup getFilterGroup(String name){
        for(FilterGroup g:filterGroups){
            if(g.getGroupName().equalsIgnoreCase(name))
                return g;
        }
        return null;

    }
}
