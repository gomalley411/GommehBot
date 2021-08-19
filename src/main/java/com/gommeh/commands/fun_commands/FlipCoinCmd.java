/*
 * Copyright (c)  2021 George O'Malley (also known as "Gommeh" or "gomalley411") <gomalley411@gmail.com>.
 * All rights reserved except where prohibited by law. You, the user, are free to edit,
 * manipulate or change any part of the code herein, as long as credit is given and
 * this copyright statement is left intact at the top of every file in the code.
 * If you violate this copyright, that is, use my code without permission, I, George
 * O'Malley, reserve the right to take appropriate action.
 *
 */

package com.gommeh.commands.fun_commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class FlipCoinCmd extends ListenerAdapter {
    @Override
    public void onMessageReceived (MessageReceivedEvent event) {
        // This statement checks if the author of the message
        // sent is a bot, and if the author of the message is
        // a bot, it doesnt respond.
        if (event.getAuthor().isBot()) {
            return;
        }

        // Gets the message that triggered the event
        Message msg = event.getMessage();

        // Gets the channel the message was sent in
        MessageChannel c = event.getChannel();

        String[] s = msg.getContentRaw().split(" ");
        // c.sendMessage(s[0]).queue();
        // System.out.println(s[0]);

        // if the command is not !flipcoin
        if (!s[0].equals("!flipcoin")) {
            return;
        }
        else {
            int coin = 0;
            Random rand = new Random();
            coin = rand.nextInt(100);
            System.out.println(coin);
            if (coin % 2 == 0) c.sendMessage("Result of coin flip: HEADS").queue();
            else c.sendMessage("Result of coin flip: TAILS").queue();
        }
    }
}
