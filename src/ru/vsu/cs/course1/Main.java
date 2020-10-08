package ru.vsu.cs.course1;

import java.util.*;
import java.util.List;

public class Main extends Thread {
    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        schedule.connectionData();
        ScheduleService srv = new ScheduleService(schedule);
        srv.createSchedule();
    }
}
