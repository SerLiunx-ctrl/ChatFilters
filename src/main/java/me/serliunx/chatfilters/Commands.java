package me.serliunx.chatfilters;

import me.serliunx.chatfilters.utils.FilterGroup;
import me.serliunx.chatfilters.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    private final ChatFilters plugin;

    public Commands(ChatFilters plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!cmd.getName().equalsIgnoreCase("cf"))
            return  false;
        if(!sender.hasPermission("chatfilters.admin"))
            return false;
        switch (args[0]) {
            case "reload":
                this.plugin.reload();
                sender.sendMessage("successful reloaded!");
            case "list":
                listAllGroups(sender);
            default:

        }
        return false;
    }

    private void showHelp(){

    }


    private void listAllGroups(CommandSender sender){
        for(FilterGroup f:plugin.getFilters().getFilterGroups()){
            Message.sendMessage(sender,f.getGroupName());
        }
    }
}
