package gui.panels;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Interfaccia base per tutti i pannelli dell'applicazione
 */
public interface BasePanel {
    /**
     * Restituisce il contenuto del pannello
     * @return Il nodo JavaFX che rappresenta il contenuto del pannello
     */
    Node getContent();
}