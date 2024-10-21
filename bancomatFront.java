package application;
//Creare una finestra che rappresenta un bancomat.
//Questa finestra dovrà avere.
//Quattro pulsanti
//1 per entrare nel bancomat
//1 per effettuare un prelievo
//1 per effettuare un versamento
//1 per stampare il saldo
//Due campi di input di tipo text
//1 per inserire il pin
//1 per inserire gli importi
//All’inizio dovranno essere visibili solo il campo di input per inserire il pin e il bottone per entrare nel bancomat
//Se il pin viene verificato come giusto, compaiono le parti per effettuare le operazioni (per questa funzione utilizzare il metodo getAccesso(pin))
//che le varia parti da false diventano true
//altrimenti stampare con alert un messaggio di errore


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
// Serve per l'immagine di background ma non riesco ad inserirla
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bancomatFront extends Application {
    
    TextField logIn; // Campo per l'inserimento del PIN
    Button bEntra; // Pulsante per il log in 
    Button BtnVersa; // Pulsante per versare
    Button BtnPreleva; // Pulsante per prelevare
    Button BtnInfo;

    Label message; // Label per i messaggi
    TextField importo; // Campo per inserire l'importo

    // Importante per collegarsi al back-end di bancomat. Istanzio un oggetto bancomatLogica
    bancomatLogica bancomat = new bancomatLogica();

    public void start(Stage s1) throws Exception {
        
        // Istanzio tre conti correnti
        bancomat.addConto(new ContoCorrente("1234", 1000));
        bancomat.addConto(new ContoCorrente("4567", 2000));
        bancomat.addConto(new ContoCorrente("8901", 3000));

        GridPane gridPane = new GridPane();
        
        // Creiamo dei widget per la pagina
        logIn = new TextField();
        logIn.setPromptText("Inserisci pin");
        logIn.setPrefWidth(200); // Imposta la larghezza preferita
        bEntra = new Button("Entra");
        
       
        message = new Label(); // Label per messaggi
        importo = new TextField();
        importo.setPromptText("Inserisci importo ");
        BtnVersa = new Button("Versa");
        BtnPreleva = new Button("Preleva");
        BtnInfo= new Button("info saldo");
        
        // Azioni per i pulsanti
        bEntra.setOnAction(e -> entra());
        BtnVersa.setOnAction(e -> caricaCredito());
        BtnPreleva.setOnAction(e -> prelevaCredito());
        BtnInfo.setOnAction(e-> StampaInfo());
       
        // Nascondi i pulsanti di versamento e prelievo . il message non lo setto a false
        BtnVersa.setVisible(false);
        BtnPreleva.setVisible(false);
        BtnInfo.setVisible(false);
        importo.setVisible(false);
        message.setVisible(false);
        
        // Imposto l'allineamento del contenuto al centro
        gridPane.setAlignment(Pos.CENTER);

        // Imposto margini tra gli elementi
        gridPane.setHgap(10); // gap orizzontale
        gridPane.setVgap(10); // gap verticale
        
        // Aggiungo i widget al gridPane (colonna, riga)
        gridPane.add(logIn, 0, 0);
        gridPane.add(bEntra, 1, 0);
        gridPane.add(BtnInfo, 0, 1);
        gridPane.add(BtnPreleva, 1, 1);
        gridPane.add(BtnVersa, 2, 1);
        gridPane.add(message, 0, 3);
        gridPane.add(importo, 0, 4);
        
        // Scena
        Scene scena = new Scene(gridPane, 800, 600);
        // Applichiamo gli stili alla finestra
      //  scena.getStylesheets().add(getClass().getResource("bancomat.css").toExternalForm());
       scena.getStylesheets().add(getClass().getResource("bancomat2.css").toExternalForm()); //secondo css
        
        //aggiungo icona
        Image icon = new Image("icon.png");
        s1.getIcons().add(icon);
      
        s1.setTitle("Bancomat");
        s1.setScene(scena);
        s1.show();
    } // FINE START 
    
    // Metodi per entrare, versare e prelevare 
    public void entra() {
        String codice = logIn.getText();
        
        if (bancomat.getAccesso(codice)) {
            logIn.setVisible(false);
            BtnVersa.setVisible(true);
            BtnPreleva.setVisible(true);
            BtnInfo.setVisible(true);
            importo.setVisible(true);
            message.setVisible(true);
            
            message.setText("Login riuscito");
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Messaggio");
            // Recupero il testo del textField con il metodo getText() e lo assegno ad una variabile stringa
            String text = codice;
            // Setto il contenuto dell'alert con il valore ottenuto da input
            alert.setContentText(text + " non riconosciuto");
            alert.showAndWait();
            //visibilita bottoni
            logIn.setVisible(true);
            BtnVersa.setVisible(false);
            BtnPreleva.setVisible(false);
            BtnInfo.setVisible(false);
            importo.setVisible(false);
            message.setVisible(true);
            message.setText("Login fallito");
        }
    }

    public void caricaCredito() {
        String codice = logIn.getText();
        double importo1;

        // Uso il try-catch per gestire l'input
        try {
            importo1 = Double.parseDouble(importo.getText()); // Importo come numero
            if (bancomat.versa(codice, importo1)) { // Metodo per aumentare il credito
                message.setText("Conto caricato con successo! Credito attuale: " + bancomat.getSaldo(codice));
            } else {
                message.setText("Operazione di versamento fallita."); //ovvero se pin non valido se importo non valido oppure conto bloccato?
            }
        } catch (Exception e) { //oppure NumberFormatExecption
            message.setText("Inserisci un importo valido.");
        }
    }

    public void prelevaCredito() {
        String codice = logIn.getText();
        double importo1;

        // Uso il try-catch per gestire l'input
        try {
            importo1 = Double.parseDouble(importo.getText()); // Importo come numero
            if (bancomat.preleva(codice, importo1)) { // Metodo per prelevare il credito
                message.setText("Prelievo avvenuto con successo! Credito attuale: " + bancomat.getSaldo(codice));
            } else {
                message.setText("Operazione di prelievo fallita.");
            }
        } catch (Exception e) { //oppure NumberFormatExecption
            message.setText("Inserisci un importo valido.");
        }
    }
    
    public void StampaInfo() {
        String codice = logIn.getText(); //  PIN dall'input
        double saldo = bancomat.getSaldo(codice); // Ottieni il saldo

        // Controlla se il saldo è maggiore di zero
        if (saldo >= 0) {
            message.setText("Il tuo saldo è: " + saldo); // Mostra il saldo
        } else {
            message.setText("PIN non riconosciuto.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
