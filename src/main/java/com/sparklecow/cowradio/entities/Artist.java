package com.sparklecow.cowradio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "artists")
public class Artist extends BaseEntity{

    @Column(name = "artist_name", nullable = false, unique = true)
    private String artistName;
    @Column(length = 500)
    private String description;
    @Column(name = "artist_image")
    private String artistImage;
    private Country country;
    @ElementCollection(targetClass = Genre.class, fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;
    private Map<String, String> socialMediaLinks;
    private List<Album> albums;
    private Integer followers;
}
