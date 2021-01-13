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

public class DeleteDrinkControler {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField drinkNum;

	@FXML
	void onDelete(ActionEvent event) {
		boolean atTheEndExit = true;
		// drinkNo = Integer.parseInt(drinkNum.getText());
		if (drinkNum.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "ادخل رقم المشروب");
			atTheEndExit = false;
		} else {
			int drinkNo = Integer.parseInt(drinkNum.getText());
			
			
			try {
				/* start dealing with database **/
				/* 1- connect to resturant database **/
				String url = "jdbc:mysql://localhost:3306/resturant";
				String username = "engtamam";
				String password = "123a";
				Connection con = DriverManager.getConnection(url, username, password);
				// 2- create statement to check if id of meal is there
				String query1 = "select number from drinks";
				Statement st1 = con.createStatement();
				ResultSet rs = st1.executeQuery(query1);
				boolean canInsert = false;
				while (rs.next()) {
					if (rs.getInt(1) == drinkNo) {
						canInsert = true;
						JOptionPane.showMessageDialog(null, " تم حذف الوجبه");

						break;
					}
				}
				if (canInsert) {
					/* 3- create the query and then the statement **/
					String query2 = "delete from drinks where number = ?";
					PreparedStatement st = con.prepareStatement(query2);
					
					st.setInt(1, drinkNo);
					/* 3- execute the query **/
					st.executeUpdate();
					st.close();
					con.close();
				}
				else
					JOptionPane.showMessageDialog(null, "رقم المشروب غير معرف مسبقا ");

			} catch (SQLException e) {
				atTheEndExit = false;

			}
		}
		if (atTheEndExit)
			((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
	}

	@FXML
	void initialize() {
		assert drinkNum != null : "fx:id=\"drinkNum\" was not injected: check your FXML file 'DeleteDrink.fxml'.";

	}
}
