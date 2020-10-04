package ru.vsu.cs.course1;

import java.util.HashSet;
import java.util.Set;

public class Lecturer {
    String name;
    Set<CourseType> disciplines = new HashSet<>();

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
}
