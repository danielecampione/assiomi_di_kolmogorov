package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import gui.panels.*;

public class MainWindow {
    private Stage stage;
    private BorderPane mainLayout;
    private VBox contentArea;
    
    public void show(Stage primaryStage) {
        this.stage = primaryStage;
        setupUI();
        
        stage.setTitle("Assiomi di Kolmogorov - Teoria della Probabilità");
        stage.setMaximized(true);
        stage.show();
    }
    
    private void setupUI() {
        mainLayout = new BorderPane();
        mainLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        // Menu superiore
        MenuBar menuBar = createMenuBar();
        mainLayout.setTop(menuBar);
        
        // Pannello laterale con navigazione
        VBox sidebar = createSidebar();
        mainLayout.setLeft(sidebar);
        
        // Area contenuto principale
        contentArea = new VBox();
        contentArea.setPadding(new Insets(20));
        contentArea.setSpacing(15);
        contentArea.setMaxWidth(Double.MAX_VALUE);
        contentArea.setFillWidth(true);
        
        ScrollPane scrollPane = new ScrollPane(contentArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Aumenta la velocità di scorrimento
        scrollPane.vvalueProperty().addListener((obs, oldVal, newVal) -> {
            scrollPane.setVvalue(newVal.doubleValue());
        });

        final double SPEED = 0.0017; // Velocità ridotta a 1/3 dell'originale (0.005/3)
        scrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
        });
        mainLayout.setCenter(scrollPane);
        
        // Mostra introduzione iniziale
        showIntroduction();
        
        Scene scene = new Scene(mainLayout);
stage.setMaximized(true);
        
        // Carica il CSS in modo sicuro
        try {
            String cssPath = getClass().getResource("/styles.css").toExternalForm();
            scene.getStylesheets().add(cssPath);
        } catch (Exception e) {
            System.out.println("Avviso: File CSS non trovato, utilizzando stili predefiniti.");
        }
        
        stage.setScene(scene);
    }
    
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Esci");
        exitItem.setOnAction(e -> stage.close());
        fileMenu.getItems().add(exitItem);
        
        Menu helpMenu = new Menu("Aiuto");
        MenuItem aboutItem = new MenuItem("Informazioni");
        aboutItem.setOnAction(e -> showAbout());
        helpMenu.getItems().add(aboutItem);
        
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }
    
    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(10));
        sidebar.setSpacing(10);
        sidebar.setPrefWidth(250);
        sidebar.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 0 1 0 0;");
        
        Label title = new Label("Navigazione");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        Button introBtn = new Button("Introduzione");
        introBtn.setPrefWidth(200);
        introBtn.setOnAction(e -> showIntroduction());
        
        Button axiom1Btn = new Button("Primo Assioma");
        axiom1Btn.setPrefWidth(200);
        axiom1Btn.setOnAction(e -> showAxiom1());
        
        Button axiom2Btn = new Button("Secondo Assioma");
        axiom2Btn.setPrefWidth(200);
        axiom2Btn.setOnAction(e -> showAxiom2());
        
        Button axiom3Btn = new Button("Terzo Assioma");
        axiom3Btn.setPrefWidth(200);
        axiom3Btn.setOnAction(e -> showAxiom3());
        
        Button examplesBtn = new Button("Esempi Pratici");
        examplesBtn.setPrefWidth(200);
        examplesBtn.setOnAction(e -> showExamples());
        
        Button simulationBtn = new Button("Simulazioni");
        simulationBtn.setPrefWidth(200);
        simulationBtn.setOnAction(e -> showSimulations());
        
        sidebar.getChildren().addAll(title, new Separator(), 
                                   introBtn, axiom1Btn, axiom2Btn, 
                                   axiom3Btn, examplesBtn, simulationBtn);
        
        return sidebar;
    }
    
    private void showIntroduction() {
        contentArea.getChildren().clear();
        IntroductionPanel panel = new IntroductionPanel();
        contentArea.getChildren().add(createCompactScrollablePane(panel.getContent()));
    }

    private void showAxiom1() {
        contentArea.getChildren().clear();
        Axiom1Panel panel = new Axiom1Panel();
        contentArea.getChildren().add(createCompactScrollablePane(panel.getContent()));
    }

    private void showAxiom2() {
        contentArea.getChildren().clear();
        Axiom2Panel panel = new Axiom2Panel();
        contentArea.getChildren().add(createCompactScrollablePane(panel.getContent()));
    }

    private void showAxiom3() {
        contentArea.getChildren().clear();
        Axiom3Panel panel = new Axiom3Panel();
        contentArea.getChildren().add(createCompactScrollablePane(panel.getContent()));
    }

    private void showExamples() {
        contentArea.getChildren().clear();
        ExamplesPanel panel = new ExamplesPanel();
        contentArea.getChildren().add(createCompactScrollablePane(panel.getContent()));
    }

    private void showSimulations() {
        contentArea.getChildren().clear();
        SimulationPanel panel = new SimulationPanel();
        contentArea.getChildren().add(createCompactScrollablePane(panel.getContent()));
    }

    private Node createCompactScrollablePane(Node content) {
        // Crea un riquadro compatto con bordi e sfondo
        VBox container = new VBox();
        container.getStyleClass().add("compact-container");
        
        // Imposta dimensioni fisse per il riquadro
        container.setPrefWidth(800);
        container.setMaxWidth(800);
        container.setPrefHeight(700); // Aumentata l'altezza per ridurre le barre di scorrimento
        container.setMaxHeight(700);
        
        // Crea ScrollPane per il contenuto
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.getStyleClass().add("compact-scrollpane");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        // Velocità di scorrimento migliorata e rallentata
        scrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * 0.0027; // Ridotta a 1/3 della velocità originale
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
        });
        
        container.getChildren().add(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        
        // Centra il container nell'area principale
        HBox centeringBox = new HBox();
        centeringBox.setAlignment(javafx.geometry.Pos.CENTER);
        centeringBox.setPadding(new Insets(20));
        centeringBox.getChildren().add(container);
        
        return centeringBox;
    }
    
    private void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informazioni");
        alert.setHeaderText("Assiomi di Kolmogorov");
        alert.setContentText("Applicazione educativa per l'apprendimento degli Assiomi di Kolmogorov\n" +
                           "nella teoria della probabilità.\n\n" +
                           "Sviluppata per studenti di informatica degli istituti tecnici.");
        alert.showAndWait();
    }
}