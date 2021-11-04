package me.serliunx.chatfilters.utils;

import me.serliunx.chatfilters.ChatFilters;
import me.serliunx.chatfilters.files.FiltersFile;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
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

    public String replaceString(String raw, Player player){
        for(FilterGroup f: filterGroups){
            if(!f.getEnable()) break;
            for(String s:f.getValues()){
                if(raw.contains(s)){
                    if(f.getSkipPermission() == null)
                        break;
                    if(player.hasPermission(f.getSkipPermission()))
                        break;
                    raw = raw.replace(s,f.getReplace());
                }
            }
        }
        return raw;
    }

    public boolean newGroup(String groupName){
        boolean ex = false;
        for(FilterGroup f: getFilterGroups()){
            if(groupName.equals(f.getGroupName())){
                ex = true;
                break;
            }
        }
        if(ex)
            return false;
        List<String> env = new ArrayList<>();
        env.add("none");
        filterGroups.add(new FilterGroup(groupName,"per.here",
                false,"*",env));

        this.config.createSection(groupName);
        this.config.createSection(groupName+".skip-permission");
        this.config.createSection(groupName+".enable");
        this.config.createSection(groupName+".replace-by");
        this.config.createSection(groupName+".values");

        this.config.set(groupName+".skip-permission","per.here");
        this.config.set(groupName+".enable",false);
        this.config.set(groupName+".replace-by","*");
        this.config.set(groupName+".values",env);

        return true;
    }

    public boolean addFilter(String groupName,String filter){
        boolean get = false;
        List<String> env;
        for(FilterGroup f: filterGroups){
            if(!f.getGroupName().equals(groupName))
                break;
            else{
                if(!f.addValue(filter)){
                    return false;
                }
                get = true;
                env = this.config.getStringList(groupName+".values");
                env.add(filter);

               this.config.set(groupName+".values",env);
            }
        }
        return get;
    }

    public boolean deleteFilter(String groupName,String filter){
        boolean get = false;
        List<String> env;
        for(FilterGroup f:filterGroups){
            if(!f.getGroupName().equals(groupName))
                break;
            else{
                if(!f.deleteValue(filter)){
                    return false;
                }
                get = true;
                env = this.config.getStringList(groupName+".values");
                env.remove(filter);

                this.config.set(groupName+".values",env);
            }
        }
        return get;
    }

    public boolean setPermission(String groupName,String per){
        boolean get = false;
        for(FilterGroup f:filterGroups){
            if(!f.getGroupName().equals(groupName))
                break;
            else{
                f.setSkipPermission(per);
                get = true;
                this.config.set(groupName+".skip-permission",per);
            }
        }
        return get;
    }

    public List<Boolean> switchEnable(String groupName){
        List<Boolean> re = new ArrayList<>();
        boolean status = false;
        boolean get = false;
        for(FilterGroup f:filterGroups){
            if(!f.getGroupName().equals(groupName))
                break;
            else{
                f.setEnable(!f.getEnable());
                get = true;
                status = f.getEnable();
                this.config.set(groupName+".enable",f.getEnable());
            }
        }
        re.add(status);
        re.add(get);
        return re;
    }

    public boolean setReplacement(String groupName,String replacement){
        boolean get = false;
        for(FilterGroup f:filterGroups){
            if(!f.getGroupName().equals(groupName))
                break;
            else{
                f.setReplace(replacement);
                get = true;
                this.config.set(groupName+".replace-by",replacement);
            }
        }
        return get;
    }

    public boolean saveFile(){
        try{
            file.saveConfigFile();
        }catch(IOException ex){
            return false;
        }
        return true;
    }
}
