package me.serliunx.chatfilters.placeholders;

import me.serliunx.chatfilters.ChatFilters;

import java.util.HashMap;
import java.util.Objects;

public final class ChatFiltersPlaceholder {
    private ChatFilters plugin;
    private HashMap<String,String> holders = new HashMap<>();

    public ChatFiltersPlaceholder(ChatFilters plugin){
        this.plugin = plugin;
        initHolders();
    }

    public void updateHolders(String key, Objects value){

    }

    private void initHolders(){
        //目前插件版本
        holders.put("{version}",this.plugin.getVersion());
        //过滤器组数量
        holders.put("{filterGroups}","");

    }


}
