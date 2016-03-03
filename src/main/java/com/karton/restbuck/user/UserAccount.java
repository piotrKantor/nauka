package com.karton.restbuck.user;

import com.karton.restbuck.task.Task;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean active;

    @NonNull
    @Column(nullable = false)
    private String hash;

    @NonNull
    @Column(nullable = false)
    private Role role;

    @OneToMany
    private List<Task> tasks;
}
