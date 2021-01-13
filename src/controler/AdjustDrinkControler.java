package controler;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AdjustDrinkControler {


	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane paneAdjust;

    @FXML
    private TextField drinkNum;
    @FXML
    private TextField oldDrinkNum;
    @FXML
    private TextField drinkName;

    @FXML
    private TextField drinkType;

    @FXML
    private TextField drinkCost;

    @FXML
    private Pane paneNum;

    @FXML
    void onAdjust(ActionEvent event) {
    	try {
			String url = "jdbc:mysql://localhost:3306/resturant";
    		String username = "engtamam";
    		String password = "123a";
    		Connection con = DriverManager.getConnection(url,username,password);
    	
    		String query = "update drinks set number=?, name=?, type=?, cost=? where number = ?";
    		PreparedStatement st = con.prepareStatement(query);
    		
    		st.setInt(1,Integer.parseInt(drinkNum.getText()));
    		st.setString(2,drinkName.getText());
    		st.setString(3, drinkType.getText());
    		st.setInt(4,Integer.parseInt(drinkCost.getText()));
    		st.setInt(5,Integer.parseInt(oldDrinkNum.getText()));
    		
    		st.executeUpdate();
    		st.close();
    		con.close();
		}catch(SQLException e) {
			
		}
    	paneNum.setVisible(true);
    	paneAdjust.setVisible(false);
    }

    @FXML
    void onGoToAdjust(ActionEvent event) {
    	boolean paneNo = false;
    	boolean paneAdj =true;
    	if(oldDrinkNum.getText().equals(""))
    		JOptionPane.showMessageDialog(null, "اخل رقم الوجبه المراد تعديلها");
    	else {
    		paneNum.setVisible(paneNo);
    		paneAdjust.setVisible(paneAdj);    		
    	}
    }

    @FXML
    void initialize() {
        assert paneAdjust != null : "fx:id=\"paneAdjust\" was not injected: check your FXML file 'AdjustDrink.fxml'.";
        assert drinkNum != null : "fx:id=\"drinkNum\" was not injected: check your FXML file 'AdjustDrink.fxml'.";
        assert drinkName != null : "fx:id=\"drinkName\" was not injected: check your FXML file 'AdjustDrink.fxml'.";
        assert drinkType != null : "fx:id=\"drinkType\" was not injected: check your FXML file 'AdjustDrink.fxml'.";
        assert drinkCost != null : "fx:id=\"drinkCost\" was not injected: check your FXML file 'AdjustDrink.fxml'.";
        assert paneNum != null : "fx:id=\"paneNum\" was not injected: check your FXML file 'AdjustDrink.fxml'.";

    }
}
