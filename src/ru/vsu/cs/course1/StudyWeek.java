package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.List;

public class StudyWeek {
    private List<StudyDay> days = new ArrayList<>();
   private Group group;

    public StudyWeek(Group group){
        this.group = group;
        for (int i = 0; i < DayWeek.values().length; i++) {
            days.add(new StudyDay(group, DayWeek.values()[i]));
        }
    }

    public Group getGroup() {
        return group;
    }

    public List<StudyDay> getDays() {
        return days;
    }

    public void addDay (StudyDay day){
        this.days.add(day);
    }
}
