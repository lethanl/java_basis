package cn.thc.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by thc on 2016/12/23
 */
//http://www.yiibai.com/quartz/run-multiple-jobs.html
public class CronTriggerExample2 {
    public static void main(String[] args) throws SchedulerException {
        JobKey jobKeyA = new JobKey("jobA", "group1");
        JobDetail jobA = JobBuilder.newJob(JobA.class).withIdentity(jobKeyA).build();

        JobKey jobKeyB = new JobKey("jobB", "group1");
        JobDetail jobB = JobBuilder.newJob(JobB.class).withIdentity(jobKeyB).build();

        JobKey jobKeyC = new JobKey("jobC", "group1");
        JobDetail jobC = JobBuilder.newJob(JobC.class).withIdentity(jobKeyC).build();

        Trigger trigger1 = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName1", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                ).build();

        Trigger trigger2 = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName2", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                ).build();

        Trigger trigger3 = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName3", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                ).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();
        scheduler.scheduleJob(jobA,trigger1);
        scheduler.scheduleJob(jobB,trigger2);
        scheduler.scheduleJob(jobC,trigger3);
    }
}
