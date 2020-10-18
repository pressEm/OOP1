package ru.vsu.cs.course1;

import ru.vsu.cs.course1.Data.Discipline;
import ru.vsu.cs.course1.Data.Group;
import ru.vsu.cs.course1.Data.Lecturer;

public class Pair {
    private Group group;
    private Lecturer lecturer;
    private Discipline discipline;
    private int studClass;
    private int dayNum;
    private int pairNum;

    public Pair(Group group, Lecturer lecturer, Discipline discipline, int studClass, int dayNum, int pairNum){
        this.dayNum = dayNum;
        this.pairNum = pairNum;
        this.group = group;
        this.lecturer = lecturer;
        this.lecturer.addStudyHour(dayNum, pairNum);
        this.discipline = discipline;
        this.studClass = studClass;
    }

    public int getStudClass() {
        return studClass;
    }

    public int getDayNum() {
        return dayNum;
    }

    public int getPairNum() {
        return pairNum;
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
