package me.serliunx.chatfilters.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

    public static void sendMessage(Player player,String str){
        sendMessage((CommandSender) player,str);
    }

    public static void sendMessage(CommandSender sender,String str){
        sender.sendMessage(transColorcode(str));
    }

    private static String transColorcode(String rawText){
        return ChatColor.translateAlternateColorCodes('&',rawText);
    }
}
