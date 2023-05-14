package com.ass.brainbitesprototype.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor; //makes constructors
import lombok.Builder; //build the entity/model
import lombok.Data; //provide get and set methods
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String photoUrl;
    private String content;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    // Hold the flashcards that are attached to this set.
    @OneToMany(mappedBy = "set", cascade = CascadeType.REMOVE)
    private List<Flashcard> flashcards = new ArrayList<>();
}
