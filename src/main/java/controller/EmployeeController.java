package controller;

import connectionDB.ConnectionOB;
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
import javafx.stage.Stage;
import model.dto.EmployeeDTO;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    EmployeeService employeeService = new Employee_DB_Controller();
    ObservableList<EmployeeDTO> employeeDTOS = FXCollections.observableArrayList();

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
    private TableColumn<?, ?> colAdress;
    @FXML
    private TableColumn<?, ?> colContact;
    @FXML
    private TableColumn<?, ?> colDob;
    @FXML
    private TableColumn<?, ?> colEmpId;
    @FXML
    private TableColumn<?, ?> colJoinDate;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colNic;
    @FXML
    private TableColumn<?, ?> colPosition;
    @FXML
    private TableColumn<?, ?> colSalary;
    @FXML
    private TableColumn<?, ?> colStates;
    @FXML
    private TableView<EmployeeDTO> tblEmployee;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtContactNo;
    @FXML
    private TextField txtDob;
    @FXML
    private TextField txtEmpId;
    @FXML
    private TextField txtJoinDate;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtNic;
    @FXML
    private TextField txtPosition;
    @FXML
    private TextField txtSalary;
    @FXML
    private TextField txtStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colStates.setCellValueFactory(new PropertyValueFactory<>("status"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblEmployee.setItems(employeeDTOS);



        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue!=null){
                txtAddress.setText(newValue.getAddress());
                txtContactNo.setText(newValue.getContactName());
                txtDob.setText(newValue.getDob());
                txtNic.setText(newValue.getNic());
                txtEmpId.setText(newValue.getEmployeeId());
                txtJoinDate.setText(newValue.getJoinedDate());
                txtPosition.setText(newValue.getPosition());
                txtName.setText(newValue.getName());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtStatus.setText(newValue.getStatus());
            }
        });
        loadTable();
    }



    @FXML
    void btnAdd(ActionEvent event) {
         String employeeId = txtEmpId.getText();
         String name = txtName.getText();
         String nic = txtNic.getText();
         String dob = txtDob.getText();
         String position = txtPosition.getText();
         double salary = Double.parseDouble(txtSalary.getText());
         String contactName = txtContactNo.getText();
         String address = txtAddress.getText();
         String joinedDate = txtJoinDate.getText();
         String status = txtStatus.getText();

         employeeService.addEmployeeDetails(employeeId,name,nic,dob,position,salary,contactName,address,joinedDate,status);
         loadTable();
         clearField();
    }

    @FXML
    void btnBack(ActionEvent event) {
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
    void btnClear(ActionEvent event) {
        clearField();
    }

    @FXML
    void btnDelete(ActionEvent event) {
        employeeService.delete(txtEmpId.getText());
        loadTable();
        clearField();
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        String employeeId = txtEmpId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String dob = txtDob.getText();
        String position = txtPosition.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String contactName = txtContactNo.getText();
        String address = txtAddress.getText();
        String joinedDate = txtJoinDate.getText();
        String status = txtStatus.getText();

        employeeService.update(employeeId,name,nic,dob,position,salary,contactName,address,joinedDate,status);

        loadTable();
        clearField();
    }


    private void clearField(){
        txtAddress.setText("");
        txtContactNo.setText("");
        txtDob.setText("");
        txtNic.setText("");
        txtEmpId.setText("");
        txtJoinDate.setText("");
        txtPosition.setText("");
        txtName.setText("");
        txtSalary.setText("");
        txtStatus.setText("");
    }

    private void loadTable(){
        tblEmployee.setItems(employeeService.loadTable());
    }

}
