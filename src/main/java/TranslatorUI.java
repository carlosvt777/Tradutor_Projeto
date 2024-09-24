import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TranslatorUI {
    public static void main(String[] args) {
        // Criando a janela principal
        JFrame frame = new JFrame("Tradutor");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando o painel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Tornando a janela visível
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Rótulo para a frase
        JLabel userLabel = new JLabel("Frase:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // Campo de texto para a frase
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // Rótulo para o código do idioma
        JLabel langLabel = new JLabel("Idioma:");
        langLabel.setBounds(10, 50, 80, 25);
        panel.add(langLabel);

        // ComboBox para selecionar o idioma
        String[] languages = { "en", "es", "fr", "de", "it", "pt", "ru", "zh-Hans" }; // Lista de idiomas suportados
        JComboBox<String> langComboBox = new JComboBox<>(languages);
        langComboBox.setBounds(100, 50, 165, 25);
        panel.add(langComboBox);

        // Botão para enviar a tradução
        JButton translateButton = new JButton("Traduzir");
        translateButton.setBounds(10, 80, 150, 25);
        panel.add(translateButton);

        // Área de texto para exibir a tradução
        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 120, 360, 100);
        resultArea.setLineWrap(true); // Quebra de linha automática
        resultArea.setWrapStyleWord(true); // Quebra em palavras inteiras
        resultArea.setEditable(false); // Impede a edição pelo usuário
        panel.add(resultArea);



        // Ação do botão
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter os dados do usuário
                String phrase = userText.getText();
                String lang = (String) langComboBox.getSelectedItem(); // Pegar o idioma selecionado

                // Chamar o método de tradução da API
                String translatedText = TranslatorAPI.translate(phrase, lang);

                // Exibir o resultado na área de texto
                resultArea.setText(translatedText);
            }
        });
    }
}
