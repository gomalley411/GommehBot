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

import com.gommeh.commands.classes.Trivia;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.*;

public class TriviaCmd extends ListenerAdapter {
    EmbedBuilder eb;
    String embedFact = "", embedDetails = "", embedSource = "";
    MessageChannel c;
    private int lines = 0;
    Scanner sc;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // This statement checks if the author of the message
        // sent is a bot, and if the author of the message is
        // a bot, it doesnt respond.
        if (event.getAuthor().isBot()) {
            return;
        }

        // Gets the message that triggered the event
        Message msg = event.getMessage();

        // Gets the channel the message was sent in
        c = event.getChannel();

        String[] s = msg.getContentRaw().split(" ");
        // c.sendMessage(s[0]).queue();
        //System.out.println(s[0]);


        // if the command does not start with !trivia
        if (!s[0].equals("!trivia")) {
            return;
        }
        else {  // if it does
            System.out.println(s[0] + "\t" + s.length);
            try {
                Scanner sc = new Scanner(new File ("C:\\Users\\gomal\\IdeaProjects\\gommehbot\\src\\main\\java\\trivia.txt"));
                if (s.length == 1) {
                    int fNum = getRand();
                    System.out.println("Random int is " + fNum);
                    getFactNumber(fNum);
                }
                else if (s.length == 2) {
                    getFactNumber(Integer.parseInt(s[1]));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ;
        }
    }

    /**
     * gets a random number between 0 and the number of lines in trivia file
     * @return
     */
    private int getRand() {
        System.out.println("in getRand()");
        try {
            Scanner sc = new Scanner(new File ("C:\\Users\\gomal\\IdeaProjects\\gommehbot\\src\\main\\java\\trivia.txt"));
            this.sc = sc;
        }
        catch (FileNotFoundException e) {
            return 0;
        }
        int lines = 0;
        while (sc.hasNextLine()) {
            System.out.println("in while loop");
            sc.nextLine();
            lines++;
        }
        // choose random number
        Random r = new Random();
        int g = r.nextInt(lines);
        return g;
    }

    /**
     * gets a random fact from trivia file
     */
    private void getRandFact() {
        getFactNumber(getRand());
    }

    /**
     * takes a fact number and retrieves the corresponding trivia info
     * @param num
     */
    private void getFactNumber(int num) {
        System.out.println("in getFactNumber(" + num + ")");
        try {
            eb = new EmbedBuilder();
            int lines = 0; // number of lines contained in trivia file

            // facts array stores the trivia

            ArrayList<Trivia> facts = new ArrayList<Trivia>(100);

            Scanner sc = new Scanner(new File ("C:\\Users\\gomal\\IdeaProjects\\gommehbot\\src\\main\\java\\trivia.txt"));
            facts.ensureCapacity(25);

            // read in trivia
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] temp = line.split(";");
                Trivia myTrivia = new Trivia(temp[0], temp[1], temp[2]);
                facts.add(myTrivia);
                System.out.println("Put in trivia--" + myTrivia.getFact() + "\n" + myTrivia.getDetails() + "\nSource:\t" + myTrivia.getSource());
                lines++;
                System.out.println("Added line " + lines);
                myTrivia = null;
            }

            // choose number
            Trivia t = facts.get(num);
            embedFact = t.getFact();
            embedDetails = t.getDetails();
            embedSource = t.getSource();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // sends message to the reports channel on discord
        MessageBuilder msgb = new MessageBuilder();

        System.out.println("about to send");
        eb.setColor(0xFF00FF)
                .setTitle("Trivia -- " + embedFact)
                .setColor(0xFF0000)
                .addField("More Info",embedDetails + "\n\nSource: " + embedSource,false)
                .setAuthor("GommehBot", "https://r72.cooltext.com/rendered/cooltext391094246472438.png")
                .setFooter("GommehBot")
                .setTimestamp(Instant.now());
        msgb.setEmbed(eb.build());
        c.sendMessage(msgb.build()).queue(); // sends message
    }

}
