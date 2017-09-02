package com.desperado.server.tools;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by desperado on 17-9-1.
 */
public class TaskScheduler {

    private Scheduler scheduler;

    private Class<? extends Job> taskClass;

    private String taskName;

    private String taskGroupName;

    private String triggerName;

    private String triggerNameGroup;

    private int intervalInSeconds;


    private TaskScheduler() throws SchedulerException {
        scheduler = new StdSchedulerFactory().getScheduler();
    }

    public static TaskScheduler newTask() {
        try {
            return new TaskScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TaskScheduler taskClass(Class<? extends Job> taskClass) {
        this.taskClass = taskClass;
        return this;
    }

    public TaskScheduler taskId(String taskName, String taskGroupName) {
        this.taskName = taskName;
        this.taskGroupName = taskGroupName;
        return this;
    }

    public TaskScheduler triggerId(String triggerName, String triggerNameGroup) {
        this.triggerName = triggerName;
        this.triggerNameGroup = triggerNameGroup;
        return this;
    }

    public TaskScheduler intervalInSeconds(int seconds) {
        this.intervalInSeconds = seconds;
        return this;
    }

    public void start() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteJob(String taskName) {
        try {
            return scheduler.deleteJob(JobKey.jobKey(taskName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void build() {
        JobDetail jobDetail = JobBuilder.newJob(taskClass)
                .withIdentity(taskName, taskGroupName)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerNameGroup)
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(intervalInSeconds))
                .startNow()
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void test() {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob()
                    .withIdentity("job", "jobgroup")
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("ct", "ctg")
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3))
                    .startNow()
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
