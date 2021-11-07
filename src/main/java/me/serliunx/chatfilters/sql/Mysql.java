package me.serliunx.chatfilters.sql;

import me.serliunx.chatfilters.ChatFilters;

public class Mysql extends SQL{
    private static String userName;
    private static String passWord;
    private static String url;
    private ChatFilters plugin;


    public Mysql(ChatFilters plugin){
        this.plugin = plugin;
    }


}
