package com.karton.restbuck.task.web;

import com.karton.restbuck.task.Task;
import com.karton.restbuck.task.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "tasks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    @NonNull
    private final TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    List<Task> getTasks(){
        return taskService.getTasks();
    }

    @RequestMapping(method = RequestMethod.POST)
    Task postTask(String name){
        return taskService.createTask(name);
    }

}
