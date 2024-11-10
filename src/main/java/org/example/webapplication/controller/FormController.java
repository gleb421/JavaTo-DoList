package org.example.webapplication.controller;

import org.example.webapplication.domain.Task;
import org.example.webapplication.repos.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/form")
public class FormController {

    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping
    public String showPage(Model model) {
        tasksRepository.deleteAll();
        Iterable<Task> tasks = tasksRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "form";
    }


    @PostMapping
    public String addTask(@RequestParam String date, @RequestParam String task,
                          @RequestParam(required = false) Integer id,
                          @RequestParam(required = false) Boolean completed, Model model) {
        Task newTask = new Task(task, date);

        // Если флажок "выполнено" не передан, ставим по умолчанию false
        if (completed == null) {
            completed = false;
        }

        newTask.setCompleted(completed); // Устанавливаем статус выполненной задачи

        // Если id присутствует, то это обновление задачи
        if (id != null && tasksRepository.existsById(id)) {
            newTask.setId(id);  // Устанавливаем id для обновляемой задачи
            tasksRepository.save(newTask); // Обновляем задачу
        } else {
            tasksRepository.save(newTask); // Создаем новую задачу
        }

        // Получаем все задачи из репозитория
        Iterable<Task> tasks = tasksRepository.findAll();
        tasks.forEach(taskk -> System.out.println(taskk.getTask())); // выводим в консоль

        // Добавляем все задачи в модель для отображения в шаблоне
        model.addAttribute("tasks", tasks);
        return "form";
    }


}
