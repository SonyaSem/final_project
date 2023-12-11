package ru.egar.enums;

public enum DocumentType {

    HIRING("найм"),
    TRANSFER("перевод"),
    DISMISSAL("увольнение");

    private final String type;

    private DocumentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
