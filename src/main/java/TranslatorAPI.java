import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;  // Importando a classe Scanner para entrada do usuário

public class TranslatorAPI {
    public static void main(String[] args) {
        try {
            // Criando um Scanner para capturar a frase e o idioma do usuário
            Scanner scanner = new Scanner(System.in);

            // Captura da frase do usuário
            System.out.println("Digite a frase que deseja traduzir:");
            String userInput = scanner.nextLine();  // Capturando a frase digitada

            // Captura do idioma de destino
            System.out.println("Digite o código do idioma de destino (ex: 'es' para espanhol, 'fr' para francês, 'en' para inglês):");
            String languageCode = scanner.nextLine();  // Capturando o código do idioma

            // Endpoint da API com o idioma de destino fornecido pelo usuário
            String endpoint = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&to=" + languageCode;

            // Crie a URL da API
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Definir o método como POST
            connection.setRequestMethod("POST");

            // Cabeçalhos obrigatórios
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", "ed9c94084d06436dad844478a5012971");  // Insira sua chave de API
            connection.setRequestProperty("Ocp-Apim-Subscription-Region", "brazilsouth");  // Exemplo: brazilsouth, westus
            connection.setRequestProperty("Content-Type", "application/json");


            // Permitir envio de dados na requisição
            connection.setDoOutput(true);

            // Corpo da requisição - O texto que você deseja traduzir
            String input = "[{\"Text\":\"" + userInput + "\"}]";  // Usando o texto inserido pelo usuário

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

            // Exibir a resposta
            System.out.println("Resposta da API: " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
