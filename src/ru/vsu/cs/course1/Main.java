package ru.vsu.cs.course1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main extends Thread {
    public static void main(String[] args) {
        List<Student> students = getStudentsFromFile("C:\\Программирование\\OOP1.1\\src\\Students2.txt");
        List<Discipline> disciplines = getDisciplinesFromFile("C:\\Программирование\\OOP1.1\\src\\Disciplines1.txt");
        List<Teacher> teachers = getTeachersFromFile("C:\\Программирование\\OOP1.1\\src\\Teachers1.txt");
//        printStudent(students);
        printDisciplines(disciplines);
        printTeachers(teachers);
//        createGroups(students, 5);
//        createGroups(students, 5);
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
        List<String[]> listStudents = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] array;
            while (line != null) {
                array = line.split(", *");
                listStudents.add(array);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listStudents.size(); i++) {
            Student l = new Student(listStudents.get(i)[0], Integer.parseInt(listStudents.get(i)[1]));
            students.add(l);
        }
        return students;
    }

    public static List<Teacher> getTeachersFromFile(String file) {
        List<Teacher> teachers = new ArrayList<>();
        List<String[]> listTeachers = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] array;
            while (line != null) {
                array = line.split(", *");
                listTeachers.add(array);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listTeachers.size(); i++) {
           teachers.add(new Teacher(listTeachers.get(i)[0]));
           teachers.get(i).hoursInWeek = Integer.parseInt(listTeachers.get(i)[1]);
        }
        return teachers;
    }


    public static List<Course> getCoursesFromFile(String file) {
        List<Course> courses = new ArrayList<>();
        List<String[]> listCourses = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] array;
            while (line != null) {
                array = line.split(", *");
                listCourses.add(array);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listCourses.size(); i++) {
//            List<Discipline> disciplines = new ArrayList<>();
            courses.add(createCourse(listCourses.get(i)));
        }
        return courses;
    }
    public static Course createCourse(String[] str) {
        Course course = new Course(Integer.parseInt(str[0]));
        for (int i = 1; i < str.length; i++) {
            course.disciplines.add(new Discipline(str[i],);
        }
        return course
    }

    public static List<Discipline> getDisciplinesFromFile(String file) {
        List<Discipline> disciplines = new ArrayList<>();
        List<String[]> listDisciplines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] array;
            while (line != null) {
                array = line.split(", *");
                listDisciplines.add(array);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listDisciplines.size(); i++) {
//            for (int j = 0; j < listDisciplines.get(i).length; j++) {
//                System.out.print(listDisciplines.get(i)[j] + " ");
//            }
//            System.out.println();
            disciplines.add(createDiscipline(listDisciplines.get(i)));
        }
        return disciplines;
    }

    public static Discipline createDiscipline(String[] str) {
        boolean flagClass = false;
        Discipline discipline = new Discipline(str[0]);
        for (int i = 1; i < str.length; i++) {
            if (isDigit(str[i])) {
            if (!flagClass) {
                if (discipline.map == null)
                discipline.map = new HashMap<>();
                discipline.map.put(Integer.parseInt(str[i]), Integer.parseInt(str[i+1]));
//                if (discipline.course == null)
//                    discipline.course = new ArrayList<>();
//                discipline.course.add(Integer.parseInt(str[i]));
//                if (discipline.hours == null)
//                    discipline.hours = new ArrayList<>();
//                discipline.hours.add(Integer.parseInt(str[i + 1]));
                i++;
            } else {
                if (discipline.classes == null)
                    discipline.classes = new ArrayList<>();

                discipline.classes.add(Integer.parseInt(str[i]));
            }
            } else {
                flagClass = true;
                if (discipline.teachers == null)
                    discipline.teachers = new ArrayList<>();
                discipline.teachers.add(new Teacher(str[i]));

            }
        }
        return discipline;
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
            System.out.print(students.get(i).name + ", ");
            System.out.print(students.get(i).course + ", ");
            System.out.print(students.get(i).points);
            System.out.println();
        }
    }

    public static void printDisciplines(List<Discipline> disciplines) {
        System.out.println(disciplines.size());
        for (int i = 0; i < disciplines.size(); i++) {
            System.out.print(disciplines.get(i).name + " ");
            System.out.println(disciplines.get(i).map);
//            System.out.print(disciplines.get(i).course + ", ");
//            System.out.print(disciplines.get(i).hours);
            for (int j = 0; j < disciplines.get(i).teachers.size(); j++) {
                System.out.print(disciplines.get(i).teachers.get(j).name + ", ");
            }
            System.out.println();
            System.out.println(disciplines.get(i).classes);
            System.out.println();
        }
    }

    public static void printTeachers(List<Teacher> teachers) {
        for (int i = 0; i < teachers.size(); i++) {
            System.out.print(teachers.get(i).name + ", ");
            System.out.print(teachers.get(i).hoursInWeek + ", ");
            System.out.println();
        }
    }

    public static void createGroups(List<Course> courses, List<Student> allStudents) {

    }

    public static List<List<Student>> distributionByCourses(List<Student> students) {
        List<List<Student>> studentsInCourse = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            switch (students.get(i).course) {
                case (1):
                    studentsInCourse.get(0).add(students.get(i));
                    break;
                case (2):
                    studentsInCourse.get(1).add(students.get(i));
                    break;
                case (3):
                    studentsInCourse.get(2).add(students.get(i));
                    break;
                case (4):
                    studentsInCourse.get(3).add(students.get(i));
                    break;
                default:
                    break;
            }
        }
        return studentsInCourse;
    }
//
//    public static void createGroup2(List<Student> students, int sizeGroup) {
//        List<Group> groups = new ArrayList<>();
//        List<List<Student>> studentsByGroup = new ArrayList<>();
//        for (int i = 0; i < students.size(); i++) {
//            groups.add(new Group(students.get(i).group, students.get(i).course, new ArrayList<>()));
//        }
//
//        for (int i = 0; i < studentsByGroup.length; i++) {
//            for (int j = 0; j < students.size(); j++) {
//                System.out.println("125 " + studentsByGroup[i].get(j));
//                studentsByGroup[i].add(students.get(j));
//            }
//        }
//        for (int i = 0; i < studentsByGroup.length; i++) {
//            for (int j = 0; j < students.size(); j++) {
//                System.out.println("125 " + studentsByGroup[i].get(j));
////                studentsByGroup[i].add(students.get(j));
//            }
//        }

//        for (int i = 0; i < students.size(); i++) {
//            studentsByGroup[students.get(i).group].add(students.get(i));
//        }
//        for (int i = 0; i < studentsByGroup.length; i++) {
//            groups.sdd(new Group())
//           groups.add(new Group(students.get(i).group, students.get(i).course), )
//        }


    public static List<List<Student>> createGroups(List<Student> allStudents, int sizeGroup) {
//        List<Group> groups = new ArrayList<>();
//        List<Student> students
        List<List<Student>> studentByGroups = new ArrayList<>();
        List<List<Student>> studentsByCourses = distributionByCourses(allStudents);
        for (int i = 0; i < studentsByCourses.size(); i++) {
                fillingGroups(allStudents, studentByGroups);
            }

            }
        return studentByGroups;
        }

        private static List<Student> fillingGroups (List<Student> allStudents, List<List<Student>> studentsByGroups) {
        List<Student> students = new ArrayList<>();
            for (int i = 0; i < allStudents.size(); i++) {
                for (int j = 0; j < studentsByGroups.get(allStudents.get(i).course).size(); j++) {
int g = allStudents.get(i).name.compareTo(studentsByGroups.get(allStudents.get(i).course).get(j).name);
//                    if (allStudents.get(i).name.compareTo(studentsByGroups[allStudents.get(i).course].get(j).name)){

//                    }
                }
            }
            return students;
        }



//        for (int i = 0; i < students.size(); i++) {
//
//            System.out.println("1 160 ! " + students.get(i).points + students.get(i).course);
//        }
//        System.out.println("l            " + studentsByCourses.length);
//        for (int i = 0; i < studentsByCourses.length; i++) {
//            if (studentsByCourses[i] != null) {
//                List<Student>[] studentsInGroup = byGroups(studentsByCourses[i], sizeGroup);
//                System.out.println("l            " + studentsInGroup.length);
//
//            }
//        }
//        System.out.println("count gr " + groups.size());
//        for (int i = 0; i < groups.size(); i++) {
//            System.out.println(groups.get(i).name);
//            System.out.println("! 136 name of group  " + groups.get(i).name + " ! course " + groups.get(i).course);
//            printStudents(groups.get(i).students);
//        }
//    }


    public static List<Student>[] byGroups(List<Student> students, int sizeGroup) {


//        Collections.sort(students);
        int countGroups = 1 + (int) Math.ceil(students.size() / sizeGroup);
        List<Student>[] studentsInGroup = new ArrayList[countGroups];


//        int ii = 0;
//        while ( (ii < students.size()) && (students.get(ii).points == 0)){
//            int points = students.get(ii).points;
//            System.out.println(points);
//            ii++;
//        }
//        int countEngGr = 1 + (int) Math.ceil(ii / sizeGroup);
//        int countNotEngGr = countGroups - countEngGr;
//        for (int i = 0; i < ii; i++) {
//            System.out.println("i%countGroups0 " + i % countEngGr);
//            if (studentsInGroup[i % countEngGr] == null)
//                studentsInGroup[i % ii] = new ArrayList<>();
//            studentsInGroup[i % countEngGr].add(students.get(0));
//            students.remove(0);
//        }
//        for (int i = ii; i < students.size(); i++) {
//            System.out.println(countGroups + " - " + countEngGr + " = " + countNotEngGr);
//            System.out.println("i%countGroups " + (i % countEngGr + countNotEngGr));
//            if (studentsInGroup[i % countEngGr + countNotEngGr - 1] == null)
////                System.out.println(i % ii + countNotEngGr);
//                studentsInGroup[i % ii + countNotEngGr] = new ArrayList<>();
//            studentsInGroup[i % students.size() + countNotEngGr - 1].add(students.get(0));
//            students.remove(0);
//        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println("i%countGroups " + i % sizeGroup);
            if (studentsInGroup[i % countGroups] == null)
                studentsInGroup[i % students.size()] = new ArrayList<>();
            studentsInGroup[i % countGroups].add(students.get(i));
        }

//        while (i < students.size()){
//            if (studentsInGroup[name] == null )
//                studentsInGroup[name] = new ArrayList<>();
//            if (studentsInGroup[name].size() >= sizeGroup){
//                name++;
//                studentsInGroup[name] = new ArrayList<>();
//            }
//            studentsInGroup[name].add(students.get(i));
//            i++;
//        }
//        for (int i = 0; i < students.size(); i++) {
//            System.out.println("i%countGroups " + i % sizeGroup);
//            if (studentsInGroup[i % countGroups] == null)
//                studentsInGroup[i % students.size()] = new ArrayList<>();
//            studentsInGroup[i % countGroups].add(students.get(i));
//        }

        return studentsInGroup;
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


    public static void printGroups(List<Group> groups) {
        for (int i = 0; i < groups.size(); i++) {
            System.out.print("+++++++" + groups.get(i).name + ", ");
            System.out.print(groups.get(i).course + ", ");
//                Student s = groups.get(i).students.get(i);
            System.out.println();
        }
    }

}
