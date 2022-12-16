# CrudConsole

## Описание

Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

Skill   
Specialty   
Developer  
Status (enum ACTIVE, DELETED)

Developer -> List<Skill> skills + Specialty specialty   

В качестве хранилища данных необходимо использовать текстовые файлы:
developers.json, skills.json, specialties.json

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

Слои:    
main.java.com.rahatbek.model - POJO клаcсы   
main.java.com.rahatbek.repository - классы, реализующие доступ к текстовым файлам  
main.java.com.rahatbek.controller - обработка запросов от пользователя   
main.java.com.rahatbek.view - все данные, необходимые для работы с консолью

## Инструкция по запуску

1) Скачать приложение

2) Перейти в репозиторий по ссылке https://github.com/Rahatbek/crudconsoleapp.git

3) Кликнуть зеленую кнопку "Clone or Download" в правой верхней части страницы.

4) Распаковать архиватором

5) Открыть проект в intellij idea

6) Запустить класс main.java.com.rahatbek.AppRunner