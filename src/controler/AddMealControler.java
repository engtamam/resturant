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

public class AddMealControler {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField mealNum;

	@FXML
	private TextField mealName;

	@FXML
	private TextField mealType;

	@FXML
	private TextField mealCost;

	@FXML
	void onAdd(ActionEvent event) {
    	boolean atTheEndExit  = true;
		if (mealCost.getText().equals("") || mealType.getText().equals("") || mealName.getText().equals("")
				|| mealNum.getText().equals("")) {
			JOptionPane.showMessageDialog(null, " املئ جميع الحقول");
    		atTheEndExit = false;	
		}
		else {
			/* store info entered **/
			String name = mealName.getText();
			String type = mealType.getText();
			int number = Integer.parseInt(mealNum.getText());
			int cost = Integer.parseInt(mealCost.getText());

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
						JOptionPane.showMessageDialog(null, "رقم الوجبه معرف مسبقا ");
						canInsert = false;
						break;
					}
				}
				if (canInsert) {
					/* 2- create statement for inserting data (preparedStatement **/
					String query = "insert into meals VALUES(?,?,?,?)";
					PreparedStatement st = con.prepareStatement(query);
					
					/* subsitute the value in the query (statement) **/
					st.setInt(1, number);
					st.setString(2, name);
					st.setString(3, type);
					st.setInt(4, cost);

					int row = st.executeUpdate();
					if (row > 0)
						JOptionPane.showMessageDialog(null, "you have add");
					
					/* close pipe of connection **/
					st.close();
					con.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "error in connection");
	    		atTheEndExit = false;
//				e.printStackTrace();
			}
		}
		if(atTheEndExit)
        	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}

	@FXML
	void initialize() {
		assert mealNum != null : "fx:id=\"mealNum\" was not injected: check your FXML file 'AddMeal.fxml'.";
		assert mealName != null : "fx:id=\"mealName\" was not injected: check your FXML file 'AddMeal.fxml'.";
		assert mealType != null : "fx:id=\"mealType\" was not injected: check your FXML file 'AddMeal.fxml'.";
		assert mealCost != null : "fx:id=\"mealCost\" was not injected: check your FXML file 'AddMeal.fxml'.";

	}
}
