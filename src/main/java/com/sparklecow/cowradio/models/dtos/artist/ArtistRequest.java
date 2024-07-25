package com.sparklecow.cowradio.models.dtos.artist;

import com.sparklecow.cowradio.entities.Country;
import com.sparklecow.cowradio.entities.Genre;

import java.util.List;
import java.util.Map;

public record ArtistRequest(
        String artistName,
        String description,
        String artistImage,
        Country country,
        List<Genre> genres,
        Map<String, String> socialMediaLinks
) {
}
