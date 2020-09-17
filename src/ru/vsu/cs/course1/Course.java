package ru.vsu.cs.course1;

import java.util.Collection;
import java.util.List;

public class Course {
    int name;//
    List<Group> groups;
    List<Discipline> disciplines;
    public Course(int name){
        this.name = name;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addDis(Discipline discipline) {
        disciplines.add(discipline);
    }
}
