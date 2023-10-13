package com.betrybe.museumfinder.service;

import static com.betrybe.museumfinder.util.CoordinateUtil.isCoordinateValid;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Uma classe de serviço para gerenciar museus.
 */
@Service
public class MuseumService implements MuseumServiceInterface {

  private final MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  /**
   * Cria um novo museu.
   *
   * @param museum O museu a ser criado.
   * @return O museu criado.
   * @throws InvalidCoordinateException Se as coordenadas do museu forem inválidas.
   */
  @Override
  public Museum createMuseum(Museum museum) {
    Boolean isCoordinateValid = isCoordinateValid(museum.getCoordinate());
    if (isCoordinateValid) {
      return museumFakeDatabase.saveMuseum(museum);
    } else {
      throw new InvalidCoordinateException();
    }
  }

  /**
   * Obtém o museu mais próximo de uma coordenada específica
   * dentro de uma distância máxima especificada.
   *
   * @param coordinate   A coordenada de referência.
   * @param maxDistance  A distância máxima para procurar um museu.
   * @return O museu mais próximo, se encontrado.
   * @throws InvalidCoordinateException Se a coordenada fornecida for inválida.
   * @throws MuseumNotFoundException Se nenhum museu for encontrado
   */
  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    Boolean isCoordinateValid = isCoordinateValid(coordinate);
    if (!isCoordinateValid) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> closestMuseum = museumFakeDatabase.getClosestMuseum(coordinate, maxDistance);
    if (closestMuseum.isEmpty()) {
      throw new MuseumNotFoundException();
    } else {
      return closestMuseum.get();
    }
  }

  /**
   * Obtém o museu através do id.
   *
   * @param id o id do museu que deseja localizar.
   * @return O museu de acordo com o id.
   * @throws MuseumNotFoundException Se nenhum museu for encontrado.
   */
  @Override
  public Museum getMuseum(Long id) {
    Optional<Museum> museum = museumFakeDatabase.getMuseum(id);

    if (museum.isEmpty()) {
      throw new MuseumNotFoundException();
    } else {
      return museum.get();
    }
  }
}
