package com.rahatbek.view;

import com.rahatbek.model.Message;

import java.util.Scanner;

public abstract class BaseView {

    protected String message;

    protected Scanner sc;

    abstract void create();

    abstract void update();

    abstract void delete();

    abstract void print();

    abstract void getById();
    protected boolean isExit = false;

    void show() {


        while (true) {
            print();
            System.out.println(Message.LINE.getMessage());
            System.out.println(message);
            System.out.println(Message.LINE.getMessage());
            String response = sc.next();
            switch (response) {
                case "1":
                    create();
                    break;
                case "2":
                    update();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    print();
                    break;
                case "5":
                    getById();
                    break;
                case "6":
                    isExit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }
            if (isExit)
                break;
        }
    }
}
