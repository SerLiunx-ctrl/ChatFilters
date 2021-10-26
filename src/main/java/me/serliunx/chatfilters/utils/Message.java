package me.serliunx.chatfilters.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Message {

    public static void sendMessage(Player player,String str){

    }

    public static void sendMessage(CommandSender sender,String str){
        sender.sendMessage(str);
    }
}
