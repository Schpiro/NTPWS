package com.NTPWS.projekt.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    @BatchSize(size = 20)
    private Set<Authority> authorities;

    @ManyToMany(mappedBy = "groupParticipant")
    @BatchSize(size = 20)
    private Set<MessageGroup> groups;

    @ManyToMany(mappedBy = "users")
    private List<Event> events;

    public User(Long id, String username, String password, Set<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(String username, String password, Set<Authority> authorities){
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
}

