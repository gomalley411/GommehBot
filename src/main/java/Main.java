/*
 * Copyright (c)  2021 George O'Malley (also known as "Gommeh" or "gomalley411") <gomalley411@gmail.com>.
 * All rights reserved except where prohibited by law. You, the user, are free to edit,
 * manipulate or change any part of the code herein, as long as credit is given and
 * this copyright statement is left intact at the top of every file in the code.
 * If you violate this copyright, that is, use my code without permission, I, George
 * O'Malley, reserve the right to take appropriate action.
 *
 */

import com.gommeh.commands.*;
import com.gommeh.commands.administration_commands.CopyrightCmd;
import com.gommeh.commands.administration_commands.ReportCmd;
import com.gommeh.commands.fun_commands.FlipCoinCmd;
import com.gommeh.commands.fun_commands.GrootCmd;
import com.gommeh.commands.fun_commands.PingCmd;
import com.gommeh.commands.fun_commands.TriviaCmd;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

// PUT YOUR TOKEN WHERE IT SAYS "TOKEN"

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault("TOKEN")
                .setActivity(Activity.playing("mind games"))
                .addEventListeners(new PingCmd())
                .addEventListeners(new ReportCmd())
                .addEventListeners(new HelpCmd())
                .addEventListeners(new RandomNumCmd())
                .addEventListeners(new GrootCmd())
                .addEventListeners(new FlipCoinCmd())
                .addEventListeners(new TriviaCmd())
                .addEventListeners(new CopyrightCmd())
                .build();

        jda.awaitReady(); // blocks the bot until the JDA is finally 100% loaded
    }
}