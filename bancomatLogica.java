package application;

import java.util.ArrayList;

public class bancomatLogica {

    // Lista di conti correnti come variabile di istanza. La metto a public per comodità e non usare i getter e setter
    public ArrayList<ContoCorrente> listaC;

    // Costruttore
    public bancomatLogica() {
        listaC = new ArrayList<>();
    }

    // Metodo per prelevare denaro
    public boolean preleva(String pin, double importo) {
        boolean ok = false;

        for (ContoCorrente c1 : listaC) {
            if (c1.getPin().equalsIgnoreCase(pin)) {
                // Controllo se c'è abbastanza saldo
                if (c1.getSaldo() >= importo) { // c1.saldo >= importo. Perché ho messo a public sia le variabili di ContoCorrente che l'ArrayList
                    // Aggiorno il saldo togliendo dal saldo
                    c1.saldo -= importo;
                    ok = true;
                }
            }
        }
        return ok; // Ritorna true se il prelievo è avvenuto con successo
    }

    // Metodo per versare denaro
    public boolean versa(String pin, double importo) {
        boolean ok = false;

        for (ContoCorrente c1 : listaC) {
            if (c1.getPin().equalsIgnoreCase(pin)) {
                // Aggiungo al saldo
                c1.saldo += importo;
                ok = true;
            }
        }
        return ok; // Ritorna true se il versamento è avvenuto con successo
    }

    // Metodo per ottenere il saldo se il PIN è corretto
    public double getSaldo(String pin) {
        double saldo = 0;
        for (ContoCorrente c1 : listaC) {
            if (c1.getPin().equalsIgnoreCase(pin)) { // Posso fare c1.pin.equalsIgnoreCase(pin) perché ho messo public 
                saldo = c1.saldo; // Restituisco il saldo se il PIN è corretto
            }
        }
        return saldo; // Ritorna il saldo
    }

    // Aggiungi un nuovo conto alla lista
    public void addConto(ContoCorrente c1) {
        listaC.add(c1);
    }

    // Metodo per accedere e verificare se il PIN corrisponde ai PIN della lista di conti 
    public boolean getAccesso(String pin) {
        boolean ok = false;
        for (ContoCorrente c1 : listaC) { 
            if (c1.getPin().equalsIgnoreCase(pin)) { // Posso fare c1.pin.equalsIgnoreCase(pin) perché ho messo public 
                ok = true; // Ritorna true se il PIN è trovato
            }
        }
        return ok; // Ritorna false se il PIN non è trovato
    }
}
