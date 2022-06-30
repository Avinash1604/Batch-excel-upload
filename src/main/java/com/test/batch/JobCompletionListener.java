package com.test.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionListener extends JobExecutionListenerSupport {
    Logger logger = LoggerFactory.getLogger(JobCompletionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("BEFORE BATCH JOB STARTS");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("BATCH JOB COMPLETED SUCCESSFULLY");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            logger.info("BATCH JOB FAILED");
        }
    }

}