package gui.panels;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import utils.MathUtils;

public class Axiom1Panel implements BasePanel {
    
    @Override
    public Node getContent() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        content.setMaxWidth(750);
        
        // Titolo
        Label title = new Label("Primo Assioma di Kolmogorov: Non-negatività");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));
        
        // Definizione formale
        TitledPane formalDefinition = createFormalDefinitionSection();
        
        // Spiegazione intuitiva
        TitledPane intuitiveExplanation = createIntuitiveExplanationSection();
        
        // Visualizzazione grafica
        TitledPane visualization = createVisualizationSection();
        
        // Esempi pratici
        TitledPane examples = createExamplesSection();
        
        // Conseguenze matematiche
        TitledPane consequences = createConsequencesSection();
        
        content.getChildren().addAll(title, formalDefinition, intuitiveExplanation, visualization, examples, consequences);
        return content;
    }
    
    private TitledPane createFormalDefinitionSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea definitionArea = new TextArea();
        definitionArea.setText("Il primo assioma di Kolmogorov stabilisce che la probabilità di un evento è sempre un numero non negativo.");
        definitionArea.setWrapText(true);
        definitionArea.setEditable(false);
        definitionArea.setPrefRowCount(3);
        definitionArea.setMaxHeight(80);
        definitionArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        Label formulaLabel = new Label("P(A) ≥ 0 per ogni evento A nello spazio campionario Ω");
        formulaLabel.setWrapText(true);
        formulaLabel.setMaxWidth(700);
        formulaLabel.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 14px; -fx-padding: 20 0 20 30;");
        
        TextArea explanationArea = new TextArea();
        explanationArea.setText("Dove:\n" +
                                  "• P(A) rappresenta la probabilità dell'evento A\n" +
                                  "• A è un qualsiasi evento (sottoinsieme) dello spazio campionario Ω\n\n" +
                                  "Questo assioma afferma semplicemente che la probabilità non può mai essere negativa. " +
                                  "È un principio fondamentale che riflette l'intuizione che la probabilità misura la " +
                                  "'possibilità' che un evento si verifichi, e questa misura non può essere negativa.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(8);
        explanationArea.setMaxHeight(200);
        explanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(definitionArea, formulaLabel, explanationArea);
        
        TitledPane titledPane = new TitledPane("Definizione Formale", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createIntuitiveExplanationSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea explanationArea = new TextArea();
        explanationArea.setText("Per comprendere intuitivamente il primo assioma, possiamo pensare alla probabilità come " +
                                  "a una misura di quanto è probabile che un evento si verifichi. Questa misura non può " +
                                  "essere negativa, proprio come non possiamo avere una distanza negativa o un'area negativa.\n\n" +
                                  "Immaginiamo di lanciare un dado a sei facce. La probabilità di ottenere un 3 è 1/6, " +
                                  "che è un numero positivo. Non avrebbe senso dire che la probabilità di ottenere un 3 è -0.2, " +
                                  "perché ciò significherebbe che l'evento ha una 'possibilità negativa' di verificarsi, " +
                                  "il che non ha significato nel mondo reale.\n\n" +
                                  "In termini pratici, se stiamo calcolando probabilità in un programma informatico, " +
                                  "questo assioma ci dice che dobbiamo sempre controllare che i nostri calcoli non producano " +
                                  "valori negativi per le probabilità, poiché ciò indicherebbe un errore nel nostro algoritmo.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(12);
        explanationArea.setMaxHeight(300);
        explanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().add(explanationArea);
        
        TitledPane titledPane = new TitledPane("Spiegazione Intuitiva", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createVisualizationSection() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea();
        introArea.setText("Visualizziamo graficamente il concetto di probabilità non negativa. " +
                            "Nel grafico seguente, l'asse orizzontale rappresenta diversi eventi, " +
                            "mentre l'asse verticale rappresenta la loro probabilità. " +
                            "Notiamo che tutti i valori di probabilità sono maggiori o uguali a zero.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(4);
        introArea.setMaxHeight(100);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Creazione del grafico combinato
        NumberAxis xAxis = new NumberAxis(0, 6, 1);
        NumberAxis yAxis = new NumberAxis(0, 1, 0.1);
        xAxis.setLabel("Eventi");
        yAxis.setLabel("Probabilità P(A)");
        
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Visualizzazione del Primo Assioma");
        lineChart.setCreateSymbols(true);
        lineChart.setLegendVisible(true);
        
        // Serie per i punti di probabilità
        XYChart.Series<Number, Number> pointSeries = new XYChart.Series<>();
        pointSeries.setName("Probabilità di diversi eventi");
        
        // Aggiunta di alcuni punti di esempio
        pointSeries.getData().add(new XYChart.Data<>(1, 0.167)); // P(ottenere 1 con un dado) = 1/6
        pointSeries.getData().add(new XYChart.Data<>(2, 0.5));   // P(ottenere testa con una moneta) = 1/2
        pointSeries.getData().add(new XYChart.Data<>(3, 0.25));  // P(estrarre un asso da un mazzo) ≈ 1/4
        pointSeries.getData().add(new XYChart.Data<>(4, 0.1));   // P(evento raro) = 0.1
        pointSeries.getData().add(new XYChart.Data<>(5, 0.0));   // P(evento impossibile) = 0
        
        // Serie per la linea rossa del limite inferiore (y = 0 per rappresentare correttamente l'assioma)
        XYChart.Series<Number, Number> limitSeries = new XYChart.Series<>();
        limitSeries.setName("Limite inferiore P(A) ≥ 0");
        
        // Creazione di una linea orizzontale esattamente a y = 0
        for (double x = 0; x <= 6; x += 0.1) {
            limitSeries.getData().add(new XYChart.Data<>(x, 0.0));
        }
        
        lineChart.getData().addAll(pointSeries, limitSeries);
        
        StackPane chartContainer = new StackPane();
        chartContainer.getChildren().add(lineChart);
        chartContainer.getStyleClass().add("chart-container");
        
        // Rimuovo il CSS inline per evitare errori - userò solo il CSS esterno
        
        TextArea explanationArea = new TextArea();
        explanationArea.setText("Il grafico mostra che tutte le probabilità sono maggiori o uguali a zero, " +
                                  "in accordo con il primo assioma. La linea rossa orizzontale rappresenta il limite inferiore " +
                                  "per qualsiasi probabilità (P(A) ≥ 0). Nessun punto può trovarsi al di sotto di questa linea. " +
                                  "I punti arancioni rappresentano esempi di probabilità valide di diversi eventi.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(4);
        explanationArea.setMaxHeight(100);
        explanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(introArea, chartContainer, explanationArea);
        
        TitledPane titledPane = new TitledPane("Visualizzazione Grafica", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createExamplesSection() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea("Vediamo alcuni esempi pratici che illustrano il primo assioma:");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(2);
        introArea.setMaxHeight(50);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 1
        Label example1Label = new Label("Esempio 1: Lancio di un dado");
        example1Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example1Area = new TextArea("Consideriamo il lancio di un dado a sei facce. La probabilità di ottenere un numero pari è:\n" +
                               "P(numero pari) = P(2) + P(4) + P(6) = 1/6 + 1/6 + 1/6 = 3/6 = 0.5\n\n" +
                               "Questa probabilità è 0.5, che è maggiore di zero, in accordo con il primo assioma.");
        example1Area.setWrapText(true);
        example1Area.setEditable(false);
        example1Area.setPrefRowCount(5);
        example1Area.setMaxHeight(120);
        example1Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 2
        Label example2Label = new Label("Esempio 2: Estrazione di una carta");
        example2Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example2Area = new TextArea("Consideriamo l'estrazione di una carta da un mazzo standard di 52 carte. " +
                               "La probabilità di estrarre un asso è:\n" +
                               "P(asso) = 4/52 = 1/13 ≈ 0.077\n\n" +
                               "Anche in questo caso, la probabilità è positiva, in accordo con il primo assioma.");
        example2Area.setWrapText(true);
        example2Area.setEditable(false);
        example2Area.setPrefRowCount(5);
        example2Area.setMaxHeight(120);
        example2Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 3
        Label example3Label = new Label("Esempio 3: Evento impossibile");
        example3Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example3Area = new TextArea("Consideriamo l'evento 'ottenere un 7' lanciando un dado a sei facce. " +
                               "Questo è un evento impossibile, quindi:\n" +
                               "P(ottenere un 7) = 0\n\n" +
                               "Anche in questo caso, la probabilità è non negativa (è esattamente zero), " +
                               "in accordo con il primo assioma.");
        example3Area.setWrapText(true);
        example3Area.setEditable(false);
        example3Area.setPrefRowCount(6);
        example3Area.setMaxHeight(140);
        example3Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(introArea, new Separator(), 
                                   example1Label, example1Area, new Separator(),
                                   example2Label, example2Area, new Separator(),
                                   example3Label, example3Area);
        
        TitledPane titledPane = new TitledPane("Esempi Pratici", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createConsequencesSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea consequencesArea = new TextArea("Il primo assioma ha diverse conseguenze importanti:\n\n" +
                                   "1. Limita il range dei valori possibili per la probabilità: P(A) deve essere almeno 0.\n\n" +
                                   "2. Insieme al secondo assioma (che stabilisce che P(Ω) = 1), definisce l'intervallo " +
                                   "in cui può variare la probabilità: 0 ≤ P(A) ≤ 1.\n\n" +
                                   "3. Permette di interpretare la probabilità come una misura, simile a lunghezza, " +
                                   "area o volume, che assegna un valore non negativo a ogni sottoinsieme dello spazio campionario.\n\n" +
                                   "4. Nella programmazione, questo assioma ci ricorda di controllare sempre che i nostri " +
                                   "algoritmi non producano probabilità negative, il che sarebbe un chiaro segnale di errore.");
        consequencesArea.setWrapText(true);
        consequencesArea.setEditable(false);
        consequencesArea.setPrefRowCount(12);
        consequencesArea.setMaxHeight(300);
        consequencesArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().add(consequencesArea);
        
        TitledPane titledPane = new TitledPane("Conseguenze Matematiche", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
}