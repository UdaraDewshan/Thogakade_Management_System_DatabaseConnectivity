package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.dto.CustomerDTO;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList();


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCusID;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableView<CustomerDTO> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadCustomerDetails();

        colCusID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        tblCustomer.setItems(customerDTOS);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtDob.setText(newValue.getDob());
                txtName.setText(newValue.getName());
                txtProvince.setText(newValue.getProvince());
                txtCustID.setText(newValue.getCustID());
                txtPostalCode.setText(newValue.getPostalCode());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtTitle.setText(newValue.getTitle());
            }
        });
    }

    @FXML
    void btnAddAction(ActionEvent event) {

         String custID = txtCustID.getText();
         String title = txtTitle.getText();
         String name = txtName.getText();
         String dob = txtDob.getText();
         double salary = Double.parseDouble(txtSalary.getText());
         String address = txtAddress.getText();
         String city = txtCity.getText();
         String province = txtProvince.getText();
         String postalCode = txtPostalCode.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1,custID);
            preparedStatement.setString(2,title);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,dob);
            preparedStatement.setString(5, String.valueOf(salary));
            preparedStatement.setString(6,address);
            preparedStatement.setString(7,city);
            preparedStatement.setString(8,province);
            preparedStatement.setString(9,postalCode);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Add Unsuccessful!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadCustomerDetails();
        clearFlild();
    }


    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Catagory.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnClearAction(ActionEvent event) {
        clearFlild();
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE CustomerID = ?");
            preparedStatement.setString(1,txtCustID.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadCustomerDetails();
        clearFlild();
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        String custID = txtCustID.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDob.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET Title =?, Name =?, DateOfBirth=?, Salary =?, Address= ? ,City=? , Province= ?, PostalCode=? WHERE CustomerID = ?");

            preparedStatement.setObject(1,title);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,dob);
            preparedStatement.setObject(4,salary);
            preparedStatement.setObject(5,address);
            preparedStatement.setObject(6,city);
            preparedStatement.setObject(7,province);
            preparedStatement.setObject(8,postalCode);
            preparedStatement.setObject(9,custID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        loadCustomerDetails();
        clearFlild();

    }

    @FXML
    void onBackExit(MouseEvent event) {
        btnBack.setStyle("-fx-background-color: linear-gradient(to right, #790b68, #ff4b2b);"
                + "-fx-background-radius: 8; -fx-border-radius: 8;"
                + "-fx-font-weight: bold; -fx-font-size: 14px; -fx-cursor: hand;");
    }

    @FXML
    void onBackHover(MouseEvent event) {
        btnBack.setStyle("-fx-background-color: linear-gradient(to right, #c31432, #240b36);"
                + "-fx-background-radius: 8; -fx-border-radius: 8;"
                + "-fx-font-weight: bold; -fx-font-size: 14px; -fx-cursor: hand;");
    }



    private void clearFlild(){
        txtAddress.setText("");
        txtCity.setText("");
        txtDob.setText("");
        txtName.setText("");
        txtProvince.setText("");
        txtCustID.setText("");
        txtPostalCode.setText("");
        txtSalary.setText("");
        txtTitle.setText("");
    }

    private void loadCustomerDetails(){
        customerDTOS.clear();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement", "root", "7392");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                CustomerDTO customerDTO = new CustomerDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );
                customerDTOS.add(customerDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
