package controller;

import connectionDB.ConnectionOB;
import javafx.collections.ObservableList;
import model.dto.SupplierDTO;

import java.sql.*;

public class Supplier_DB_Controller implements SupplierService{
    @Override
    public void add(String supplierID, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email) {
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO supplier VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1,supplierID);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,companyName);
            preparedStatement.setObject(4,address);
            preparedStatement.setObject(5,city);
            preparedStatement.setObject(6,province);
            preparedStatement.setObject(7,postalCode);
            preparedStatement.setObject(8,phone);
            preparedStatement.setObject(9,email);

            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String supplierID) {
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM supplier WHERE SupplierID = ?");
            preparedStatement.setObject(1,supplierID);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String supplierID, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email) {
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
            PreparedStatement stm=connection.prepareStatement("UPDATE supplier SET Name = ? , CompanyName = ? , Address = ? , City = ? , Province = ? , PostalCode = ? , Phone=?, Email= ? WHERE SupplierID=?");

            stm.setObject(1,name);
            stm.setObject(2,companyName);
            stm.setObject(3,address);
            stm.setObject(4,city);
            stm.setObject(5,province);
            stm.setObject(6,postalCode);
            stm.setObject(7,phone);
            stm.setObject(8,email);
            stm.setObject(9,supplierID);

            stm.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<SupplierDTO> loadTable() {

        ObservableList<SupplierDTO> supplierDTOS = javafx.collections.FXCollections.observableArrayList();

        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from supplier");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                SupplierDTO supplierDTO = new SupplierDTO(
                        resultSet.getNString("SupplierID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CompanyName"),
                        resultSet.getString("Address"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
                supplierDTOS.add(supplierDTO);

            };

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return supplierDTOS;
    }
}
