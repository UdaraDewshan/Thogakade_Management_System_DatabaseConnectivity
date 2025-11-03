package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Button btnLogin;

    @FXML
    void btnLoginAction(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"))));
        Stage stage1 = (Stage) btnLogin.getScene().getWindow();
        stage1.close();
        stage.show();
        stage.setTitle("Login Page");
    }

}
