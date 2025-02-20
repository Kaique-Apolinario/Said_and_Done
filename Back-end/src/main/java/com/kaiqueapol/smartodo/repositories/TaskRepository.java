package com.kaiqueapol.smartodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaiqueapol.smartodo.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
