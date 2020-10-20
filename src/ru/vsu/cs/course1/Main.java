package ru.vsu.cs.course1;

import java.util.*;
import java.util.List;

public class Main extends Thread {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Schedule schedule = new Schedule();
        schedule.connectionData();
        ScheduleService scheduleService = new ScheduleService(schedule);

        System.out.println("");
        System.out.println("0 - Close");
        System.out.println("1 - Schedule for all groups");
        System.out.println("2 - Schedule for certain group");
        System.out.println("3 - Schedule for certain student");
        int n = scanner.nextInt();
        while (n != 0) {
            if (n == 1) {
                scheduleService.printAllSchedule();
            }
            if (n == 2) {
                System.out.print("Enter the group number in the format: <course>.<group> - ");
                String group = scanner.next();
//                scheduleService.printScheduleForGroup(group);
                scheduleService.printScheduleForGroup(group);
            }
            if (n == 3) {
                System.out.print("Enter the name of student to get his schedule: ");
                String student = scanner.next();
                scheduleService.printScheduleForStudent(student);
            }
            System.out.println("what action next?");
            n = scanner.nextInt();
        }
    }
}
