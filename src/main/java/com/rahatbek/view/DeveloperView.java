package com.rahatbek.view;

import com.rahatbek.controller.DeveloperController;
import com.rahatbek.controller.SkillController;
import com.rahatbek.controller.SpecialtyController;
import com.rahatbek.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeveloperView extends BaseView {

    DeveloperController developerController;
    SkillController skillController;
    SpecialtyController specialtyController;

    public DeveloperView(DeveloperController developerController, SkillController skillController,
                         SpecialtyController specialtyController, Scanner sc) {
        this.developerController = developerController;
        this.skillController = skillController;
        this.specialtyController = specialtyController;
        this.sc = sc;
        this.message = mainMenuMessage;
    }

    private final String mainMenuMessage = "Выберите действие над разработчиками:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список разработчиков\n" +
            " 5. Выход";

    private final String printMenuMessage = "Список разработчиков:\n" +
            "ID; NAME";

    private final String createMenuMessage = "Создание разработчика.\n" +
            Message.NAME.getMessage();

    private final String editMenuMessage = "Редактирование разработчика.\n" +
            Message.ID.getMessage();

    private final String deleteMenuMessage = "Удаление разработчика\n" +
            Message.ID.getMessage();


    public void create() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createMenuMessage);

        String name = sc.next();
        System.out.println(Message.LASTNAME.getMessage());
        String lastName = sc.next();

        System.out.println("Выберите номер id специальности из списка");
        List<Specialty> allSpecialty = specialtyController.getAll();
        allSpecialty.stream().forEach(System.out::println);
        Long idSpecialty = sc.nextLong();
        Specialty saveForDeveloper = specialtyController.getById(idSpecialty);

        System.out.println("Выберите id из списка из навыков");
        List<Skill> all = skillController.getAll();

        Set<Skill> skillsForSaveInDeveloper = new HashSet<>();

        printSkill(all);
        boolean isEnough = true;
        while (true) {
            long idSkill = sc.nextLong();
            System.out.println("Хотите добавить навык из данного списка");

            List<Skill> listMinus = listSkillMinus(idSkill, all);
            printSkill(listMinus);
            listSkillAdd(idSkill, all, skillsForSaveInDeveloper);
            System.out.println("Введите 0 если достаточно");
            if (idSkill == 0)
                isEnough = false;
            if (!isEnough)
                break;
            all = listMinus;
        }

        System.out.println("skills for developer " + skillsForSaveInDeveloper);
        List<Skill> collect = skillsForSaveInDeveloper.stream().sorted(Comparator.comparing(Skill::getId)).collect(Collectors.toList());
        System.out.println(collect);

        developerController.save(name, lastName, saveForDeveloper, collect);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }

    private void printSkill(List<Skill> all) {
        for (Skill item : all)
            System.out.println(item);
    }

    private List<Skill> listSkillMinus(long minus, List<Skill> skills) {
        List<Skill> minusSkill = new ArrayList<>(skills);
        Skill skill = minusSkill.stream().filter(s -> s.getId().equals(minus))
                .findFirst()
                .orElse(null);
        minusSkill.remove(skill);
        return minusSkill;
    }

    private Set<Skill> listSkillAdd(long add, List<Skill> skillsFromFile, Set<Skill> listSkillForAdd) {
        Skill skill = skillsFromFile.stream().filter(s -> s.getId().equals(add))
                .findFirst()
                .orElse(null);
        if (skill != null)
            listSkillForAdd.add(skill);
        return listSkillForAdd;
    }

    public void update() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(editMenuMessage);

        Long id = sc.nextLong();

        System.out.println(developerController.getById(id));
        System.out.println(Message.NAME.getMessage());
        String name = sc.next();

        System.out.println(Message.LASTNAME.getMessage());
        String lastName = sc.next();

        System.out.println("Поменять или оставить специальность");
        System.out.println("Текущая специальность: " + developerController.getById(id).getSpecialty());
        System.out.println("Список специальностей:");
        specialtyController.getAll().stream().forEach(System.out::println);
        long idSpecialty = sc.nextLong();
        Specialty byId = specialtyController.getById(idSpecialty);



        System.out.println("Хотите удалить навык?\n" +
                "1.Да\n" +
                "2.Нет");
        List<Skill> curSkills = developerController.getById(id).getSkills();
        System.out.println("Текущие навыки: " + curSkills);
        List<Skill> allSkills = skillController.getAll();

        String response = sc.next();

        List<Skill> arrayListSkill = new ArrayList<>(allSkills);
        for (Skill deletedSkill : curSkills) {
            arrayListSkill.remove(Math.toIntExact(deletedSkill.getId() - 1));
        }


        List<Skill> listMinus = new ArrayList<>();

//        switch (response) {
//            case "1":
//            while (true) {
//                System.out.println("Для удаления навыка введите id");
//                long idSkill = sc.nextLong();
//                listMinus = listSkillMinus(idSkill, curSkills);
//                printSkill(listMinus);
//                curSkills = listMinus;
//                if (idSkill == 0)
//                    break;
//            }
//            case "2":
//            break;
//        }
//
//        System.out.println("Хотите добавить навык?\n" +
//                "1.Да\n" +
//                "2.Нет");
//        response = sc.next();
//        System.out.println("Отсутствующие списки навыков: " + arrayListSkill);
//        switch (response) {
//            case "1":
//                while (true) {
//                    System.out.println("Для удаления навыка введите id");
//                    long idSkill = sc.nextLong();
//                    listMinus = listSkillAdd(idSkill, curSkills, arrayListSkill);
//                    printSkill(listMinus);
//                    curSkills = listMinus;
//                    if (idSkill == 0)
//                        break;
//                }
//            case "2":
//                break;
//        }

        System.out.println();
//        developerController.update(id, name, Status.ACTIVE);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }


    public void delete() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(deleteMenuMessage);
        Long id = sc.nextLong();

        developerController.deleteById(id);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }

    public void print() {
        List<Developer> all = developerController.getAll();


        System.out.println(Message.LINE.getMessage());
        System.out.println(printMenuMessage);
        if (all != null)
            System.out.println(all);
        else
            System.out.println(Message.EMPTY_LIST.getMessage());
        System.out.println(Message.LINE.getMessage());
    }
}
