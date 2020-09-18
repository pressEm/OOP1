package ru.vsu.cs.course1;

import java.util.Map;

public class Course {
    private int name;//номер курса
    private Map<Integer, Group> groups;//ключ - номер, значение - объект группы
    private Map<String, Discipline> disciplines;

    public Course(int name) {
        this.name = name;
    }

    public void addGroup(Group group) {
        groups.put(group.name, group);
    }

    public void addDis(Discipline discipline) {
        disciplines.put(discipline.getName(), discipline);
    }

    public Group getGroup (int name){
        return groups.get(name);
    }
    public Discipline getDiscipline (String name){
        return disciplines.get(name);
    }
}
