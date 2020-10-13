package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Discipline {
    private CourseType courseType;
    private Map<String, Integer> groupHoursMap = new HashMap<>(); //group -- hours
//    private List<Integer> classHours = new ArrayList<>(); //class -- hours
    private Map<Integer, List<Integer>> classHourMap = new HashMap<>(); //class -- hour

    //    private Map<Integer,List<Integer>> classesNotEmpty = new HashMap<>(); // hour --
    private List<Integer> classes;

    public Discipline(CourseType courseType) {
        this.courseType = courseType;
    }

    public void setClasses(List<Integer> classes) {
        this.classes = classes;

    }

    public void setStudClass(int studClass, int studHour){
        for (Integer stClass:classHourMap.keySet()){

            if ( classHourMap.get(stClass) == null) {
                List hours = new ArrayList();
                classHourMap.put(studClass, hours);
            }
//            hours.add(studHour);
            classHourMap.get(stClass).add(studClass);
//            classHourMap.put(studClass, hours);
        }
    }

    public  Map<Integer, List<Integer>> getClassHours() {
        return classHourMap;
    }

    public int getHoursForGroup(Group group) {
        return groupHoursMap.get(group.getName());
    }

    public void setHours(String nameGroup, Integer hours) {
        this.groupHoursMap.put(nameGroup, hours);
    }

    public int getHours(String nameGroup){
        if (this.getGroupHoursMap().get(nameGroup)!=null){
            return this.groupHoursMap.get(nameGroup);
        }
        else return -1;
    }

    public String getCourseType() {
        return courseType.name();
    }

    public Map<String, Integer> getGroupHoursMap() {
        return groupHoursMap;
    }

    public List<Integer> getClasses() {
        return classes;
    }


    public Integer getHoursOnCourse(int nameCourse) {
        return groupHoursMap.get(nameCourse);
    }
}
