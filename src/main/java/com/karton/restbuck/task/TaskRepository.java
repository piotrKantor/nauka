package com.karton.restbuck.task;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{

    List<Task> findByName(@Param("name") String name);
}
