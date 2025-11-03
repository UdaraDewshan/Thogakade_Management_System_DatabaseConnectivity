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
import javafx.stage.Stage;
import model.dto.EmployeeDTO;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

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
        loadTable();
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
    }

    @FXML
    void btnAdd(ActionEvent event) {
         String  employeeId = txtEmpId.getText();
         String name = txtName.getText();
         String nic = txtNic.getText();
         String dob = txtDob.getText();
         String position = txtPosition.getText();
         double salary = Double.parseDouble(txtSalary.getText());
         String contactName = txtContactNo.getText();
         String address = txtAddress.getText();
         String joinedDate = txtJoinDate.getText();
         String status = txtStatus.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1,employeeId);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,nic);
            preparedStatement.setObject(4,dob);
            preparedStatement.setObject(5,position);
            preparedStatement.setObject(6,salary);
            preparedStatement.setObject(7,contactName);
            preparedStatement.setObject(8,address);
            preparedStatement.setObject(9,joinedDate);
            preparedStatement.setObject(10,status);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE EmployeeID = ? ");
            preparedStatement.setObject(1,txtEmpId.getText());
            int i = preparedStatement.executeUpdate();

            if (i>0){
                JOptionPane.showMessageDialog(null,"Deleted success");
            }else{
                JOptionPane.showMessageDialog(null,"Deleted Unsuccess");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET Name = ?, NIC = ?, DateOfBirth = ? , Position = ?, Salary = ?, ContactNumber = ?, Address = ?, JoinedDate = ?, Status = ? WHERE EmployeeID = ?");
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,nic);
            preparedStatement.setObject(3,dob);
            preparedStatement.setObject(4,position);
            preparedStatement.setObject(5,salary);
            preparedStatement.setObject(6,contactName);
            preparedStatement.setObject(7,address);
            preparedStatement.setObject(8,joinedDate);
            preparedStatement.setObject(9,status);
            preparedStatement.setObject(10,employeeId);

            int i = preparedStatement.executeUpdate();
            if (i>0){
                JOptionPane.showMessageDialog(null,"Update success");
            }else{
                JOptionPane.showMessageDialog(null,"Update Unsuccess");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
        employeeDTOS.clear();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                EmployeeDTO employeeDTO = new EmployeeDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10)
                );
                employeeDTOS.add(employeeDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
