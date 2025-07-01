package gui.panels;

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

public class ExamplesPanel implements BasePanel {
    
    @Override
    public Node getContent() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        content.setMaxWidth(750);
        
        // Titolo
        Label title = new Label("Esempi Pratici degli Assiomi di Kolmogorov");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));
        
        // Esempio 1: Sistema informatico
        TitledPane computerSystem = createComputerSystemExample();
        
        // Esempio 2: Rete di comunicazione
        TitledPane networkExample = createNetworkExample();
        
        // Esempio 3: Algoritmo di machine learning
        TitledPane mlExample = createMachineLearningExample();
        
        // Esempio 4: Sicurezza informatica
        TitledPane securityExample = createSecurityExample();
        
        // Esempio 5: Analisi di algoritmi
        TitledPane algorithmExample = createAlgorithmExample();
        
        content.getChildren().addAll(title, computerSystem, networkExample, mlExample, securityExample, algorithmExample);
        return content;
    }
    
    private TitledPane createComputerSystemExample() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea();
        introArea.setText("Consideriamo un sistema informatico che può trovarsi in diversi stati di funzionamento. " +
                            "Analizziamo come gli assiomi di Kolmogorov si applicano a questo scenario.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(2);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Definizione del problema
        Label problemLabel = new Label("Definizione del Problema");
        problemLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea problemArea = new TextArea();
        problemArea.setText("Un server può trovarsi in uno dei seguenti stati:\n" +
                              "• Funzionamento normale (N)\n" +
                              "• Sovraccarico (S)\n" +
                              "• Errore temporaneo (E)\n" +
                              "• Guasto critico (G)\n\n" +
                              "Basandoci su dati storici, abbiamo le seguenti probabilità:\n" +
                              "P(N) = 0.7, P(S) = 0.2, P(E) = 0.08, P(G) = 0.02");
        problemArea.setWrapText(true);
        problemArea.setEditable(false);
        problemArea.setPrefRowCount(8);
        problemArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Applicazione degli assiomi
        Label axiomsLabel = new Label("Applicazione degli Assiomi");
        axiomsLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        VBox axiomsBox = new VBox(10);
        
        TextArea axiom1Area = new TextArea();
        axiom1Area.setText("Primo Assioma: Tutte le probabilità sono non negative\n" +
                                "P(N) = 0.7 ≥ 0 ✓\n" +
                                "P(S) = 0.2 ≥ 0 ✓\n" +
                                "P(E) = 0.08 ≥ 0 ✓\n" +
                                "P(G) = 0.02 ≥ 0 ✓");
        axiom1Area.setWrapText(true);
        axiom1Area.setEditable(false);
        axiom1Area.setPrefRowCount(6);
        axiom1Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        TextArea axiom2Area = new TextArea();
        axiom2Area.setText("Secondo Assioma: La somma di tutte le probabilità è 1\n" +
                                "P(N) + P(S) + P(E) + P(G) = 0.7 + 0.2 + 0.08 + 0.02 = 1.0 ✓");
        axiom2Area.setWrapText(true);
        axiom2Area.setEditable(false);
        axiom2Area.setPrefRowCount(2);
        axiom2Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        TextArea axiom3Area = new TextArea();
        axiom3Area.setText("Terzo Assioma: Probabilità di eventi mutuamente esclusivi\n" +
                                "P(sistema non normale) = P(S ∪ E ∪ G) = P(S) + P(E) + P(G)\n" +
                                "= 0.2 + 0.08 + 0.02 = 0.3");
        axiom3Area.setWrapText(true);
        axiom3Area.setEditable(false);
        axiom3Area.setPrefRowCount(3);
        axiom3Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        axiomsBox.getChildren().addAll(axiom1Area, new Separator(), axiom2Area, new Separator(), axiom3Area);
        
        // Grafico a torta
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Distribuzione degli Stati del Sistema");
        
        PieChart.Data normal = new PieChart.Data("Normale (70%)", 70);
        PieChart.Data overload = new PieChart.Data("Sovraccarico (20%)", 20);
        PieChart.Data error = new PieChart.Data("Errore (8%)", 8);
        PieChart.Data critical = new PieChart.Data("Guasto (2%)", 2);
        
        pieChart.getData().addAll(normal, overload, error, critical);
        
        content.getChildren().addAll(introArea, new Separator(), problemLabel, problemArea, 
                                   new Separator(), axiomsLabel, axiomsBox, pieChart);
        
        TitledPane titledPane = new TitledPane("Esempio 1: Stati di un Sistema Informatico", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createNetworkExample() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea("Analizziamo la trasmissione di pacchetti in una rete di comunicazione e come " +
                            "gli assiomi di Kolmogorov ci aiutano a modellare la probabilità di successo.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(2);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Scenario
        Label scenarioLabel = new Label("Scenario di Rete");
        scenarioLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea scenarioArea = new TextArea("Un pacchetto di dati può essere:\n" +
                               "• Trasmesso con successo (S): P(S) = 0.85\n" +
                               "• Perso durante la trasmissione (P): P(P) = 0.10\n" +
                               "• Corrotto durante la trasmissione (C): P(C) = 0.05\n\n" +
                               "Questi eventi sono mutuamente esclusivi: un pacchetto non può essere " +
                               "contemporaneamente trasmesso con successo e perso.");
        scenarioArea.setWrapText(true);
        scenarioArea.setEditable(false);
        scenarioArea.setPrefRowCount(7);
        scenarioArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Calcoli probabilistici
        Label calculationsLabel = new Label("Calcoli Probabilistici");
        calculationsLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea calculationsArea = new TextArea("Probabilità di trasmissione non riuscita:\n" +
                                   "P(non successo) = P(P ∪ C) = P(P) + P(C) = 0.10 + 0.05 = 0.15\n\n" +
                                   "Verifica del secondo assioma:\n" +
                                   "P(S) + P(P) + P(C) = 0.85 + 0.10 + 0.05 = 1.0 ✓\n\n" +
                                   "Probabilità di successo usando il complemento:\n" +
                                   "P(S) = 1 - P(non successo) = 1 - 0.15 = 0.85 ✓");
        calculationsArea.setWrapText(true);
        calculationsArea.setEditable(false);
        calculationsArea.setPrefRowCount(8);
        calculationsArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Grafico a barre
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 1, 0.1);
        xAxis.setLabel("Risultato Trasmissione");
        yAxis.setLabel("Probabilità");
        
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Probabilità di Trasmissione Pacchetti");
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Probabilità");
        series.getData().add(new XYChart.Data<>("Successo", 0.85));
        series.getData().add(new XYChart.Data<>("Perso", 0.10));
        series.getData().add(new XYChart.Data<>("Corrotto", 0.05));
        
        barChart.getData().add(series);
        
        content.getChildren().addAll(introArea, new Separator(), scenarioLabel, scenarioArea,
                                   new Separator(), calculationsLabel, calculationsArea, barChart);
        
        TitledPane titledPane = new TitledPane("Esempio 2: Trasmissione in Rete", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createMachineLearningExample() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea("Esaminiamo come gli assiomi di Kolmogorov si applicano nella classificazione " +
                            "di email come spam o non-spam usando un algoritmo di machine learning.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(2);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Problema di classificazione
        Label problemLabel = new Label("Problema di Classificazione");
        problemLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea problemArea = new TextArea("Un algoritmo di classificazione analizza email e le classifica come:\n" +
                              "• Spam (S): P(S) = 0.3\n" +
                              "• Non-spam/Ham (H): P(H) = 0.7\n\n" +
                              "L'algoritmo può anche commettere errori:\n" +
                              "• Falso positivo: classificare ham come spam\n" +
                              "• Falso negativo: classificare spam come ham");
        problemArea.setWrapText(true);
        problemArea.setEditable(false);
        problemArea.setPrefRowCount(7);
        problemArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Matrice di confusione
        Label matrixLabel = new Label("Matrice di Confusione (Probabilità)");
        matrixLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        GridPane matrix = new GridPane();
        matrix.setHgap(10);
        matrix.setVgap(5);
        matrix.setPadding(new Insets(10));
        
        // Headers
        matrix.add(new Label(""), 0, 0);
        matrix.add(new Label("Predetto Spam"), 1, 0);
        matrix.add(new Label("Predetto Ham"), 2, 0);
        matrix.add(new Label("Totale"), 3, 0);
        
        // Righe
        matrix.add(new Label("Reale Spam"), 0, 1);
        matrix.add(new Label("0.25"), 1, 1);
        matrix.add(new Label("0.05"), 2, 1);
        matrix.add(new Label("0.30"), 3, 1);
        
        matrix.add(new Label("Reale Ham"), 0, 2);
        matrix.add(new Label("0.10"), 1, 2);
        matrix.add(new Label("0.60"), 2, 2);
        matrix.add(new Label("0.70"), 3, 2);
        
        matrix.add(new Label("Totale"), 0, 3);
        matrix.add(new Label("0.35"), 1, 3);
        matrix.add(new Label("0.65"), 2, 3);
        matrix.add(new Label("1.00"), 3, 3);
        
        // Applicazione degli assiomi
        TextArea axiomApplicationArea = new TextArea("Applicazione degli Assiomi:\n\n" +
                                       "Primo Assioma: Tutte le probabilità nella matrice sono ≥ 0 ✓\n\n" +
                                       "Secondo Assioma: La somma di tutte le probabilità è 1:\n" +
                                       "0.25 + 0.05 + 0.10 + 0.60 = 1.0 ✓\n\n" +
                                       "Terzo Assioma: Eventi mutuamente esclusivi\n" +
                                       "P(classificazione corretta) = P(vero positivo ∪ vero negativo)\n" +
                                       "= P(vero positivo) + P(vero negativo) = 0.25 + 0.60 = 0.85");
        axiomApplicationArea.setWrapText(true);
        axiomApplicationArea.setEditable(false);
        axiomApplicationArea.setPrefRowCount(10);
        axiomApplicationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(introArea, new Separator(), problemLabel, problemArea,
                                   new Separator(), matrixLabel, matrix, new Separator(), axiomApplicationArea);
        
        TitledPane titledPane = new TitledPane("Esempio 3: Classificazione Machine Learning", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createSecurityExample() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea("Analizziamo la sicurezza di un sistema informatico e la probabilità di diversi " +
                            "tipi di attacchi, applicando gli assiomi di Kolmogorov.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(2);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Tipi di attacchi
        Label attacksLabel = new Label("Tipi di Attacchi Informatici");
        attacksLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea attacksArea = new TextArea("In un giorno tipico, un sistema può subire:\n" +
                              "• Nessun attacco (N): P(N) = 0.60\n" +
                              "• Attacco DDoS (D): P(D) = 0.15\n" +
                              "• Tentativo di intrusione (I): P(I) = 0.20\n" +
                              "• Malware (M): P(M) = 0.05\n\n" +
                              "Questi eventi sono mutuamente esclusivi per semplicità del modello.");
        attacksArea.setWrapText(true);
        attacksArea.setEditable(false);
        attacksArea.setPrefRowCount(7);
        attacksArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Analisi di sicurezza
        Label analysisLabel = new Label("Analisi di Sicurezza");
        analysisLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea analysisArea = new TextArea("Probabilità di subire almeno un attacco:\n" +
                               "P(attacco) = P(D ∪ I ∪ M) = P(D) + P(I) + P(M)\n" +
                               "= 0.15 + 0.20 + 0.05 = 0.40\n\n" +
                               "Verifica usando il complemento:\n" +
                               "P(attacco) = 1 - P(N) = 1 - 0.60 = 0.40 ✓\n\n" +
                               "Probabilità di attacco grave (DDoS o Malware):\n" +
                               "P(D ∪ M) = P(D) + P(M) = 0.15 + 0.05 = 0.20");
        analysisArea.setWrapText(true);
        analysisArea.setEditable(false);
        analysisArea.setPrefRowCount(8);
        analysisArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Grafico di sicurezza
        PieChart securityChart = new PieChart();
        securityChart.setTitle("Distribuzione Probabilità Attacchi");
        
        PieChart.Data noAttack = new PieChart.Data("Nessun Attacco (60%)", 60);
        PieChart.Data ddos = new PieChart.Data("DDoS (15%)", 15);
        PieChart.Data intrusion = new PieChart.Data("Intrusione (20%)", 20);
        PieChart.Data malware = new PieChart.Data("Malware (5%)", 5);
        
        securityChart.getData().addAll(noAttack, ddos, intrusion, malware);
        
        content.getChildren().addAll(introArea, new Separator(), attacksLabel, attacksArea,
                                   new Separator(), analysisLabel, analysisArea, securityChart);
        
        TitledPane titledPane = new TitledPane("Esempio 4: Sicurezza Informatica", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createAlgorithmExample() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea("Esaminiamo l'analisi probabilistica di un algoritmo di ordinamento randomizzato " +
                            "e come gli assiomi di Kolmogorov si applicano alla sua complessità.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(2);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Algoritmo QuickSort randomizzato
        Label algorithmLabel = new Label("QuickSort Randomizzato");
        algorithmLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea algorithmArea = new TextArea("Consideriamo QuickSort con pivot scelto casualmente. Per un array di n elementi, " +
                                "la complessità può essere:\n" +
                                "• Caso migliore O(n log n): P(migliore) = probabilità di pivot sempre ottimale\n" +
                                "• Caso medio O(n log n): P(medio) = probabilità di pivot ragionevolmente buoni\n" +
                                "• Caso peggiore O(n²): P(peggiore) = probabilità di pivot sempre pessimi\n\n" +
                                "Per semplicità, consideriamo un modello semplificato:");
        algorithmArea.setWrapText(true);
        algorithmArea.setEditable(false);
        algorithmArea.setPrefRowCount(7);
        algorithmArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Modello probabilistico
        Label modelLabel = new Label("Modello Probabilistico Semplificato");
        modelLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea modelArea = new TextArea("Per ogni scelta del pivot:\n" +
                            "• Pivot ottimo (primi/ultimi 25%): P(ottimo) = 0.25\n" +
                            "• Pivot buono (25%-75%): P(buono) = 0.50\n" +
                            "• Pivot cattivo (rimanenti): P(cattivo) = 0.25\n\n" +
                            "Applicazione degli assiomi:\n" +
                            "1. Tutte le probabilità sono ≥ 0 ✓\n" +
                            "2. P(ottimo) + P(buono) + P(cattivo) = 0.25 + 0.50 + 0.25 = 1.0 ✓\n" +
                            "3. P(non ottimo) = P(buono ∪ cattivo) = P(buono) + P(cattivo) = 0.75");
        modelArea.setWrapText(true);
        modelArea.setEditable(false);
        modelArea.setPrefRowCount(9);
        modelArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Grafico delle prestazioni
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 0.6, 0.1);
        xAxis.setLabel("Qualità del Pivot");
        yAxis.setLabel("Probabilità");
        
        BarChart<String, Number> performanceChart = new BarChart<>(xAxis, yAxis);
        performanceChart.setTitle("Distribuzione Qualità Pivot in QuickSort");
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Probabilità");
        series.getData().add(new XYChart.Data<>("Ottimo", 0.25));
        series.getData().add(new XYChart.Data<>("Buono", 0.50));
        series.getData().add(new XYChart.Data<>("Cattivo", 0.25));
        
        performanceChart.getData().add(series);
        
        TextArea conclusionArea = new TextArea("Conclusione: Gli assiomi di Kolmogorov forniscono la base matematica per " +
                                 "analizzare rigorosamente le prestazioni probabilistiche degli algoritmi randomizzati, " +
                                 "permettendo di calcolare complessità attese e probabilità di casi specifici.");
        conclusionArea.setWrapText(true);
        conclusionArea.setEditable(false);
        conclusionArea.setPrefRowCount(3);
        conclusionArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(introArea, new Separator(), algorithmLabel, algorithmArea,
                                   new Separator(), modelLabel, modelArea, performanceChart, 
                                   new Separator(), conclusionArea);
        
        TitledPane titledPane = new TitledPane("Esempio 5: Analisi di Algoritmi Randomizzati", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
}