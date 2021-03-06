package com.karton.restbuck.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(String name) {
        Task task=Task.builder().name(name).build();
        taskRepository.save(task);
        return task;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}
