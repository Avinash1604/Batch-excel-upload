package com.test.batch;

import com.test.model.LeadFile;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job readLeadFilesJob() {
        return jobBuilderFactory
                .get("readLeadFilesJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobCompletionListener())
                .start(step1())
                .next(sendEmail())
                .build();
    }

    private Step sendEmail() {
      return stepBuilderFactory
              .get("sendMail")
              .tasklet(new EmailTasklet())
              .build();
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("processExcelData").<LeadFile, LeadFile>chunk(5)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemWriter<? super LeadFile> writer() {
        return new ConsoleItemWriter<>();
    }

    @Bean
    public ItemReader<? extends LeadFile> reader() {
        return new ConsoleItemReader<>();
    }

    @Bean
    public JobExecutionListener jobCompletionListener() {
        return new JobCompletionListener();
    }
}
