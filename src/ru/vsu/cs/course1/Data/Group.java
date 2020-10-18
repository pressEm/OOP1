package ru.vsu.cs.course1.Data;

import java.util.List;

public class Group {

    private String name;
    private List<Student> students;

    public Group (String name){
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getName(){
        return this.name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void  addStudents(List<Student> students) {
       this.students = students;
    }
}
