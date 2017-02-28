package cn.thc.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * Created by thc on 2016/12/23
 */
//http://www.yiibai.com/quartz/quartz-joblistener.html
public class CronTriggerExample {
    public static void main(String[] args) throws Exception {
        JobKey jobKey = new JobKey("dummyJobName", "group1");
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity(jobKey).build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1").withSchedule(
                CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
        ).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        //Listener attached to jobKey
        scheduler.getListenerManager().addJobListener(
                new HelloJobListener(), KeyMatcher.keyEquals(jobKey)
        );

        scheduler.start();
        scheduler.scheduleJob(job,trigger);
    }
}
