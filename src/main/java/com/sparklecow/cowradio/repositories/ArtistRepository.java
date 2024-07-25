package com.sparklecow.cowradio.repositories;

import com.sparklecow.cowradio.entities.Artist;
import com.sparklecow.cowradio.entities.Country;
import com.sparklecow.cowradio.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Page<Artist> findByCountry(Country country, Pageable pageable);
    Page<Artist> findByArtistNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Artist> findAllByOrderByFollowersDesc(Pageable pageable);
    Page<Artist> findByGenresContaining(Genre genre, Pageable pageable);
}
