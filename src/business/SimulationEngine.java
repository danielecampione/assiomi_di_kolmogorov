package business;

import java.util.Random;

/**
 * Motore di simulazione per gli esperimenti probabilistici
 * Gestisce la logica di business separata dalla GUI
 */
public class SimulationEngine {
    private Random random;
    
    // Dati per la simulazione del dado
    private int[] diceCounts;
    private int diceTotal;
    
    // Dati per la simulazione della moneta
    private int coinHeads;
    private int coinTails;
    
    // Dati per la simulazione delle carte
    private int[] cardCounts; // [Asso, Re, Regina, Jack, Altre]
    private int cardTotal;
    
    public SimulationEngine() {
        this.random = new Random();
        initializeData();
    }
    
    private void initializeData() {
        // Inizializza contatori dado (6 facce)
        diceCounts = new int[6];
        diceTotal = 0;
        
        // Inizializza contatori moneta
        coinHeads = 0;
        coinTails = 0;
        
        // Inizializza contatori carte (Asso, Re, Regina, Jack, Altre)
        cardCounts = new int[5];
        cardTotal = 0;
    }
    
    // ==================== SIMULAZIONE DADO ====================
    
    /**
     * Simula il lancio di un dado a 6 facce
     * @return Il risultato del lancio (1-6)
     */
    public int rollDice() {
        int result = random.nextInt(6) + 1; // 1-6
        diceCounts[result - 1]++; // Array è 0-indexed
        diceTotal++;
        return result;
    }
    
    /**
     * Ottiene i conteggi per ogni faccia del dado
     * @return Array con i conteggi per le facce 1-6
     */
    public int[] getDiceCounts() {
        return diceCounts.clone();
    }
    
    /**
     * Ottiene il numero totale di lanci del dado
     * @return Numero totale di lanci
     */
    public int getDiceTotal() {
        return diceTotal;
    }
    
    /**
     * Calcola le frequenze relative per ogni faccia del dado
     * @return Array con le frequenze relative
     */
    public double[] getDiceFrequencies() {
        double[] frequencies = new double[6];
        if (diceTotal > 0) {
            for (int i = 0; i < 6; i++) {
                frequencies[i] = (double) diceCounts[i] / diceTotal;
            }
        }
        return frequencies;
    }
    
    /**
     * Verifica il secondo assioma per il dado (somma delle frequenze = 1)
     * @return La somma delle frequenze relative
     */
    public double getDiceFrequencySum() {
        double sum = 0.0;
        double[] frequencies = getDiceFrequencies();
        for (double freq : frequencies) {
            sum += freq;
        }
        return sum;
    }
    
    /**
     * Reset della simulazione del dado
     */
    public void resetDice() {
        diceCounts = new int[6];
        diceTotal = 0;
    }
    
    // ==================== SIMULAZIONE MONETA ====================
    
    /**
     * Simula il lancio di una moneta
     * @return true per testa, false per croce
     */
    public boolean flipCoin() {
        boolean isHeads = random.nextBoolean();
        if (isHeads) {
            coinHeads++;
        } else {
            coinTails++;
        }
        return isHeads;
    }
    
    /**
     * Ottiene il numero di teste
     * @return Numero di teste
     */
    public int getCoinHeads() {
        return coinHeads;
    }
    
    /**
     * Ottiene il numero di croci
     * @return Numero di croci
     */
    public int getCoinTails() {
        return coinTails;
    }
    
    /**
     * Ottiene il numero totale di lanci della moneta
     * @return Numero totale di lanci
     */
    public int getCoinTotal() {
        return coinHeads + coinTails;
    }
    
    /**
     * Calcola le frequenze relative per testa e croce
     * @return Array [frequenza_testa, frequenza_croce]
     */
    public double[] getCoinFrequencies() {
        int total = getCoinTotal();
        if (total > 0) {
            return new double[]{
                (double) coinHeads / total,
                (double) coinTails / total
            };
        }
        return new double[]{0.0, 0.0};
    }
    
    /**
     * Reset della simulazione della moneta
     */
    public void resetCoin() {
        coinHeads = 0;
        coinTails = 0;
    }
    
    // ==================== SIMULAZIONE CARTE ====================
    
    /**
     * Simula l'estrazione di una carta da un mazzo standard
     * @return Il tipo di carta estratta
     */
    public String drawCard() {
        // Simula un mazzo di 52 carte
        // 4 assi, 4 re, 4 regine, 4 jack, 36 altre
        int cardValue = random.nextInt(52) + 1;
        
        String cardType;
        if (cardValue <= 4) {
            cardType = "Asso";
            cardCounts[0]++;
        } else if (cardValue <= 8) {
            cardType = "Re";
            cardCounts[1]++;
        } else if (cardValue <= 12) {
            cardType = "Regina";
            cardCounts[2]++;
        } else if (cardValue <= 16) {
            cardType = "Jack";
            cardCounts[3]++;
        } else {
            cardType = "Altra";
            cardCounts[4]++;
        }
        
        cardTotal++;
        return cardType;
    }
    
    /**
     * Ottiene i conteggi per ogni tipo di carta
     * @return Array [Asso, Re, Regina, Jack, Altre]
     */
    public int[] getCardCounts() {
        return cardCounts.clone();
    }
    
    /**
     * Ottiene il numero totale di carte estratte
     * @return Numero totale di estrazioni
     */
    public int getCardTotal() {
        return cardTotal;
    }
    
    /**
     * Calcola le frequenze relative per ogni tipo di carta
     * @return Array con le frequenze relative
     */
    public double[] getCardFrequencies() {
        double[] frequencies = new double[5];
        if (cardTotal > 0) {
            for (int i = 0; i < 5; i++) {
                frequencies[i] = (double) cardCounts[i] / cardTotal;
            }
        }
        return frequencies;
    }
    
    /**
     * Calcola la probabilità dell'unione di eventi mutuamente esclusivi
     * Esempio: P(Asso ∪ Re) = P(Asso) + P(Re)
     * @return La frequenza osservata dell'unione Asso ∪ Re
     */
    public double getAceOrKingFrequency() {
        if (cardTotal > 0) {
            return (double) (cardCounts[0] + cardCounts[1]) / cardTotal;
        }
        return 0.0;
    }
    
    /**
     * Ottiene le probabilità teoriche per le carte
     * @return Array con le probabilità teoriche [Asso, Re, Regina, Jack, Altre]
     */
    public double[] getTheoreticalCardProbabilities() {
        return new double[]{
            4.0/52.0,  // Asso: 4/52
            4.0/52.0,  // Re: 4/52
            4.0/52.0,  // Regina: 4/52
            4.0/52.0,  // Jack: 4/52
            36.0/52.0  // Altre: 36/52
        };
    }
    
    /**
     * Reset della simulazione delle carte
     */
    public void resetCards() {
        cardCounts = new int[5];
        cardTotal = 0;
    }
    
    // ==================== METODI DI UTILITÀ ====================
    
    /**
     * Reset completo di tutte le simulazioni
     */
    public void resetAll() {
        resetDice();
        resetCoin();
        resetCards();
    }
    
    /**
     * Verifica se una frequenza osservata è vicina alla probabilità teorica
     * @param observed Frequenza osservata
     * @param theoretical Probabilità teorica
     * @param tolerance Tolleranza accettabile
     * @return true se la differenza è entro la tolleranza
     */
    public boolean isCloseToTheoretical(double observed, double theoretical, double tolerance) {
        return Math.abs(observed - theoretical) <= tolerance;
    }
    
    /**
     * Calcola l'errore percentuale tra frequenza osservata e probabilità teorica
     * @param observed Frequenza osservata
     * @param theoretical Probabilità teorica
     * @return Errore percentuale
     */
    public double getPercentageError(double observed, double theoretical) {
        if (theoretical == 0) return 0.0;
        return Math.abs(observed - theoretical) / theoretical * 100.0;
    }
    
    /**
     * Genera statistiche riassuntive per tutte le simulazioni
     * @return Stringa con le statistiche
     */
    public String getOverallStatistics() {
        StringBuilder stats = new StringBuilder();
        
        stats.append("=== STATISTICHE GENERALI ===\n");
        stats.append(String.format("Lanci dado: %d\n", diceTotal));
        stats.append(String.format("Lanci moneta: %d\n", getCoinTotal()));
        stats.append(String.format("Estrazioni carte: %d\n", cardTotal));
        
        if (diceTotal > 0) {
            stats.append(String.format("Verifica secondo assioma (dado): %.6f\n", getDiceFrequencySum()));
        }
        
        if (getCoinTotal() > 0) {
            double[] coinFreq = getCoinFrequencies();
            stats.append(String.format("Verifica secondo assioma (moneta): %.6f\n", coinFreq[0] + coinFreq[1]));
        }
        
        return stats.toString();
    }
}