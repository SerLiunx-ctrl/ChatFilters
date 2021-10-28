package me.serliunx.chatfilters.utils;

import me.serliunx.chatfilters.ChatFilters;
import me.serliunx.chatfilters.files.LanguageFile;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Language {
    private final ChatFilters plugin;
    private final LanguageFile file;
    private FileConfiguration config;
    private HashMap<String,String> lang = new HashMap<>();
    private HashMap<String,List<String>> langList = new HashMap<>();
    public static String errorLang = "language error!";

    public Language(ChatFilters plugin,LanguageFile file){
        this.plugin = plugin;
        this.file = file;
        loadFromFile();
    }

    public void loadFromFile(){
        file.reloadConfig(true);
        this.config = file.getConfiguration();
        lang.clear();
        langList.clear();
        for (String s: config.getKeys(false)){
            if(config.isList(s)){
//                plugin.getLogger().info("List: " + s);
                langList.put(s,config.getStringList(s));
            }
            else{
//                plugin.getLogger().info("NotList: " + s);
                lang.put(s, config.getString(s));
            }
        }
    }

    public String getTranslate(String rawText){
        return lang.getOrDefault(rawText, errorLang);
    }

    public List<String> getTranslateList(String rawText){
        return langList.getOrDefault(rawText,new ArrayList<>
                (Arrays.asList(errorLang,errorLang)));
    }

}
