package utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Classe di utilità per la gestione di formule matematiche e visualizzazioni
 */
public class MathUtils {
    
    /**
     * Crea un oggetto Text con formattazione per formule matematiche
     * @param formula La formula matematica da visualizzare
     * @return Un oggetto Text formattato
     */
    public static Text createMathText(String formula) {
        Text text = new Text(formula);
        text.setFont(Font.font("Cambria Math", FontWeight.NORMAL, 15));
        return text;
    }
    
    /**
     * Converte una probabilità in percentuale
     * @param probability La probabilità (da 0 a 1)
     * @return La percentuale corrispondente
     */
    public static String probabilityToPercentage(double probability) {
        return String.format("%.2f%%", probability * 100);
    }
    
    /**
     * Calcola la probabilità dell'unione di due eventi mutuamente esclusivi
     * @param probA Probabilità dell'evento A
     * @param probB Probabilità dell'evento B
     * @return Probabilità dell'unione A ∪ B
     */
    public static double unionProbabilityMutuallyExclusive(double probA, double probB) {
        return probA + probB;
    }
    
    /**
     * Calcola la probabilità dell'unione di due eventi qualsiasi
     * @param probA Probabilità dell'evento A
     * @param probB Probabilità dell'evento B
     * @param probIntersection Probabilità dell'intersezione A ∩ B
     * @return Probabilità dell'unione A ∪ B
     */
    public static double unionProbability(double probA, double probB, double probIntersection) {
        return probA + probB - probIntersection;
    }
    
    /**
     * Calcola la probabilità dell'intersezione di eventi indipendenti
     * @param probabilities Array di probabilità degli eventi
     * @return Probabilità dell'intersezione
     */
    public static double intersectionIndependentEvents(double... probabilities) {
        double result = 1.0;
        for (double prob : probabilities) {
            result *= prob;
        }
        return result;
    }
    
    /**
     * Calcola la probabilità complementare (1 - P(A))
     * @param probability Probabilità dell'evento
     * @return Probabilità dell'evento complementare
     */
    public static double complementProbability(double probability) {
        return 1.0 - probability;
    }
}