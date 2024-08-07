package com.sparklecow.cowradio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "songs")
public class Song extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;
    @Column(nullable = false)
    private String soundCloudUrl;
    @Column(columnDefinition = "TEXT")
    private String embedCode;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "songs")
    private List<Playlist> playlist;
}

