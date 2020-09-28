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

    // создание курсов
    public static Map<Integer, Course> createCourse(List<Student> allStudents, List<Discipline> disciplines) {
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
        for (Course course : map.values()) {
            for (Group group : course.getAllGroups().values()) {
                for (Student student : group.students) {
                    student.setGroup(group);
                }
            }
        }
        return map;
    }

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
}


