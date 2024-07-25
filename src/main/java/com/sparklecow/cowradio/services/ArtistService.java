package com.sparklecow.cowradio.services;

import com.sparklecow.cowradio.models.dtos.artist.ArtistListResponse;
import com.sparklecow.cowradio.models.dtos.artist.ArtistResponse;
import org.springframework.data.domain.Page;

public interface ArtistService {

    public Page<ArtistListResponse> findArtistByNameContaining();
    public Page<ArtistListResponse> findArtistByCountry();
    public Page<ArtistListResponse> findArtistByGenre();
    public ArtistResponse findArtistById();
}
