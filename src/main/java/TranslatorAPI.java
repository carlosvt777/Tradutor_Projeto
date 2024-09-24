import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONObject;

public class TranslatorAPI {

    // Método estático para realizar a tradução
    public static String translate(String text, String languageCode) {
        try {
            // Endpoint da API com o idioma de destino fornecido pelo usuário
            String endpoint = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&to=" + languageCode;

            // Criar a URL da API
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Definir o método como POST
            connection.setRequestMethod("POST");

            // Cabeçalhos obrigatórios
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", "ed9c94084d06436dad844478a5012971");  // Insira sua chave de API
            connection.setRequestProperty("Ocp-Apim-Subscription-Region", "brazilsouth");
            connection.setRequestProperty("Content-Type", "application/json");

            // Permitir envio de dados na requisição
            connection.setDoOutput(true);

            // Corpo da requisição - O texto que você deseja traduzir
            String input = "[{\"Text\":\"" + text + "\"}]";

            // Enviar a requisição
            OutputStream os = connection.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            os.close();

            // Ler a resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseLine;
            StringBuilder response = new StringBuilder();
            while ((responseLine = in.readLine()) != null) {
                response.append(responseLine);
            }
            in.close();



            // Parsear o JSON de resposta
            JSONArray jsonArray = new JSONArray(response.toString());
            String translatedText = jsonArray.getJSONObject(0).getJSONArray("translations").getJSONObject(0).getString("text");

            return translatedText; // Retornar o texto traduzido

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao traduzir";  // Retornar uma mensagem de erro se algo der errado
        }
    }
}
