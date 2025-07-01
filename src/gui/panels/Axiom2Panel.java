package gui.panels;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import utils.MathUtils;

public class Axiom2Panel implements BasePanel {
    
    @Override
    public Node getContent() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        content.setMaxWidth(750);
        
        // Titolo
        Label title = new Label("Secondo Assioma di Kolmogorov: Normalizzazione");
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
        definitionArea.setText("Il secondo assioma di Kolmogorov stabilisce che la probabilità dell'intero spazio campionario è uguale a 1.");
        definitionArea.setWrapText(true);
        definitionArea.setEditable(false);
        definitionArea.setPrefRowCount(2);
        definitionArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        HBox formulaBox = new HBox(10);
        formulaBox.setPadding(new Insets(20, 0, 20, 30));
        Text formula = MathUtils.createMathText("P(Ω) = 1");
        formulaBox.getChildren().add(formula);
        
        TextArea explanationArea = new TextArea();
        explanationArea.setText("Dove:\n" +
                                  "• P(Ω) rappresenta la probabilità dell'intero spazio campionario Ω\n\n" +
                                  "Questo assioma afferma che la probabilità che si verifichi un qualsiasi evento possibile è 1, " +
                                  "ovvero la certezza. Lo spazio campionario Ω contiene tutti i possibili risultati dell'esperimento, " +
                                  "quindi è certo che uno di questi risultati si verificherà.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(6);
        explanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(definitionArea, formulaBox, explanationArea);
        
        TitledPane titledPane = new TitledPane("Definizione Formale", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createIntuitiveExplanationSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea explanationArea = new TextArea();
        explanationArea.setText("Per comprendere intuitivamente il secondo assioma, possiamo pensare alla probabilità come " +
                                  "a una distribuzione di un totale di 1 (o 100%) tra tutti i possibili risultati di un esperimento.\n\n" +
                                  "Immaginiamo di lanciare un dado a sei facce. Lo spazio campionario Ω è l'insieme {1, 2, 3, 4, 5, 6}, " +
                                  "che contiene tutti i possibili risultati del lancio. Il secondo assioma ci dice che P(Ω) = 1, " +
                                  "cioè la probabilità di ottenere uno qualsiasi di questi numeri è 1 (o 100%). Questo è intuitivo: " +
                                  "siamo certi che il risultato del lancio sarà uno dei numeri da 1 a 6.\n\n" +
                                  "In termini pratici, questo assioma ci dice che la somma delle probabilità di tutti i possibili " +
                                  "risultati mutuamente esclusivi di un esperimento deve essere uguale a 1. Questo è un principio " +
                                  "fondamentale che usiamo costantemente nella programmazione quando lavoriamo con distribuzioni di probabilità.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(10);
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
        introArea.setText("Visualizziamo graficamente il concetto che la probabilità dell'intero spazio campionario è 1. " +
                            "Nel grafico a torta seguente, l'intero cerchio rappresenta lo spazio campionario Ω, " +
                            "e la sua area totale rappresenta una probabilità di 1 (o 100%).");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(3);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Creazione del grafico a torta
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Visualizzazione del Secondo Assioma");
        
        // Esempio con un dado
        PieChart.Data slice1 = new PieChart.Data("P(1) = 1/6", 16.67);
        PieChart.Data slice2 = new PieChart.Data("P(2) = 1/6", 16.67);
        PieChart.Data slice3 = new PieChart.Data("P(3) = 1/6", 16.67);
        PieChart.Data slice4 = new PieChart.Data("P(4) = 1/6", 16.67);
        PieChart.Data slice5 = new PieChart.Data("P(5) = 1/6", 16.67);
        PieChart.Data slice6 = new PieChart.Data("P(6) = 1/6", 16.67);
        
        pieChart.getData().addAll(slice1, slice2, slice3, slice4, slice5, slice6);
        
        // Aggiunta di una legenda
        VBox legend = new VBox(5);
        legend.setPadding(new Insets(10));
        legend.setMaxWidth(Double.MAX_VALUE);
        
        HBox totalBox = new HBox(10);
        totalBox.setMaxWidth(Double.MAX_VALUE);
        Circle totalCircle = new Circle(5, Color.BLACK);
        TextArea totalTextArea = new TextArea("P(Ω) = P(1) + P(2) + P(3) + P(4) + P(5) + P(6) = 1");
        totalTextArea.setWrapText(true);
        totalTextArea.setEditable(false);
        totalTextArea.setPrefRowCount(2);
        totalTextArea.setMaxWidth(Double.MAX_VALUE);
        totalTextArea.setPrefWidth(600);
        totalTextArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        HBox.setHgrow(totalTextArea, Priority.ALWAYS);
        totalBox.getChildren().addAll(totalCircle, totalTextArea);
        
        legend.getChildren().add(totalBox);
        
        TextArea explanationArea = new TextArea();
        explanationArea.setText("Il grafico a torta mostra come la probabilità totale (1) sia distribuita tra tutti i possibili " +
                                  "risultati del lancio di un dado. Ogni fetta rappresenta la probabilità di un singolo risultato (1/6), " +
                                  "e la somma di tutte le fette è esattamente 1, in accordo con il secondo assioma.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(3);
        explanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(introArea, pieChart, legend, explanationArea);
        
        TitledPane titledPane = new TitledPane("Visualizzazione Grafica", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createExamplesSection() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea("Vediamo alcuni esempi pratici che illustrano il secondo assioma:");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(1);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 1
        Label example1Label = new Label("Esempio 1: Lancio di una moneta");
        example1Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example1Area = new TextArea("Consideriamo il lancio di una moneta. Lo spazio campionario è Ω = {testa, croce}.\n" +
                               "P(testa) = 0.5\n" +
                               "P(croce) = 0.5\n" +
                               "P(Ω) = P(testa) + P(croce) = 0.5 + 0.5 = 1\n\n" +
                               "La probabilità dell'intero spazio campionario è 1, in accordo con il secondo assioma.");
        example1Area.setWrapText(true);
        example1Area.setEditable(false);
        example1Area.setPrefRowCount(6);
        example1Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 2
        Label example2Label = new Label("Esempio 2: Estrazione di una carta");
        example2Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example2Area = new TextArea("Consideriamo l'estrazione di una carta da un mazzo standard di 52 carte. " +
                               "Lo spazio campionario contiene 52 possibili risultati, uno per ogni carta.\n" +
                               "P(carta 1) = 1/52\n" +
                               "P(carta 2) = 1/52\n" +
                               "...\n" +
                               "P(carta 52) = 1/52\n" +
                               "P(Ω) = P(carta 1) + P(carta 2) + ... + P(carta 52) = 52 * (1/52) = 1\n\n" +
                               "Anche in questo caso, la probabilità dell'intero spazio campionario è 1.");
        example2Area.setWrapText(true);
        example2Area.setEditable(false);
        example2Area.setPrefRowCount(8);
        example2Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 3
        Label example3Label = new Label("Esempio 3: Lancio di due dadi");
        example3Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example3Area = new TextArea("Consideriamo il lancio di due dadi a sei facce. Lo spazio campionario contiene 36 possibili " +
                               "combinazioni (6 × 6 = 36).\n" +
                               "P((1,1)) = 1/36\n" +
                               "P((1,2)) = 1/36\n" +
                               "...\n" +
                               "P((6,6)) = 1/36\n" +
                               "P(Ω) = somma di tutte le 36 probabilità = 36 * (1/36) = 1\n\n" +
                               "La probabilità dell'intero spazio campionario è ancora 1.");
        example3Area.setWrapText(true);
        example3Area.setEditable(false);
        example3Area.setPrefRowCount(8);
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
        
        TextArea consequencesArea = new TextArea("Il secondo assioma ha diverse conseguenze importanti:\n\n" +
                                   "1. Insieme al primo assioma (che stabilisce che P(A) ≥ 0), definisce l'intervallo " +
                                   "in cui può variare la probabilità: 0 ≤ P(A) ≤ 1.\n\n" +
                                   "2. Stabilisce che la probabilità è normalizzata, cioè la somma delle probabilità di tutti " +
                                   "i possibili risultati mutuamente esclusivi è 1.\n\n" +
                                   "3. Permette di calcolare la probabilità dell'evento complementare: P(A') = 1 - P(A), " +
                                   "dove A' è il complemento di A rispetto a Ω.\n\n" +
                                   "4. Nella programmazione, questo assioma ci fornisce un utile controllo di coerenza: " +
                                   "se la somma delle probabilità di tutti i possibili risultati mutuamente esclusivi " +
                                   "non è uguale a 1, c'è un errore nei nostri calcoli.");
        consequencesArea.setWrapText(true);
        consequencesArea.setEditable(false);
        consequencesArea.setPrefRowCount(12);
        consequencesArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().add(consequencesArea);
        
        TitledPane titledPane = new TitledPane("Conseguenze Matematiche", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
}