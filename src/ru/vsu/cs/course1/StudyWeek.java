package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.List;

public class StudyWeek {
    List<StudyDay> week = new ArrayList<>();

    public void addDay (StudyDay day){
        this.week.add(day);
    }
}
