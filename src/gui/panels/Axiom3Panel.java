package gui.panels;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

public class Axiom3Panel implements BasePanel {
    
    @Override
    public Node getContent() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        content.setMaxWidth(750);
        
        // Titolo
        Label title = new Label("Terzo Assioma di Kolmogorov: Additività");
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
        
        // Estensione agli eventi infiniti
        TitledPane infiniteExtension = createInfiniteExtensionSection();
        
        content.getChildren().addAll(title, formalDefinition, intuitiveExplanation, visualization, examples, consequences, infiniteExtension);
        return content;
    }
    
    private TitledPane createFormalDefinitionSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea definitionArea = new TextArea();
        definitionArea.setText("Il terzo assioma di Kolmogorov stabilisce che per eventi mutuamente esclusivi, " +
                                 "la probabilità dell'unione è uguale alla somma delle probabilità.");
        definitionArea.setWrapText(true);
        definitionArea.setEditable(false);
        definitionArea.setPrefRowCount(2);
        definitionArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        VBox formulaBox = new VBox(10);
        formulaBox.setPadding(new Insets(20, 0, 20, 30));
        
        Text formula1 = MathUtils.createMathText("Se A ∩ B = ∅, allora P(A ∪ B) = P(A) + P(B)");
        Text formula2 = MathUtils.createMathText("Più in generale, per eventi A₁, A₂, ..., Aₙ mutuamente esclusivi:");
        Text formula3 = MathUtils.createMathText("P(A₁ ∪ A₂ ∪ ... ∪ Aₙ) = P(A₁) + P(A₂) + ... + P(Aₙ)");
        
        formulaBox.getChildren().addAll(formula1, formula2, formula3);
        
        TextArea explanationArea = new TextArea();
        explanationArea.setText("Dove:\n" +
                                  "• A ∩ B = ∅ significa che gli eventi A e B sono mutuamente esclusivi (disgiunti)\n" +
                                  "• A ∪ B rappresenta l'unione degli eventi A e B\n" +
                                  "• P(A ∪ B) è la probabilità che si verifichi A oppure B\n\n" +
                                  "Questo assioma afferma che se due eventi non possono verificarsi contemporaneamente, " +
                                  "la probabilità che si verifichi uno dei due è semplicemente la somma delle loro probabilità individuali.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(7);
        explanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(definitionArea, formulaBox, explanationArea);
        
        TitledPane titledPane = new TitledPane("Definizione Formale", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createIntuitiveExplanationSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea explanationArea = new TextArea("Per comprendere intuitivamente il terzo assioma, possiamo pensare a eventi che non possono " +
                                  "verificarsi contemporaneamente. Ad esempio, nel lancio di un dado, l'evento 'ottenere un 2' " +
                                  "e l'evento 'ottenere un 5' sono mutuamente esclusivi: non possiamo ottenere sia 2 che 5 " +
                                  "nello stesso lancio.\n\n" +
                                  "Il terzo assioma ci dice che la probabilità di ottenere 2 OPPURE 5 è semplicemente la somma " +
                                  "delle probabilità individuali: P(2 o 5) = P(2) + P(5) = 1/6 + 1/6 = 2/6 = 1/3.\n\n" +
                                  "Questo principio è fondamentale nella programmazione quando calcoliamo probabilità di eventi " +
                                  "complessi che possono essere scomposti in eventi più semplici e mutuamente esclusivi. " +
                                  "È la base per molti algoritmi di calcolo probabilistico.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(8);
        explanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().add(explanationArea);
        
        TitledPane titledPane = new TitledPane("Spiegazione Intuitiva", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createVisualizationSection() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea("Visualizziamo graficamente il concetto di additività per eventi mutuamente esclusivi. " +
                            "Il grafico a barre seguente mostra come la probabilità dell'unione di eventi mutuamente " +
                            "esclusivi sia uguale alla somma delle probabilità individuali.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(3);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Creazione del grafico a barre
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 0.6, 0.1);
        xAxis.setLabel("Eventi");
        yAxis.setLabel("Probabilità");
        
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Visualizzazione del Terzo Assioma");
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Probabilità");
        
        // Esempio con un dado: P(numero pari) = P(2) + P(4) + P(6)
        series.getData().add(new XYChart.Data<>("P(2)", 1.0/6));
        series.getData().add(new XYChart.Data<>("P(4)", 1.0/6));
        series.getData().add(new XYChart.Data<>("P(6)", 1.0/6));
        series.getData().add(new XYChart.Data<>("P(2∪4∪6)", 3.0/6));
        
        barChart.getData().add(series);
        
        // Visualizzazione con diagramma di Venn
        HBox vennBox = new HBox(20);
        vennBox.setPadding(new Insets(20));
        
        VBox vennA = new VBox(5);
        Circle circleA = new Circle(30, Color.LIGHTBLUE);
        Text labelA = new Text("A");
        labelA.setFont(Font.font("System", FontWeight.BOLD, 14));
        vennA.getChildren().addAll(circleA, labelA);
        
        Text plusSign = new Text("+");
        plusSign.setFont(Font.font("System", FontWeight.BOLD, 20));
        
        VBox vennB = new VBox(5);
        Circle circleB = new Circle(30, Color.LIGHTCORAL);
        Text labelB = new Text("B");
        labelB.setFont(Font.font("System", FontWeight.BOLD, 14));
        vennB.getChildren().addAll(circleB, labelB);
        
        Text equalsSign = new Text("=");
        equalsSign.setFont(Font.font("System", FontWeight.BOLD, 20));
        
        VBox vennUnion = new VBox(5);
        Rectangle rectUnion = new Rectangle(80, 30, Color.LIGHTGREEN);
        Text labelUnion = new Text("A ∪ B");
        labelUnion.setFont(Font.font("System", FontWeight.BOLD, 14));
        vennUnion.getChildren().addAll(rectUnion, labelUnion);
        
        vennBox.getChildren().addAll(vennA, plusSign, vennB, equalsSign, vennUnion);
        
        TextArea vennExplanationArea = new TextArea("Il diagramma mostra che per eventi mutuamente esclusivi A e B, " +
                                      "la probabilità dell'unione A ∪ B è uguale alla somma P(A) + P(B).");
        vennExplanationArea.setWrapText(true);
        vennExplanationArea.setEditable(false);
        vennExplanationArea.setPrefRowCount(2);
        vennExplanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(introArea, barChart, vennBox, vennExplanationArea);
        
        TitledPane titledPane = new TitledPane("Visualizzazione Grafica", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createExamplesSection() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea("Vediamo alcuni esempi pratici che illustrano il terzo assioma:");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(1);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 1
        Label example1Label = new Label("Esempio 1: Lancio di un dado - numeri pari");
        example1Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example1Area = new TextArea("Consideriamo l'evento 'ottenere un numero pari' lanciando un dado.\n" +
                               "Questo evento può essere scomposto in tre eventi mutuamente esclusivi:\n" +
                               "A = {ottenere 2}, B = {ottenere 4}, C = {ottenere 6}\n\n" +
                               "P(A) = 1/6, P(B) = 1/6, P(C) = 1/6\n" +
                               "P(numero pari) = P(A ∪ B ∪ C) = P(A) + P(B) + P(C) = 1/6 + 1/6 + 1/6 = 3/6 = 1/2\n\n" +
                               "Il terzo assioma ci permette di calcolare facilmente questa probabilità.");
        example1Area.setWrapText(true);
        example1Area.setEditable(false);
        example1Area.setPrefRowCount(7);
        example1Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 2
        Label example2Label = new Label("Esempio 2: Estrazione di carte - figure");
        example2Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example2Area = new TextArea("Consideriamo l'evento 'estrarre una figura' da un mazzo di 52 carte.\n" +
                               "Questo evento può essere scomposto in tre eventi mutuamente esclusivi:\n" +
                               "A = {estrarre un jack}, B = {estrarre una regina}, C = {estrarre un re}\n\n" +
                               "P(A) = 4/52, P(B) = 4/52, P(C) = 4/52\n" +
                               "P(figura) = P(A ∪ B ∪ C) = P(A) + P(B) + P(C) = 4/52 + 4/52 + 4/52 = 12/52 = 3/13\n\n" +
                               "Anche in questo caso, il terzo assioma semplifica il calcolo.");
        example2Area.setWrapText(true);
        example2Area.setEditable(false);
        example2Area.setPrefRowCount(7);
        example2Area.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Esempio 3
        Label example3Label = new Label("Esempio 3: Lancio di due monete");
        example3Label.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        TextArea example3Area = new TextArea("Consideriamo il lancio di due monete e l'evento 'ottenere almeno una testa'.\n" +
                               "Lo spazio campionario è Ω = {TT, TC, CT, CC}\n" +
                               "L'evento 'almeno una testa' può essere scomposto in:\n" +
                               "A = {TT}, B = {TC}, C = {CT}\n\n" +
                               "P(A) = 1/4, P(B) = 1/4, P(C) = 1/4\n" +
                               "P(almeno una testa) = P(A ∪ B ∪ C) = P(A) + P(B) + P(C) = 1/4 + 1/4 + 1/4 = 3/4\n\n" +
                               "Il terzo assioma ci permette di calcolare questa probabilità in modo diretto.");
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
        
        TextArea consequencesArea = new TextArea("Il terzo assioma ha diverse conseguenze importanti:\n\n" +
                                   "1. Permette di calcolare la probabilità di eventi complessi scomponendoli in eventi " +
                                   "più semplici e mutuamente esclusivi.\n\n" +
                                   "2. Fornisce la base per la formula dell'inclusione-esclusione per eventi non mutuamente esclusivi: " +
                                   "P(A ∪ B) = P(A) + P(B) - P(A ∩ B).\n\n" +
                                   "3. Insieme al secondo assioma, implica che P(A') = 1 - P(A), dove A' è il complemento di A.\n\n" +
                                   "4. È fondamentale per la definizione di distribuzioni di probabilità discrete, " +
                                   "dove la probabilità di ogni possibile valore è non negativa e la somma di tutte " +
                                   "le probabilità è 1.\n\n" +
                                   "5. Nella programmazione, questo assioma è alla base di molti algoritmi di simulazione " +
                                   "e calcolo probabilistico, permettendo di costruire eventi complessi da eventi semplici.");
        consequencesArea.setWrapText(true);
        consequencesArea.setEditable(false);
        consequencesArea.setPrefRowCount(14);
        consequencesArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().add(consequencesArea);
        
        TitledPane titledPane = new TitledPane("Conseguenze Matematiche", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createInfiniteExtensionSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea extensionArea = new TextArea("Il terzo assioma può essere esteso al caso di una sequenza infinita numerabile di eventi " +
                                "mutuamente esclusivi. Questa estensione è nota come σ-additività:\n\n");
        extensionArea.setWrapText(true);
        extensionArea.setEditable(false);
        extensionArea.setPrefRowCount(3);
        extensionArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        Text formula = MathUtils.createMathText("Se A₁, A₂, A₃, ... sono eventi mutuamente esclusivi, allora:\n" +
                                              "P(A₁ ∪ A₂ ∪ A₃ ∪ ...) = P(A₁) + P(A₂) + P(A₃) + ...");
        
        TextArea explanationArea = new TextArea("\n\nQuesta proprietà è fondamentale per la teoria della misura e permette di trattare " +
                                   "spazi campionari infiniti in modo rigoroso. È particolarmente importante quando si " +
                                   "lavora con variabili aleatorie continue e distribuzioni di probabilità continue.\n\n" +
                                   "Nell'informatica, questa estensione è utile quando si modellano processi che possono " +
                                   "avere un numero infinito di possibili stati o risultati, come nei sistemi di code, " +
                                   "nei processi stocastici e nelle simulazioni di sistemi complessi.");
        explanationArea.setWrapText(true);
        explanationArea.setEditable(false);
        explanationArea.setPrefRowCount(6);
        explanationArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().addAll(extensionArea, formula, explanationArea);
        
        TitledPane titledPane = new TitledPane("Estensione agli Eventi Infiniti (σ-additività)", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
}