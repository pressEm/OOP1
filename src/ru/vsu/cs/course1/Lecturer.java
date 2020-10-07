package ru.vsu.cs.course1;

import java.util.HashSet;
import java.util.Set;

public class Lecturer {
    private String name;
    private Set<CourseType> disciplines = new HashSet<>();

    public Lecturer (String name){
        this.name = name;
    }

    public void setDiscipline(CourseType discipline){
        disciplines.add(discipline);
    }

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
