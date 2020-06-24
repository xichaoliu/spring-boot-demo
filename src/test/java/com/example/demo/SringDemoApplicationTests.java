package com.example.demo;

import java.util.concurrent.ExecutionException;

import com.example.demo.algorithm.MergeSort;
import com.example.demo.job.AsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SringDemoApplicationTests {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AsyncTask asyncTask;
	// @Test
	// public void contextLoads() throws InterruptedException,ExecutionException {
	// 	for(int i = 0; i< 100; i++) {
	// 		asyncTask.doTask1(i);
	// 	}
	// 	logger.info("All tasks finished.");
	// }
	@Test
	public void testAlgori() throws InterruptedException,ExecutionException {
		int[] a = {38,29,46,13,75,99,6,70};
		MergeSort merge = new MergeSort();
		merge.mergeSort(a, 0, a.length - 1);
        System.out.println("排序好的数组：");
        for (int e : a) {
			logger.info(e+"");
        }
	}
}
