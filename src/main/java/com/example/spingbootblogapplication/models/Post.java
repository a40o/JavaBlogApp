package com.example.spingbootblogapplication.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;


    public String convertDate(LocalDateTime date){
        return DateTimeFormatter.ISO_DATE.format(date);
    }

    public String convertTime(LocalDateTime date){
        return DateTimeFormatter.ISO_LOCAL_TIME.format(date.withNano(0));
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", body='" + body + "'" +
                ", createdAt='" + createdAt + "'" +
                ", updatedAt='" + updatedAt + "'" +
                "}";
    }

}