package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe de conselho para tratamento de exceções gerais na aplicação.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Trata exceções relacionadas a coordenadas inválidas.
   *
   * @param exception A exceção de coordenada inválida.
   * @return ResponseEntity com status HTTP de Bad Request e mensagem de erro.
   */
  @ExceptionHandler({ InvalidCoordinateException.class })
  public ResponseEntity<String> handlerInvalidCoordinate(RuntimeException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Coordenada inválida!");
  }

  /**
   * Trata exceções relacionadas a museus não encontrados.
   *
   * @param exception A exceção de museu não encontrado.
   * @return ResponseEntity com status HTTP de Not Found e mensagem de erro.
   */
  @ExceptionHandler({ MuseumNotFoundException.class })
  public ResponseEntity<String> handlerMuseumNotFound(RuntimeException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Museu não encontrado!");
  }

  /**
   * Trata outras exceções não especificadas.
   *
   * @param exception A exceção genérica.
   * @return ResponseEntity com status HTTP de Internal Server Error e mensagem de erro.
   */
  @ExceptionHandler({ Exception.class })
  public ResponseEntity<String> handlerOtherExceptions(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}
