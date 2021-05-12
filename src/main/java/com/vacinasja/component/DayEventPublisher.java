package com.vacinasja.component;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DayEventPublisher {
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	@Scheduled(cron = "0 0 0 * * ?", zone = TIME_ZONE)
	public void passouDia() {
		System.out.println("passou um diaaa");
	}
}
