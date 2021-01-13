package controler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResturantControler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    
    @FXML
    void onEnter(Event event) {
    	boolean atTheEndExit = true;
    	if(username.getText().trim().equals("") ||password.getText().trim().equals("")) {
    		JOptionPane.showMessageDialog(null, "املئ الحقول");
			atTheEndExit = false;
    	}
    	else {
    		try {
    				Connection con = getSqlConnection();
    				Stage s = new Stage();
    		    	FXMLLoader loader = new FXMLLoader();
    		    	loader.setLocation(getClass().getResource("/interfaces/MainMenu.fxml"));
    		    	try {s.setScene(new Scene(loader.load()));}
    		    	catch (IOException e) {}
    		    	s.setTitle("الرئيسية");
    		    	s.show();
    		    	con.close();
    		} catch (SQLException e) {
    			atTheEndExit = false;
    			JOptionPane.showMessageDialog(null, "تاكد ان الرمز صحيح");
//    			e.printStackTrace();
    		}
    	}
    	if(atTheEndExit)
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();;
    }
    @FXML
    void onExit(ActionEvent event) {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();;
    }
 
    
    /*method for connection repeted in all controler classes **/
   Connection getSqlConnection() throws SQLException {
    	String url = "jdbc:mysql://localhost:3306/resturant";
		String username1 = username.getText();
		String password1 = password.getText();
		return DriverManager.getConnection(url,username1,password1);
    }
    @FXML
    void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'Resturant.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'Resturant.fxml'.";

    }
}
