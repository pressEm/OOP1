package ru.vsu.cs.course1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Discipline {

    List<Integer> classes;
    String name;
    Map<Integer, Integer> map; //course -- hours
    List<Integer> course;
    List<Integer> hours;

    //    List<Integer> hours;
    List<Teacher> teachers;

    public Discipline(String  name){
        this.name = name;
//        this.course = group;
//        this.hours = hours;
//        this.teacher = teacher;
    }
//    public Map<Integer, Integer> createMap (int course, int hours){
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(course, hours);
//    }
}
