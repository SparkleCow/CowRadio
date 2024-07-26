package com.sparklecow.cowradio.models.dtos.artist;


import com.sparklecow.cowradio.entities.Genre;
import lombok.Builder;

import java.util.List;

//DTO that contains relevant information for the artist list show in frontend
@Builder
public record ArtistListResponse(
        Integer id,
        String artistName,
        String artistImage,
        Integer followersCount,
        List<Genre> genres
) {
}
