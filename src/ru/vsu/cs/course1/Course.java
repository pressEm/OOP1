package ru.vsu.cs.course1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Course {
    private int name;//номер курса
    private Map<Integer, Group> groups = new HashMap<>();//ключ - номер, значение - объект группы
    private Map<String, Discipline> disciplines = new HashMap<>();

    public Course(int name) {
        this.name = name;
    }

    public int getName(){
        return this.name;
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
    public Set<String> getAllDisciplines(){
        return this.disciplines.keySet();
    }
    public Discipline getDiscipline (String name){
        return disciplines.get(name);
    }
}
