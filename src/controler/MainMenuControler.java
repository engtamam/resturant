package controler;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Drinks;
import model.Meal;

public class MainMenuControler {
	
	
	Connection con ;
	//-----------------
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField drinksCount;

    @FXML
    private TextField mealsCount;

    @FXML
    private TableView<Meal> mealTable;
    @FXML
    private TableColumn<Meal, Integer> mealColumnNo ;

    @FXML
    private TableColumn<Meal, String> mealColumnName;

    @FXML
    private TableColumn<Meal, String> mealColumnType;

    @FXML
    private TableColumn<Meal, Integer> mealColumnCost;

    @FXML
    private TextField mealSearch;

    @FXML
    private Label doneX;

    @FXML
    private Pane drinks;
    @FXML
    private Pane meals;
 
    @FXML
    private TableView<Drinks> drinkTable;
    @FXML
    private TableColumn<Drinks, Integer> drinkColumnNo;

    @FXML
    private TableColumn<Drinks, String> drinkColumnName;

    @FXML
    private TableColumn<Drinks, String> drinkColumnType;

    @FXML
    private TableColumn<Drinks, Integer> drinkColmnCost;

    @FXML
    private TextField drinkSearch;

    @FXML
    private Label doneX1;

    /*methods ------------------------------------------------------**/
    
    @FXML
    void onAddDrink(ActionEvent event) throws IOException {
    	loadInterfaces("AddDrink.fxml");
    }
    @FXML
    void onAdjustDrink(ActionEvent event) {
    	loadInterfaces("AdjustDrink.fxml");
    }

    @FXML
    void onBranchDrink(ActionEvent event) {

    }

    @FXML
    void onDeleteDrink(ActionEvent event) {
    	loadInterfaces("DeleteDrink.fxml");
    }

    @FXML
    void entered(MouseEvent event) {
    	((Button)event.getSource()).setScaleX(1.1);
    	((Button)event.getSource()).setScaleY(1.1);

    }

    @FXML
    void exited(MouseEvent event) {
    	((Button)event.getSource()).setScaleX(1);
    	((Button)event.getSource()).setScaleY(1);
    }

    @FXML
    void onDrinks(ActionEvent event) {
    	drinks.setVisible(true);
    	meals.setVisible(false);
    }

    @FXML
    void onExit2(ActionEvent event) {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();;

    }
//--------------meals--------------------------
    @FXML
    void onMeals(ActionEvent event) {
    	drinks.setVisible(false);
    	meals.setVisible(true);
    }
    @FXML
    void onAddMeal(ActionEvent event) {
    	loadInterfaces("Addmeal.fxml");
    }
    @FXML
    void onAdjustMeal(ActionEvent event) {
    	loadInterfaces("AdjustMeal.fxml");
    }
    @FXML
    void onBranchMeal(ActionEvent event) {
    }
    @FXML
    void onDeleteMeal(ActionEvent event) {
    	loadInterfaces("DeleteMeal.fxml");
    }
	@FXML
    void onMealRefresh(ActionEvent event) {
	        mealList = getMeals();
	        mealTable.setItems(mealList); 
	        int[] count = getMealsAndDrinksCount();
	        mealsCount.setText(""+count[0]);
	}
	@FXML
    void onDrinkRefresh(ActionEvent event) {
	        drinkList = getDrinks();
	        drinkTable.setItems(drinkList); 
	        int[] count = getMealsAndDrinksCount();
	        drinksCount.setText(count[1]+"");
	}
    
    
    /*Repeted method **/
    void loadInterfaces(String interfaceName) {
    	FXMLLoader loader  = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/interfaces/"+interfaceName));
    	Stage s = new Stage();
    			try {
					s.setScene(new Scene(loader.load()));
				} catch (IOException e) {
					e.printStackTrace();
				}
    			s.show();  		
    }
	//connection 
    Connection getSqlConnection() throws SQLException {
 	String url = "jdbc:mysql://localhost:3306/resturant";
		String username1 = "engtamam";
		String password1 = "123a";
		return DriverManager.getConnection(url,username1,password1);
 }
	
    //(initialization)  get Meals table
	public ObservableList<Meal> getMeals() {
	    ObservableList<Meal> mealList = FXCollections.observableArrayList();
		try {
			con = getSqlConnection();
			Statement st = con.createStatement();
			String query = "select * from meals";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				mealList.add(new Meal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "خطا في الاتصال مع قواعد البيانات");
		}

		return mealList;
	}

	// (initialization) get Drinks table
	public ObservableList<Drinks> getDrinks() {
		ObservableList<Drinks> drinkList = FXCollections.observableArrayList();
		try {
			con = getSqlConnection();
			Statement st = con.createStatement();
			String query = "select * from drinks";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				drinkList.add(new Drinks(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "خطا في الاتصال مع قواعد البيانات");
		}

		return drinkList;
	}
    //(initialization) get drink and meals counts
	int[] getMealsAndDrinksCount() {
//		int mealNo  = 0;
//		int drinkNo = 0;
		//meal count at zero index
		int[] count = new int[2];
    	 try {
			con = getSqlConnection();
			String drinkQuery = "select number from drinks";
			String mealQuery  = "select number from meals";
			Statement st1 = con.createStatement();
//			Statement st2 = con.createStatement();

			
			ResultSet rs1 = st1.executeQuery(drinkQuery);
			while(rs1.next()) {count[1]++;}
			ResultSet rs2 = st1.executeQuery(mealQuery);
			//exxception is accuring here 
			while(rs2.next()) {count[0]++;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return count;
	}
    
    
    
    
    
    
    
    
    
    
    
    ObservableList<Meal> mealList;
    ObservableList<Drinks> drinkList;

    @FXML
    void initialize() {
		int[] count = getMealsAndDrinksCount();
        drinksCount.setText(count[1]+"");
        mealsCount.setText(""+count[0]);
        
        mealColumnNo.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("id"));
        mealColumnName.setCellValueFactory(new PropertyValueFactory<Meal,String>("name"));
        mealColumnType.setCellValueFactory(new PropertyValueFactory<Meal,String>("type"));
        mealColumnCost.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("cost"));
        mealList = getMeals();
        mealTable.setItems(mealList);      
        
        drinkColumnNo.setCellValueFactory(new PropertyValueFactory<Drinks,Integer>("id"));
        drinkColumnName.setCellValueFactory(new PropertyValueFactory<Drinks,String>("name"));
        drinkColumnType.setCellValueFactory(new PropertyValueFactory<Drinks,String>("type"));
        drinkColmnCost.setCellValueFactory(new PropertyValueFactory<Drinks,Integer>("cost"));
        drinkList = getDrinks();
        drinkTable.setItems(drinkList);
        
        assert mealSearch != null : "fx:id=\"mealSearch\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert doneX != null : "fx:id=\"doneX\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert drinks != null : "fx:id=\"drinks\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert drinkSearch != null : "fx:id=\"drinkSearch\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert doneX1 != null : "fx:id=\"doneX1\" was not injected: check your FXML file 'MainMenu.fxml'.";

    }
   

}
