package me.serliunx.chatfilters.utils;

import me.serliunx.chatfilters.ChatFilters;
import me.serliunx.chatfilters.files.FiltersFile;

import java.util.List;

public class FiltersManager {
    private ChatFilters plugin;
    private FiltersFile file;
    public Filter filter;
    public List<FilterGroup> filterGroups;

    public FiltersManager(ChatFilters plugin, FiltersFile file){
        this.plugin = plugin;
        this.file = file;
    }

    public void loadFromFile(){

    }

    public void getFilter(){

    }

    public void addFilter(){

    }

    public void deleteFilter(){

    }
}
