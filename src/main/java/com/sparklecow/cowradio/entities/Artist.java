package com.sparklecow.cowradio.entities;

import com.sparklecow.cowradio.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "artists")
public class Artist extends BaseEntity{
    @Column(name = "artist_name", nullable = false, unique = true)
    private String artistName;
    @Column(length = 500)
    private String description;
    @Column(name = "artist_image")
    private String artistImage;
    @Enumerated(EnumType.STRING)
    private Country country;
    @CollectionTable(name = "artist_genres", joinColumns = @JoinColumn(name = "artist_id"))
    @ElementCollection(targetClass = Genre.class, fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;
    @CollectionTable(name = "socialMedia", joinColumns = @JoinColumn(name = "artist_id"))
    @ElementCollection(targetClass = Genre.class, fetch = FetchType.LAZY)
    @MapKeyColumn(name = "social_media")
    private Map<String, String> socialMediaLinks;
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Album> albums;
    private Integer followersCount;
    @ManyToMany(mappedBy = "followedArtist", fetch = FetchType.LAZY)
    private List<User> followers;
}
