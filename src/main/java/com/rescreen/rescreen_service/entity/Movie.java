package com.rescreen.rescreen_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(
        name = "movies",
        indexes = {
                @Index(name = "idx_movie_title", columnList = "title"),
                @Index(name = "idx_movie_genre", columnList = "genre")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Movie title (Original or Re-release)
     */
    @Column(nullable = false, length = 150)
    private String title;

    /**
     * Short description / storyline
     */
    @Column(length = 1000)
    private String description;

    /**
     * Movie genre (Drama, Action, Romance, etc.)
     */
    @Column(nullable = false, length = 50)
    private String genre;

    /**
     * Duration in minutes
     */
    @Column(name = "duration_minutes")
    private Integer duration;

    /**
     * Original release date
     */
    private LocalDate originalReleaseDate;

    /**
     * IMDb or platform rating
     */
    private Double rating;

    /**
     * Language of the movie
     */
    @Column(nullable = false, length = 30)
    private String language;

    /**
     * Movie lifecycle status
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MovieStatus status;

    /**
     * Audit fields
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Automatically set audit values
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = MovieStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}
