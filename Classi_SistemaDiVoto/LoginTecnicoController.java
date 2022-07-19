package Application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginTecnicoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private Label labelavvisopass;

    @FXML
    private Label labelavvisouser;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void pressione(ActionEvent event) {
    	
    	//lettura credenziali immesse
    	String user =username.getText().toString();
    	String pass = String.valueOf(password.getText());
    	//controlloInput su username e password, con relativo messaggio di errore
    	int UserValido = ControlloInputUser(user);
    	if(UserValido == 1) {labelavvisouser.setText("Username non può essere vuoto");}
    	if(UserValido == 2) {labelavvisouser.setText("Possibile tentativo SQLInjection");}
    	int PassValido = ControlloInputPass(pass);
    	if(PassValido == 1) {labelavvisopass.setText("Password non può essere vuoto");}
    	if(PassValido == 2) {labelavvisopass.setText("Possibile tentativo SQLInjection");}
    	if(PassValido == 3) {labelavvisopass.setText("La password deve contenere almeno 8 caratteri");}
    	if(PassValido == 4) {labelavvisopass.setText("La password deve contenere almeno una maiuscola");}

    }

    @FXML
    void initialize() {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'LoginTecnico.fxml'.";
        assert labelavvisopass != null : "fx:id=\"labelavvisopass\" was not injected: check your FXML file 'LoginTecnico.fxml'.";
        assert labelavvisouser != null : "fx:id=\"labelavvisouser\" was not injected: check your FXML file 'LoginTecnico.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'LoginTecnico.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'LoginTecnico.fxml'.";

    }
    
    public static int ControlloInputUser(String testo) {
    	if (testo.isEmpty()) {return 1;}
    	if (testo.contains("SQL")||testo.contains("SELECT")||testo.contains("DELETE")) {return 2;}
    	return 0;
    }
    public static int ControlloInputPass(String testo) {
    	if (testo.isEmpty()) {return 1;}
    	if (testo.contains("SQL")||testo.contains("SELECT")||testo.contains("DELETE")) {return 2;}
    	if (testo.length()<8) {return 3;}
    	String minuscolo = testo.toLowerCase();
    	if (minuscolo.equals(testo)) {return 4;}
    	return 0;
    }

}
