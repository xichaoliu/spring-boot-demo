package com.example.demo;

import java.util.concurrent.ExecutionException;

import com.example.demo.job.AsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SringDemoApplicationTests {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AsyncTask asyncTask;
	@Test
	public void contextLoads() throws InterruptedException,ExecutionException {
		for(int i = 0; i< 100; i++) {
			asyncTask.doTask1(i);
		}
		logger.info("All tasks finished.");
	}
}
