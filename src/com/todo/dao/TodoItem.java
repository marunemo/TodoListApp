package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.current_date=date_format.format(new Date());
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = date_format.format(current_date);
    }
    
    public void setCurrent_date(String current_date) {
    	this.current_date = current_date;
    }
    
    public String toSaveString() {
    	return title + "##" + desc + "##" + current_date + '\n';
    }
}
