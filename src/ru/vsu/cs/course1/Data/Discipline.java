package ru.vsu.cs.course1.Data;

import ru.vsu.cs.course1.Enum.CourseType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Discipline {
    private CourseType courseType;
    private Map<String, Integer> groupHoursMap = new HashMap<>(); //group -- hours

    private List<Integer> classes;

    public Discipline(CourseType courseType) {
        this.courseType = courseType;
    }

    public void setClasses(List<Integer> classes) {
        this.classes = classes;
    }

    public void setHours(String nameGroup, Integer hours) {
        this.groupHoursMap.put(nameGroup, hours);
    }

    public List<Integer> getClasses() {
        return classes;
    }

    public int getHoursForGroup(Group group) {
        return groupHoursMap.get(group.getName());
    }

    public int getHours(String nameGroup){
        if (this.getGroupHoursMap().get(nameGroup)!=null){
            return this.groupHoursMap.get(nameGroup);
        }
        else return -1;
    }

    public String getCourseType() {
        return courseType.name();
    }

    public Map<String, Integer> getGroupHoursMap() {
        return groupHoursMap;
    }

    public Integer getHoursOnCourse(int nameCourse) {
        return groupHoursMap.get(nameCourse);
    }
}
