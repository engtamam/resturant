package controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ResturantControler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    void onEnter(ActionEvent event) {

    }

    @FXML
    void onExit(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'Resturant.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'Resturant.fxml'.";

    }
}
