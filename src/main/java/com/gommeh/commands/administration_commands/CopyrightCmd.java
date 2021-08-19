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

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;

public class CopyrightCmd extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // This statement checks if the author of the message
        // sent is a bot, and if the author of the message is
        // a bot, it doesnt respond.
        if(event.getAuthor().isBot()) {
            return;
        }

        // This if statement checks if the message is in DMs and
        // ignores it if it is, if you dont want that to happen,
        // delete or modify this line to your needs
        if(event.isFromType(ChannelType.PRIVATE)) {
            return;
        }

        // Gets the message that triggered the event
        Message msg = event.getMessage();
        String copyright = "Copyright (c)  2021 George O'Malley (also known as \"Gommeh\" or \"gomalley411\")" +
                " <gomalley411@gmail.com>.\nAll rights reserved except where prohibited by law. You, the user, are free " +
                "to edit, manipulate or change any part of the code herein, as long as credit is given and this copyright " +
                "statement is left intact at the top of every file in the code. \nIf you violate this copyright, that is, " +
                "use my code without permission, I, George O'Malley, reserve the right to take appropriate action.";

        if(msg.getContentRaw().equals("!copyright"))
        // msg.getContentRaw() gets the message's content
        // with the markdown and hidden characters, even the
        // \ if it happened to be after a symbol, in which
        // Discord makes it disappear.
        {
            MessageChannel channel = event.getChannel();

            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(0xFF0000)
                    .setDescription(copyright)
                    .setAuthor("GommehBot", "https://r72.cooltext.com/rendered/cooltext391094246472438.png")
                    .setFooter("GommehBot")
                    .setTimestamp(Instant.now());


            MessageBuilder msgb = new MessageBuilder();
            msgb.setEmbed(eb.build());
            MessageChannel c = event.getChannel();
            c.sendMessage(msgb.build()).queue();

            // Important to call .queue()
        }
    }

}
