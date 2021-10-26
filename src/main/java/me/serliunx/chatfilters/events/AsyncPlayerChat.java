package me.serliunx.chatfilters.events;

import me.serliunx.chatfilters.ChatFilters;
import org.bukkit.event.Listener;

public class AsyncPlayerChat implements Listener {
    private ChatFilters plugin;

    public AsyncPlayerChat(ChatFilters plugin){
        this.plugin = plugin;
    }
}
