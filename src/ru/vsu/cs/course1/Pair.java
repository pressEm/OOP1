package ru.vsu.cs.course1;

import java.util.List;

public class Pair {
    private List<Group> groups;
    private Group group;
    private List<Lecturer> lecturers;
    private Lecturer lecturer;
    private List<Discipline> disciplines;
    private Discipline discipline;

    public Pair(Group group, Lecturer lecturer, Discipline discipline){
        this.group = group;
        this.lecturer = lecturer;
        this.discipline = discipline;
    }
    public Pair(List<Group> groups, List<Lecturer> lecturers, List<Discipline> disciplines){
        this.groups = groups;
        this.lecturers = lecturers;
        this.disciplines = disciplines;
    }

    public Discipline getDiscipline (){
        return this.discipline;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
