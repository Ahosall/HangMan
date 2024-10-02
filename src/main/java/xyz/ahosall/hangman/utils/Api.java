package xyz.ahosall.hangman.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class Api {

  private String baseUrl = "https://api.dicionario-aberto.net";
  private HttpClient client;
  private Gson gson;

  public Api() {
    this.client = HttpClient.newHttpClient();
    this.gson = new Gson();
  }

  public <T> T getData(String path, Class<T> clazz) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(this.baseUrl + path))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return gson.fromJson(response.body(), clazz);
  }
}
