
package stokmobil;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfPages;
    @FXML
    private TableView<stokMobil> tvBooks;
    @FXML
    private TableColumn<stokMobil, Integer> colId;
    @FXML
    private TableColumn<stokMobil, String> colTitle;
    @FXML
    private TableColumn<stokMobil, String> colAuthor;
    @FXML
    private TableColumn<stokMobil, Integer> colYear;
    @FXML
    private TableColumn<stokMobil, String> colPages;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showBooks();
    }    
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stok_mobil", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<stokMobil> getBooksList(){
        ObservableList<stokMobil> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM mobil";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            stokMobil mobil;
            while(rs.next()){
                mobil = new stokMobil(rs.getInt("id"), rs.getString("nama_mobil"), rs.getString("merk"), rs.getInt("stok"),rs.getString("harga"));
                bookList.add(mobil);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }
    
    public void showBooks(){
        ObservableList<stokMobil> list = getBooksList();
        
        colId.setCellValueFactory(new PropertyValueFactory<stokMobil, Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<stokMobil, String>("nama_mobil"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<stokMobil, String>("merk"));
        colYear.setCellValueFactory(new PropertyValueFactory<stokMobil, Integer>("stok"));
        colPages.setCellValueFactory(new PropertyValueFactory<stokMobil, String>("harga"));
        
        tvBooks.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO mobil VALUES (" + tfId.getText() + ",'" + tfTitle.getText() + "','" + tfAuthor.getText() + "',"
                + tfYear.getText() + "," + tfPages.getText() + ")";
        executeQuery(query);
        showBooks();
    }
    
    private void updateRecord(){
        String query = "UPDATE  mobil SET nama_mobil  = '" + tfTitle.getText() + "', merk = '" + tfAuthor.getText() + "', stok = " +
                tfYear.getText() + ", harga = " + tfPages.getText() + " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
    
    private void deleteButton(){
        String query = "DELETE FROM mobil WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
