package ru.vsu.cs.course1;

import ru.vsu.cs.course1.Data.Group;

import java.util.ArrayList;
import java.util.List;

public class StudyWeek {
    private List<StudyDay> days = new ArrayList<>();
    private Group group;

    public StudyWeek(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public List<StudyDay> getDays() {
        return days;
    }

    public StudyDay getDay(DayWeek dayWeek) {
        for (StudyDay studyDay : this.days) {
            if (studyDay.getDayWeek().name().equals(dayWeek.name())) {
                return studyDay;
            }
        }
        return new StudyDay();
    }

    public void addDay(StudyDay day) {
        this.days.add(day);
    }
}
