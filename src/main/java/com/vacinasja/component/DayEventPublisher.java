package com.vacinasja.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vacinasja.event.passou_dia.PassouDiaEvent;

@Component
@EnableScheduling
public class DayEventPublisher {
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
	
	@Scheduled(cron = "0 0 0 * * ?", zone = TIME_ZONE)
	public void passouDia() {
		applicationEventPublisher.publishEvent(new PassouDiaEvent(this));
	}
}
