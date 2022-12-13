package com.rahatbek.view;

import com.rahatbek.controller.SkillController;
import com.rahatbek.model.Message;
import com.rahatbek.repository.GenericRepository;
import com.rahatbek.repository.SkillRepository;
import com.rahatbek.repository.gson.GsonSkillRepositoryImpl;

import java.util.Scanner;

public class ConsoleRunner {

    BaseView skillView;;

    private final String damagedDataMessage = "Данные повреждены!";

    private final String menuMessage = "Выберете действие:\n" +
            "1. Навыки\n" +
            "2. Специальности\n" +
            "3. Разработчики\n" +
            "4. Выход";

    private Scanner sc = new Scanner(System.in);

    public ConsoleRunner() {

        SkillRepository skillRepository = new GsonSkillRepositoryImpl();

        SkillController skillController = new SkillController(skillRepository);

        skillView = new SkillView(skillController, sc);
    }

    public void run() {
        boolean isExit = false;

        while (true) {
            System.out.println(Message.LINE.getMessage());
            System.out.println(menuMessage);
            System.out.println(Message.LINE.getMessage());
            String response = sc.next();

            switch (response) {
                case "1":
                    skillView.show();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
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
