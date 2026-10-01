package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.GroupResponse;
import com.example.taskmanager.model.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {
    public GroupResponse toResponse(Group group){
        return new GroupResponse(
            group.getId(),
                group.getName(),
                group.getDescription(),
                    group.getDescription(),
                        group.getCreatedAt()
        );
    }
}
