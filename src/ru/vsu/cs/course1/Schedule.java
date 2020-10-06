package ru.vsu.cs.course1;

import java.util.*;

public class Schedule {
    private List<Group> groups;
    private List<Lecturer> lecturers;
    private List<Discipline> disciplines;

    Schedule() {
        this.lecturers = randomLecturers();
        this.groups = createGroups(randomStudents(10));
        this.disciplines = createDisciplines(CourseType.values());

    }


    public List<Group> getGroups() {
        return groups;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }


    public void connectionData() {
        printGroups(groups);
        printDisciplines(disciplines);
        printLecturers(lecturers);
        createSchedule();
    }

    public void createSchedule() {
        List<Discipline> allDisc = new ArrayList<>();
        for (Discipline discipline : disciplines) {
            for (Integer k : discipline.getMap().values()) {
                for (int i = 0; i < k; i++) {
                    allDisc.add(discipline);
                }
            }
        }
        List<Discipline> listDisc1Gr = discFor1group(disciplines, groups.get(0));
        System.out.println("___________________________________________________");
        System.out.println("Disciplines for group on week  " + listDisc1Gr.size());
        for (int i = 0; i < listDisc1Gr.size(); i++) {
            System.out.println(i + "                      " + listDisc1Gr.get(i).getCourseType());
        }
        System.out.println("___________________________________________________");

        System.out.println("получить пары по дням недели");
        StudyWeek studyWeekFor1gr = createStudyWeekFor1Group(listDisc1Gr, groups.get(0));
        for (int i = 0; i < studyWeekFor1gr.getDays().size(); i++) {
            System.out.println(studyWeekFor1gr.getDays().get(i).getDayWeek());
            for (int j = 0; j < studyWeekFor1gr.getDays().get(i).getPairs().size(); j++) {
                System.out.print(studyWeekFor1gr.getDays().get(i).getPairsList().get(j).getDiscipline().getCourseType() + ",  ");
            }
            System.out.println();
        }
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
    private StudyWeek createStudyWeekFor1Group(List<Discipline> discInWeekFor1Group, Group group){
        StudyWeek studyWeek = new StudyWeek(group);
        List<Discipline> thisDisc = new ArrayList<>(discInWeekFor1Group);
        for (int i = 0; i < 6; i++) {
            StudyDay studyDay = new StudyDay(group, DayWeek.values()[i]);

            boolean isFindLecture;
            for (int j = 0; ((j < 6) && (thisDisc.size()>0)); j++){
                isFindLecture = false;
                for (Lecturer lecturer : lecturers) {
                    if ((!isFindLecture) && (lecturer.ifExistDisc(thisDisc.get(0)))&& (thisDisc.size()>0)) {
                        System.out.println("87  disc count   "+thisDisc.size());
                        Pair pair = new Pair(group, lecturer, thisDisc.get(0));
                        studyDay.addPair(pair);
                        thisDisc.remove(0);
                        isFindLecture = true;
                    }
                }
            }
//            StudyDay studyDayFor1gr = createStudyDayFor1Group(listDisc1Gr, group, DayWeek.values()[i]);
            studyWeek.addDay(studyDay);
        }
        return studyWeek;
    }

    private StudyDay createStudyDayFor1Group(List<Discipline> discInWeekFor1Group, Group group, DayWeek dayWeek) {
//        StudyWeek studyWeek;
//        int countPairsInDay = (int) (discInWeekFor1Group.size()/6) + 1;
//        DayWeek[] daysInWeek = DayWeek.values();
        List<Discipline> thisDisc = new ArrayList<>();
        thisDisc.addAll(discInWeekFor1Group);
        StudyDay studyDay = new StudyDay(group, dayWeek);

        boolean isFindLecture;
                for (int i = 0; i < 6; i++){
                    isFindLecture = false;
                    for (Lecturer lecturer : lecturers) {
                        if ((!isFindLecture) && (lecturer.ifExistDisc(thisDisc.get(0)))) {
                        Pair pair = new Pair(group, lecturer, thisDisc.get(0));
                    studyDay.addPair(pair);
                    thisDisc.remove(0);
                    isFindLecture = true;
                }
            }
        }
//                ВЫВОД ДИСЦИПЛИН ЗА 1 ДЕНЬ
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        for (Pair pair : studyDay.getPairs()) {
//            System.out.println(pair.getDiscipline().getCourseType());
//        }
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


//        int i = 0;
//        for (Discipline discipline:thisDisc){
//
//            if (i < 6) {
//                for (Lecturer lecturer : lecturers) {
//                    if (lecturer.ifExistDisc(discipline.getCourseType())) {
//                        Pair pair = new Pair(group, lecturer, discipline);
//                        studyDay.addPair(pair);
////                            thisDisc.remove(0);
//                    }
//                }
//                i++;
//            }
//
//        }
//        System.out.println("______________________________");
//        for (int j = 0; j < discInWeekFor1Group.size(); j++) {
//            System.out.println(discInWeekFor1Group.get(j).getCourseType());
//        }
//        System.out.println("______________________________");
        return studyDay;
    }

    private List<Discipline> createDisciplines(CourseType[] values) {
        List<Discipline> disciplines = new ArrayList<>();
        for (CourseType courseType : values) {
            disciplines.add(new Discipline(courseType));
        }
        for (int i = 0; i < disciplines.size(); i++) {
            List<Integer> classes = new ArrayList<>();
            for (int j = 0; j < random(1, 3); j++) {
                classes.add(random(10, 50));
            }
            disciplines.get(i).setClasses(classes);
            for (Group group : groups) {
                disciplines.get(i).setHours(group.getName(), random(0, 3));
            }
        }
        return disciplines;
    }

    public void printGroups(List<Group> groups) {
        for (Group group : groups) {
            System.out.println(group.getName() + " GROUP ");
            for (Student student : group.getStudents()) {
                System.out.println("    " + student.getName());
            }
        }
        System.out.println("____________________________________________________");
    }

    public void printStudents(List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getName() + " " + students.get(i).getCourse());
        }
    }

    public void printDisciplines(List<Discipline> disciplines) {
        for (Discipline discipline : disciplines) {
            System.out.println(discipline.getCourseType());
            System.out.println(discipline.getMap() + " -- group=hours");
            System.out.println(discipline.getClasses() + " -- classes");
        }
        System.out.println("____________________________________________________");
    }

    public void printLecturers(List<Lecturer> lecturers) {
        System.out.println(lecturers.size());
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getName() + " -- " + lecturer.getDisciplines());
        }
        System.out.println("____________________________________________________");
    }

    private List<Student> randomStudents(int countStudents) {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < countStudents; i++) {
            studentList.add(new Student("student".concat(String.valueOf(i + 1)), random(1, 4)));
        }
        return studentList;
    }

    private int findMinK(int[] arr) {
        int min = 10;
        int k = 0;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] < min) {
                min = arr[i];
                k = i;
            }
        }
        return k;
    }

    private boolean ifExist0(int[] arr) {
        boolean exist = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                exist = true;
            }
        }
        return exist;
    }

    private List<Lecturer> randomLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        CourseType[] disciplines = CourseType.values();
        int[] meetDisc = new int[disciplines.length];
        int i = 0;
        while (ifExist0(meetDisc)) {
            Lecturer lecturer = new Lecturer("lecturer".concat(String.valueOf(i)));
            int min = 0;
            int max = 4;
            int numDisc = findMinK(meetDisc);
            for (int j = 0; j < 3; j++) {
                if (lecturer.getDisciplines().size() == 0) {
                    lecturer.setDiscipline(disciplines[numDisc]);
                    meetDisc[numDisc] = meetDisc[numDisc] + 1;
                } else {
                    if (((lecturer.getDisciplines().size() == 1) && (random(0, 100) < 70)) || ((lecturer.getDisciplines().size() == 2) && (random(0, 100) < 50))) {
                        if (numDisc < 5) {
                            min = 0;
                            max = 4;
                        } else if (numDisc < 8) {
                            min = 5;
                            max = 7;
                        } else if (numDisc < 12) {
                            min = 8;
                            max = 11;
                        } else if (numDisc < 16) {
                            min = 12;
                            max = 15;
                        } else if ((numDisc == 16) || (numDisc == 17)) {
                            numDisc = -1;
                        }
                    } else numDisc = -1;
                    if (numDisc != -1) {
                        numDisc = random(min, max);
                        lecturer.setDiscipline(disciplines[numDisc]);
                        meetDisc[numDisc]++;
                    } else {
                        j = 3;
                    }
                }
            }
            lecturers.add(lecturer);
            i++;
        }
        return lecturers;
    }

    private int random(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private List<Group> createGroups(List<Student> students) {
        List<Group> groups = new ArrayList<>();
        List<Student>[] studentsByCourses = new ArrayList[4];
        for (Student student : students) {
            switch (student.getCourse()) {
                case (1):
                    if (studentsByCourses[0] == null) {
                        studentsByCourses[0] = new ArrayList<>();
                    }
                    studentsByCourses[0].add(student);
                    break;
                case (2):
                    if (studentsByCourses[1] == null) {
                        studentsByCourses[1] = new ArrayList<>();
                    }
                    studentsByCourses[1].add(student);
                    break;
                case (3):
                    if (studentsByCourses[2] == null) {
                        studentsByCourses[2] = new ArrayList<>();
                    }
                    studentsByCourses[2].add(student);
                    break;
                case (4):
                    if (studentsByCourses[3] == null) {
                        studentsByCourses[3] = new ArrayList<>();
                    }
                    studentsByCourses[3].add(student);
                    break;
            }
        }
        for (int i = 0; i < studentsByCourses.length; i++) {
            if (studentsByCourses[i] != null) {
                List<Student>[] studentsByGroups = byGroup(studentsByCourses[i], 6);
                for (int j = 0; j < studentsByGroups.length; j++) {
                    Group group = new Group(String.valueOf(i + 1).concat(".").concat(String.valueOf(j + 1)));
                    group.addStudents(studentsByGroups[j]);
                    groups.add(group);
                }
            }
        }
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < groups.get(i).getStudents().size(); j++) {
                groups.get(i).getStudents().get(j).setGroup(groups.get(i));
            }
        }
        return groups;
    }

    private List<Student>[] byGroup(List<Student> studentsOnCourse, int maximumPerGroup) {
        int numberOfGroups = studentsOnCourse.size() / maximumPerGroup;
        if (studentsOnCourse.size() % maximumPerGroup > 0) numberOfGroups++;
        List<Student> groups[] = new ArrayList[numberOfGroups];
        for (int i = 0; i < studentsOnCourse.size(); i++) {
            if (groups[i % numberOfGroups] == null) groups[i % studentsOnCourse.size()] = new ArrayList<>();
            groups[i % numberOfGroups].add(studentsOnCourse.get(i));
        }
        return groups;
    }
}


//            for (int j = 0; j < 3; j++) {
//                if (((lecturer.getDisciplines().size() == 1) && (random(0, 100) < 50))
//                        || ((lecturer.getDisciplines().size() == 2) && (random(0, 100) < 30))) {
//                    if (numDisc < 5) {
//                        numDisc = random(0, 4);
//                    } else if (numDisc < 8) {
//                        numDisc = random(5, 7);
//                    } else if (numDisc < 12) {
//                        numDisc = random(8, 11);
//                    } else if (numDisc < 16) {
//                        numDisc = random(12, 15);
//                    } else if ((numDisc == 16) || (numDisc == 17)) {
//                        numDisc = -1;
//                    }
//                }
//                if (numDisc != -1) {
//                    lecturer.setDiscipline(disciplines[numDisc]);
//                }
//            }


//    ЛЕКТОРЫ ПО ИХ ЗАДАННОМУ КОЛИЧЕСТВУ
//        for (int i = 0; i < countLecturers; i++) {
//            Lecturer lecturer = new Lecturer("lecturer".concat(String.valueOf(i)));
//            int min = 0;
//            int max = 4;
//            int numDisc = findMinK(meetDisc);
//            for (int j = 0; j < 3; j++) {
//                if (lecturer.getDisciplines().size() == 0) {
//                    lecturer.setDiscipline(disciplines[numDisc]);
//                    System.out.println(numDisc + " numDisc");
//                    meetDisc[numDisc] = meetDisc[numDisc]+1;
//
//                } else {
//                    if (((lecturer.getDisciplines().size() == 1) || (lecturer.getDisciplines().size() == 2)) && (random(0, 100) < 50)) {
//                        if (numDisc < 5) {
//                            min = 0;
//                            max = 4;
//                        } else if (numDisc < 8) {
//                            min = 5;
//                            max = 7;
//                        } else if (numDisc < 12) {
//                            min = 8;
//                            max = 11;
//                        } else if (numDisc < 16) {
//                            min = 12;
//                            max = 15;
//                        } else if ((numDisc == 16) || (numDisc == 17)) {
//                            numDisc = -1;
//                        }
//                    } else numDisc = -1;
//                    if (numDisc != -1) {
//                        numDisc = random(min, max);
//                        lecturer.setDiscipline(disciplines[numDisc]);
//                        meetDisc[numDisc]++;
//                    } else {
//                        j = 3;
//                    }
//                }
//            }
//            lecturers.add(lecturer);
//        }
