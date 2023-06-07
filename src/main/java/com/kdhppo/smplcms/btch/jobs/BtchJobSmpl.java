package com.kdhppo.smplcms.btch.jobs;

import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 배치 잡 관련
 */
@Component
public class BtchJobSmpl extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(BtchJobSmpl.class);

	public BtchJobSmpl() {}

	public void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("배치 잡 시작 ~~~~~~~~~~");

		try {
			Map<String,Object> jdata = context.getJobDetail().getJobDataMap();
			logger.info("데이터 : "+jdata.get("kkk"));
			logger.info("10초마다 Job 실행");
		} catch(Exception ex2) {
			//ex2.printStackTrace();
			logger.info("배치 잡 오류 : "+ex2.getMessage());
		}

		logger.info("배치 잡 종료 ~~~~~~~~~~");
	}

}