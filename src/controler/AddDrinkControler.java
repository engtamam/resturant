package controler;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDrinkControler {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField drinkNum;

	@FXML
	private TextField drinkName;

	@FXML
	private TextField drinkType;

	@FXML
	private TextField drinkCost;

	@FXML
	void onAdd(ActionEvent event) {
		boolean atTheEndExit = true;
		if (drinkCost.getText().equals("") || drinkType.getText().equals("") || drinkName.getText().equals("")
				|| drinkNum.getText().equals("")) {
			JOptionPane.showMessageDialog(null, " املئ جميع الحقول");
			atTheEndExit = false;	
		}
		else {
			/* store info entered **/
			String name = drinkName.getText();
			String type = drinkType.getText();
			int number = Integer.parseInt(drinkNum.getText());
			int cost = Integer.parseInt(drinkCost.getText());

			/* start database dealtin with database **/
			try {
				/* 1- create connection with database **/
				String url = "jdbc:mysql://localhost:3306/resturant";
				String username = "engtamam";
				String password = "123a";
				Connection con = DriverManager.getConnection(url, username, password);
				// 2- create statement to check if id of meal is there
				String query1 = "select number from drinks";
				Statement st1 = con.createStatement();
				ResultSet rs = st1.executeQuery(query1);
				boolean canInsert = true;
				while (rs.next()) {
					if (rs.getInt(1) == number) {
						JOptionPane.showMessageDialog(null, "رقم المشروب معرف مسبقا ");
						canInsert = false;
						break;
					}
				}
				if (canInsert) {
					/* 3- create statement for inserting data (preparedStatement **/
					String query2 = "insert into drinks VALUES(?,?,?,?)";
					PreparedStatement st2 = con.prepareStatement(query2);
					
					/* Substitute the value in the query (statement) **/
					st2.setInt(1, number);
					st2.setString(2, name);
					st2.setString(3, type);
					st2.setInt(4, cost);

					int row = st2.executeUpdate();
					if (row > 0)
						JOptionPane.showMessageDialog(null, "you have add");
					
					/* close pipe of connection **/
					st2.close();
					con.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "خطا في الاتصال مع قواعد البيانات");
	    		atTheEndExit = false;
			}
		}
		if(atTheEndExit)
        	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

	}

	@FXML
	void initialize() {
		assert drinkNum != null : "fx:id=\"drinkNum\" was not injected: check your FXML file 'AddDrink.fxml'.";
		assert drinkName != null : "fx:id=\"drinkName\" was not injected: check your FXML file 'AddDrink.fxml'.";
		assert drinkType != null : "fx:id=\"drinkType\" was not injected: check your FXML file 'AddDrink.fxml'.";
		assert drinkCost != null : "fx:id=\"drinkCost\" was not injected: check your FXML file 'AddDrink.fxml'.";

	}
}
