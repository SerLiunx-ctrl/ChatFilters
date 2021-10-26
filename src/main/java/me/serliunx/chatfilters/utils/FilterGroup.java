package me.serliunx.chatfilters.utils;

import java.util.List;

public class FilterGroup extends Filter{
    public String replace;
    public boolean enable;
    public List<String> values;

    public FilterGroup(String groupName,String skipPermission,boolean enable,String replace,List<String> values){
        super(groupName,skipPermission);

        this.groupName = groupName;
        this.skipPermission = skipPermission;
        this.values = values;
        this.replace = replace;
        this.enable = enable;
    }
}
