package ru.vsu.cs.course1;

public class Student {
    private String name;
    private int course;
    //    private int groupName;
    private Group group;
//    public int points;

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

//    @Override
//    public int compareTo(Object o) {
//        Student otherStudent = (Student) o;
//        int thisValue = this.points;
//        int otherValue = otherStudent.points;
//        return thisValue - otherValue;
//    }


//    public void getGroup(){
//        if
//    }
}
