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
        reload();
    }

    private void loadFromFile(){
        file.reloadConfig(true);
       this.config = file.getConfiguration();
       try{
           filterGroups.clear();
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

    private void loadFromMysql(){
        if(!plugin.useMysql)
            return;
        plugin.getLogger().info("loaded from mysql.");
    }

    public void reload(){
        loadFromFile();
        loadFromMysql();
    }

    public FilterGroup getFilterGroup(String name){
        for(FilterGroup g:filterGroups){
            if(g.getGroupName().equalsIgnoreCase(name))
                return g;
        }
        return null;
    }

    public List<FilterGroup> getFilterGroups(){
        return this.filterGroups;
    }

    public String replaceString(String raw){
        for(FilterGroup f: filterGroups){
//            plugin.getLogger().info(f.getGroupName());
            if(!f.getEnable()) break;
            for(String s:f.getValues()){
                if(raw.contains(s)){
//                    plugin.getLogger().info("find one!");
                    raw = raw.replace(s,f.getReplace());
                }
            }
        }
        return raw;
    }

    public boolean addFilter(String groupName,String filter){
        boolean get = false;
        for(FilterGroup f: filterGroups){
            if(!f.getGroupName().equals(groupName))
                break;
            else{
                if(!f.addValue(filter)){
                    return false;
                }
                get = true;
            }
        }
        return get;
    }
}
