package ru.vsu.cs.course1;

import ru.vsu.cs.course1.Data.*;

import java.util.*;

public class Schedule {
    private List<Group> groups;
    private List<Lecturer> lecturers;
    private List<Discipline> disciplines;
    private Map<Integer, StudyClass> studyClasses = new HashMap<>();


    Schedule() {
        this.lecturers = randomLecturers();
        this.groups = createGroups(randomStudents(400));
        this.disciplines = createDisciplines(CourseType.values());
        this.studyClasses = createStudyClasses();
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

    public Map<Integer, StudyClass> getStudyClasses() {
        return studyClasses;
    }

    public void connectionData() {
        printGroups(groups);
        printDisciplines(disciplines);
        printLecturers(lecturers);
//        printStudyClasses(studyClasses);
    }


    private List<Discipline> createDisciplines(CourseType[] values) {
        List<Discipline> disciplines = new ArrayList<>();
        for (CourseType courseType : values) {
            disciplines.add(new Discipline(courseType));
        }
        for (Discipline discipline : disciplines) {
            List<Integer> classes = new ArrayList<>();
            for (int j = 0; j < random(1, 3); j++) {
                classes.add(random(10, 50));
            }
            discipline.setClasses(classes);
            for (Group group : groups) {
                if (needAddPair(group, disciplines)) {
                    discipline.setHours(group.getName(), random(0, 3));
                } else {
                    discipline.setHours(group.getName(), 0);
                }
            }
        }
        return disciplines;
    }

    private boolean needAddPair(Group group, List<Discipline> disciplines) {
        int countPairs = 0;
        for (Discipline discipline : disciplines) {
            if (discipline.getHours(group.getName()) != -1){

                countPairs = countPairs + discipline.getHours(group.getName());
            }
        }
        if (countPairs<24){
            return true;
        }else{
            return false;
        }
    }


    private List<Student> randomStudents(int countStudents) {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < countStudents; i++) {
            studentList.add(new Student("student".concat(String.valueOf(i + 1)), random(1, 4)));
        }
        return studentList;
    }
    

    private Map<Integer,StudyClass> createStudyClasses(){
        Map<Integer,StudyClass> studyClassMap = new HashMap<>();
        for (Discipline discipline:disciplines){
            for (Integer studyClass : discipline.getClasses()){
                if (!studyClassMap.containsKey(studyClassMap)){
                    studyClassMap.put(studyClass, new StudyClass(studyClass));
                }
            }
        }
        return studyClassMap;
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
                List<Student>[] studentsByGroups = byGroup(studentsByCourses[i], 30);
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


    public void printStudyClasses(Map<Integer,StudyClass> studyClasses){
        System.out.println("StudyClasses:");
        for (StudyClass studyClass: studyClasses.values()){
            System.out.println(studyClass.getNameClass() + " ");
        }
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
            System.out.println(discipline.getGroupHoursMap() + " -- group=hours");
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
}