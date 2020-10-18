package ru.vsu.cs.course1.Data;

import ru.vsu.cs.course1.StudyWeek;

public class Student {
    private String name;
    private int course;
    private Group group;
    private StudyWeek studyWeek;

    public Student(String name, int course) {
        this.name = name;
        this.course = course;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setStudyWeek(StudyWeek studyWeek) {
        this.studyWeek = studyWeek;
    }

    public String getName() {
        return name;
    }

    public StudyWeek getStudyWeek(){
        return this.studyWeek;
    }

    public int getCourse() {
        return course;
    }

    public Group getGroup() {
        return group;
    }
}
