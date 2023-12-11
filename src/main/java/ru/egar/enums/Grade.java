package ru.egar.enums;

public enum Grade {
    GOOD("хорошо"),
    OK("нормально"),
    BAD("плохо");

    public final String result;

    Grade(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
