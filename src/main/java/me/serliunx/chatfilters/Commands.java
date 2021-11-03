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
        if(args.length < 1){
            showHelp(sender);
            return false;
        }
        switch (args[0]) {
            case "reload":
                this.plugin.reload();
                Message.sendMessage(sender,plugin.getLang().getTranslate("reloaded"));
                break;
            case "list":
                listAllGroups(sender);
                break;
            case "add":
                addFilter(args,sender);
                break;
            case "newgroup":
                newGroup(args,sender);
                break;
            case "save":
                saveToFile(sender);
                break;
            default:
                showHelp(sender);
        }
        return false;
    }

    private void showHelp(CommandSender sender){
        for(String s:plugin.getLang().getTranslateList("help_information"))
            Message.sendMessage(sender,s);
    }


    //
    private void listAllGroups(CommandSender sender){
        Message.sendMessage(sender,"&b&l============================");
        String color;
        for(FilterGroup f:plugin.getFilters().getFilterGroups()){
            if(f.getEnable())
                color = "&a&l+";
            else
                color = "&c&l-";
            Message.sendMessage(sender,color+"  &7[ &e"+f.getGroupName()+"&b | &a"+f.getSkipPermission()+" &7]");
        }
        Message.sendMessage(sender,"&b&l============================");
    }

    private void newGroup(String[] args,CommandSender sender){
        if(!(args.length == 2)){
            showHelp(sender);
            return;
        }
       if(!plugin.getFilters().newGroup(args[1]))
           Message.sendMessage(sender,plugin.getLang().getTranslate("filterGroup_already_in"));
    }

    private void saveToFile(CommandSender sender){
        if(!plugin.filterEdited){
            Message.sendMessage(sender,plugin.getLang().getTranslate("filters_not_edited"));
            return;
        }

        if(plugin.getFilters().saveFile()){
            Message.sendMessage(sender,plugin.getLang().getTranslate("filtersFile_save_successfully"));
            plugin.filterEdited = false;
        }

    }

    private void addFilter(String[] args,CommandSender sender){
        String groupName;
        String filter;

        if(!(args.length == 3)){
            showHelp(sender);
            return;
        }

        groupName = args[1];
        filter = args[2];

        if(!plugin.getFilters().addFilter(groupName,filter)){
            Message.sendMessage(sender,plugin.getLang().getTranslate("filters_add_failure"));
        }else{
            Message.sendMessage(sender,plugin.getLang().getTranslate("filters_add_successfully"));
            plugin.filterEdited = true;
        }
    }
}
