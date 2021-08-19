/*
 * Copyright (c)  2021 George O'Malley (also known as "Gommeh" or "gomalley411") <gomalley411@gmail.com>.
 * All rights reserved except where prohibited by law. You, the user, are free to edit,
 * manipulate or change any part of the code herein, as long as credit is given and
 * this copyright statement is left intact at the top of every file in the code.
 * If you violate this copyright, that is, use my code without permission, I, George
 * O'Malley, reserve the right to take appropriate action.
 *
 */

package com.gommeh.commands.classes;

public class Command {
    String n, d, u, a;

    public Command(String name, String desc, String usage) {
        n = name;
        d = desc;
        u = usage;
        a = null;
    }

    public Command(String name, String desc, String usage, String aliases) {
        n = name;
        d = desc;
        u = usage;
        a = aliases;
    }

    public String getName() {
        return n;
    }

    public String getDesc() {
        return d;
    }

    public String getUsage() {
        return u;
    }

    public String getAliases() {
        return a;
    }
}
