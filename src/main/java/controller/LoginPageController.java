package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void LoginAction(ActionEvent event) {

    }

    @FXML
    void btnLoginAction(ActionEvent event) throws Exception {
        String userName = usernameField.getText();
        String password = passwordField.getText();
        if (!userName.isEmpty() && !password.isEmpty()){
            if (userName.equals("admin")){
                if (password.equals("1234")){
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Catagory.fxml"))));
                    Stage stage1 = (Stage) btnLogin.getScene().getWindow();
                    stage1.close();
                    stage.show();
                    stage.setTitle("Dashboard");
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error Password");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error User Name");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password or User Name Empty");
            alert.showAndWait();
        }
    }

}
