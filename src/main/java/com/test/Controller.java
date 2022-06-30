package com.test;

import com.test.model.JobTypeEnum;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@RestController
@EnableAsync
public class Controller {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    ListableJobLocator jobLocator1;

    @Autowired
    Job job;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JobLocator jobLocator;


    @GetMapping("/greeting")
    public CompletableFuture<String> greeting() throws JobInstanceAlreadyCompleteException, NoSuchJobException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        return this.runBatch("test");
    }

    @Async
    private CompletableFuture<String> runBatch(String test) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, NoSuchJobException {
        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("before"+test);
            Job job = (Job) context.getBean(JobTypeEnum.LEAD_JOB.label);
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(job, params);
            System.out.println("after"+test);
            return null;
        });
        return CompletableFuture.completedFuture("completes");
    }

}
