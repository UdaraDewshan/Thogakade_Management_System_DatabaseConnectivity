package controller;

import connectionDB.ConnectionOB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.ItemDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item_DB_Controller implements ItemService{

    @Override
    public void add(String itemCode, String description, String category, int qtyOnHand, double unitPrice) {
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO item VALUES(?,?,?,?,?)");
            preparedStatement.setObject(1,itemCode);
            preparedStatement.setObject(2,description);
            preparedStatement.setObject(3,category);
            preparedStatement.setObject(4,qtyOnHand);
            preparedStatement.setObject(5,unitPrice);

            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String itemCode) {
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ? ");
            preparedStatement.setObject(1,itemCode);
            int i = preparedStatement.executeUpdate();

            if (i>0){
                JOptionPane.showMessageDialog(null,"DELETE SUCCESS");
            }else{
                JOptionPane.showMessageDialog(null,"DELETE UNSUCCESS");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String itemCode, String description, String category, int qtyOnHand, double unitPrice) {
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
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

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<ItemDTO> loadTable() {

        ObservableList<ItemDTO> itemDTOS = javafx.collections.FXCollections.observableArrayList();

        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
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

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return itemDTOS;
    }
}
