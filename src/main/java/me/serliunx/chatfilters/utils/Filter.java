package me.serliunx.chatfilters.utils;

public class Filter {
    public String groupName;
    public String skipPermission;

    public Filter(String groupName,String skipPermission){
        this.groupName = groupName;
        this.skipPermission = skipPermission;
    }

    public String getSkipPermission(){
        return this.skipPermission;
    }
}
