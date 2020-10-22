package ru.vsu.cs.course1;

import ru.vsu.cs.course1.Data.*;
import ru.vsu.cs.course1.Enum.DayWeek;

import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
//    private List<StudyWeek> listOfWeeks;
//    private Schedule schedule;

    public ScheduleService(Schedule schedule) {
//        this.schedule = schedule;
//        listOfWeeks = createListOfWeeks(schedule);
    }

    public void printAllSchedule(Schedule schedule) {
        List<List<String>> strings = new ArrayList<>();
        for (StudyWeek studyWeek : schedule.getListOfWeeks()) {
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

    public void printScheduleForStudyClass(Schedule schedule, int className){
        StudyClass studyClass = schedule.getStudyClasses().get(className);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Pair pair = studyClass.getPair(i, j);
                if (pair != null){
                    System.out.println(j+1 +") " + pair.getGroup().getName() + ", " + pair.getDiscipline().getCourseType() + ", " + pair.getLecturer().getName());
                } else {
                    System.out.println(j+1 + ") ");
                }
            }
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

    public void printScheduleForStudent(Schedule schedule, String studentName) {
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

    public void printScheduleForGroup(Schedule schedule, String groupName) {
        List<String> strings = new ArrayList<>();
        boolean find = false;
        for (StudyWeek studyWeek : schedule.getListOfWeeks()) {
            if (studyWeek.getGroup() != null && studyWeek.getGroup().getName().equals(groupName)) {
                find = true;
                strings.add("Pairs for group " + studyWeek.getGroup().getName() + " in week");
                for (DayWeek dayWeek : DayWeek.values()) {
                    strings.add("  " + studyWeek.getDay(dayWeek).getDayWeek());
                    List<Pair> pairs = studyWeek.getDay(dayWeek).getPairsList();
                    int counter = 0;
                    for (int i = 0; i < 6; i++) {
                        if (pairs.size() > i - counter && pairs.get(i - counter).getPairNum() == i) {
                            strings.add((i + 1) + ") "  + pairs.get(i - counter).getDiscipline().getCourseType() + ", "
                                    + pairs.get(i - counter).getStudClass()+ ", "
                                    + pairs.get(i - counter).getLecturer().getName());
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

    public void createListOfWeeks(Schedule schedule) {
        List<StudyWeek> listOfWeeks = new ArrayList<>();
        for (Group group : schedule.getGroups()) {
            StudyWeek studyWeek = createStudyWeekFor1Group(schedule, group);
            listOfWeeks.add(studyWeek);
            for (Student student : group.getStudents()) {
                student.setStudyWeek(studyWeek);
            }
        }
        schedule.setListOfWeeks(listOfWeeks);
//        return listOfWeeks;
    }

    private StudyWeek createStudyWeekFor1Group(Schedule schedule, Group group) {
        StudyWeek studyWeek = new StudyWeek(group);
        List<Discipline> thisDisc = discFor1group(schedule.getDisciplines(), group); // дисциплины в нед для 1 группы
        thisDisc.add(null);
        for (int i = 0; i < 6; i++) {
            StudyDay studyDay = new StudyDay(group, i);
            for (int j = 0; j < 6; j++) {//цикл по парам
                if (thisDisc.get(0) != null) {
                    Lecturer lecturer = findLecturer(schedule, thisDisc.get(0), i, j);
                    if (lecturer != null) { //нашли лектора
                        Integer studClass = findClass(schedule, thisDisc.get(0), i, j); //ищем кабинет
                        if (studClass != null) { //нашли кабинет
                            Pair pair = new Pair(group, lecturer, thisDisc.remove(0), studClass, i, j);
                            studyDay.addPair(pair);
                            lecturer.addStudyHour(i, j);
                            schedule.getStudyClasses().get(studClass).addStudyHour(i, j);
                            schedule.getStudyClasses().get(studClass).addPair(pair, i, j);
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

    private Lecturer findLecturer(Schedule schedule, Discipline discipline, int day, int pair) {
        for (Lecturer lecturer : schedule.getLecturers()) {
            if ((lecturer.isTeachDisc(discipline)) && (lecturer.isFree(day, pair))) {
                return lecturer;
            }
        }
        return null;
    }

    private Integer findClass(Schedule schedule, Discipline discipline, int day, int pair) {
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