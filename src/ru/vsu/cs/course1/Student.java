package ru.vsu.cs.course1;

public class Student {
    private String name;
    private int course;
    private Group group;

    public Student(String name, int course) {
        this.name = name;
        this.course = course;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    public Group getGroup() {
        return group;
    }
}
