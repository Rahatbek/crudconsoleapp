package com.rahatbek.view;

import com.rahatbek.repository.*;

import java.util.Scanner;

public class RunConsole {

    BaseView skillView;

    BaseView SpecialtyView;

    BaseView DeveloperView;

    private final String corruptedDateMsg = "Данные повреждены";

    private String menuList = 
            "Выберите действие:\n" +
            "1.Разработчики\n" +
            "2.Навыки\n" +
            "3.Специальность\n" +
            "4.выход";
    
    private Scanner sc = new Scanner(System.in);
    
    public RunConsole() {
        try {
            DeveloperRepository devRepo = new GsonDeveloperRepositoryImpl();
            SkillRepository skillRepo = new GsonSkillRepositoryImpl();
            SpecialtyRepository specRepo = new GsonSpecialtyRepositoryImpl();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    void run() {
//        boolean isExit = false;
//        while (true) {
//            System.out.println(Message.LINE.getMessage());
//            System.out.println(menuList);
//            System.out.println(Message.LINE.getMessage());
//            String response = sc.next();
//            switch (response) {
//                case "1":
//                    create();
//                    break;
//                case "2":
//                    edit();
//                    break;
//                case "3":
//                    delete();
//                    break;
//                case  "4":
//                    print();
//                    break;
//                case "5":
//                    isExit = true;
//                    break;
//                default:
//                    System.out.println(Message.ERROR_INPUT.getMessage());
//                    break;
//            }
//            if (isExit)
//                break;
//        }
//    }
}
