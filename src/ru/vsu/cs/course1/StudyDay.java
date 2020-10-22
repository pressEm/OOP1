package ru.vsu.cs.course1;

import ru.vsu.cs.course1.Data.Group;
import ru.vsu.cs.course1.Enum.DayWeek;

import java.util.*;

public class StudyDay {
    private Map<Integer, Pair> pairs = new HashMap<>();
    private DayWeek dayWeek;
    private Group group;

    public StudyDay(Group group, int day) {
        this.group = group;
        switch (day) {
            case 0:
                this.dayWeek = DayWeek.MONDAY;
                break;
            case 1:
                this.dayWeek = DayWeek.TUESDAY;
                break;
            case 2:
                this.dayWeek = DayWeek.WEDNESDAY;
                break;
            case 3:
                this.dayWeek = DayWeek.THURSDAY;
                break;
            case 4:
                this.dayWeek = DayWeek.FRIDAY;
                break;
            case 5:
                this.dayWeek = DayWeek.SATURDAY;
                break;
        }
    }

    public StudyDay() {
    }

    public void addPair(Pair pair) {
        this.pairs.put(pairs.size(), pair);
    }

    public Group getGroup() {
        return group;
    }

    public DayWeek getDayWeek() {
        return dayWeek;
    }

    public Map<Integer, Pair> getMapPairs() {
        return pairs;
    }

    public List<Pair> getPairsList() {
        List<Pair> pairList = new ArrayList<>();
        pairList.addAll(pairs.values());
        return pairList;
    }
}
