package me.serliunx.chatfilters.utils;

import me.serliunx.chatfilters.ChatFilters;
import me.serliunx.chatfilters.files.LanguageFile;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;

public class Language {
    private final ChatFilters plugin;
    private final LanguageFile file;
    private FileConfiguration config;
    public HashMap<String,String> lang = new HashMap<>();
    public HashMap<String,List<String>> langList = new HashMap<>();

    public Language(ChatFilters plugin,LanguageFile file){
        this.plugin = plugin;
        this.file = file;
        loadFromFile();
    }

    public void loadFromFile(){
        file.reloadConfig(true);
        this.config = file.getConfiguration();
        lang.clear();

        for (String s: config.getKeys(false)){
            if(config.isList(s))
                langList.put(s,config.getStringList(s));
            else
                lang.put(s, config.getString(s));
        }
    }

    public String getTranslate(String rawText){
        return lang.getOrDefault(rawText, "language error!");
    }

    public List<String> getTranslateList(String rawText){
        return langList.get(rawText);
    }

}
