package com.sparklecow.cowradio.services.artist;

import com.sparklecow.cowradio.entities.Artist;
import com.sparklecow.cowradio.entities.Country;
import com.sparklecow.cowradio.entities.Genre;
import com.sparklecow.cowradio.models.dtos.artist.ArtistListResponse;
import com.sparklecow.cowradio.models.dtos.artist.ArtistRequest;
import com.sparklecow.cowradio.models.dtos.artist.ArtistResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface ArtistService {
    /*General use. All users will be able to use these methods*/
    public Page<ArtistListResponse> findArtistByNameContaining(String name, int page, int size);
    public Page<ArtistListResponse> findArtistByCountry(Country country, int page, int size);
    public Page<ArtistListResponse> findArtistByGenre(Genre genre, int page, int size);
    public ArtistResponse findArtistById(Integer id);

    /*Methods for Users with artist role. Only a user with artist role will be able to create, update and delete their
    * own artist accounts*/
    public Integer createArtist(ArtistRequest artistRequest, Authentication authentication);
    public Integer updateArtist(ArtistRequest artistRequest, Integer id, Authentication authentication);
    public Integer deleteArtist(Integer id, Authentication authentication);

    /*Method only valid for users with admin role*/
    public Artist findArtistByIdForAdmin(Integer id);
    public Page<Artist> findAllArtists(int page, int size);
}
