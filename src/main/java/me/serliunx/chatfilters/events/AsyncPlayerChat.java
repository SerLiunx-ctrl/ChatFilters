package me.serliunx.chatfilters.events;

import me.serliunx.chatfilters.ChatFilters;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {
    private final ChatFilters plugin;

    public AsyncPlayerChat(ChatFilters plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent evt){
        if(evt.getPlayer().isOp())
            return;
        evt.setMessage(this.plugin.getFilters().replaceString(evt.getMessage(), evt.getPlayer()));
    }
}
