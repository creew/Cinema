package edu.school21.cinema.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Client {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private FileDescription avatar;

    public UUID getId() {
        return id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public FileDescription getAvatar() {
        return avatar;
    }

    public void setAvatar(FileDescription avatar) {
        this.avatar = avatar;
    }
}
