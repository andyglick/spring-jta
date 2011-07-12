package org.springsource.examples.sawt.services.integration;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

/**
 * Simple transformer to take a file detected by the inbound {@link java.io.File file} adapter and transform it to a
 * {@link org.springframework.batch.integration.launch.JobLaunchRequest job launch request}.
 */
@Component
public class FileToJobRequestTransformer {

    @Autowired
    @Qualifier("importData")
    private Job job;

    @Transformer
    public JobLaunchRequest fromFile( @Headers Map map,  @Payload File file) throws Exception {

        JobParameters parms = new JobParametersBuilder()
                .addDate("date", new Date())
                .addString("input.file", "file:///" + file.getAbsolutePath())
                .toJobParameters();

        return new JobLaunchRequest(this.job, parms);

    }
}