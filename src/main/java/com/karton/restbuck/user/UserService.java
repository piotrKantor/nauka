package com.karton.restbuck.user;

import com.karton.restbuck.task.Task;
import com.karton.restbuck.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(String name){
        User user = User.builder().name(name).build();
        userRepository.save(user);
        return user;
    }

    public User addTask(Long id, String name){
        User user=userRepository.findOne(id);
        Task task=taskRepository.findByName(name).get(0);
        user.getTasks().add(task);
        userRepository.save(user);
        return user;
    }
}
