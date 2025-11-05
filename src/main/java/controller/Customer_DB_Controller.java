package controller;

import connectionDB.ConnectionOB;
import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer_DB_Controller implements CustemerService{

    @Override
    public void addCustomerDetails(String custID,String title,String name,String dob,double salary,String address,String city,String province,String postalCode){
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String custId){
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE CustomerID = ?");
            preparedStatement.setString(1,custId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String custID,String title,String name,String dob,double salary,String address,String city,String province,String postalCode){
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
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

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<CustomerDTO> loadTable(){

        ObservableList<CustomerDTO> customerDTOS = javafx.collections.FXCollections.observableArrayList();

        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return customerDTOS;
    }
}
