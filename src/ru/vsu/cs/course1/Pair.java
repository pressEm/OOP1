package ru.vsu.cs.course1;

import java.util.List;

public class Pair {
    private Group group;
    private Lecturer lecturer;
    private Discipline discipline;

    public Pair(Group group, Lecturer lecturer, Discipline discipline){
        this.group = group;
        this.lecturer = lecturer;
        this.discipline = discipline;
    }

    public Discipline getDiscipline (){
        return this.discipline;
    }

    public Group getGroup() {
        return group;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }
}
