package com.sparklecow.cowradio.services.artist;

import com.sparklecow.cowradio.entities.Artist;
import com.sparklecow.cowradio.entities.Country;
import com.sparklecow.cowradio.entities.Genre;
import com.sparklecow.cowradio.models.dtos.artist.ArtistListResponse;
import com.sparklecow.cowradio.models.dtos.artist.ArtistRequest;
import com.sparklecow.cowradio.models.dtos.artist.ArtistResponse;
import com.sparklecow.cowradio.repositories.ArtistRepository;
import com.sparklecow.cowradio.services.mappers.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImp implements ArtistService{

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Override
    public Page<ArtistListResponse> findArtistByNameContaining(String name, int page, int size) {
        return null;
    }

    @Override
    public Page<ArtistListResponse> findArtistByCountry(Country country, int page, int size) {
        return null;
    }

    @Override
    public Page<ArtistListResponse> findArtistByGenre(Genre genre, int page, int size) {
        return null;
    }

    @Override
    public ArtistResponse findArtistById(Integer id) {
        return null;
    }

    @Override
    public Integer createArtist(ArtistRequest artistRequest, Authentication authentication) {
        return null;
    }

    @Override
    public Integer updateArtist(ArtistRequest artistRequest, Integer id, Authentication authentication) {
        return null;
    }

    @Override
    public Integer deleteArtist(Integer id, Authentication authentication) {
        return null;
    }

    @Override
    public Artist findArtistByIdForAdmin(Integer id) {
        return null;
    }

    @Override
    public Page<Artist> findAllArtists(int page, int size) {
        return null;
    }
}
