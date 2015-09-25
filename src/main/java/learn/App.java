package learn;

import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static final Logger logger = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) {
        logger.info("Application started");
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        
        Job job = ctx.getBean("simpleJob", Job.class);
        
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        
        jobParametersBuilder.addString("param1", "param1 value");
        
        try {
            jobLauncher.run(job, jobParametersBuilder.toJobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            logger.error("Already running task",e);
        } catch (JobRestartException e) {
            logger.error("Restart task",e);
        } catch (JobInstanceAlreadyCompleteException e) {
            logger.error("Already completed task",e);
        } catch (JobParametersInvalidException e) {
            logger.error("Param invalid",e);
        }
    }
}
