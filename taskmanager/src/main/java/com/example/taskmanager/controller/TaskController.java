package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks"; // Esta es la vista tasks.html
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "taskDetail"; // Esta es la vista taskDetail.html
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task()); // Agregar un objeto Task vacío
        return "createTask"; // Vista createTask.html
    }

    // Ruta para crear una tarea (POST)
    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks"; // Redirige a la lista de tareas después de crearla
    }

    // Ruta para actualizar el estado de la tarea (PUT)
    @PostMapping("/{id}/updateStatus")
    public String updateTaskStatus(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted()); // Cambiar el estado de completado
            taskService.updateTask(task); // Actualiza la tarea en la base de datos
        }
        return "redirect:/tasks"; // Redirige a la lista de tareas después de actualizarla
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.updateTask(task);
        return "redirect:/tasks"; // Redirige a la lista de tareas después de actualizarla
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks"; // Redirige después de eliminar la tarea
    }



}
