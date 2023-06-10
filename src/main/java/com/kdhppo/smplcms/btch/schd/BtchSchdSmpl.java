package com.kdhppo.smplcms.btch.schd;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.kdhppo.smplcms.btch.jobs.BtchJobSmpl;

import jakarta.annotation.PostConstruct;

/**
 * 배치 스케줄 트리거 관련
 */
//@Configuration
public class BtchSchdSmpl extends BtchSchdBase {

	@Autowired
	private Scheduler scheduler; // 쿼츠 스케줄 객체

	/** 크론 실행 주기 설정 */
	private final String cronSchd = "0/10 * * * * ?";

	@PostConstruct
	public void run(){
		Map<String,Object> jdata = new HashMap<String,Object>();
		//서비스 객체 등, 잡 클래스에서는 자동 주입을 못받으니, 여기서 받아서, 맵에다가 넘겨서,
		//잡에서 맵을 읽어서, 해당 필요한 서비스 객체 등을 받아서 셋팅해서 사용하면 됨.
		jdata.put("kkk", "bbb");
		JobDetail detail = runJobDetail(BtchJobSmpl.class, jdata);

		try {
			// 크론형식 지정 후 스케줄 시작
			scheduler.scheduleJob(detail, runJobTrigger(cronSchd));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
