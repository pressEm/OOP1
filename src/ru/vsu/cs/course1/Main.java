package ru.vsu.cs.course1;

import java.util.*;

public class Main extends Thread {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Schedule schedule = new Schedule();
        schedule.connectionData();
        ScheduleService scheduleService = new ScheduleService(schedule);
        scheduleService.createListOfWeeks(schedule);

        System.out.println("");
        System.out.println("Choose the action");
        System.out.println("0 - Close");
        System.out.println("1 - Schedule for all groups");
        System.out.println("2 - Schedule for certain group");
        System.out.println("3 - Schedule for student");
        System.out.println("4 - Schedule for study class");
        int n = scanner.nextInt();
        while (n != 0) {
            if (n == 1) {
                scheduleService.printAllSchedule(schedule);
            }
            if (n == 2) {
                System.out.print("Enter the group number in the format: <course>.<group> - ");
                String group = scanner.next();
                scheduleService.printScheduleForGroup(schedule, group);
            }
            if (n == 3) {
                System.out.print("Enter the name of student to get his schedule: ");
                String student = scanner.next();
                scheduleService.printScheduleForStudent(schedule, student);
            }
            if (n == 4){
                System.out.print("Enter the studClass: ");
                int studClass = scanner.nextInt();
                scheduleService.printScheduleForStudyClass(schedule, studClass);
            }
            System.out.println("what action next?");
            n = scanner.nextInt();
        }
    }
}
