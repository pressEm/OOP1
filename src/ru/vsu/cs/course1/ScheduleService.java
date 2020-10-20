package ru.vsu.cs.course1;

//import ru.vsu.cs.course1.Enums.DayWeek;
//import ru.vsu.cs.course1.Objects.*;

import ru.vsu.cs.course1.Data.Discipline;
import ru.vsu.cs.course1.Data.Group;
import ru.vsu.cs.course1.Data.Lecturer;
import ru.vsu.cs.course1.Data.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScheduleService {
    private List<StudyWeek> listOfWeeks;
    private Schedule schedule;

    public ScheduleService(Schedule schedule) {
        this.schedule = schedule;
        listOfWeeks = getListOfWeeks();
    }

    public void printAllSchedule() {
        List<List<String>> strings = new ArrayList<>();
        for (StudyWeek studyWeek : listOfWeeks) {
            List<String> groupSchedule = getWeekFor1Gr(studyWeek);
            List<String> spaces = setLength(groupSchedule, findMaxLength(groupSchedule) + 3);
            strings.add(concatLists(groupSchedule, spaces));
        }
        List<String> string = strings.remove(0);
        for (List<String> l : strings) {
            for (int i = 0; i < l.size(); i++) {
                string.add(i, string.remove(i).concat(l.get(i)));
            }
        }
        for (String s : string) {
            System.out.println(s);
        }
    }
//    public void printAllScheduleMap() {
//        List<List<String>> strings = new ArrayList<>();
////        Map<String, List<String>> stringListMap = new LinkedHashMap<>();
//        for (StudyWeek studyWeek : mapOfWeeks.values()) {
//            List<String> groupSchedule = getWeekFor1Gr(studyWeek);
//            List<String> spaces = setLength(groupSchedule, findMaxLength(groupSchedule) + 3);
//            strings.add(concatLists(groupSchedule, spaces));
////            stringListMap.put(studyWeek.getGroup().getName(), concatLists(groupSchedule, spaces));
//        }
//        List<String> string = strings.remove(0);
//        for (List<String> l : strings) {
//            for (int i = 0; i < l.size(); i++) {
//                string.add(i, string.remove(i).concat(l.get(i)));
//            }
//        }
//
//        for (String s : string) {
//            System.out.println(s);
//        }
//
//    }
//    public void printScheduleForGroupMap(String groupName) {
//        for (Group group : schedule.getGroups()){
//            if (group.getName().equals(groupName)){
//                List<String> groupSchedule = getWeekFor1Gr(mapOfWeeks.get(groupName));
//                for (String s : groupSchedule) {
//                    System.out.println(s);
//                }
//            }
//        }
//    }

    public void printScheduleForStudent(String studentName) {
        boolean find = false;
        for (Student student : schedule.getAllStudents()) {
            if (student.getName().equals(studentName)) {
                find = true;
                System.out.println("Schedule for" + studentName +":");
                for (String s : getWeekFor1Gr(student.getStudyWeek())) {
                    System.out.println(s);
                }
            }
        }
        if (!find){
            System.out.println("This student doesn't exist");
            System.out.println();
        }
    }

    public void printScheduleForGroup(String groupName) {
        List<String> strings = new ArrayList<>();
        boolean find = false;
        for (StudyWeek studyWeek : listOfWeeks) {
            if (studyWeek.getGroup() != null && studyWeek.getGroup().getName().equals(groupName)) {
                find = true;
                strings.add("Pairs for group " + studyWeek.getGroup().getName() + " in week");
                for (DayWeek dayWeek : DayWeek.values()) {
                    strings.add("  " + studyWeek.getDay(dayWeek).getDayWeek());
                    List<Pair> pairs = studyWeek.getDay(dayWeek).getPairsList();
                    int counter = 0;
                    for (int i = 0; i < 6; i++) {
                        if (pairs.size() > i - counter && pairs.get(i - counter).getPairNum() == i) {
                            strings.add((i + 1) + ") " + pairs.get(i - counter).getLecturer().getName() + ", "
                                    + pairs.get(i - counter).getDiscipline().getCourseType() + ", "
                                    + pairs.get(i - counter).getStudClass());
                        } else {
                            strings.add((i + 1) + ") ");
                            counter++;
                        }
                    }
                }
            }
        }
        if (find) {
            for (String s : strings) {
                System.out.println(s);
            }
        } else {
            System.out.println("This group  doesn't exist");
            System.out.println();
        }
    }


    private List<String> concatLists(List<String> l1, List<String> l2) {
        List<String> l3 = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            l3.add(l1.get(i) + l2.get(i));
        }
        return l3;
    }

    private int findMaxLength(List<String> strings) {
        int max = 0;
        for (String s : strings) {
            max = Math.max(s.length(), max);
        }
        return max;
    }

    private List<String> setLength(List<String> strings, int max) {
        List<String> spaces = new ArrayList<>();
        for (String s : strings) {
            spaces.add(" ".repeat(Math.max(0, max - s.length())));
        }
        return spaces;
    }

//    public static void printSchedule(Schedule schedule) {
//        List<StudyWeek> list = getListOfWeeks(schedule);
//        for (StudyWeek studyWeek : list) {
//            System.out.println(printWeekFor1Gr(studyWeek));
//        }
//        System.out.println();
//    }

    private List<StudyWeek> getListOfWeeks() {
        List<StudyWeek> listOfWeeks = new ArrayList<>();
        for (Group group : schedule.getGroups()) {
            StudyWeek studyWeek = createStudyWeekFor1Group(group);
            listOfWeeks.add(studyWeek);
            for (Student student : group.getStudents()) {
                student.setStudyWeek(studyWeek);
            }
        }
        return listOfWeeks;
    }
    private Map<String, StudyWeek> getMapOfWeeks() {
        Map<String, StudyWeek> map = new LinkedHashMap<>();
        for (Group group : schedule.getGroups()) {
            StudyWeek studyWeek = createStudyWeekFor1Group(group);
            map.put(group.getName(), studyWeek);
            for (Student student : group.getStudents()) {
                student.setStudyWeek(studyWeek);
            }
        }
        return map;
    }

    private StudyWeek createStudyWeekFor1Group(Group group) {
        StudyWeek studyWeek = new StudyWeek(group);
        List<Discipline> thisDisc = discFor1group(schedule.getDisciplines(), group); // дисциплины в нед для 1 группы
        thisDisc.add(null);
        for (int i = 0; i < 6; i++) {
            StudyDay studyDay = new StudyDay(group, i);
            for (int j = 0; j < 6; j++) {//цикл по парам
                if (thisDisc.get(0) != null) {
                    Lecturer lecturer = findLecturer(thisDisc.get(0), i, j);
                    if (lecturer != null) { //нашли лектора
                        Integer studClass = findClass(thisDisc.get(0), i, j); //ищем кабинет
                        if (studClass != null) { //нашли кабинет
                            Pair pair = new Pair(group, lecturer, thisDisc.remove(0), studClass, i, j);
                            studyDay.addPair(pair);
                            lecturer.addStudyHour(i, j);
                            schedule.getStudyClasses().get(studClass).addStudyHour(i, j);
                        } else {
                            thisDisc.add(thisDisc.remove(0));
                            j--;
                        }
                    } else {
                        thisDisc.add(thisDisc.remove(0));
                        j--;
                    }
                } else {
                    thisDisc.add(thisDisc.remove(0));
                }
            }
            studyWeek.addDay(studyDay);
        }
        return studyWeek;
    }

    private Lecturer findLecturer(Discipline discipline, int day, int pair) {
        for (Lecturer lecturer : schedule.getLecturers()) {
            if ((lecturer.isTeachDisc(discipline)) && (lecturer.isFree(day, pair))) {
                return lecturer;
            }
        }
        return null;
    }

    private Integer findClass(Discipline discipline, int day, int pair) {
        for (Integer studyClassForDisc : discipline.getClasses()) {
            if (schedule.getStudyClasses().get(studyClassForDisc).ifStudyClassEmpty(day, pair)) {
                return studyClassForDisc;
            }
        }
        return null;
    }

    private List<Discipline> discFor1group(List<Discipline> allDisc, Group group) {
        List<Discipline> discInWeekFor1Group = new ArrayList<>();
        for (Discipline discipline : allDisc) {
            for (int i = 0; i < discipline.getHoursForGroup(group); i++) {
                discInWeekFor1Group.add(discipline);
            }
        }
        return discInWeekFor1Group;
    }
    private static List<String> getWeekFor1Gr(StudyWeek studyWeek) {
        List<String> strings = new ArrayList<>();
        strings.add("Pairs for group " + studyWeek.getGroup().getName());
        for (DayWeek dayWeek : DayWeek.values()) {
            strings.add("__________________________");
            strings.add("  " + studyWeek.getDay(dayWeek).getDayWeek());
            List<Pair> pairs = studyWeek.getDay(dayWeek).getPairsList();
            int counter = 0;
            for (int i = 0; i < 6; i++) {
                if (pairs.size() > i - counter && pairs.get(i - counter).getPairNum() == i) {
                    strings.add((i + 1) + ") " + pairs.get(i - counter).getDiscipline().getCourseType() + ", "
                            + pairs.get(i - counter).getStudClass() + ", " + pairs.get(i - counter).getLecturer().getName());
                } else {
                    strings.add((i + 1) + ") ");
                    counter++;
                }
            }
        }
        return strings;
    }
}