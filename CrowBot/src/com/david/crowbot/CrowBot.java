package com.david.crowbot;

import javax.security.auth.login.LoginException;

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
			jda = new JDABuilder(AccountType.BOT).setToken("NzA1ODk3NTIzMDYzMjkxOTc1.XqyY-Q.uoXZ9-2F0D3AWuqELthDv36ggDo").build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		jda.getPresence().setActivity(Activity.watching("chat"));
		
		jda.addEventListener(new CommandHandler());
	}
	
}
