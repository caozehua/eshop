package com.unicom.test.microservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {

	private static final Logger LOG = LoggerFactory.getLogger(TestSchedule.class);

	@Scheduled(fixedRate = 30*1000, initialDelay = 0*1000)
	public void testTask1() {
		LOG.trace("task trace测试");
	}

	@Scheduled(fixedRate = 30*1000, initialDelay = 5*1000)
	public void testTask2() {
		LOG.debug("task debug测试");
	}

	@Scheduled(fixedRate = 30*1000, initialDelay = 10*1000)
	public void testTask3() {
		LOG.info("task info测试");
	}

	@Scheduled(fixedRate = 30*1000, initialDelay = 15*1000)
	public void testTask4() {
		LOG.warn("task warn测试");
	}

	@Scheduled(fixedRate = 30*1000, initialDelay = 20*1000)
	public void testTask5() {
		LOG.error("task error测试");
	}
}
