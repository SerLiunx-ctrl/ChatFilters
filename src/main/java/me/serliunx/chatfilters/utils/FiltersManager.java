package me.serliunx.chatfilters.utils;

import me.serliunx.chatfilters.ChatFilters;
import me.serliunx.chatfilters.files.FiltersFile;

public class FiltersManager {
    private ChatFilters plugin;
    private FiltersFile file;

    public FiltersManager(ChatFilters plugin, FiltersFile file){
        this.plugin = plugin;
        this.file = file;
    }

    public void loadFromFile(){

    }
}
