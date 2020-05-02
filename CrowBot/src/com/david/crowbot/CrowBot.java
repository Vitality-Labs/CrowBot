package com.david.crowbot;
import javax.security.auth.login.LoginException;
import javax.swing.JFrame;

import com.david.crowbot.helpers.UiPane;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class CrowBot {

	public static JDA jda;
	public static String commandPrefix = "!";
	
	public static void main(String[] args) {
		try {
			jda = new JDABuilder(AccountType.BOT).setToken("").build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		jda.getPresence().setActivity(Activity.watching("chat"));
		
		jda.addEventListener(new CommandHandler());
		
		JFrame frame = new JFrame("Crowbot Terminal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 100);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(new UiPane(frame.getSize()));
		frame.setVisible(true);
	}
	
}
