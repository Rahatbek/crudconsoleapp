package com.rahatbek.view;

import com.rahatbek.controller.SkillController;
import com.rahatbek.model.BaseEntity;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SkillViewImpl extends BaseView {

    SkillController skillController;

    private final String menuSkill = "Выберите действие над навыками:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список категорий\n" +
            " 5. Выход";

    private final String printSkill = "Список навыков:\n" +
            "ID; NAME; STATUS";

    private final String createSkill = "Создание навыка.\n" + Message.NAME.getMessage();

    private final String editSkill = "Редактироваение навыка.\n" + Message.ID.getMessage();

    private final String deleteSkill = "Удаление навыка.\n" + Message.ID.getMessage();

    public SkillViewImpl(SkillController skillController, Scanner sc) {
        this.skillController = skillController;
        this.sc = sc;
        this.msg = menuSkill;
    }

    @Override
    void create() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createSkill);
        String name = sc.next();
        try {
            skillController.create(name, Status.ACTIVE);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    void edit() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(editSkill);
        Long id = sc.nextLong();
        System.out.println(Message.NAME.getMessage());
        String name = sc.next();
        try {
            skillController.update(id, name, Status.ACTIVE);;
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    void delete() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(deleteSkill);
        Long id = sc.nextLong();
        try {
            skillController.delete(id);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    void print() {
        List<Skill> skills;
        try {
            skills = skillController.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(Message.LINE.getMessage());
        System.out.println(printSkill);
        Collections.sort(skills, Comparator.comparing(BaseEntity::getId));
        if (skills.size() != 0) {
            for (Skill i : skills) {
                System.out.println(i.getId() + "; " + i.getName());
            }
        } else {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }
}
