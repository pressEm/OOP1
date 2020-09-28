package ru.vsu.cs.course1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main extends Thread {
    public static void main(String[] args) {
        List<Student> students = getStudentsFromFile("C:\\Программирование\\OOP1.1\\src\\Students2.txt"); // студент + курс
        Map<String, Teacher> teachers = getTeachersFromFile("C:\\Программирование\\OOP1.1\\src\\Teachers1.txt"); // учитель + ставка в неделю
        List<Discipline> disciplines = getDisciplinesFromFile("C:\\Программирование\\OOP1.1\\src\\Disciplines1.txt", teachers); // предмет + курс--часов в нед + препод + аудитория
        Map<Integer, Course> courses = createCourse(students, disciplines);

        // Вывод всех курсов (courses)
        for (Course course : courses.values()) {
            System.out.println(course.getName());
            for (Group nameGroup : course.getAllGroups().values()) {
                System.out.println(nameGroup.getName() + " group: ");
                for (Student student : nameGroup.students) {
                    System.out.print(student.getName() + " " + student.getCourse() + " " + student.getGroup().getName() + " | ");
                }
                System.out.println();
            }
            System.out.println(course.getAllDisciplines() + " ");
            System.out.println("-----------------");
        }

//        printStudent(students);
//        printCourses(courses);
        printDisciplines(disciplines);
//        printTeachers(teachers);
//        createGroups(students, 5);
//        createGroups(students, 5);
    }

    public static void printCourses(Map<Integer, Course> courseMap) {
        System.out.println("@@@" + courseMap.keySet());

        for (Integer i : courseMap.keySet()) {
            System.out.println(i);
            if ((courseMap == null) && (courseMap.get(i).getAllDisciplines() == null)) {
                System.out.println("errjr 28");
            } else
                System.out.println(courseMap.get(i).getName() + "   " + courseMap.get(i).getAllDisciplines());
        }


    }


//    public void processStudents(List<Student> students) {
//        List<Course> courses = new ArrayList<>();
//        for (int i = 0; i < students.size(); i++) {
//            if (courses.get(students.get(i).course-1) == null) {
//                courses.add(new Course(students.get(i).course));
//            }
//            if   (courses.get(students.get(i).course).groups.contains(students.get(i).group) == null){
//
//            }
//
//            courses.get(students.get(i).course).addGroup(   hhhh  );
//        }
//    }

    public static List<Student> getStudentsFromFile(String file) {
        List<Student> students = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] str;
            while (line != null) {
                str = line.split(", *");
                students.add(new Student(str[0], Integer.parseInt(str[1])));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static Map<String, Teacher> getTeachersFromFile(String file) {
        Map<String, Teacher> teachers = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] str;
            while (line != null) {
                str = line.split(", *");
                teachers.put(str[0], new Teacher(str[0], Integer.parseInt(str[1])));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teachers;
    }


//    public static List<Course> getCoursesFromFile(String file) {
//        List<Course> courses = new ArrayList<>();
//        List<String[]> listCourses = new ArrayList<>();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line = br.readLine();
//            String[] array;
//            while (line != null) {
//                array = line.split(", *");
//                listCourses.add(array);
//                line = br.readLine();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < listCourses.size(); i++) {
////            List<Discipline> disciplines = new ArrayList<>();
//            courses.add(createCourse(listCourses.get(i)));
//        }
//        return courses;
//    }
//    public static Course createCourse(String[] str) {
//        Course course = new Course(Integer.parseInt(str[0]));
//        for (int i = 1; i < str.length; i++) {
//            course.disciplines.add(new Discipline(str[i],);
//        }
//        return course
//    }

    public static List<Discipline> getDisciplinesFromFile(String file, Map<String, Teacher> teachers) {
        List<Discipline> disciplines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] str;
            while (line != null) {
                str = line.split(", *");
                disciplines.add(createDiscipline(str, teachers));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    public static Discipline createDiscipline(String[] str, Map<String, Teacher> teacherMap) {
        boolean flagClass = false;
        Map<Integer, Integer> map = new HashMap<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Integer> classes = new ArrayList<>();
        for (int i = 1; i < str.length; i++) {
            if (isDigit(str[i])) {
                if (!flagClass) {
                    map.put(Integer.parseInt(str[i]), Integer.parseInt(str[i + 1]));
                    i++;
                } else {
                    classes.add(Integer.parseInt(str[i]));
                }
            } else {
                flagClass = true;
                teachers.add(teacherMap.get(str[i]));
            }
        }
        return new Discipline(str[0], map, teachers, classes);
    }


    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void printStudents(List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            System.out.print(students.get(i).getName() + ", ");
            System.out.print(students.get(i).getCourse() + ", ");
            System.out.print(students.get(i).getGroup().getClass());
            System.out.println();
        }
    }


    //доделать
    public static void printDisciplines(List<Discipline> disciplines) {

//        System.out.println(disciplines.size());
        for (Discipline discipline : disciplines) {
            System.out.println(discipline.getName() + ": ");
            System.out.println(discipline.getCourses());
            for (Teacher currTeacher : discipline.getTeachers()) {
                System.out.print(currTeacher.name + ", ");
            }
            for (Integer currClasses : discipline.getClasses()) {
                System.out.print(currClasses + " ");
            }
            System.out.println();
        }

    }


    //
//        create groups
//
//
//
    public static Map<Integer, Course> createCourse(List<Student> allStudents, List<Discipline> disciplines) {
        for (Student s : allStudents) {
            System.out.println(s.getName() + " " + s.getCourse());
        }
        printDisciplines(disciplines);
        Map<Integer, Course> map = new HashMap<>(); // список курсов
        Map<Integer, List<Student>> studentsByCourses = new HashMap<>(); // имя курса -- список студентов этого курса
        for (Student student : allStudents) {

            // создание курсов и ввод дисциплин в курс
            if (!map.containsKey(student.getCourse())) {
                map.put(student.getCourse(), new Course(student.getCourse()));
            }
            Course course = map.get(student.getCourse());

            for (Discipline discipline : disciplines) {
                if (discipline.getMap().containsKey(course.getName())) {
                    map.get(course.getName()).addDis(discipline);
                }
            }

            // распределение студентов по курсам
            if (studentsByCourses.get(student.getCourse()) == null) {
                studentsByCourses.put(student.getCourse(), new ArrayList<>());
            }
            List<Student> list = studentsByCourses.get(student.getCourse());
            list.add(student);
            studentsByCourses.put(student.getCourse(), list);
        }

        // вывод списков студентов по курсам
//        for (List<Student> studentList : studentsByCourses.values()) {
//            for (Student student : studentList) {
//                System.out.println("FDJHBVHBDRHBJHRVBHJDRBVHJBFVJRF");
//
//                System.out.println(student.getCourse() + "  " + student.getName());
//            }
//        }

        // добавление группы в курс
        for (Integer numCourse : studentsByCourses.keySet()) {
            List<Student> groups[] = byGroup(studentsByCourses.get(numCourse), 3);
            for (int i = 0; i < groups.length; i++) {
                Group group = new Group(i + 1);
                group.addStudents(groups[i]);
                map.get(numCourse).addGroup(group);
            }
        }
        // добавление группы в студента
        for (Course course : map.values()){
            for (Group group : course.getAllGroups().values()){
                for (Student student : group.students){
                    student.setGroup(group);
                }
            }
        }
        return map;
    }


//    public static Group createGroup(List<Student>[] studentsByGroups) {
//        for (int i = 0; i < studentsByGroups.length; i++) {
//
//        }
//    }


    public static List<Student>[] byGroup(List<Student> studentsOnCourse, int maximumPerGroup) {
        int numberOfGroups = studentsOnCourse.size() / maximumPerGroup;
        if (studentsOnCourse.size() % maximumPerGroup > 0) numberOfGroups++;
        List<Student> groups[] = new ArrayList[numberOfGroups];
        for (int i = 0; i < studentsOnCourse.size(); i++) {
            if (groups[i % numberOfGroups] == null) groups[i % studentsOnCourse.size()] = new ArrayList<>();
            groups[i % numberOfGroups].add(studentsOnCourse.get(i));
//            studentsOnCourse.get(i).setGroup(studentsOnCourse.get(i));
        }
        return groups;
    }


//    public static List<Student>[] byGroups(List<Student> students, int sizeGroup) {
//        int countGroups = 1 + (int) Math.ceil(students.size() / sizeGroup);
//        List<Student>[] studentsInGroup = new ArrayList[countGroups];
//        for (int i = 0; i < students.size(); i++) {
//            System.out.println("i%countGroups " + i % sizeGroup);
//            if (studentsInGroup[i % countGroups] == null)
//                studentsInGroup[i % students.size()] = new ArrayList<>();
//            studentsInGroup[i % countGroups].add(students.get(i));
//        }
//        return studentsInGroup;
//    }
}
//    public static void createGroups(List<Student> students, int sizeGroup) {
//        List<Group> groups = new ArrayList<>();
//        List<Student>[] studentsByCourses = createCourse(students);
//        List<Student>[] studentsInGroup = new ArrayList[4];
//        for (int i = 0; i < studentsByCourses.length; i++) {
//            if (studentsByCourses[i] != null) {
//                studentsInGroup = byGroups(studentsByCourses[i], i + 1, 5);
//                System.out.println("! 149 ! " +studentsInGroup.length);
//                for (int j = 0; j < studentsInGroup.length; j++) {
//                    groups.add(new Group((j+1), i, studentsInGroup[j]));
////                    System.out.println("! 152 name group ! " + (j+1) + "! course !" + i);
////                    printStudent(studentsInGroup[j]);
////                    for (int k = 0; k < studentsInGroup[j].size(); k++) {
////
////                        System.out.println(studentsInGroup[j].get(k).name + " " + studentsInGroup[j].get(k).course + " " + j + " " + studentsInGroup[j].get(k).group);
////                    }
//                }
//            }
//        }
//        for (int i = 0; i < groups.size(); i++) {
//            System.out.println("! 163 name ! " + groups.get(i).name + groups.get(i).course);
//            printStudent(groups.get(i).students);
//        }
////        for (int i = 0; i < studentsInGroup.length; i++) {
////            for (int j = 0; j < studentsInGroup[i].size(); j++) {
////
////                System.out.println(studentsInGroup[i].get(j).name + " " + studentsInGroup[i].get(j).course + " " + i + " " + studentsInGroup[i].get(j).group);
////            }
////
////        }
//    }

/*
        public static void byGroups(List<Student> students, int sizeGroup) {
        List<Student>[] studentsByCourses = createCourse(students);

        List<Group> groups = new ArrayList<>();

        for (int i = 0; i < studentsByCourses.length; i++) {
            if (studentsByCourses[i].size() != 0) {
//            System.out.println((studentsByCourses[i].size() / sizeGroup));
                List<Student>[] studentsByGroups = new ArrayList[studentsByCourses[i].size() / sizeGroup];
                System.out.println("1 " + studentsByCourses[i].size());


//            System.out.println("i " + i);
                studentsByGroups = createGroup(studentsByCourses[i], i, sizeGroup);
                System.out.println("2 " + studentsByGroups.length);

                for (int j = 0; j < studentsByGroups.length; j++) {
                    groups.add(new Group(j, i, studentsByGroups[j]));
                }
            }
        }

            for (int i = 0; i < groups.size(); i++) {
                System.out.println("i " + groups.get(i).name + "     name " + groups.get(i).name + "    course " + groups.get(i).course);
                printStudent(groups.get(i).students);
            }
        }


 */

//                for (int j = 0; j < studentsInCourse[i].size(); j++) {
//
//                    System.out.print(studentsInCourse[i].get(j).name + "  ");
//                    System.out.println(studentsInCourse[i].get(j).course);
//                }

//                List<Student>[] studentsByGroups = new ArrayList[studentsByCourses[i].size()/sizeGroup];
//                for (int j = 0; j < studentsByGroups.length; j++) {
//
//                }
//                groups = createGroup(studentsInCourse[i], groups, i); //1+(int) Math.ceil(studentsInCourse[0].size()/10)


//        for (int i = 0; i < groups1.length; i++) {
//            System.out.print("++" + groups.size() + "  ");
//            for (int j = 0; j < groups1.length; j++) {
//                Group gr = new Group(i, 1, groups1[i]);
//                groups.add(gr);
//            }
////            groups.add(new Group(i,1,groups1[i]));
//            System.out.println(groups.get(0).course);
//        }
//        for (int i = 0; i < groups.size(); i++) {
//            System.out.print("+++++++" + groups.get(i).name + ", ");
//            System.out.print(groups.get(i).course + ", ");
////                Student s = groups.get(i).students.get(i);
//            System.out.println();
//        }
//        printGroups(groups);

//        for (int i = 0; i < groups4.length; i++) {
//            if (groups4[i] != null) {
//                for (int j = 0; j < groups4[i].size(); j++) {
//                    System.out.println(" 1 course   " + i + " group  " + j + "-" + groups4[i].get(j).name + "  ");
//                }
//            }
//        }
//        System.out.println();

//
//    public static void printGroups(List<Group> groups) {
//        for (int i = 0; i < groups.size(); i++) {
//            System.out.print("+++++++" + groups.get(i).name + ", ");
//            System.out.print(groups.get(i).course + ", ");
////                Student s = groups.get(i).students.get(i);
//            System.out.println();
//        }
//    }


