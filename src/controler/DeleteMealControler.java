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
import javafx.stage.Stage;

public class DeleteMealControler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField mealNum;

    @FXML
    void onDelete(ActionEvent event) {
    	boolean atTheEndExit = true;
    	if(mealNum.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "ادخل رقم المشروب");
    		atTheEndExit = false;
    	}
    	else {
    		try {
    		/*start dealing with database**/
    		/*1- connect to resturant database**/
    		String url      = "jdbc:mysql://localhost:3306/resturant";
    		String username =  "engtamam";
    		String password = "123a";
    		Connection con = DriverManager.getConnection(url,username,password);
    		
    		/*2- create the query and then the statement**/
    		String query1 = "delete from meals where number = ?";
    		PreparedStatement st = con.prepareStatement(query1);
    		int mealNo = 0 ;
    		try {
				mealNo = Integer.parseInt(mealNum.getText());
	    	}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "اددخل في الحقل فقط ارقام");
	    		atTheEndExit = false;
			}
    		st.setInt(1,mealNo);
    		/*3- execute the query**/
    		st.executeUpdate();
    		st.close();
    		con.close();
    		}catch(SQLException e) {
        		atTheEndExit = false;    			
    		}
    	}
    	if(atTheEndExit)
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void initialize() {
        assert mealNum != null : "fx:id=\"mealNum\" was not injected: check your FXML file 'DeleteMeal.fxml'.";

    }
}
