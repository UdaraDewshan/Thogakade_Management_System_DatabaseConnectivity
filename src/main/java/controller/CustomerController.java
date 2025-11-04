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
    CustemerService custemerService = new Customer_DB_Controller();



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

        loadCustomerDetails();

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

        custemerService.addCustomerDetails(custID,title,name,dob,salary,address,city,province,postalCode);
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
        custemerService.deleteCustomer(txtCustID.getText());
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


        custemerService.updateCustomer(custID,title,name,dob,salary,address,city,province,postalCode);
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
        tblCustomer.setItems(custemerService.loadTable());
    }

}
