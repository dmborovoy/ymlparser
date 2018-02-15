package com.dimas.ymlparser.config;

import com.dimas.ymlparser.model.Model;
import com.dimas.ymlparser.model.Offer;
import com.dimas.ymlparser.service.YmlListener;
import com.dimas.ymlparser.service.YmlReader;
import com.dimas.ymlparser.service.YmlWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;


@Configuration
@EnableBatchProcessing
@ComponentScan
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class Config {

    @Value("${ymlparser.filename}")
    private String fileName;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    YmlListener ymlListener;


    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .listener(ymlListener)
                .build();
    }

    @Bean
    public ItemWriter<Model> ymlWriter() {
        return new YmlWriter();
    }

    @Bean
    public ItemReader<Model> ymlReader() {
        return new YmlReader(fileName);
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Model, Model>chunk(1)
                .reader(ymlReader())
//                .processor(new YmlProcessor())
                .writer(ymlWriter())
                .build();
    }

}
