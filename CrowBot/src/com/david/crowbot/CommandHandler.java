package com.david.crowbot;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import com.david.crowbot.helpers.Logic;
import com.david.crowbot.helpers.ReactionHandler;
import com.david.crowbot.helpers.ReactionListener;
import com.david.crowbot.polls.PollType;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandHandler extends ListenerAdapter {

	private String[] commands = { "help", "poll"};

	private String[] commandArgs = { " ", " [Type] [Type Arguments]"};
	
	private String[] letters = {"a", "b", "c", "d"};
	
	int size;
	
	public ReactionHandler reactionHandler = new ReactionHandler();

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(",");
		int commandNum = -1;
		
		for (int i = 0; i < commands.length; i++) {
			if (args[0].equalsIgnoreCase(CrowBot.commandPrefix + commands[i])) {
				commandNum = i;
			}
		}

		System.out.println(commandNum);

		event.getChannel().sendTyping();

		switch (commandNum) {
		
		case (0):
			runHelpCommand(event.getChannel());
			break;
			
		case (1):

			if (args.length >= 5) {
				if (args[1].trim().equalsIgnoreCase(PollType.ListPoll)) {
					createListPoll(event, args);
				} else if (args[1].trim().equalsIgnoreCase(PollType.ConfirmPoll)) {
					createConfirmPoll(event, args);
				} else {
					event.getChannel().sendMessage(
							"Poll type unknown. Use " + PollType.ConfirmPoll + " or " + PollType.ListPoll + ".").queue();;
				}
			} else {
				event.getChannel().sendMessage("Command arguments are incorrect!").queue();;
			}
			
			break;
			
		}

	}

	private void runHelpCommand(TextChannel channel) {
		String output = "Available Commands Are: \n";

		for (int i = 0; i < commands.length; i++) {
			output += "\t- " + commands[i] + " " + commandArgs[i] + "\n";
		}

		channel.sendMessage(output).queue();
	}

	private void createListPoll(GuildMessageReceivedEvent event, String[] args) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setColor(Logic.getRandomColor());
		embedBuilder.setTitle(args[2].trim());
		size = 0;
		
		for (int i = 3; i < args.length; i++) {
			embedBuilder.addField(letters[i-3], args[i].trim(), false);
			size++;
		}
		
		embedBuilder.addField("", "Pick below!", false);
		embedBuilder.setFooter("Poll created by " + event.getAuthor().getAsTag());
		
		event.getChannel().sendMessage(embedBuilder.build()).queue((msg) -> {
			
			ReactionListener<String> handler = new ReactionListener<>(event.getGuild().getIdLong(), msg.getId());
			handler.setExpiresIn(TimeUnit.MINUTES, 1);
			
			switch (size) {
				
			case (2):
				handler.registerReaction("🇦", (ret) -> foo());
				handler.registerReaction("🅱️", (ret) -> foo());
				break;
			case (3):
				handler.registerReaction("🇦", (ret) -> foo());
				handler.registerReaction("🅱️", (ret) -> foo());
				handler.registerReaction("🇨", (ret) -> foo());
				break;
			case (4):
				handler.registerReaction("🇦", (ret) -> foo());
				handler.registerReaction("🅱️", (ret) -> foo());
				handler.registerReaction("🇨", (ret) -> foo());
				handler.registerReaction("🇩", (ret) -> foo());
				break;
			}
			
			reactionHandler.addReactionListener(event.getGuild().getIdLong(), msg, handler);
			
		});
	}

	private void createConfirmPoll(GuildMessageReceivedEvent event, String[] args) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setColor(Logic.getRandomColor());
		embedBuilder.setTitle(args[2].trim());
		embedBuilder.addField("Date: ", args[3].trim(), true);
		embedBuilder.addField("Time: ", args[4].trim(), true);
		embedBuilder.addField("Place: ", args[5].trim(), true);
		embedBuilder.addField("", "Will you be there?", false);
		embedBuilder.setFooter("Poll created by " + event.getAuthor().getAsTag());
		
		event.getChannel().sendMessage(embedBuilder.build()).queue((msg) -> {
			
			ReactionListener<String> handler = new ReactionListener<>(event.getGuild().getIdLong(), msg.getId());
			handler.setExpiresIn(TimeUnit.MINUTES, 1);
			handler.registerReaction("✅", (ret) -> foo());
			handler.registerReaction("❌",  (ret) -> bar());
			
			reactionHandler.addReactionListener(event.getGuild().getIdLong(), msg, handler);
		});
	}

	private Object bar() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object foo() {
		// TODO Auto-generated method stub
		return null;
	}

}
