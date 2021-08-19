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

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class RandomNumCmd extends ListenerAdapter {
    private int ans = 0;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        ans = 0;

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
        int min, max;
        try {
            min = Integer.parseInt(s[1]);
            max = Integer.parseInt(s[2]);
        }
        catch (Exception e) {
            return;
        }
        // c.sendMessage("min: " + min + "\tmax:" + max).queue();

        // if min = max
        if (min == max) {
            c.sendMessage("The two numbers you put in must be different.").queue();
            return;
        }
        if (min > max) {
            c.sendMessage("Max is greater than minimum. Switching...").queue();
            int temp = min;
            min = max;
            max = temp;
        }

        if (s[0].equals("!rn") || s[0].equals("!rnum") || s[0].equals("!randomnumber")) {
            c.sendMessage("Generating...").queue();
            Random r = new Random();
            ans = r.nextInt(max - min + 1) + min;
            c.sendMessage("A random number between " + min + " and " + max + " is " + ans + ".").queue();
        }
        else {
            c.sendMessage("Improper use of the random number command. Usage: !rn <min> <max>").queue();
        }
    }
    private int getAns() {
        return ans;
    }
}