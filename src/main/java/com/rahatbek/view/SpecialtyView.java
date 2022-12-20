package com.rahatbek.view;

import com.rahatbek.controller.SpecialtyController;
import com.rahatbek.model.Message;
import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView extends BaseView {

    SpecialtyController specialtyController;

    public SpecialtyView(SpecialtyController specialtyController, Scanner sc) {
        this.specialtyController = specialtyController;
        this.sc = sc;
        this.message = mainMenuMessage;
    }

    private final String mainMenuMessage = "Выберите действие над специальностями:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список специальностей\n" +
            " 5. Выход";

    private final String printMenuMessage = "Список специальностей:\n" +
            "ID; NAME";

    private final String createMenuMessage = "Создание специальности.\n" +
            Message.NAME.getMessage();

    private final String editMenuMessage = "Редактирование специальности.\n" +
            Message.ID.getMessage();

    private final String deleteMenuMessage = "Удаление специальности\n" +
            Message.ID.getMessage();


    public void create() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createMenuMessage);

        String name = sc.next();

        specialtyController.save(name);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }

    public void update() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(editMenuMessage);

        Long id = sc.nextLong();
        System.out.println(Message.NAME.getMessage());

        String name = sc.next();

        specialtyController.update(id, name, Status.ACTIVE);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }

    public void delete() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(deleteMenuMessage);
        Long id = sc.nextLong();

        specialtyController.deleteById(id);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());

        System.out.println(Message.LINE.getMessage());
    }

    public void print() {
        List<Specialty> all = specialtyController.getAll();


        System.out.println(Message.LINE.getMessage());
        System.out.println(printMenuMessage);
        if (all != null)
            System.out.println(all);
        else
            System.out.println(Message.EMPTY_LIST.getMessage());
        System.out.println(Message.LINE.getMessage());
    }

    public void getById() {
        super.isExit = true;
    }
}
