package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.List;

public class ScheduleService extends Schedule {
    private List<Group> groups;
    private List<Discipline> disciplines;
    private List<Lecturer> lecturers;
    private Schedule schedule = new Schedule();



    public ScheduleService(){
        this.groups = schedule.getGroups();
        this.disciplines = schedule.getDisciplines();
        this.lecturers = schedule.getLecturers();
    }

//    public void createSchedule(){
//
////        createWeekFor1group();
//        createSchedule();
//    }

    public void createSchedule() {
        List<Discipline> allDisc = new ArrayList<>();
        for (Discipline discipline : disciplines) {
            for (Integer k : discipline.getMap().values()) {
                for (int i = 0; i < k; i++) {
                    allDisc.add(discipline);
                }
            }
        }
        List<StudyWeek> schedule = new ArrayList<>();
        int i = 0;
        for (Group group:groups){
            i++;
            StudyWeek studyWeek = createStudyWeekFor1Group(group);
            schedule.add(createStudyWeekFor1Group(group));
            printWeekFor1Gr(studyWeek, i);
        }
        List<Discipline> listDisc1Gr = discFor1group(disciplines, groups.get(0));
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
                System.out.print(pair.getDiscipline().getCourseType()+", ");
            }
            System.out.println();
        }
        System.out.println("___________________________________________________");
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
    //условие окончания дисциплин для изучения
    private StudyWeek createStudyWeekFor1Group(Group group){
//        List<Discipline> disciplinesInWeek1 = discFor1group(disciplines, group);
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
                    if ((!isFindLecture) && (lecturer.ifExistDisc(thisDisc.get(0)))&& (thisDisc.size()>0)) {
                        studyDay.addPair(new Pair(group, lecturer, thisDisc.get(0)));
                        thisDisc.remove(0);
                        isFindLecture = true;
                    }
                }
            }
            studyWeek.addDay(studyDay);
        }
        return studyWeek;
    }

//    private StudyDay createStudyDayFor1Group(List<Discipline> discInWeekFor1Group, Group group, DayWeek dayWeek) {
////        StudyWeek studyWeek;
////        int countPairsInDay = (int) (discInWeekFor1Group.size()/6) + 1;
////        DayWeek[] daysInWeek = DayWeek.values();
//        List<Discipline> thisDisc = new ArrayList<>();
//        thisDisc.addAll(discInWeekFor1Group);
//        StudyDay studyDay = new StudyDay(group, dayWeek);
//
//        boolean isFindLecture;
//        for (int i = 0; i < 6; i++){
//            isFindLecture = false;
//            for (Lecturer lecturer : lecturers) {
//                if ((!isFindLecture) && (lecturer.ifExistDisc(thisDisc.get(0)))) {
//                    Pair pair = new Pair(group, lecturer, thisDisc.get(0));
//                    studyDay.addPair(pair);
//                    thisDisc.remove(0);
//                    isFindLecture = true;
//                }
//            }
//        }
////                ВЫВОД ДИСЦИПЛИН ЗА 1 ДЕНЬ
////        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
////        for (Pair pair : studyDay.getPairs()) {
////            System.out.println(pair.getDiscipline().getCourseType());
////        }
////        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        return studyDay;
//    }

    public void createWeekFor1group(){
        StudyWeek week = new StudyWeek(groups.get(0));

    }
}
