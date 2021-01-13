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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdjustMealControler {


	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane paneAdjust;

    @FXML
    private TextField mealNum;
    @FXML
    private TextField oldMealNum;
    @FXML
    private TextField mealName;

    @FXML
    private TextField mealType;

    @FXML
    private TextField mealCost;

    @FXML
    private Pane paneNum;

    @FXML
    void onAdjust(ActionEvent event) {
    	boolean atTheEndExit = true;
    	if(mealNum.getText().equals("")||mealName.getText().equals("")||
    	   mealType.getText().equals("") ||mealCost.getText().equals("") ) {
    		JOptionPane.showMessageDialog(null, "املئ جميع الحقول ");
    		atTheEndExit = false;
    	}
    	try {
			String url = "jdbc:mysql://localhost:3306/resturant";
    		String username = "engtamam";
    		String password = "123a";
    		Connection con = DriverManager.getConnection(url,username,password);
    	
    		String query = "update meals set number=?, name=?, type=?, cost=? where number = ?";
    		PreparedStatement st = con.prepareStatement(query);
    		
    		st.setInt(1,Integer.parseInt(mealNum.getText()));
    		st.setString(2,mealName.getText());
    		st.setString(3, mealType.getText());
    		st.setInt(4,Integer.parseInt(mealCost.getText()));
    		st.setInt(5,Integer.parseInt(oldMealNum.getText()));
    		
    		st.executeUpdate();
    		st.close();
    		con.close();
		}catch(SQLException e) {
    		atTheEndExit = false;

		}
//    	paneNum.setVisible(true);
//    	paneAdjust.setVisible(false);
    	if(atTheEndExit)
        	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }

    @FXML
    void onGoToAdjust(ActionEvent event) {
    	boolean paneNo = false;
    	boolean paneAdj =true;
    	if(oldMealNum.getText().equals(""))
    		JOptionPane.showMessageDialog(null, "اخل رقم الوجبه المراد تعديلها");
    	else {
    		paneNum.setVisible(paneNo);
    		paneAdjust.setVisible(paneAdj);    		
    	}
    }

    @FXML
    void initialize() {
        assert paneAdjust != null : "fx:id=\"paneAdjust\" was not injected: check your FXML file 'AdjustMeal.fxml'.";
        assert mealNum != null : "fx:id=\"mealNum\" was not injected: check your FXML file 'AdjustMeal.fxml'.";
        assert mealName != null : "fx:id=\"mealName\" was not injected: check your FXML file 'AdjustMeal.fxml'.";
        assert mealType != null : "fx:id=\"mealType\" was not injected: check your FXML file 'AdjustMeal.fxml'.";
        assert mealCost != null : "fx:id=\"mealCost\" was not injected: check your FXML file 'AdjustMeal.fxml'.";
        assert paneNum != null : "fx:id=\"paneNum\" was not injected: check your FXML file 'AdjustMeal.fxml'.";

    }
}
