package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleService {
    private List<Group> groups;
    private List<Discipline> disciplines;
    private List<Lecturer> lecturers;

    public ScheduleService(Schedule schedule){
        this.groups = schedule.getGroups();
        this.disciplines = schedule.getDisciplines();
        this.lecturers = schedule.getLecturers();
    }

    public void createSchedule() {
        List<Discipline> allDisc = new ArrayList<>();
        for (Discipline discipline : disciplines) {
            for (Integer k : discipline.getGroupHoursMap().values()) {
                for (int i = 0; i < k; i++) {
                    allDisc.add(discipline);
                }
            }
        }
        List<StudyWeek> schedule = new ArrayList<>();
//        StudyWeek[] scheduleArr = new StudyWeek[groups.size()];
        Map<Integer,List<Integer>> classesBusy = new HashMap<>(); // class -- hours

        int i = 0;
        for (Group group:groups){
            StudyWeek studyWeek = createStudyWeekFor1Group(group, classesBusy);
            schedule.add(studyWeek);
            for (Student student:group.getStudents()){
                student.setStudyWeek(studyWeek);
            }
//            scheduleArr[i] = studyWeek;
        }
        i = 0;
      printSchedule(schedule);

    }
//
//    private boolean isClassFree(int studClass){
//
//    }

    private boolean isLectFree(Lecturer lecturer, int hour){
        for (Integer studyHour : lecturer.getStudyHours()){
            if (studyHour == hour){
                return false;
            }
        }
        return true;
    }

    private StudyWeek createStudyWeekFor1Group(Group group, Map<Integer,List<Integer>> lectBusy){
        List<Discipline> thisDisc = discFor1group(disciplines, group);
        StudyWeek studyWeek = new StudyWeek(group);
//         6 дней в нед
        for (int i = 0; i < 6; i++) {
            StudyDay studyDay = new StudyDay(group, DayWeek.values()[i]);
            boolean isFindLecture;
            for (int j = 0; ((j < 6) && (thisDisc.size()>0)); j++){
//                 6 пар в день
                isFindLecture = false;
                for (Lecturer lecturer : lecturers) {
                    if ((!isFindLecture) && (isLectFree(lecturer,(i*10+j))) && (lecturer.ifExistDisc(thisDisc.get(0)))&& (thisDisc.size()>0)) {
                        studyDay.addPair(new Pair(group, lecturer, thisDisc.get(0), i, j));
                        thisDisc.remove(0);
                        isFindLecture = true;
                    }
                }
            }
            studyWeek.addDay(studyDay);
        }
        return studyWeek;
    }

    public List<Discipline> discFor1group(List<Discipline> allDisc, Group group) {
        List<Discipline> discInWeekFor1Group = new ArrayList<>();
        for (Discipline discipline : allDisc) {
            for (int i = 0; i < discipline.getHoursForGroup(group); i++) {
                discInWeekFor1Group.add(discipline);
            }
        }
        return discInWeekFor1Group;
    }

    private void printSchedule(List<StudyWeek> schedule){
        int i = 0;
        for (StudyWeek studyWeek : schedule){
            i++;
            printWeekFor1Gr(studyWeek, i);
        }
        System.out.println();
    }

    public void printDiscFor1Gr(List<Discipline> listDisc1Gr){
        System.out.println("Disciplines for group on week  " + listDisc1Gr.size());
        for (int i = 0; i < listDisc1Gr.size(); i++) {
            System.out.println(i + "                      " + listDisc1Gr.get(i).getCourseType());
        }
        System.out.println("___________________________________________________");
    }

    public void printWeekFor1Gr(StudyWeek studyWeek, int i){
        System.out.println("Pairs for " + i + " group in week");
        for (DayWeek dayWeek : DayWeek.values()){
            System.out.println(studyWeek.getDay(dayWeek).getDayWeek());
            for (Pair pair : studyWeek.getDay(dayWeek).getPairsList()){
                System.out.print(pair.getLecturer().getName()+", ");
                System.out.print(pair.getPairNum()+", ");
                System.out.print(pair.getDiscipline().getCourseType()+", ");
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("___________________________________________________");
    }

}
