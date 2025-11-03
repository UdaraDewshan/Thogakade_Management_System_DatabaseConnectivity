package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CatagoryPageController {

    @FXML
    private Button btnCusManagement;

    @FXML
    private Button btnEmployeeManagement;

    @FXML
    private Button btnItemManagement;

    @FXML
    private Button btnSupManagement;

    @FXML
    private void onHover(javafx.scene.input.MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: white; -fx-text-fill: #2c5364; -fx-font-size: 14px; -fx-background-radius: 12;");
    }

    @FXML
    private void onExit(javafx.scene.input.MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 12;");
    }

    @FXML
    void cusManagement(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"))));
            stage.setTitle("Customer Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = (Stage) btnCusManagement.getScene().getWindow();
        stage1.close();
        stage.show();
    }

    @FXML
    void employeeManagement(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/EmployeeView.fxml"))));
            stage.setTitle("Employee Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = (Stage) btnCusManagement.getScene().getWindow();
        stage1.close();
        stage.show();
    }

    @FXML
    void itemManagement(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"))));
            stage.setTitle("Item Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = (Stage) btnCusManagement.getScene().getWindow();
        stage1.close();
        stage.show();
    }

    @FXML
    void supManagement(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SupplierView.fxml"))));
            stage.setTitle("Supplier Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = (Stage) btnCusManagement.getScene().getWindow();
        stage1.close();
        stage.show();
    }

}


