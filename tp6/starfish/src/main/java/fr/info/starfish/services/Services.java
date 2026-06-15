package fr.info.starfish.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import org.springframework.stereotype.Service;
import fr.info.starfish.modele.Infrastructure; 
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Services {

  private List<Infrastructure> listeInfrastructure;
  private final OkHttpClient httpClient;
  private static final Logger logger = LoggerFactory.getLogger(Services.class);

  private String[] titre = {
    "Asterix gladiateur",
    "Lucky Luke, Sur la piste des Dalton",
    "Garfield prend du poids",
    "L'Anniversaire d'Asterix et Obelix"
  };
  private String[] auteur = {
    "R. Goscinny, A. Uderzo",
    "Morris, R. Goscinny",
    "J. Davis",
    "R. Goscinny, A. Uderzo"
  };
  private String categorie = "bande dessinee";

  public Services() {
    listeInfrastructure = new ArrayList<>();
    init();
    httpClient = new OkHttpClient();
  }

  public void init() {
      Infrastructure i = new Infrastructure();
      i.init("3", "France Paris", "Paris", "Data Center", "National", "dn=460", "Premier pays europeen");
      listeInfrastructure.add(i);
  }

  public List<Infrastructure> liste() {
    return listeInfrastructure;
  }

  public Infrastructure selection() {
      Infrastructure i = new Infrastructure();
      i.init("3", "France Paris", "Paris", "Data Center", "National", "dn=460", "Premier pays europeen");
      return i;
  }

  public Infrastructure ajouterInfrastructure(Infrastructure Infrastructure) {
    listeInfrastructure.add(Infrastructure);
    return Infrastructure;
  }

  public JSONObject search(String query) throws IOException {
    String encodedQuery = java.net.URLEncoder.encode(query, "UTF-8"); 
    Request request = new Request.Builder()
    .url("https://nominatim.openstreetmap.org/search?format=json&q=" + encodedQuery)
    .header("User-Agent", "starfish/1.0 (ton.mail@etu.univ-paris13.fr)")
    .build();
    try (Response response = httpClient.newCall(request).execute()) {
        if (!response.isSuccessful()) {
            logger.warn("Nominatim code inattendu : {}", response.code());
            return null;
        }
        JSONArray jsonArray = new JSONArray(response.body().string());
        logger.info("Nominatim : {} résultat(s) pour '{}'", jsonArray.length(), query);
        if (jsonArray.length() > 0) {
            return jsonArray.getJSONObject(0);
        }
        return null;
    }
}
}