package com.karton.restbuck.user;

import com.karton.restbuck.task.Task;
import com.karton.restbuck.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public List<UserAccount> getUsers(){
        return userRepository.findAll();
    }

    public UserAccount createUser(String name, String password)throws Exception{
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        UserAccount userAccount = UserAccount
                .builder()
                .name(name)
                .hash(passwordEncoder.encode(password))
                .active(true)
                .role(Role.USER)
                .build();
        userRepository.save(userAccount);
        return userAccount;
    }

    public UserAccount addTask(Long id, String name){
        UserAccount userAccount =userRepository.findOne(id);
        Task task=taskRepository.findByName(name).get(0);
        userAccount.getTasks().add(task);
        userRepository.save(userAccount);
        return userAccount;
    }


}
