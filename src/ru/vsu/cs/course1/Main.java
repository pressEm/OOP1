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
        Map<String, Teacher> teachers = getTeachersFromFile("C:\\Программирование\\OOP1.1\\src\\Teachers1.txt");
        List<Discipline> disciplines = getDisciplinesFromFile("C:\\Программирование\\OOP1.1\\src\\Disciplines1.txt", teachers);
//        printStudent(students);
        printDisciplines(disciplines);
//        printTeachers(teachers);
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
            System.out.print(students.get(i).getGroup().name);
            System.out.println();
        }
    }


    //доделать
    public static void printDisciplines(List<Discipline> disciplines) {
        System.out.println(disciplines.size());
        for (int i = 0; i < disciplines.size(); i++) {
            System.out.print(disciplines.get(i).getName() + " ");
            for (Teacher currTeacher : disciplines.get(i).getTeachers()) {
                System.out.print(currTeacher.name + " ");
            }
//            System.out.println(disciplines.get(i).g);
//            System.out.print(disciplines.get(i).course + ", ");
//            System.out.print(disciplines.get(i).hours);
            for (Integer currClasses : disciplines.get(i).getClasses()) {
                System.out.print(currClasses + " ");
                System.out.println();
            }
        }
    }

//    public static void printTeachers(Map<String, Teacher> teachers) {
//        System.out.println();
//    }


    public static Map<Integer, Course> createCourse(List<Student> allStudents, List<Discipline> disciplines) {
        Map<Integer, Course> map = new HashMap<>();
        for (Student student : allStudents) {
            if (!map.containsKey(student.getCourse())) {
                map.put(student.getCourse(), new Course(student.getCourse()));
            }
            Course course = map.get(student.getCourse());
        }
        return map;
    }

//    public static List<List<Student>> distributionByCourses(List<Student> students) {
//        List<List<Student>> studentsInCourse = new ArrayList<>();
//        for (int i = 0; i < students.size(); i++) {
//            switch (students.get(i).course) {
//                case (1):
//                    studentsInCourse.get(0).add(students.get(i));
//                    break;
//                case (2):
//                    studentsInCourse.get(1).add(students.get(i));
//                    break;
//                case (3):
//                    studentsInCourse.get(2).add(students.get(i));
//                    break;
//                case (4):
//                    studentsInCourse.get(3).add(students.get(i));
//                    break;
//                default:
//                    break;
//            }
//        }
//        return studentsInCourse;
//    }
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

    private static List<Student> fillingGroups(List<Student> allStudents, List<List<Student>> studentsByGroups) {
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
