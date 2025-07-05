package gui.panels;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import utils.MathUtils;

public class IntroductionPanel implements BasePanel {
    
    @Override
    public Node getContent() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        content.setMaxWidth(750);
        
        // Titolo
        Label title = new Label("Introduzione agli Assiomi di Kolmogorov");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: #2c3e50;");
        
        // Introduzione alla teoria della probabilità
        TitledPane probabilityIntro = createProbabilityIntroSection();
        
        // Chi era Kolmogorov
        TitledPane kolmogorovBio = createKolmogorovBioSection();
        
        // Panoramica degli assiomi
        TitledPane axiomsOverview = createAxiomsOverviewSection();
        
        // Importanza nella teoria della probabilità
        TitledPane importance = createImportanceSection();
        
        content.getChildren().addAll(title, probabilityIntro, kolmogorovBio, axiomsOverview, importance);
        return content;
    }
    
    private TitledPane createProbabilityIntroSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea textArea = new TextArea();
        textArea.setText("La teoria della probabilità è un ramo della matematica che studia i fenomeni aleatori, "
                + "ovvero quei fenomeni il cui risultato non può essere previsto con certezza.\n\n"
                + "Prima di Kolmogorov, la teoria della probabilità mancava di una base assiomatica rigorosa. "
                + "Diversi matematici avevano proposto approcci diversi, ma nessuno era riuscito a fornire una "
                + "formalizzazione completa e coerente.\n\n"
                + "Nel 1933, il matematico russo Andrey Nikolaevich Kolmogorov pubblicò il suo lavoro "
                + "'Grundbegriffe der Wahrscheinlichkeitsrechnung' (Fondamenti della teoria della probabilità), "
                + "in cui presentò un sistema assiomatico che ha rivoluzionato questo campo.");
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefRowCount(8);
        textArea.setMinHeight(100);
        textArea.setPrefHeight(100);
        textArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().add(textArea);
        
        TitledPane titledPane = new TitledPane("Cos'è la Teoria della Probabilità", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createKolmogorovBioSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea textArea = new TextArea();
        textArea.setText("Andrey Nikolaevich Kolmogorov (1903-1987) è stato uno dei più grandi matematici del XX secolo. "
                + "Nato in Russia, ha dato contributi fondamentali in diversi campi della matematica, tra cui la teoria "
                + "della probabilità, la teoria degli insiemi, la logica, la teoria della turbolenza, la teoria della complessità "
                + "e la teoria dell'informazione.\n\n"
                + "Il suo lavoro sulla teoria della probabilità ha fornito per la prima volta una base matematica rigorosa "
                + "per questa disciplina, permettendo di trattare la probabilità con lo stesso rigore di altri campi della matematica. "
                + "Gli assiomi che portano il suo nome sono ancora oggi alla base di tutta la teoria moderna della probabilità.");
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefRowCount(6);
        textArea.setMinHeight(600);
        textArea.setPrefHeight(600);
        textArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().add(textArea);
        
        TitledPane titledPane = new TitledPane("Chi era Andrey Kolmogorov", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
    
    private TitledPane createAxiomsOverviewSection() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        TextArea introArea = new TextArea();
        introArea.setText("Gli assiomi di Kolmogorov sono tre principi fondamentali che definiscono una misura di probabilità. "
                + "Questi assiomi forniscono una base matematica rigorosa per la teoria della probabilità.");
        introArea.setWrapText(true);
        introArea.setEditable(false);
        introArea.setPrefRowCount(2);
        introArea.setMinHeight(100);
        introArea.setPrefHeight(100);
        introArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Primo assioma
        Label axiom1Title = new Label("Primo Assioma: Non-negatività");
        axiom1Title.setFont(Font.font("System", FontWeight.BOLD, 18));
        axiom1Title.setStyle("-fx-text-fill: #8b5cf6;");
        
        Label axiom1Label = new Label("La probabilità di un evento è un numero non negativo: P(A) ≥ 0 per ogni evento A");
        axiom1Label.setWrapText(true);
        axiom1Label.setMaxWidth(700);
        
        // Secondo assioma
        Label axiom2Title = new Label("Secondo Assioma: Normalizzazione");
        axiom2Title.setFont(Font.font("System", FontWeight.BOLD, 18));
        axiom2Title.setStyle("-fx-text-fill: #8b5cf6;");
        
        Label axiom2Label = new Label("La probabilità dell'intero spazio campionario è 1: P(Ω) = 1");
        axiom2Label.setWrapText(true);
        axiom2Label.setMaxWidth(700);
        
        // Terzo assioma
        Label axiom3Title = new Label("Terzo Assioma: Additività");
        axiom3Title.setFont(Font.font("System", FontWeight.BOLD, 18));
        axiom3Title.setStyle("-fx-text-fill: #8b5cf6;");
        
        Label axiom3Label = new Label("Per eventi mutuamente esclusivi, la probabilità dell'unione è la somma delle probabilità: P(A ∪ B) = P(A) + P(B) se A ∩ B = ∅");
        axiom3Label.setWrapText(true);
        axiom3Label.setMaxWidth(700);
        
        content.getChildren().addAll(introArea, new Separator(), 
                                   axiom1Title, axiom1Label, new Separator(),
                                   axiom2Title, axiom2Label, new Separator(),
                                   axiom3Title, axiom3Label);
        
        TitledPane titledPane = new TitledPane("Panoramica degli Assiomi", content);
        titledPane.setExpanded(true);
        return titledPane;
    }
    
    private TitledPane createImportanceSection() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        TextArea textArea = new TextArea();
        textArea.setText("Gli assiomi di Kolmogorov hanno rivoluzionato la teoria della probabilità, fornendo una base "
                + "matematica rigorosa che ha permesso di sviluppare ulteriormente questo campo. Grazie a questi assiomi, "
                + "è possibile derivare tutte le proprietà fondamentali della probabilità.\n\n"
                + "Nell'informatica, la teoria della probabilità basata sugli assiomi di Kolmogorov è fondamentale in molti campi, tra cui:\n"
                + "• Intelligenza artificiale e machine learning\n"
                + "• Analisi degli algoritmi\n"
                + "• Crittografia\n"
                + "• Teoria dell'informazione\n"
                + "• Reti neurali\n"
                + "• Simulazioni Monte Carlo\n\n"
                + "Per uno studente di informatica, comprendere questi assiomi significa acquisire le basi per affrontare "
                + "problemi complessi in cui l'incertezza gioca un ruolo fondamentale.");
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefRowCount(12);
        textArea.setMinHeight(600);
        textArea.setPrefHeight(600);
        textArea.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        content.getChildren().add(textArea);
        
        TitledPane titledPane = new TitledPane("Importanza nella Teoria della Probabilità e nell'Informatica", content);
        titledPane.setExpanded(false);
        return titledPane;
    }
}