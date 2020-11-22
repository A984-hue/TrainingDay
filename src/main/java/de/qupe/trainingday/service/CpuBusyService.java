package de.qupe.trainingday.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CpuBusyService {
	
	private Random random;
	
	public CpuBusyService() {
		this.random = new Random();
	}

	public void busyForSeconds(int seconds) {
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = start.plusSeconds(seconds);
		while(end.isAfter(LocalDateTime.now()));
	}
	
	public void busyForSecondsAsync(int seconds) {
		new Thread(() -> this.busyForSeconds(seconds)).start();
	}

	public void busyForRandomTime(int maxSeconds) {
		this.busyForSeconds(this.random.nextInt(maxSeconds));
	}

	public void busyForRandomTimeAsync(int maxSeconds) {
		this.busyForSecondsAsync(this.random.nextInt(maxSeconds));
	}
}