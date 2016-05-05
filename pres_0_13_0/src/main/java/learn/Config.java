package learn;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class Config {
    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public JobRepository jobRepository() {
        return 
            new SimpleJobRepository(
                new MapJobInstanceDao(), 
                new MapJobExecutionDao(), 
                new MapStepExecutionDao(), 
                new MapExecutionContextDao()
            );
    }
    
    @Bean
    public JobLauncher jobLauncher() {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository());
        
        return simpleJobLauncher;
    }
    
    @Bean
    public Job simpleJob() {
        return
            jobBuilderFactory
                .get("job1")
                .incrementer(new RunIdIncrementer())
                .start(echoLineStep())
                .build();
    }
    
    @Bean
    public Step echoLineStep() {
        return
            stepBuilderFactory
                .get("step1")
                .tasklet(
                    new Tasklet() {
                        @Override
                        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                            LoggerFactory.getLogger("echoLineTasklet").info("Hello from tasklet");
                            
                            LoggerFactory
                                    .getLogger(
                                            "echoLineTasklet"
                                    )
                                    .info(
                                        "Parameters: {}", 
                                        chunkContext
                                            .getStepContext()
                                            .getJobParameters()
                                    );
                            
                            return RepeatStatus.FINISHED;
                        }
                    }
                )
                .build();
    }
}
