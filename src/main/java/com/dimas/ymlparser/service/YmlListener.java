package com.dimas.ymlparser.service;

import com.dimas.ymlparser.dao.VariationRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class YmlListener implements JobExecutionListener {

    @Autowired
    private VariationRepository repository;
//    private Job job;
//    private JobLauncher jobLauncher;
//
//    public YmlListener(final Job job, final JobLauncher jobLauncher) {
//        this.job = job;
//        this.jobLauncher = jobLauncher;
//    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("___Strarting job {}");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job is completed job");
//        repository.findAll().forEach(e->log.info(e));
//        repository.findAll().forEach(e -> System.out.println(e));
        repository.findAll().forEach(e -> log.info("{}", e));
//        CompletableFuture.runAsync(()->{
//            try {
//                jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
//            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
//                    | JobParametersInvalidException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        });
    }

}