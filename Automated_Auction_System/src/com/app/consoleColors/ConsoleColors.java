package com.app.consoleColors;

public class ConsoleColors {

    // ANSI escape codes-

    public static final String RESET = "\033[0m"; // Text Reset

    // Colors
    public static final String GREEN = "\033[0;32m";
    public static final String DARK_BLUE = "\033[38;2;72;0;255m";
    public static final String ORANGE = "\033[38;2;225;153;0m";

    // Bold
    public static final String RED_BOLD = "\033[1;31m"; // RED
    public static final String GREEN_BOLD = "\033[1;32m"; // GREEN
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE

    // Background
    public static final String CYAN_BACKGROUND = "\033[46m"; // CYAN
    public static final String ROSY_PINK_BACKGROUND = "\033[48;2;255;0;162m"; // ROSY PINK
    public static final String RED_BACKGROUND = "\033[41m"; // RED
    public static final String GREEN_BACKGROUND = "\033[42m"; // GREEN

}