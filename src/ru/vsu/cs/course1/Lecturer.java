package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lecturer {
    private String name;
    private Set<CourseType> disciplines = new HashSet<>();
    private List<Integer> studyHours = new ArrayList<>();

    public Lecturer (String name){
        this.name = name;
    }

    public void setDiscipline(CourseType discipline){
        disciplines.add(discipline);
    }

    public void addStudyHour(Integer hour){
        this.studyHours.add(hour);
    }

    public List<Integer> getStudyHours() {
        return studyHours;
    }

//    public boolean isFree (int hour){
//        for (Integer studyHour : studyHours){
//            if (studyHour == hour){
//                return false;
//            }
//        }
//        return true;
//    }

    public Set<CourseType> getDisciplines(){
        return this.disciplines;
    }

    public String getName() {
        return name;
    }

    public boolean ifExistDisc(Discipline currDisc){
        String currDiscName = currDisc.getCourseType();
        for (CourseType courseType : disciplines){
            if (currDiscName.equals(courseType.name())){
                return true;
            }
        }
        return false;
    }
}
