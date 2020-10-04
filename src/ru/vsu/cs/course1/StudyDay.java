package ru.vsu.cs.course1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudyDay {
    private Map<Integer,Pair> pairs = new HashMap<>();
    private DayWeek dayWeek;
    private Group group;
    public StudyDay (Group group, DayWeek dayWeek){
        this.group = group;
        this.dayWeek = dayWeek;
    }

    public Map<Integer, Pair> getPairs() {
        return pairs;
    }
    public Collection<Pair> getPair(){
        return pairs.values();
    }
}
