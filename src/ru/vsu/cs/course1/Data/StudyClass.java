package ru.vsu.cs.course1.Data;

import ru.vsu.cs.course1.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyClass {
    private int nameClass;
    private List<Integer> classHours = new ArrayList<>();
    private Map<String,Pair> pairs = new HashMap<>();

    public StudyClass(Integer nameClass) {
        this.nameClass = nameClass;
    }

    public void addStudyHour(int day, int pair) {
        if (!classHours.contains(10 * day + pair)) {
            classHours.add(10 * day + pair);
        }
    }

    public void addPair(Pair pair, int day, int hour){
        String dayHour = Integer.toString(day).concat(".").concat(Integer.toString(hour));
        pairs.put(dayHour,pair);
    }

    public Pair getPair(int day, int hour) {
        String key = Integer.toString(day).concat(".").concat(Integer.toString(hour));
        if (pairs.containsKey(key)){
            return pairs.get(key);
        }
        return null;
    }

    public List<Integer> getClassHours() {
        return classHours;
    }

    public boolean ifStudyClassEmpty(int day, int pair) {
        if (classHours.contains(10 * day + pair)) {
            return false;
        }
        return true;
    }

    public int getNameClass() {
        return nameClass;
    }

}
