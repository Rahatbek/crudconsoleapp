package com.rahatbek.view;

public enum Message {

    LINE("--------------------------------"),
    ERROR_INPUT("Неправильный ввод. Повторите попытку!"),
    EMPTY_LIST("Список пуст"),
    SUCCESSFUL_OPERATION("Успешная операция"),
    ERROR_OPERATION("Ошибка!"),
    NAME("Введите имя:"),
    ID("Введите ID:"),
    NOT_FIND_ID("Нет ID = ");

    private final String message;

    Message(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
