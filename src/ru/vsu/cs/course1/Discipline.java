package ru.vsu.cs.course1;

import java.util.*;

public class Discipline {

    private String name;
    private Map<Integer, Integer> map; //course -- hours
    private List<Teacher> teachers;
    private List<Integer> classes;

    public Discipline(String  name, Map<Integer,Integer> map, List<Teacher> teachers, List<Integer> classes){
        this.name = name;
        this.map = map;
        this.teachers = teachers;
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public List<Integer> getClasses() {
        return classes;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public Set<Integer> getCourses() {

        return map.keySet();
    }

    public Integer getHours(int nameCourse) {
        return map.get(nameCourse);
    }
    //    public Map<Integer, Integer> createMap (int course, int hours){
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(course, hours);
//    }
}
