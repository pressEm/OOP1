package ru.vsu.cs.course1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Discipline {
    private CourseType courseType;
    private Map<String, Integer> groupHoursMap = new HashMap<>(); //group -- hours
//    private Map<Integer,List<Integer>> classesNotEmpty = new HashMap<>(); // hour --
    private List<Integer> classes;

    public Discipline(CourseType courseType) {
        this.courseType = courseType;
    }

    public void setClasses(List<Integer> classes) {
        this.classes = classes;

    }



    public int getHoursForGroup(Group group) {
        return groupHoursMap.get(group.getName());
    }

    public void setHours(String nameGroup, Integer hours) {
        this.groupHoursMap.put(nameGroup, hours);
    }

    public String getCourseType() {
        return courseType.name();
    }

    public Map<String, Integer> getGroupHoursMap() {
        return groupHoursMap;
    }

    public List<Integer> getClasses() {
        return classes;
    }


    public Integer getHours(int nameCourse) {
        return groupHoursMap.get(nameCourse);
    }
}
