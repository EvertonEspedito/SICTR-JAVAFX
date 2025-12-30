package app;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.concurrent.Task;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import service.ClimaService;

// COMPILAR: javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.graphics -cp lib/json-20230618.jar -d out src/service/ClimaService.java src/app/ProjetoSICTR.java
// EXECUTAR: java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.graphics -cp out:lib/json-20230618.jar app.ProjetoSICTR

public class ProjetoSICTR extends Application {

    private ProgressIndicator loader;

    @Override
    public void start(Stage palco) {

        ImageView iconeClima = new ImageView();
        iconeClima.setFitWidth(64);
        iconeClima.setFitHeight(64);

        TextField campoCidade = new TextField();
        campoCidade.setPromptText("Digite o nome da cidade");

        Button botaoBuscar = new Button("Buscar Clima");

        TextArea areaResultado = new TextArea();
        areaResultado.setEditable(false);
        areaResultado.setWrapText(true);

        // ✅ inicializa o loader da CLASSE
        loader = new ProgressIndicator();
        loader.setVisible(false);
        loader.setPrefSize(90, 90);

        botaoBuscar.setOnAction(e -> {

            String cidade = campoCidade.getText();

            if (cidade.isEmpty()) {
                areaResultado.setText("Digite uma cidade.");
                return;
            }

            loader.setVisible(true);
            botaoBuscar.setDisable(true);
            areaResultado.clear();
            iconeClima.setImage(null);

            Task<String> tarefaClima = new Task<>() {
                @Override
                protected String call() throws Exception {
                    String json = ClimaService.getDadosClimaticos(cidade);
                    return ClimaService.formatarDados(json);
                }
            };

            tarefaClima.setOnSucceeded(ev -> {
                areaResultado.setText(tarefaClima.getValue());
                loader.setVisible(false);
                botaoBuscar.setDisable(false);
            });

            tarefaClima.setOnFailed(ev -> {
                areaResultado.setText("Erro ao buscar dados climáticos.");
                loader.setVisible(false);
                botaoBuscar.setDisable(false);
            });

            new Thread(tarefaClima).start();
        });

        VBox layout = new VBox(
            15,
            campoCidade,
            botaoBuscar,
            loader,
            iconeClima,
            areaResultado
        );

        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center;");

        Scene cena = new Scene(layout, 420, 450);
        cena.getStylesheets().add(
            getClass().getResource("/style.css").toExternalForm()
        );

        palco.setTitle("SICTR - Sistema Climático em Tempo Real");
        palco.setScene(cena);
        palco.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
