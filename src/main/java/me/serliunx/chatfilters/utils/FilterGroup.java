package me.serliunx.chatfilters.utils;

import java.util.List;

public class FilterGroup{
    private String groupName;
    private String skipPermission;
    private String replace;
    private boolean enable;
    private List<String> values;

    public FilterGroup(String groupName,String skipPermission,boolean enable,String replace,List<String> values){
        this.groupName = groupName;
        this.skipPermission = skipPermission;
        this.values = values;
        this.replace = replace;
        this.enable = enable;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public String getSkipPermission(){
        return this.skipPermission;
    }

    public String getReplace(){
        return this.replace;
    }

    public boolean getEnable(){
        return this.enable;
    }

    public List<String> getValues(){
        return this.values;
    }

    public void setSkipPermission(String s){
        if(s.equals("null"))
            this.skipPermission = null;
        else
            this.skipPermission = s;
    }

    public void setReplace(String s){
        this.replace = s;
    }

    public void setEnable(boolean b){
        this.enable = b;
    }

    public boolean addValue(String s){
        if(!values.contains(s)){
            values.add(s);
            return true;
        }
        return false;
    }

    public boolean deleteValue(String s){
        if(values.contains(s)){
            values.remove(s);
            return true;
        }
        return false;
    }
}
