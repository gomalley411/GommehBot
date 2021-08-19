/*
 * Copyright (c)  2021 George O'Malley (also known as "Gommeh" or "gomalley411") <gomalley411@gmail.com>.
 * All rights reserved except where prohibited by law. You, the user, are free to edit,
 * manipulate or change any part of the code herein, as long as credit is given and
 * this copyright statement is left intact at the top of every file in the code.
 * If you violate this copyright, that is, use my code without permission, I, George
 * O'Malley, reserve the right to take appropriate action.
 *
 */

package com.gommeh.commands;

import com.gommeh.commands.classes.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;
import java.util.ArrayList;

public class HelpCmd extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {

        // This statement checks if the author of the message
        // sent is a bot, and if the author of the message is
        // a bot, it doesnt respond.
        if(event.getAuthor().isBot()) {
            return;
        }

        // Gets the message that triggered the event
        Message msg = event.getMessage();

        ArrayList<Command> cmds = new ArrayList<Command>();
        cmds.add(new Command("!copyright", "Gives information about this bot's copyright", "!copyright"));
        cmds.add(new Command("!flipcoin","Flips a coin", "!flipcoin"));
        cmds.add(new Command("!groot", "I am Groot!", "!groot"));
        cmds.add(new Command("!help", "Shows this help screen", "!help"));
        cmds.add(new Command("!ping", "Ping pong.", "!ping"));
        cmds.add(new Command("!randomnumber", "Generates a random number between the two desired integers", "!randomnumber <min> <max>","!rn, !rnum"));
        cmds.add(new Command("!report", "Reports a user who breaks the rules", "!report <name> <reason>"));
        cmds.add(new Command("!trivia","Gives you a so-called \"trivia tidbit\".", "!trivia"));


        if(msg.getContentRaw().equals("!help"))
        {
            MessageBuilder msgb = new MessageBuilder();
            MessageChannel c = event.getChannel();

            //event.getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessage("COMMAND LIST")).queue();
            EmbedBuilder eb = new EmbedBuilder()
                    .setTitle("Command list")
                    .setColor(0xFF0000)
                    .setAuthor("GommehBot", "https://r72.cooltext.com/rendered/cooltext391094246472438.png")
                    .setFooter("GommehBot")
                    .setTimestamp(Instant.now());
            for (Command cmd : cmds) {
                eb.addField(cmd.getName(), "Description: " + cmd.getDesc() + " - Usage: " + cmd.getUsage(), false);
                if (cmd.getAliases() != null) eb.addField("Aliases for " + cmd.getName(), cmd.getAliases(), false);
            }
            msgb.setEmbed(eb.build());
            event.getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessage(msgb.build())).queue();
            // Important to call .queue()
        }
    }
}