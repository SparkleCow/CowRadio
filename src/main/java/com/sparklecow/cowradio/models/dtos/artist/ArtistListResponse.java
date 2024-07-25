package com.sparklecow.cowradio.models.dtos.artist;


import java.util.List;

//DTO that contains relevant information for the artist list show in frontend
public record ArtistListResponse(
        Integer id,
        String artistName,
        String artistImage,
        Integer followersCount,
        List<String> genres
) {
}
