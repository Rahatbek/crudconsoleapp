package com.rahatbek.view;

import com.rahatbek.controller.DeveloperController;
import com.rahatbek.controller.SkillController;
import com.rahatbek.controller.SpecialtyController;
import com.rahatbek.repository.*;
import com.rahatbek.repository.implrepo.GsonDeveloperRepositoryImpl;
import com.rahatbek.repository.implrepo.GsonSkillRepositoryImpl;
import com.rahatbek.repository.implrepo.GsonSpecialtyRepositoryImpl;
import com.rahatbek.service.DeveloperService;
import com.rahatbek.service.SkillService;
import com.rahatbek.service.SpecialtyService;
import com.rahatbek.service.impl.DeveloperServiceImpl;
import com.rahatbek.service.impl.SkillServiceImpl;
import com.rahatbek.service.impl.SpecialtyServiceImpl;

import java.util.Scanner;

public class RunConsole {

    BaseView skillView;

    BaseView specialtyView;

    BaseView developerView;

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
            SkillRepository skillRepository = new GsonSkillRepositoryImpl();
            SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();
            DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl(specialtyRepository, skillRepository);

            SkillService skillService = new SkillServiceImpl(skillRepository, developerRepository);
            SpecialtyService specialtyService = new SpecialtyServiceImpl(specialtyRepository, developerRepository);
            DeveloperService developerService = new DeveloperServiceImpl(developerRepository);

            DeveloperController developerController = new DeveloperController(developerService);
            SkillController skillController = new SkillController(skillService);
            SpecialtyController specialtyController = new SpecialtyController(specialtyService);

            specialtyView = new SpecialtyViewImpl(specialtyController, sc);
            skillView = new SkillViewImpl(skillController, sc);
            developerView = new DeveloperViewImpl(developerController, sc, specialtyView, skillView);

        } catch (Exception e) {
            System.out.println(corruptedDateMsg);
        }
    }

    public void run() {
        boolean isExit = false;
        while (true) {
            System.out.println(Message.LINE.getMessage());
            System.out.println(menuList);
            System.out.println(Message.LINE.getMessage());
            String response = sc.next();
            switch (response) {
                case "1":
                    developerView.show();
                    break;
                case "2":
                    skillView.show();
                    break;
                case "3":
                    specialtyView.show();
                    break;
                case  "4":
                    isExit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }
            if (isExit)
                break;
        }
        sc.close();
    }
}
