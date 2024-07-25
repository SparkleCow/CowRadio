package com.sparklecow.cowradio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "albums")
public class Album extends BaseEntity{
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @Column(nullable = false, name = "cover_image")
    private String coverImage;
    @Column(nullable = false, name = "release_date")
    private LocalDate releaseDate;
    @CollectionTable(name = "album_genres", joinColumns = @JoinColumn(name = "album_id"))
    @ElementCollection(targetClass = Genre.class, fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "album_collaborations", joinColumns =
        @JoinColumn(name = "album_id"), inverseJoinColumns =
        @JoinColumn(name = "artist_id")
    )
    private List<Artist> collaborations;
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Song> songs;
}
