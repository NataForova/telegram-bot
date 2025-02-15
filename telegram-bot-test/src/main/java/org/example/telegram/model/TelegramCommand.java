package org.example.telegram.model;

import lombok.Getter;

public enum TelegramCommand {
    START("/start"),
    SIGN_UP("/signup"),
    LOGIN("/login"),
    LOG_OUT("/logout"),
    ADD_WORD("/add-word"),
    FIND_TRANSLATION("/find-translation"),
    START_TRAINING("/start-training"),
    STOP_TRAINING("/stop-training"),
    GET_RANDOM_WORD("/get-random-word");

    private final String command;

    TelegramCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
