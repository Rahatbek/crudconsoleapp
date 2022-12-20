package com.rahatbek.view;

import com.rahatbek.controller.SkillController;
import com.rahatbek.model.Message;
import com.rahatbek.model.Skill;
import com.rahatbek.model.Status;

import java.util.List;
import java.util.Scanner;

public class SkillView extends BaseView {

    SkillController skillController;

    public SkillView(SkillController skillController, Scanner sc) {
        this.skillController = skillController;
        this.sc = sc;
        this.message = mainMenuMessage;
    }

    private final String mainMenuMessage = "Выберите действие над навыками:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список навыков\n" +
            " 5. Выход";

    private final String printMenuMessage = "Список навыков:\n" +
            "ID; NAME";

    private final String createMenuMessage = "Создание навыка.\n" +
            Message.NAME.getMessage();

    private final String editMenuMessage = "Редактирование навыка.\n" +
            Message.ID.getMessage();

    private final String deleteMenuMessage = "Удаление навыка\n" +
            Message.ID.getMessage();


    public void create() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createMenuMessage);

        String name = sc.next();

        skillController.save(name);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }

    public void update() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(editMenuMessage);

        Long id = sc.nextLong();
        System.out.println(Message.NAME.getMessage());

        String name = sc.next();

        skillController.update(id, name, Status.ACTIVE);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }

    public void delete() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(deleteMenuMessage);
        Long id = sc.nextLong();

        skillController.deleteById(id);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }

    public void print() {
        List<Skill> all = skillController.getAll();


        System.out.println(Message.LINE.getMessage());
        System.out.println(printMenuMessage);
        if (all != null)
            System.out.println(all);
        else
            System.out.println(Message.EMPTY_LIST.getMessage());
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    void getById() {
        super.isExit = true;
    }
}
