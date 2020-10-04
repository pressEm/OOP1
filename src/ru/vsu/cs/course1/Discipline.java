package ru.vsu.cs.course1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Discipline {
    private CourseType courseType;
    private Map<String, Integer> map = new HashMap<>(); //group -- hours
    private List<Integer> classes;

    public Discipline(CourseType courseType) {
        this.courseType = courseType;
    }

    public void setClasses(List<Integer> classes){
        this.classes = classes;

    }

    public void setHours(String nameGroup, Integer hours){
        this.map.put(nameGroup, hours);
    }

    public String getCourseType(){
        return courseType.name();
    }
    public Map<String, Integer> getMap() {
        return map;
    }

    public List<Integer> getClasses() {
        return classes;
    }


    public Integer getHours(int nameCourse) {
        return map.get(nameCourse);
    }
}
