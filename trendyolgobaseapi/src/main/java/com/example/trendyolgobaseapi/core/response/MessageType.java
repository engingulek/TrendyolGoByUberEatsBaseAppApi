package com.example.trendyolgobaseapi.core.response;


public enum MessageType {
    EXIST_ID("User already exists"),
    USER_CREATED("User successfully created"),
    SUCCESS_FETCH("Data fetched successfully"),
    DATA_ADD_TO_DATABASE_SUCCESS("Data added to database successfully"),
    DATA_NOT_ADD_TO_DATABASE("Failed to add data to database"),
    UUI_ALREADY_EXISTS("UUID already exists in the database"),
    DATA_FEtCHED_SUCCESSFULLY("Data fetched successfully");

    private final String text;

    MessageType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}