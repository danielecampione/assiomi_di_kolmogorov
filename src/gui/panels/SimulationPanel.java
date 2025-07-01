package gui.panels;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.MathUtils;
import business.SimulationEngine;

import java.util.Random;

public class SimulationPanel implements BasePanel {
    private SimulationEngine simulationEngine;
    private Random random;
    
    // Componenti UI per la simulazione del dado
    private Label diceResultLabel;
    private BarChart<String, Number> diceChart;
    private XYChart.Series<String, Number> diceSeries;
    private Button rollDiceButton;
    private Button resetDiceButton;
    private Label diceStatsLabel;
    
    // Componenti UI per la simulazione della moneta
    private Label coinResultLabel;
    private PieChart coinChart;
    private Button flipCoinButton;
    private Button resetCoinButton;
    private Label coinStatsLabel;
    
    // Componenti UI per la simulazione di eventi mutuamente esclusivi
    private BarChart<String, Number> mutuallyExclusiveChart;
    private XYChart.Series<String, Number> mutuallyExclusiveSeries;
    private Button simulateMutuallyExclusiveButton;
    private Label mutuallyExclusiveStatsLabel;
    
    public SimulationPanel() {
        this.simulationEngine = new SimulationEngine();
        this.random = new Random();
    }
    
    @Override
    public Node getContent() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        content.setMaxWidth(750);
        
        // Titolo
        Label title = new Label("Simulazioni Interattive degli Assiomi di Kolmogorov");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));
        
        // Introduzione
        TextArea introArea = new TextArea("Queste simulazioni ti permettono di vedere gli assiomi di Kolmogorov in azione. " +
                            "Esegui le simulazioni e osserva come i risultati si avvicinano alle probabilità teoriche " +
                            "man mano che aumenta il numero di prove.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(3);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Simulazione 1: Lancio del dado
        TitledPane diceSimulation = createDiceSimulation();
        
        // Simulazione 2: Lancio della moneta
        TitledPane coinSimulation = createCoinSimulation();
        
        // Simulazione 3: Eventi mutuamente esclusivi
        TitledPane mutuallyExclusiveSimulation = createMutuallyExclusiveSimulation();
        
        content.getChildren().addAll(title, introArea, diceSimulation, coinSimulation, mutuallyExclusiveSimulation);
        return content;
    }
    
    private TitledPane createDiceSimulation() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea descriptionArea = new TextArea();
        descriptionArea.setText("Simulazione del lancio di un dado a 6 facce. Osserva come le frequenze " +
                                  "si avvicinano alle probabilità teoriche (1/6 ≈ 0.167 per ogni faccia) " +
                                  "man mano che aumenta il numero di lanci.");
        descriptionArea.setWrapText(true);
        descriptionArea.setEditable(false);
        descriptionArea.setPrefRowCount(3);
        descriptionArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Controlli
        HBox controls = new HBox(10);
        rollDiceButton = new Button("Lancia Dado");
        resetDiceButton = new Button("Reset");
        diceResultLabel = new Label("Risultato: -");
        diceResultLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        controls.getChildren().addAll(rollDiceButton, resetDiceButton, diceResultLabel);
        
        // Grafico
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 1, 0.1);
        xAxis.setLabel("Faccia del Dado");
        yAxis.setLabel("Frequenza Relativa");
        
        diceChart = new BarChart<>(xAxis, yAxis);
        diceChart.setTitle("Frequenze Relative del Lancio del Dado");
        
        diceSeries = new XYChart.Series<>();
        diceSeries.setName("Frequenza Osservata");
        for (int i = 1; i <= 6; i++) {
            diceSeries.getData().add(new XYChart.Data<>(String.valueOf(i), 0.0));
        }
        diceChart.getData().add(diceSeries);
        
        // Statistiche
        diceStatsLabel = new Label("Lanci totali: 0\nVerifica secondo assioma: Somma frequenze = 0.000");
        
        // Event handlers
        rollDiceButton.setOnAction(e -> rollDice());
        resetDiceButton.setOnAction(e -> resetDiceSimulation());
        
        content.getChildren().addAll(descriptionArea, controls, diceChart, diceStatsLabel);
        
        TitledPane titledPane = new TitledPane("Simulazione 1: Lancio del Dado", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createCoinSimulation() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea descriptionArea = new TextArea();
        descriptionArea.setText("Simulazione del lancio di una moneta. Osserva come le frequenze di testa e croce " +
                                  "si avvicinano a 0.5 ciascuna, e come la loro somma è sempre 1.0 (secondo assioma).");
        descriptionArea.setWrapText(true);
        descriptionArea.setEditable(false);
        descriptionArea.setPrefRowCount(2);
        descriptionArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Controlli
        HBox controls = new HBox(10);
        flipCoinButton = new Button("Lancia Moneta");
        resetCoinButton = new Button("Reset");
        coinResultLabel = new Label("Risultato: -");
        coinResultLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        controls.getChildren().addAll(flipCoinButton, resetCoinButton, coinResultLabel);
        
        // Grafico a torta
        coinChart = new PieChart();
        coinChart.setTitle("Distribuzione Lanci Moneta");
        
        PieChart.Data headsData = new PieChart.Data("Testa", 0);
        PieChart.Data tailsData = new PieChart.Data("Croce", 0);
        coinChart.getData().addAll(headsData, tailsData);
        
        // Statistiche
        coinStatsLabel = new Label("Lanci totali: 0\nTesta: 0 (0.000)\nCroce: 0 (0.000)\nSomma: 0.000");
        
        // Event handlers
        flipCoinButton.setOnAction(e -> flipCoin());
        resetCoinButton.setOnAction(e -> resetCoinSimulation());
        
        content.getChildren().addAll(descriptionArea, controls, coinChart, coinStatsLabel);
        
        TitledPane titledPane = new TitledPane("Simulazione 2: Lancio della Moneta", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createMutuallyExclusiveSimulation() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea descriptionArea = new TextArea();
        descriptionArea.setText("Simulazione di eventi mutuamente esclusivi. Simula l'estrazione di carte da un mazzo " +
                                  "e osserva come la probabilità dell'unione di eventi mutuamente esclusivi " +
                                  "(es. P(Asso ∪ Re)) sia uguale alla somma delle probabilità individuali.");
        descriptionArea.setWrapText(true);
        descriptionArea.setEditable(false);
        descriptionArea.setPrefRowCount(3);
        descriptionArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Controlli
        HBox controls = new HBox(10);
        simulateMutuallyExclusiveButton = new Button("Estrai Carta");
        Button resetMutuallyExclusiveButton = new Button("Reset");
        
        controls.getChildren().addAll(simulateMutuallyExclusiveButton, resetMutuallyExclusiveButton);
        
        // Grafico
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 0.2, 0.02);
        xAxis.setLabel("Tipo di Carta");
        yAxis.setLabel("Frequenza Relativa");
        
        mutuallyExclusiveChart = new BarChart<>(xAxis, yAxis);
        mutuallyExclusiveChart.setTitle("Frequenze Relative Estrazione Carte");
        
        mutuallyExclusiveSeries = new XYChart.Series<>();
        mutuallyExclusiveSeries.setName("Frequenza Osservata");
        mutuallyExclusiveSeries.getData().add(new XYChart.Data<>("Asso", 0.0));
        mutuallyExclusiveSeries.getData().add(new XYChart.Data<>("Re", 0.0));
        mutuallyExclusiveSeries.getData().add(new XYChart.Data<>("Regina", 0.0));
        mutuallyExclusiveSeries.getData().add(new XYChart.Data<>("Jack", 0.0));
        mutuallyExclusiveSeries.getData().add(new XYChart.Data<>("Altre", 0.0));
        mutuallyExclusiveChart.getData().add(mutuallyExclusiveSeries);
        
        // Statistiche
        mutuallyExclusiveStatsLabel = new Label("Estrazioni totali: 0\n" +
                                              "P(Asso): 0.000 (teorica: 0.077)\n" +
                                              "P(Re): 0.000 (teorica: 0.077)\n" +
                                              "P(Asso ∪ Re): 0.000 (teorica: 0.154)");
        
        // Event handlers
        simulateMutuallyExclusiveButton.setOnAction(e -> drawCard());
        resetMutuallyExclusiveButton.setOnAction(e -> resetMutuallyExclusiveSimulation());
        
        content.getChildren().addAll(descriptionArea, controls, mutuallyExclusiveChart, mutuallyExclusiveStatsLabel);
        
        TitledPane titledPane = new TitledPane("Simulazione 3: Eventi Mutuamente Esclusivi", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private void rollDice() {
        int result = simulationEngine.rollDice();
        diceResultLabel.setText("Risultato: " + result);
        
        // Aggiorna il grafico
        updateDiceChart();
        updateDiceStats();
    }
    
    private void updateDiceChart() {
        int[] counts = simulationEngine.getDiceCounts();
        int total = simulationEngine.getDiceTotal();
        
        for (int i = 0; i < 6; i++) {
            double frequency = total > 0 ? (double) counts[i] / total : 0.0;
            diceSeries.getData().get(i).setYValue(frequency);
        }
    }
    
    private void updateDiceStats() {
        int total = simulationEngine.getDiceTotal();
        int[] counts = simulationEngine.getDiceCounts();
        
        double sum = 0.0;
        for (int count : counts) {
            sum += total > 0 ? (double) count / total : 0.0;
        }
        
        diceStatsLabel.setText(String.format("Lanci totali: %d\nVerifica secondo assioma: Somma frequenze = %.3f", 
                                            total, sum));
    }
    
    private void resetDiceSimulation() {
        simulationEngine.resetDice();
        diceResultLabel.setText("Risultato: -");
        updateDiceChart();
        updateDiceStats();
    }
    
    private void flipCoin() {
        boolean isHeads = simulationEngine.flipCoin();
        coinResultLabel.setText("Risultato: " + (isHeads ? "Testa" : "Croce"));
        
        updateCoinChart();
        updateCoinStats();
    }
    
    private void updateCoinChart() {
        int heads = simulationEngine.getCoinHeads();
        int tails = simulationEngine.getCoinTails();
        
        coinChart.getData().get(0).setPieValue(heads);
        coinChart.getData().get(1).setPieValue(tails);
    }
    
    private void updateCoinStats() {
        int heads = simulationEngine.getCoinHeads();
        int tails = simulationEngine.getCoinTails();
        int total = heads + tails;
        
        double headsFreq = total > 0 ? (double) heads / total : 0.0;
        double tailsFreq = total > 0 ? (double) tails / total : 0.0;
        double sum = headsFreq + tailsFreq;
        
        coinStatsLabel.setText(String.format("Lanci totali: %d\nTesta: %d (%.3f)\nCroce: %d (%.3f)\nSomma: %.3f",
                                            total, heads, headsFreq, tails, tailsFreq, sum));
    }
    
    private void resetCoinSimulation() {
        simulationEngine.resetCoin();
        coinResultLabel.setText("Risultato: -");
        updateCoinChart();
        updateCoinStats();
    }
    
    private void drawCard() {
        String cardType = simulationEngine.drawCard();
        
        updateMutuallyExclusiveChart();
        updateMutuallyExclusiveStats();
    }
    
    private void updateMutuallyExclusiveChart() {
        int[] counts = simulationEngine.getCardCounts();
        int total = simulationEngine.getCardTotal();
        
        String[] cardTypes = {"Asso", "Re", "Regina", "Jack", "Altre"};
        
        for (int i = 0; i < cardTypes.length; i++) {
            double frequency = total > 0 ? (double) counts[i] / total : 0.0;
            mutuallyExclusiveSeries.getData().get(i).setYValue(frequency);
        }
    }
    
    private void updateMutuallyExclusiveStats() {
        int[] counts = simulationEngine.getCardCounts();
        int total = simulationEngine.getCardTotal();
        
        double aceFreq = total > 0 ? (double) counts[0] / total : 0.0;
        double kingFreq = total > 0 ? (double) counts[1] / total : 0.0;
        double unionFreq = aceFreq + kingFreq;
        
        mutuallyExclusiveStatsLabel.setText(String.format(
            "Estrazioni totali: %d\n" +
            "P(Asso): %.3f (teorica: 0.077)\n" +
            "P(Re): %.3f (teorica: 0.077)\n" +
            "P(Asso ∪ Re): %.3f (teorica: 0.154)",
            total, aceFreq, kingFreq, unionFreq));
    }
    
    private void resetMutuallyExclusiveSimulation() {
        simulationEngine.resetCards();
        updateMutuallyExclusiveChart();
        updateMutuallyExclusiveStats();
    }
}