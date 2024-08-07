package com.proz.pokeapi;

import java.io.IOException;
import java.util.ArrayList;

import com.proz.pokeapi.usecases.GetAPokemonUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proz.pokeapi.dto.Pokemon;
import com.proz.pokeapi.usecases.GetAllPokemonsUseCase;

@SpringBootApplication
@RestController
public class PokeApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(PokeApiApplication.class, args);
  }

  // Rota /pokemons com um parametro "size"
  @GetMapping("/pokemons")
  public ArrayList<Pokemon> getAllPokemons(@RequestParam(value = "size", defaultValue = "10") int size) {
    try {
      GetAllPokemonsUseCase useCase = new GetAllPokemonsUseCase();

      ArrayList<Pokemon> allPokemons = useCase.execute(size);

      return allPokemons;
    } catch (IOException | InterruptedException e) {
      // Retorna uma lista vazia
      return new ArrayList<Pokemon>();
    }
  }

  // Rota /pokemon com parametro index de pokemon
  @GetMapping("/pokemon/{index}")
  public Pokemon getAPokemon(@PathVariable("index") short index) {
    try {
      GetAPokemonUseCase useCase = new GetAPokemonUseCase();

      return useCase.execute(index);
    } catch (IOException | InterruptedException e) {
      // Retorna um objeto vazio
      return new Pokemon();
    }
  }
}
