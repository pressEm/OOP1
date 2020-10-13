package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleService {
    private List<Group> groups;
    private List<Discipline> disciplines;
    private List<Lecturer> lecturers;

    public ScheduleService(Schedule schedule) {
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
        Map<Integer, List<Integer>> classesBusy = new HashMap<>(); // class -- hours

        int i = 0;
        for (Group group : groups) {
            StudyWeek studyWeek = createStudyWeekFor1Group(group, classesBusy, schedule);
            schedule.add(studyWeek);
            for (Student student : group.getStudents()) {
                student.setStudyWeek(studyWeek);
            }
//            scheduleArr[i] = studyWeek;
        }
        i = 0;
        printSchedule(schedule);
//        printDiscFor1Gr(discFor1group(disciplines, groups.get(0)));

    }

    private int findClass(Discipline discipline, int day, int hour, List<StudyWeek> schedule) {
        int numPair = 10 * day + hour;
        for (Integer studClass : discipline.getClasses()) {
            if (isClassFree(studClass, day, hour, schedule)) {
                return studClass;
            }
        }
        return -1;
    }

    private boolean isClassFree(int studClass, int day, int hour, List<StudyWeek> schedule) {
        for (StudyWeek week : schedule) {
            for (StudyDay dayWeek : week.getDays()) {
                for (Pair pair : dayWeek.getPairsList()) {
                    if ((pair.getStudClass() == studClass) && (pair.getDayNum() == day) && (pair.getPairNum() == hour)) {
                        return false;
                    }
                }
            }
            return true;
        }
//        for (Discipline discipline : disciplines){
//            for (List<Integer> classHours : discipline.getClassHours().values()){
//                for (Integer hour: classHours){
//                    if (hour == studClass){
//                        return false;
//                    }
//                }
//            }
//        }
        return true;
    }

    private boolean isLectFree(Lecturer lecturer, int hour) {
        for (Integer studyHour : lecturer.getStudyHours()) {
            if (studyHour == hour) {
                return false;
            }
        }
        return true;
    }
//
//    private boolean classIsEmpty(List<Integer> hours, int hourCurr){
//        for (Integer hour: hours){
//            if (hour == hourCurr){
//                return false;
//            }
//        }
//        return true;
//    }

    private StudyWeek createStudyWeekFor1Group(Group group, Map<Integer, List<Integer>> lectBusy, List<StudyWeek> schedule) {
        List<Discipline> thisDisc = discFor1group(disciplines, group);
        StudyWeek studyWeek = new StudyWeek(group);
//        Map<Integer, StudyClass>  studyClasses = new HashMap<>();
//        Map <Integer, List<Integer>> classes = new HashMap<>(); // class -- hours
//         6 дней в нед
        for (int i = 0; i < 6; i++) {
            StudyDay studyDay = new StudyDay(group, DayWeek.values()[i]);
            boolean isFindLecture;
            for (int j = 0; ((j < 6) && (thisDisc.size() > 0)); j++) {
//                 6 пар в день
                isFindLecture = false;
                for (Lecturer lecturer : lecturers) {
                    while ((!isFindLecture) && (lecturer.isTeachDisc(thisDisc.get(0))) &&
                            (isLectFree(lecturer, (i * 10 + j))) && (thisDisc.size() > 0)) {
                        int studClass = findClass(thisDisc.get(0), i, j, schedule);
                        if (studClass != -1) {


//                        thisDisc.get(0).setStudClass(studClass, 10*i+j);

//                        studyClasses.put(studClass, new StudyClass(studClass));
                            studyDay.addPair(new Pair(group, lecturer, thisDisc.get(0), studClass, i, j));
                            thisDisc.remove(0);
                            isFindLecture = true;
                        } else {
                            thisDisc.add(thisDisc.get(0));
                        }


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

    private void printSchedule(List<StudyWeek> schedule) {
        int i = 0;
        for (StudyWeek studyWeek : schedule) {
            i++;
            printWeekFor1Gr(studyWeek, i);
        }
        System.out.println();
    }

    public void printDiscFor1Gr(List<Discipline> listDisc1Gr) {
        System.out.println("Disciplines for group on week  " + listDisc1Gr.size());
        for (int i = 0; i < listDisc1Gr.size(); i++) {
            System.out.println(i + "                      " + listDisc1Gr.get(i).getCourseType() + " в кабинете: " + listDisc1Gr.get(i).getClasses());
            for (List<Integer> hour : listDisc1Gr.get(i).getClassHours().values()) {

            }
        }
        System.out.println("___________________________________________________");
    }

    public void printWeekFor1Gr(StudyWeek studyWeek, int i) {
        System.out.println("Pairs for " + i + " group in week");
        for (DayWeek dayWeek : DayWeek.values()) {
            System.out.println(studyWeek.getDay(dayWeek).getDayWeek());
            for (Pair pair : studyWeek.getDay(dayWeek).getPairsList()) {
                System.out.print(pair.getLecturer().getName() + ", ");
                System.out.print(pair.getPairNum() + ", ");
                System.out.print(pair.getDiscipline().getCourseType() + ", ");
                System.out.print(pair.getStudClass() + ", ");
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("___________________________________________________");
    }

}
