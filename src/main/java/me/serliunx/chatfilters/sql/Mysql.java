package me.serliunx.chatfilters.sql;

import me.serliunx.chatfilters.ChatFilters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql extends SQL{
    private String userName;
    private String passWord;
    private String url;
    private final ChatFilters plugin;
    private static Connection connc;


    public Mysql(ChatFilters plugin){
        this.plugin = plugin;
        loadSQLconfig();
    }

    public void loadSQLconfig(){
        this.url = plugin.getConfig().getString("mysql_url");
        this.userName = plugin.getConfig().getString("mysql_userName");
        this.passWord = plugin.getConfig().getString("mysql_passWord");
    }

    public void conSql() throws SQLException{
        try{
            connc = DriverManager.getConnection(url,userName,passWord);
        }catch(SQLException e){
            plugin.getLogger().info("mysql connect failed.");
        }
    }

    public void disconnectSQL() throws Exception{
        try{
            if(connc != null && !connc.isClosed()){
                connc.close();
            }
        }catch(Exception e){
            plugin.getLogger().info("mysql disconnect failed.");
            throw e;
        }
    }

}
