package com.rahatbek.view;

import com.rahatbek.controller.SpecialtyController;
import com.rahatbek.model.BaseEntity;
import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SpecialtyViewImpl extends BaseView{

    private SpecialtyController specialtyController;

    private final String menuSpecialty = "Выберите действие над специальностью:\n" +
            " 1. Содать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список\n" +
            " 5. Выход\n";

    private final String printSpecialty = "Список специальностей:\n" +
            "ID; NAME; STATUS";

    private final String createSpecialty = "Создание специальности.\n" +
            Message.NAME.getMessage();

    private final String editSpecialty = "Редактирование специальности" + Message.ID.getMessage();

    private final String deleteSpecialty = "Удаление специальности" + Message.ID.getMessage();

    public SpecialtyViewImpl(SpecialtyController specialtyController, Scanner sc) {
        this.specialtyController = specialtyController;
        this.sc = sc;
        this.msg = menuSpecialty;
    }

    @Override
    void create() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createSpecialty);
        String name = sc.next();
        try {
            specialtyController.create(name, Status.ACTIVE);
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
        System.out.println(editSpecialty);
        Long id = sc.nextLong();
        System.out.println(Message.NAME.getMessage());
        String name = sc.next();
        try {
            specialtyController.update(id, name, Status.ACTIVE);
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
        System.out.println(deleteSpecialty);
        Long id = sc.nextLong();
        try {
            specialtyController.delete(id);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    void print() {
        List<Specialty> specialties;
        try {
            specialties = specialtyController.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        Collections.sort(specialties, Comparator.comparing(BaseEntity::getId));
        System.out.println(Message.LINE.getMessage());
        System.out.println(printSpecialty);
        if (specialties.size() != 0) {
            for (Specialty i : specialties) {
                System.out.println(i.getId() + "; " + i.getName() + "; " + i.getStatus());
            }
        } else {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }
}
