package com.kdhppo.smplcms.btch.schd;

import static org.quartz.JobBuilder.newJob;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 * 배치 스케줄 클래스 공통 메서드 등 클래스 확장용.
 */
public class BtchSchdBase {

	public Trigger runJobTrigger(String scheduleExp){
		// 크론 스케줄 사용
		return TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
	}

	public JobDetail runJobDetail(Class<? extends Job> job, Map<String,Object> params){
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.putAll(params);
		// 스케줄 생성
		return newJob(job).usingJobData(jobDataMap).build();
	}

}