package ru.vsu.cs.course1.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyClass {
    private int nameClass;
    private List<Integer> classHours = new ArrayList<>();

    public StudyClass(Integer nameClass){
        this.nameClass = nameClass;
    }

    public void addStudyHour(int day, int pair){
        if (!classHours.contains(10 * day + pair)) {
            classHours.add(10 * day + pair);
        }
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
