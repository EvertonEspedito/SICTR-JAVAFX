package service;

import org.json.JSONObject;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;



public class ClimaService {

    public static String getDadosClimaticos(String cidade) throws Exception {
        String apiKey = Files.readString(
            Paths.get("api-key.txt")
        ).trim();


        String cidadeFormatada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
        String apiUrl = "http://api.weatherapi.com/v1/current.json?key="
                + apiKey + "&q=" + cidadeFormatada;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
    
    public static String getIconUrl(String dados) {
    JSONObject json = new JSONObject(dados);
    return "https:" + json
        .getJSONObject("current")
        .getJSONObject("condition")
        .getString("icon");
    }

    public static String formatarDados(String dados) {
        JSONObject json = new JSONObject(dados);

        if (json.has("error")) {
            return "Cidade não encontrada.";
        }

        JSONObject location = json.getJSONObject("location");
        JSONObject current = json.getJSONObject("current");

        return """
        Cidade: %s, %s
        Temperatura: %.1f °C
        Sensação térmica: %.1f °C
        Condição: %s
        Umidade: %d%%
        Vento: %.1f km/h
        Pressão: %.1f mb
        Atualizado em: %s
        """.formatted(
                location.getString("name"),
                location.getString("country"),
                current.getDouble("temp_c"),
                current.getDouble("feelslike_c"),
                current.getJSONObject("condition").getString("text"),
                current.getInt("humidity"),
                current.getDouble("wind_kph"),
                current.getDouble("pressure_mb"),
                current.getString("last_updated")
        );
    }
}
