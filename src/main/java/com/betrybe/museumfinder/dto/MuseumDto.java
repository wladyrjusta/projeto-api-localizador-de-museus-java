package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * Esta é a classe MuseumDto para transferencia
 * de dados do tipo Museum entre aplicação, usuários e banco de dados.
 */

public record MuseumDto(
    Long id,
    String name,
    String description,
    String address,
    String collectionType,
    String subject,
    String url,
    Coordinate coordinate
) {}
