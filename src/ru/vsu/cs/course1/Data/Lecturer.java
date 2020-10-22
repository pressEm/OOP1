package ru.vsu.cs.course1.Data;

import ru.vsu.cs.course1.Enum.CourseType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lecturer {
    private String name;
    private Set<CourseType> disciplines = new HashSet<>();
    private List<Integer> busyHours = new ArrayList<>();

    public Lecturer (String name){
        this.name = name;
    }

    public void setDiscipline(CourseType discipline){
        disciplines.add(discipline);
    }

    public void addStudyHour(int day, int pair){
        this.busyHours.add(10*day+pair);
    }

    public Set<CourseType> getDisciplines(){
        return this.disciplines;
    }

    public String getName() {
        return name;
    }

    public boolean isFree(int day, int pair){
        for (Integer hour : busyHours){
            if (hour == 10*day+pair){
                return false;
            }
        }
        return true;
    }

    public boolean isTeachDisc(Discipline currDisc){
        String currDiscName = currDisc.getCourseType();
        for (CourseType courseType : this.disciplines){
            if (currDiscName.equals(courseType.name())){
                return true;
            }
        }
        return false;
    }
}
