package com.betrybe.museumfinder.controller;

import static com.betrybe.museumfinder.util.ModelDtoConverter.dtoToModel;
import static com.betrybe.museumfinder.util.ModelDtoConverter.modelToDto;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe de controle para lidar com requisições HTTP relacionadas a museus.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  private final MuseumServiceInterface museumService;

  /**
   * Construtor para MuseumController.
   *
   * @param museumService A implementação da interface MuseumServiceInterface.
   */
  @Autowired
    public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * Cria um novo museu.
   *
   * @param museumCreationDto O MuseumCreationDto contendo os dados do museu.
   * @return ResponseEntity com o objeto Museum criado.
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumCreationDto) {
    Museum newMuseum = dtoToModel(museumCreationDto);

    Museum newMuseumFromDb = museumService.createMuseum(newMuseum);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(newMuseumFromDb);
  }

  /**
   * Obtém o museu mais próximo com base na latitude, longitude e distância máxima.
   *
   * @param lat       A coordenada de latitude.
   * @param lng       A coordenada de longitude.
   * @param maxDistKm A distância máxima em quilômetros.
   * @return ResponseEntity com o MuseumDto mais próximo.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam Double lat, @RequestParam Double lng,
      @RequestParam(name = "max_dist_km") Double maxDistKm
  ) {
    Coordinate coordinate = new Coordinate(lat, lng);
    Museum closestMuseum = museumService.getClosestMuseum(coordinate, maxDistKm);
    MuseumDto museumDto = modelToDto(closestMuseum);
    return ResponseEntity.ok(museumDto);
  }

  /**
   * Obtém o museu através do id.
   *
   * @param id o id do museu que deseja localizar.
   * @return O museu de acordo com o id ou status 404.
   */
  @GetMapping("/{id}")
  public ResponseEntity<MuseumDto> getMuseum(@PathVariable Long id) {
    Museum museum = museumService.getMuseum(id);
    MuseumDto museumDto = modelToDto(museum);
    return ResponseEntity.ok(museumDto);
  }
}
