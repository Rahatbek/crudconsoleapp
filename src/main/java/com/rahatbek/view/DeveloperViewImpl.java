package com.rahatbek.view;

import com.rahatbek.controller.DeveloperController;
import com.rahatbek.model.Developer;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;

import java.util.*;

public class DeveloperViewImpl extends BaseView{

    private DeveloperController developerController;

    private BaseView specialtyView;

    private BaseView skillView;

    private final String menuDeveloper = "Выберите дейстивие над разработчиками:\n" +
            " 1. Создать разработчика\n" +
            " 2. Редактировать разработчика\n" +
            " 3. Удалить разработчика\n" +
            " 4. Вывести список разработчиков\n" +
            " 5. Выхода";

    private final String printDeveloper = "Список разработчиков\n" +
            "ID; first name; last name; навыки; специальности; статус";

    private final String createDeveloper = "Создание разработчика";

    private final String editDeveloper = "Редактирование разработчика.\n" + Message.ID.getMessage();

    private final String deleteDeveloper = "Удаление разработчика.\n" + Message.ID.getMessage();

    private final String addSameSkill = "Навык уже добавлен! Выберите другую...'n" + "ID = ";

    private final String wantAddSkill = "Хотите добавить еще навык? (y/n):";

    private final String answerYes = "y";

    public DeveloperViewImpl(DeveloperController developerController, Scanner sc, BaseView specialtyView, BaseView skillView) {
        this.developerController = developerController;
        this.sc = sc;
        this.specialtyView = specialtyView;
        this.skillView = skillView;
    }

    public void show() {
        boolean isExit = false;
        while (true) {
            print();
            System.out.println(Message.LINE.getMessage());
            System.out.println(menuDeveloper);
            System.out.println(Message.LINE.getMessage());

            String response = sc.next();

            switch (response) {
                case "1":
                    create();
                    break;
                case "2":
                    edit();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    print();
                    break;
                case "5":
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
    @Override
    void create() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createDeveloper);
        String name = createDeveloperName();
        String lastName = createDeveloperLastName();
        Long specialtyId = chooseSpecialtyId();
        Set<Long> skillsId = chooseSkillsId();
        try {
            developerController.create(name,lastName,specialtyId, skillsId, Status.ACTIVE);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION);
        }
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    void edit() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(editDeveloper);
        Long id = sc.nextLong();

        try {
            developerController.checkEdit(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
            return;
        }

        String name = createDeveloperName();
        String lastName = createDeveloperLastName();
        Long specialtyId = chooseSpecialtyId();
        Set<Long> skillsId = chooseSkillsId();
        try {
            developerController.update(id, name, lastName, specialtyId,skillsId, Status.ACTIVE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    void delete() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(deleteDeveloper);
        Long id = sc.nextLong();
        try {
            developerController.delete(id);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    void print() {
        List<Developer> developers;
        try {
            developers = developerController.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        Collections.sort(developers, Comparator.comparing(Developer::getId));
        System.out.println(Message.LINE.getMessage());
        System.out.println(printDeveloper);
        if (developers.size() != 0) {
            for (Developer dev : developers) {
                String printLine = dev.getId() + "; " + dev.getFirstName() + "; " + dev.getLastName() + "; " +
                        dev.getSpecialtyId() + "; " + dev.getSkillsId() + "; " + dev.getStatus();
                StringJoiner joiner = new StringJoiner("/");
                for (Skill i : dev.getSkills()) {
                    joiner.add(i.getName());
                }
                printLine += joiner.toString();
                System.out.println(printLine);
            }
        } else {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    private String createDeveloperName() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(Message.NAME.getMessage());
        String name = sc.next();
        System.out.println(Message.LINE.getMessage());
        return name;
    }

    private String createDeveloperLastName() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(Message.NAME.getMessage());
        String lastName = sc.next();
        System.out.println(Message.LINE.getMessage());
        return lastName;
    }

    private Long chooseSpecialtyId() {
        Long specialtyId;
        while (true) {
            specialtyView.print();
            System.out.println(Message.LINE.getMessage());
            System.out.println(Message.ID.getMessage());
            specialtyId = sc.nextLong();
            try {
                developerController.checkSpecialty(specialtyId);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
        System.out.println(Message.LINE.getMessage());
        return specialtyId;
    }

    private Set<Long> chooseSkillsId() {
        Set<Long> skillsId = new HashSet<>();

        while (true) {
            skillView.print();
            System.out.println(Message.LINE.getMessage());
            System.out.println(Message.ID.getMessage());
            Long skillId = sc.nextLong();
            try {
                developerController.checkSkill(skillId);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (skillsId.contains(skillId)) {
                System.out.println(addSameSkill);
            } else {
                skillsId.add(skillId);
            }

            System.out.println(wantAddSkill);
            String response = sc.next();
            if (!response.equalsIgnoreCase(answerYes)) {
                break;
            }
            System.out.println(Message.LINE.getMessage());
        }
        return skillsId;
    }
}
