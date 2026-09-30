package com.example.taskmanager.repository;

import com.example.taskmanager.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Long> {

    List<GroupMember> findByUserId(Long userId);
    List<GroupMember> findByGroupId(Long groupId);

    Optional<GroupMember> findByGroupIdAndUserId(Long userId, Long groupId);

    boolean existsByGroupIdAndUserId(
            Long userId,
            Long groupId
    );
}
