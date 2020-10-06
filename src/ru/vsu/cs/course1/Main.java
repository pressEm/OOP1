package ru.vsu.cs.course1;

import java.util.*;
import java.util.List;

public class Main extends Thread {
    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        schedule.connectionData();
//        ScheduleService schedule = new ScheduleService();
//        schedule.createSchedule();


//        schedule.printGroups(schedule.getGroups());
    }
}

//
//        List<Student> students = randomStudents(200);
//        List<Lecturer> lecturers = randomLecturers(7);
//        List<Discipline> disciplines = new ArrayList<>();
//
//        List<Group> groups = createGroups(students);
//        printGroups(groups);
//
//        CourseType c = CourseType.randomDisciplines();
//        for (int i = 0; i < students.size(); i++) {
//            System.out.println(students.get(i).getName() + " " + students.get(i).getCourse());
//        }
//
//        for (int i = 0; i < lecturers.size(); i++) {
//            System.out.println(lecturers.get(i).getName() + " " + lecturers.get(i).getHoursInWeek());
//        }
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(CourseType.randomDisciplines());
//        }
//    }
//
//    public static void printGroups(List<Group> groups) {
//        for (int i = 0; i < groups.size(); i++) {
//            System.out.println(groups.get(i).getName());
//            for (int j = 0; j < groups.get(i).students.size(); j++) {
//                System.out.println(groups.get(i).students.get(j).getName() + " " +
//                        groups.get(i).students.get(j).getCourse() + " " + groups.get(i).students.get(j).getGroup().getName());
//            }
//        }
//    }
//
//    public static List<Student> randomStudents(int countStudents) {
//        List<Student> studentList = new ArrayList<>();
//        for (int i = 0; i < countStudents; i++) {
//            studentList.add(new Student("student".concat(String.valueOf(i + 1)), random(1, 4)));
//        }
//        return studentList;
//    }
//
//    public static List<Lecturer> randomLecturers(int countLecturers) {
//        List<Lecturer> lectererList = new ArrayList<>();
//        for (int i = 0; i < countLecturers; i++) {
//            Lecturer lecturer = new Lecturer("lecturer".concat(String.valueOf(i)), random(5, 10));
////            lecterer.
//            lectererList.add(lecturer);
//        }
//        return lectererList;
//    }
//
//
//
//    public static int random(int min, int max) {
//        max -= min;
//        return (int) (Math.random() * ++max) + min;
//    }
//
//
//    public static List<Group> createGroups(List<Student> students) {
//        List<Group> groups = new ArrayList<>();
//        List<Student>[] studentsByCourses = new ArrayList[4];
//        for (Student student : students) {
//            switch (student.getCourse()) {
//                case (1):
//                    if (studentsByCourses[0] == null) {
//                        studentsByCourses[0] = new ArrayList<>();
//                    }
//                    studentsByCourses[0].add(student);
//                    break;
//                case (2):
//                    if (studentsByCourses[1] == null) {
//                        studentsByCourses[1] = new ArrayList<>();
//                    }
//                    studentsByCourses[1].add(student);
//                    break;
//                case (3):
//                    if (studentsByCourses[2] == null) {
//                        studentsByCourses[2] = new ArrayList<>();
//                    }
//                    studentsByCourses[2].add(student);
//                    break;
//                case (4):
//                    if (studentsByCourses[3] == null) {
//                        studentsByCourses[3] = new ArrayList<>();
//                    }
//                    studentsByCourses[3].add(student);
//                    break;
//            }
//        }
//        for (int i = 0; i < studentsByCourses.length; i++) {
////             System.out.println(" st by " + (i+1) + " courses (" +studentsByCourses[i].size() + " students):");
////             for (int j = 0; j < studentsByCourses[i].size(); j++) {
////                 System.out.println(studentsByCourses[i].get(j).getName());
////             }
//            List<Student>[] studentsByGroups = byGroup(studentsByCourses[i], 20);
//            for (int j = 0; j < studentsByGroups.length; j++) {
//                Group group = new Group(String.valueOf(i + 1).concat(".").concat(String.valueOf(j + 1)));
//                group.addStudents(studentsByGroups[j]);
//                groups.add(group);
//            }
//        }
//
//        for (int i = 0; i < groups.size(); i++) {
//            System.out.println(groups.get(i).getName());
//            for (int j = 0; j < groups.get(i).students.size(); j++) {
//                groups.get(i).students.get(j).setGroup(groups.get(i));
//                System.out.println(groups.get(i).students.get(j).getName() + " " +
//                        groups.get(i).students.get(j).getCourse() + " " + groups.get(i).students.get(j).getGroup().getName());
//            }
//        }
//        return groups;
//    }
//
//    public static List<Student>[] byGroup(List<Student> studentsOnCourse, int maximumPerGroup) {
//        int numberOfGroups = studentsOnCourse.size() / maximumPerGroup;
//        if (studentsOnCourse.size() % maximumPerGroup > 0) numberOfGroups++;
//        List<Student> groups[] = new ArrayList[numberOfGroups];
//        for (int i = 0; i < studentsOnCourse.size(); i++) {
//            if (groups[i % numberOfGroups] == null) groups[i % studentsOnCourse.size()] = new ArrayList<>();
//            groups[i % numberOfGroups].add(studentsOnCourse.get(i));
////            studentsOnCourse.get(i).setGroup(studentsOnCourse.get(i));
//        }
//        return groups;
//    }
//}






//     создание курсов
//    public static Map<Integer, Course> createCourse(List<Student> allStudents, List<CourseType> disciplines) {
//        Map<Integer, Course> map = new HashMap<>(); // список курсов
//        Map<Integer, List<Student>> studentsByCourses = new HashMap<>(); // имя курса -- список студентов этого курса
//        for (Student student : allStudents) {
//            // создание курсов и ввод дисциплин в курс
//            if (!map.containsKey(student.getCourse())) {
//                map.put(student.getCourse(), new Course(student.getCourse()));
//            }
//            Course course = map.get(student.getCourse());
//
//            for (Discipline discipline : disciplines) {
//                if (discipline.getMap().containsKey(course.getName())) {
//                    map.get(course.getName()).addDis(discipline);
//                }
//            }
//            // распределение студентов по курсам
//            if (studentsByCourses.get(student.getCourse()) == null) {
//                studentsByCourses.put(student.getCourse(), new ArrayList<>());
//            }
//            List<Student> list = studentsByCourses.get(student.getCourse());
//            list.add(student);
//            studentsByCourses.put(student.getCourse(), list);
//        }
//
//        // вывод списков студентов по курсам
////        for (List<Student> studentList : studentsByCourses.values()) {
////            for (Student student : studentList) {
////                System.out.println("FDJHBVHBDRHBJHRVBHJDRBVHJBFVJRF");
////
////                System.out.println(student.getCourse() + "  " + student.getName());
////            }
////        }
//
//        // добавление группы в курс
//        for (Integer numCourse : studentsByCourses.keySet()) {
//            List<Student> groups[] = byGroup(studentsByCourses.get(numCourse), 3);
//            for (int i = 0; i < groups.length; i++) {
//                Group group = new Group(i + 1);
//                group.addStudents(groups[i]);
//                map.get(numCourse).addGroup(group);
//            }
//        }
//        // добавление группы в студента
//        for (Course course : map.values()) {
//            for (Group group : course.getAllGroups().values()) {
//                for (Student student : group.students) {
//                    student.setGroup(group);
//                }
//            }
//        }
//        return map;
//    }




//    public static void printCourses(Map<Integer, Course> courseMap) {
//        System.out.println("@@@" + courseMap.keySet());
//        for (Integer i : courseMap.keySet()) {
//            System.out.println(i);
//            if ((courseMap == null) && (courseMap.get(i).getAllDisciplines() == null)) {
//                System.out.println("errjr 28");
//            } else
//                System.out.println(courseMap.get(i).getName() + "   " + courseMap.get(i).getAllDisciplines());
//        }
//    }
//
//    public static List<Student> getStudentsFromFile(String file) {
//        List<Student> students = new ArrayList<>();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line = br.readLine();
//            String[] str;
//            while (line != null) {
//                str = line.split(", *");
//                students.add(new Student(str[0], Integer.parseInt(str[1])));
//                line = br.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return students;
//    }
//
//    public static Map<String, Teacher> getTeachersFromFile(String file) {
//        Map<String, Teacher> teachers = new HashMap<>();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line = br.readLine();
//            String[] str;
//            while (line != null) {
//                str = line.split(", *");
//                teachers.put(str[0], new Teacher(str[0], Integer.parseInt(str[1])));
//                line = br.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return teachers;
//    }
//
//    public static List<Discipline> getDisciplinesFromFile(String file, Map<String, Teacher> teachers) {
//        List<Discipline> disciplines = new ArrayList<>();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line = br.readLine();
//            String[] str;
//            while (line != null) {
//                str = line.split(", *");
//                disciplines.add(createDiscipline(str, teachers));
//                line = br.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return disciplines;
//    }
//
//    public static Discipline createDiscipline(String[] str, Map<String, Teacher> teacherMap) {
//        boolean flagClass = false;
//        Map<Integer, Integer> map = new HashMap<>();
//        List<Teacher> teachers = new ArrayList<>();
//        List<Integer> classes = new ArrayList<>();
//        for (int i = 1; i < str.length; i++) {
//            if (isDigit(str[i])) {
//                if (!flagClass) {
//                    map.put(Integer.parseInt(str[i]), Integer.parseInt(str[i + 1]));
//                    i++;
//                } else {
//                    classes.add(Integer.parseInt(str[i]));
//                }
//            } else {
//                flagClass = true;
//                teachers.add(teacherMap.get(str[i]));
//            }
//        }
//        return new Discipline(str[0], map, teachers, classes);
//    }
//
//    private static boolean isDigit(String s) throws NumberFormatException {
//        try {
//            Integer.parseInt(s);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    public static void printStudents(List<Student> students) {
//        for (int i = 0; i < students.size(); i++) {
//            System.out.print(students.get(i).getName() + ", ");
//            System.out.print(students.get(i).getCourse() + ", ");
//            System.out.print(students.get(i).getGroup().getClass());
//            System.out.println();
//        }
//    }
//
//
//    //доделать
//    public static void printDisciplines(List<Discipline> disciplines) {
//        for (Discipline discipline : disciplines) {
//            System.out.println(discipline.getName() + ": ");
//            System.out.println(discipline.getCourses());
//            for (Teacher currTeacher : discipline.getTeachers()) {
//                System.out.print(currTeacher.name + ", ");
//            }
//            for (Integer currClasses : discipline.getClasses()) {
//                System.out.print(currClasses + " ");
//            }
//            System.out.println();
//        }
//
//    }
//
//    // создание курсов
//    public static Map<Integer, Course> createCourse(List<Student> allStudents, List<Discipline> disciplines) {
//        Map<Integer, Course> map = new HashMap<>(); // список курсов
//        Map<Integer, List<Student>> studentsByCourses = new HashMap<>(); // имя курса -- список студентов этого курса
//        for (Student student : allStudents) {
//            // создание курсов и ввод дисциплин в курс
//            if (!map.containsKey(student.getCourse())) {
//                map.put(student.getCourse(), new Course(student.getCourse()));
//            }
//            Course course = map.get(student.getCourse());
//
//            for (Discipline discipline : disciplines) {
//                if (discipline.getMap().containsKey(course.getName())) {
//                    map.get(course.getName()).addDis(discipline);
//                }
//            }
//            // распределение студентов по курсам
//            if (studentsByCourses.get(student.getCourse()) == null) {
//                studentsByCourses.put(student.getCourse(), new ArrayList<>());
//            }
//            List<Student> list = studentsByCourses.get(student.getCourse());
//            list.add(student);
//            studentsByCourses.put(student.getCourse(), list);
//        }
//
//        // вывод списков студентов по курсам
////        for (List<Student> studentList : studentsByCourses.values()) {
////            for (Student student : studentList) {
////                System.out.println("FDJHBVHBDRHBJHRVBHJDRBVHJBFVJRF");
////
////                System.out.println(student.getCourse() + "  " + student.getName());
////            }
////        }
//
//        // добавление группы в курс
//        for (Integer numCourse : studentsByCourses.keySet()) {
//            List<Student> groups[] = byGroup(studentsByCourses.get(numCourse), 3);
//            for (int i = 0; i < groups.length; i++) {
//                Group group = new Group(i + 1);
//                group.addStudents(groups[i]);
//                map.get(numCourse).addGroup(group);
//            }
//        }
//        // добавление группы в студента
//        for (Course course : map.values()) {
//            for (Group group : course.getAllGroups().values()) {
//                for (Student student : group.students) {
//                    student.setGroup(group);
//                }
//            }
//        }
//        return map;
//    }
//
//    public static List<Student>[] byGroup(List<Student> studentsOnCourse, int maximumPerGroup) {
//        int numberOfGroups = studentsOnCourse.size() / maximumPerGroup;
//        if (studentsOnCourse.size() % maximumPerGroup > 0) numberOfGroups++;
//        List<Student> groups[] = new ArrayList[numberOfGroups];
//        for (int i = 0; i < studentsOnCourse.size(); i++) {
//            if (groups[i % numberOfGroups] == null) groups[i % studentsOnCourse.size()] = new ArrayList<>();
//            groups[i % numberOfGroups].add(studentsOnCourse.get(i));
////            studentsOnCourse.get(i).setGroup(studentsOnCourse.get(i));
//        }
//        return groups;
//    }
//}