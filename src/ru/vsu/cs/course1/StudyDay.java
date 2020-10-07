package ru.vsu.cs.course1;

import java.util.*;

public class StudyDay {
    private Map<Integer,Pair> pairs = new HashMap<>();
    private DayWeek dayWeek;
    private Group group;
    public StudyDay (Group group, DayWeek dayWeek){
        this.group = group;
        this.dayWeek = dayWeek;
    }
    public StudyDay (){
    }

    public void addPair(Pair pair){
        this.pairs.put(pairs.size(), pair);
    }

    public Group getGroup() {
        return group;
    }

    public DayWeek getDayWeek() {
        return dayWeek;
    }
//    public getDay(DayWeek dayWeek){
//        if (dayWeek.name().equals(this.dayWeek)){
//            return
//        }
//    }

    public Map<Integer, Pair> getMapPairs() {
        return pairs;
    }

//    public Collection<Pair> getPairs(){
//        return pairs.values();
//    }

    public List<Pair> getPairsList (){
        List<Pair> pairList = new ArrayList<>();
        pairList.addAll(pairs.values());
        return pairList;
    }
}
