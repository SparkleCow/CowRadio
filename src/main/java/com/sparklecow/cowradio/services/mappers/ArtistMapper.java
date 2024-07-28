package com.sparklecow.cowradio.services.mappers;

import com.sparklecow.cowradio.entities.Artist;
import com.sparklecow.cowradio.models.dtos.artist.ArtistListResponse;
import com.sparklecow.cowradio.models.dtos.artist.ArtistRequest;
import com.sparklecow.cowradio.models.dtos.artist.ArtistResponse;
import org.springframework.stereotype.Service;

@Service
public class ArtistMapper {

    public Artist toArtist(ArtistRequest artistRequest) {
        return Artist.builder()
                .artistName(artistRequest.artistName())
                .description(artistRequest.description())
                .artistImage(artistRequest.artistImage())
                .country(artistRequest.country())
                .genres(artistRequest.genres())
                .socialMediaLinks(artistRequest.socialMediaLinks())
                .followersCount(0)
                .build();
    }
    public ArtistListResponse toArtistListResponse(Artist artist) {
        return ArtistListResponse.builder()
                .id(artist.getId())
                .artistName(artist.getArtistName())
                .artistImage(artist.getArtistImage())
                .genres(artist.getGenres())
                .followersCount(artist.getFollowersCount())
                .build();
    }

    public ArtistResponse toArtistResponse(Artist artist) {
        return ArtistResponse.builder()
                .id(artist.getId())
                .artistName(artist.getArtistName())
                .description(artist.getDescription())
                .artistImage(artist.getArtistImage())
                .country(artist.getCountry())
                .genres(artist.getGenres())
                .socialMediaLinks(artist.getSocialMediaLinks())
                .albums(artist.getAlbums())
                .followersCount(artist.getFollowersCount())
                .build();
    }
}
