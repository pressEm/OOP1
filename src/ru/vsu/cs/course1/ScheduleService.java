package ru.vsu.cs.course1;

import java.util.List;

public class ScheduleService extends Schedule {
    private List<Group> groups;
    private List<Discipline> disciplines;
    private List<Lecturer> lecturers;
    private Schedule schedule = new Schedule();



    public ScheduleService(){
//        this.groups = schedule.getGroups();
//        this.disciplines = schedule.getDisciplines();
//        this.lecturers = schedule.getLecturers();
    }

    public void createSchedule(){
        schedule.connectionData();
        createWeekFor1group();
    }

    public void createWeekFor1group(){
        StudyWeek week = new StudyWeek(groups.get(0));

    }
}
