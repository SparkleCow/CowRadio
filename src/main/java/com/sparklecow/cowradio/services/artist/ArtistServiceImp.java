package com.sparklecow.cowradio.services.artist;

import com.sparklecow.cowradio.entities.Artist;
import com.sparklecow.cowradio.entities.Country;
import com.sparklecow.cowradio.entities.Genre;
import com.sparklecow.cowradio.entities.user.Role;
import com.sparklecow.cowradio.entities.user.User;
import com.sparklecow.cowradio.models.dtos.artist.ArtistListResponse;
import com.sparklecow.cowradio.models.dtos.artist.ArtistRequest;
import com.sparklecow.cowradio.models.dtos.artist.ArtistResponse;
import com.sparklecow.cowradio.repositories.ArtistRepository;
import com.sparklecow.cowradio.services.mappers.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImp implements ArtistService{

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Cacheable("findArtistByName")
    @Override
    public Page<ArtistListResponse> findArtistByNameContaining(String artistName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("artistName").ascending());
        Page<Artist> artists = artistRepository.findByArtistNameContainingIgnoreCase(artistName, pageable);
        //Load the genres list to avoid lazy load problem with hibernate and redis.
        artists.forEach(artist -> {
            Hibernate.initialize(artist.getGenres());
        });
        return artists.map(artistMapper::toArtistListResponse);
    }

    @Cacheable("findArtistByCountry")
    @Override
    public Page<ArtistListResponse> findArtistByCountry(Country country, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("country").ascending());
        Page<Artist> artists = artistRepository.findByCountry(country, pageable);
        //Load the genres list to avoid lazy load problem with hibernate and redis.
        artists.forEach(artist -> {
            Hibernate.initialize(artist.getGenres());
        });
        return artists.map(artistMapper::toArtistListResponse);
    }

    @Cacheable("findArtistByGenre")
    @Override
    public Page<ArtistListResponse> findArtistByGenre(Genre genre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("genre").ascending());
        Page<Artist> artists = artistRepository.findByGenresContaining(genre, pageable);
        return artists.map(artistMapper::toArtistListResponse);
    }

    @Cacheable("findArtist")
    @Override
    public ArtistResponse findArtistById(Integer id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return artistMapper.toArtistResponse(artist);
    }

    @CacheEvict(value = {"findArtistByName", "findArtistByCountry", "findArtistByGenre"}, allEntries = true)
    @Override
    public Integer createArtist(ArtistRequest artistRequest, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        if (!user.getRoles().contains(Role.ARTIST)){
            //TODO Enhance exceptions
            throw new RuntimeException();
        }
        Artist artist = artistMapper.toArtist(artistRequest);
        return artistRepository.save(artist).getId();
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
