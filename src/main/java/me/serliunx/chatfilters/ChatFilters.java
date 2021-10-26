package me.serliunx.chatfilters;

import me.serliunx.chatfilters.events.AsyncPlayerChat;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class ChatFilters extends JavaPlugin {

    @Override
    public void onEnable() {
        //注册指令执行器
        Objects.requireNonNull(this.getCommand("cf")).setExecutor(new Commands(this));
        //注册事件
        this.getServer().getPluginManager().registerEvents(new AsyncPlayerChat(this),this);
    }

    public void reload(){
        this.reloadConfig();
    }
}
