package com.sparklecow.cowradio.controllers;

import com.sparklecow.cowradio.services.artist.ArtistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Artist Controller", description = "Controller for artist")
public class ArtistController {

    private final ArtistService artistService;
}
