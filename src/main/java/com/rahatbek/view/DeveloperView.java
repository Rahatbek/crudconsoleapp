package com.rahatbek.view;

import com.rahatbek.controller.DeveloperController;
import com.rahatbek.controller.SkillController;
import com.rahatbek.controller.SpecialtyController;
import com.rahatbek.model.*;

import java.util.*;
import java.util.stream.Collectors;

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


        List<Skill> curSkills = developerController.getById(id).getSkills();
        List<Skill> allSkills = skillController.getAll();


        while (true) {
            System.out.println("Удалить навык по id или 0 для выхода");
            System.out.println("Текущие навыки: " + curSkills);
            long idSkill = sc.nextLong();
            List<Skill> skills = listSkillMinus(idSkill, curSkills);
            curSkills = skills;
            if (idSkill == 0)
                break;
        }
        System.out.println(curSkills);
        List<Skill> listSkillForMinus = new ArrayList<>(allSkills);
        List<Skill> listSkillForAdd = new ArrayList<>();
        for (Skill skill : curSkills) {
            Long id1 = skill.getId();
            listSkillForAdd = listSkillMinus(id1, listSkillForMinus);
            listSkillForMinus = listSkillForAdd;
        }
        System.out.println(listSkillForMinus);

        Set<Skill> skills1;
        while (true) {
            System.out.println("Добавить навык по id или 0 для выхода");
            System.out.println("Навыки для добавления: " + listSkillForMinus);
            long idSkill = sc.nextLong();
            List<Skill> skills = listSkillMinus(idSkill, listSkillForMinus);
            skills1 = listSkillAdd(idSkill, listSkillForMinus, curSkills.stream().collect(Collectors.toSet()));
            List<Skill> newSkills = skills1.stream().collect(Collectors.toList());
            listSkillForMinus = skills;
            curSkills = newSkills;
            if (idSkill == 0)
                break;
        }
        System.out.println(curSkills);

        developerController.update(id, name, lastName, byId, curSkills);
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
