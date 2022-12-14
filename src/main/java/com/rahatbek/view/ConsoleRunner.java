package com.rahatbek.view;

import com.rahatbek.controller.DeveloperController;
import com.rahatbek.controller.SkillController;
import com.rahatbek.controller.SpecialtyController;
import com.rahatbek.model.Message;
import com.rahatbek.repository.DeveloperRepository;
import com.rahatbek.repository.SkillRepository;
import com.rahatbek.repository.SpecialtyRepository;
import com.rahatbek.repository.gson.GsonDeveloperRepositoryImpl;
import com.rahatbek.repository.gson.GsonSkillRepositoryImpl;
import com.rahatbek.repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.Scanner;

public class ConsoleRunner {

    BaseView skillView;
    BaseView specialtyView;
    BaseView developerView;

    private final String damagedDataMessage = "Данные повреждены!";

    private final String menuMessage = "Выберете действие:\n" +
            "1. Навыки\n" +
            "2. Специальности\n" +
            "3. Разработчики\n" +
            "4. Выход";

    private Scanner sc = new Scanner(System.in);

    public ConsoleRunner() {

        SkillRepository skillRepository = new GsonSkillRepositoryImpl();
        SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();
        DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

        SkillController skillController = new SkillController(skillRepository);
        SpecialtyController specialtyController = new SpecialtyController(specialtyRepository);
        DeveloperController developerController = new DeveloperController(developerRepository);

        skillView = new SkillView(skillController, sc);
        specialtyView = new SpecialtyView(specialtyController, sc);
        developerView = new DeveloperView(developerController, skillController, specialtyController, sc);

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
                    specialtyView.show();
                    break;
                case "3":
                    developerView.show();
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
