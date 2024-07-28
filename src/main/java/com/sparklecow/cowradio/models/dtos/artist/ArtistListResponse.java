package com.sparklecow.cowradio.models.dtos.artist;


import com.sparklecow.cowradio.entities.Genre;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

//DTO that contains relevant information for the artist list show in frontend
@Builder
public record ArtistListResponse(
        Integer id,
        String artistName,
        String artistImage,
        Integer followersCount,
        List<Genre> genres
)implements Serializable {
    private static final long serialVersionUID = 1L;
}
