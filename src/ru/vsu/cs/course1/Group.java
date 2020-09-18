package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.List;

public class Group {
    int name;
//    int course;
    List<Student> students;

    public Group (int name){
        this.name = name;
//        this.course = course;
    }

    public void  addStudent(Student student) {
        students.add(student);
    }
}
