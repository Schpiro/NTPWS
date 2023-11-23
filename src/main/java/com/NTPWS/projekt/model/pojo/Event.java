package com.NTPWS.projekt.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id")
    private User creator;
    private String title;
    private String location;
    private LocalDateTime date;
    private String eventDetails;
    private LocalDateTime creationDate;

    @ManyToMany
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(mappedBy = "event")
    private List<Comment> comments;

    public Event(User creator, String title, String location, LocalDateTime date, String eventDetails) {
        this.creator = creator;
        this.title = title;
        this.location = location;
        this.date = date;
        this.eventDetails = eventDetails;
        this.creationDate = LocalDateTime.now();
    }
}
