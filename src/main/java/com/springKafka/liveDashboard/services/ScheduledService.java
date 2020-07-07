package com.springKafka.liveDashboard.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledService {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledService.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @Autowired
	SimpMessagingTemplate template;
    
    
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
//        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    	int message = new Random().nextInt(80);
        logger.info("Push Message :: {}", message );
        
        template.convertAndSend("/topic/temperature", String.valueOf(message));
    }

}
