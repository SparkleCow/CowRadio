package com.sparklecow.cowradio.controllers;

import com.sparklecow.cowradio.entities.Country;
import com.sparklecow.cowradio.entities.Genre;
import com.sparklecow.cowradio.models.dtos.artist.ArtistListResponse;
import com.sparklecow.cowradio.models.dtos.artist.ArtistRequest;
import com.sparklecow.cowradio.models.dtos.artist.ArtistResponse;
import com.sparklecow.cowradio.services.artist.ArtistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Artist Controller", description = "Controller for artist")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<Page<ArtistListResponse>> findAllArtist(@RequestParam(name = "artist", defaultValue = "") String artistName,
                                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                                  @RequestParam(name = "size", defaultValue = "20") int size){
        return ResponseEntity.ok(artistService.findArtistByNameContaining(artistName, page, size));
    }

    @GetMapping("/country")
    public ResponseEntity<Page<ArtistListResponse>> findArtistByCountry(@RequestParam(name = "country") Country country,
                                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                                        @RequestParam(name = "size", defaultValue = "20") int size){
        return ResponseEntity.ok(artistService.findArtistByCountry(country, page, size));
    }

    @GetMapping("/genre")
    public ResponseEntity<Page<ArtistListResponse>> findArtistByGenre(@RequestParam(name = "genre") Genre genre,
                                                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                                                      @RequestParam(name = "size", defaultValue = "20") int size){
        return ResponseEntity.ok(artistService.findArtistByGenre(genre, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponse> findArtistById(@PathVariable Integer id){
        return ResponseEntity.ok(artistService.findArtistById(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createArtist(@RequestBody ArtistRequest artistRequest, Authentication authentication){
        return ResponseEntity.ok(artistService.createArtist(artistRequest, authentication));
    }

}
