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
import model.dto.CustomerDTO;
import model.dto.ItemDTO;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList();


    @FXML
    private TextField txtCatagary;

    @FXML
    private TextField txtQty;

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
    private TableColumn<?, ?> colcode;

    @FXML
    private TableColumn<?, ?> colCatogory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<ItemDTO> tblItem;

    @FXML
    private TextField txtDiscription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();

        colCatogory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colcode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItem.setItems(itemDTOS);

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                txtItemCode.setText(newValue.getItemCode());
                txtDiscription.setText(newValue.getDescription());
                txtPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtCatagary.setText(newValue.getCategory());
                txtQty.setText(String.valueOf(newValue.getQtyOnHand()));

            }
        });

    }

    private void loadTable() {
        itemDTOS.clear();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("select * FROM item");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ItemDTO itemDTO = new ItemDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getDouble(5)
                );
                itemDTOS.add(itemDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnAddAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String description = txtDiscription.getText();
        String category = txtCatagary.getText();
        int qtyOnHand = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtPrice.getText());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO item VALUES(?,?,?,?,?)");
            preparedStatement.setObject(1,itemCode);
            preparedStatement.setObject(2,description);
            preparedStatement.setObject(3,category);
            preparedStatement.setObject(4,qtyOnHand);
            preparedStatement.setObject(5,unitPrice);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        loadTable();
        clerText();
    }

    @FXML
    void btnBackActiioon(ActionEvent event) {
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
        clerText();
    }



    @FXML
    void btnDeleteAction(ActionEvent event) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ? ");
            preparedStatement.setObject(1,txtItemCode.getText());
            int i = preparedStatement.executeUpdate();

            if (i>0){
                JOptionPane.showMessageDialog(null,"DELETE SUCCESS");
            }else{
                JOptionPane.showMessageDialog(null,"DELETE UNSUCCESS");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        loadTable();
        clerText();
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String description = txtDiscription.getText();
        String category = txtCatagary.getText();
        int qtyOnHand = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtPrice.getText());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/togakademanagement","root","7392");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item SET Description = ?, Category = ?, QtyOnHand = ? , UnitPrice = ? WHERE ItemCode = ?");
            preparedStatement.setObject(1,description);
            preparedStatement.setObject(2,category);
            preparedStatement.setObject(3,qtyOnHand);
            preparedStatement.setObject(4,unitPrice);
            preparedStatement.setObject(5,itemCode);

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
        clerText();
    }

    private void clerText(){
        txtQty.setText("");
        txtDiscription.setText("");
        txtCatagary.setText("");
        txtPrice.setText("");
        txtItemCode.setText("");
    }


}
