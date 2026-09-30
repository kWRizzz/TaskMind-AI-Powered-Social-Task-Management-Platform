package com.example.taskmanager.repository;

import com.example.taskmanager.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
