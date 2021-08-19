/*
 * Copyright (c)  2021 George O'Malley (also known as "Gommeh" or "gomalley411") <gomalley411@gmail.com>.
 * All rights reserved except where prohibited by law. You, the user, are free to edit,
 * manipulate or change any part of the code herein, as long as credit is given and
 * this copyright statement is left intact at the top of every file in the code.
 * If you violate this copyright, that is, use my code without permission, I, George
 * O'Malley, reserve the right to take appropriate action.
 *
 */

package com.gommeh.commands.administration_commands;

import com.gommeh.commands.classes.DateAndTime;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.*;

public class ReportCmd extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // This statement checks if the author of the message
        // sent is a bot, and if the author of the message is
        // a bot, it doesnt respond.
        if (event.getAuthor().isBot()) {
            return;
        }
        String[] s = event.getMessage().getContentRaw().split(" ");
        if (s[0].equals("!rn") || s[0].equals("!randomnumber") || s[0].equals("!rnum") || s[0].charAt(0) != '!') {
            return;
        }
        else if (!s[0].equals("!report")) return;
        //System.out.println(s[0]);
        String user = s[1];
        //System.out.println(s[1]);
        String rule = s[2];
        for (int i = 3; i < s.length; i++) {
            rule += " " + s[i];
        }
        //System.out.println(s[2]);

        // sets up the embed with all the required information
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(0xFF00FF)
                .setTitle("Report")
                .setColor(0xFF0000)
                .addField("Reported by", event.getAuthor().getAsMention(), false)
                .addField("User being reported", s[1], false)
                .addField("Reason", rule, false)
                .setAuthor("GommehBot", "https://r72.cooltext.com/rendered/cooltext391094246472438.png")
                .setFooter("GommehBot")
                .setTimestamp(Instant.now());

        if (s[0] == "!rn" || s[0] == "!randomnumber" || s[0] == "!rnum")
            return;

        // sends message to the reports channel on discord
        MessageBuilder msg = new MessageBuilder();
        msg.setEmbed(eb.build());
        MessageChannel c = event.getChannel();

        // this is where you change which channel your reports go so your staff can look at them
        TextChannel tc = event.getGuild().getTextChannelsByName("reports-go-here", true).get(0);
        if (s[0].equals("!report")) {

            DateAndTime dat = new DateAndTime();
            tc.sendMessage(msg.build()).queue();
            c.sendMessage(
                    "Report issued :)\nThe reporter has been sent at " +
                     "copy of the report for their convenience.").queue();
            event.getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessage(
                    "This is your copy of the server report you issued on " + dat.getDateStr() +  ". Please maintain it" +
                            " for future reference. Your report is important to us and will be reviewed by server staff." +
                            "\nThank you!")).queue(); // text of DM
            event.getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessage(msg.build())).queue();
        }
        else if (!s[0].equals("!rn") && !s[0].equals("!rnum") && !s[0].equals("!randomnumber")) {
            c.sendMessage("Improper use of the !report command. Usage: !report <name> <reason>").queue();
        }

    }

}
