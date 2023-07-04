package com.example.demo.model;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseForShow {

    private String name;

    private String weekday;

    private Integer start;
    
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
    
    public void setWeekdayByInt(int w) {
    	switch(w) {
    		case 0:
    			weekday = "Monday";
    			break;
    		case 1:
    			weekday = "Tuesday";
    			break;
    		case 2:
    			weekday = "Wednesday";
    			break;
    		case 3:
    			weekday = "Thursday";
    			break;
    		case 4:
    			weekday = "Friday";
    			break;
    		case 5:
    			weekday = "Saturday";
    			break;
    		case 6:
    			weekday = "Sunday";
    			break;
    	}
    }
}