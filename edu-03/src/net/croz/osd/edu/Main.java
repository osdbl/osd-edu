package net.croz.osd.edu;

import java.util.Scanner;

import net.croz.osd.edu.conf.AppConfig;
import net.croz.osd.edu.conf.SecurityConfig;
import net.croz.osd.edu.services.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static ApplicationContext ctx = 
		new AnnotationConfigApplicationContext(AppConfig.class, SecurityConfig.class);
	
	@Autowired
	private Service service;
	
	public static void main(String[] args) {
		Main main = ctx.getBean(Main.class);
		main.doAuthenticate();
		main.doHello();
		main.encodePassword();
	}
	
	private void doAuthenticate() {
		boolean isAuthenticated = false;
		while (!isAuthenticated) {
			System.out.print("Enter username:");
			String username = scanner.next();
			System.out.print("Enter password:");
			String password = scanner.next();
			isAuthenticated = service.doAuthenticate(username, password);
		}
	}
	
	private void doHello() {
		service.helloUser();
	}
	
	private void encodePassword() {
		while (true) {
			System.out.print("Enter raw password or quit [q]:");
			String rawPassword = scanner.next();
			if ("q".equalsIgnoreCase(rawPassword)) System.exit(0);
			try {
				System.out.println("Encoded password:" + service.encode(rawPassword));
			}
			catch (AccessDeniedException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
}
