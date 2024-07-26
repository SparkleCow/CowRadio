package com.sparklecow.cowradio.models.dtos.artist;

import com.sparklecow.cowradio.entities.Album;
import com.sparklecow.cowradio.entities.Country;
import com.sparklecow.cowradio.entities.Genre;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record ArtistResponse(
        Integer id,
        String artistName,
        String description,
        String artistImage,
        Country country,
        List<Genre> genres,
        Map<String, String> socialMediaLinks,
        List<Album> albums,
        Integer followersCount
) {
}
