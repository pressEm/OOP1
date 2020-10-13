package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyClass {
    private int nameClass;
    private Map<Integer,Pair> hourPairMap;

    private Map<Integer, List<Integer>> classHour;
    StudyClass(){
        this.classHour = new HashMap<>();
//        for (Integer )
    }
//    private List<Pair> pairs = new ArrayList<>();

    public void fullTimeClass (int stClass, int stHour){
        if (classHour.get(stClass).isEmpty()){
            classHour.put(stClass, new ArrayList<>());
        }
//        List<Integer> hours = classHour.get(stClass);
//        hours.add(stHour);
        this.classHour.get(stClass).add(stHour);
    }

    public boolean isClassEmpty (int currHour){
        for (List<Integer> hours: classHour.values()){
            for (Integer hour : hours){
                if (currHour == hour){
                    return false;
                }
            }
        }
        return true;
    }
//
//    public StudyClass(int nameClass){
//        this.nameClass = nameClass;
//    }
//
//
//    public void addPair(Pair pair){
//        this.hourPairMap.put(pair.getDayNum()*10+pair.getPairNum(), pair);
//    }
}
