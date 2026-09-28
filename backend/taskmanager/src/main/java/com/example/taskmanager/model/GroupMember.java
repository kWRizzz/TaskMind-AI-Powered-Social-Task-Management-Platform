package com.example.taskmanager.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "group_members",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"group_id","user_id"}
                )
        }

)
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn
}
