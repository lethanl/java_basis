package cn.thc.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by thc on 2016/12/23
 */
//http://www.yiibai.com/quartz/quartz-helloworld.html
public class SimpleTriggerExample {
    public static void main(String[] args) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("dummyJobName", "group1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1").withSchedule(
                //SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()
                CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
        ).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
