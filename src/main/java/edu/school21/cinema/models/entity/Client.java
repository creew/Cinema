package edu.school21.cinema.models.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "session_id")
    private UUID sessionId;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private FileDescription avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public FileDescription getAvatar() {
        return avatar;
    }

    public void setAvatar(FileDescription avatar) {
        this.avatar = avatar;
    }
}
