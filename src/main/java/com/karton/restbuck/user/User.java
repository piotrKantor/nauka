package com.karton.restbuck.user;

import com.karton.restbuck.task.Task;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @OneToMany
    private List<Task> tasks;
}
