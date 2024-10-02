package xyz.ahosall.hangman.utils;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import xyz.ahosall.hangman.structures.RandomWord;
import xyz.ahosall.hangman.structures.Word;

public class Api {

  private String baseUrl = "https://api.dicionario-aberto.net";
  private HttpClient client;
  private Gson gson;

  public Api() {
    this.client = HttpClient.newHttpClient();
    this.gson = new Gson();
  }

  <T> T getData(String path, Class<T> clazz) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(this.baseUrl + path))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return gson.fromJson(response.body(), clazz);
  }

  public RandomWord getRandomWord() throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(this.baseUrl + "/random"))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return gson.fromJson(response.body(), RandomWord.class);
  }

  public List<Word> getDefinitionWord(String word) throws Exception {
    String uri = this.baseUrl + "/word/" + word;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    Type listType = TypeToken.getParameterized(List.class, Word.class).getType();
    return gson.fromJson(response.body(), listType);
  }
}
