package controller.employee;

import connectionDB.ConnectionOB;
import javafx.collections.ObservableList;
import model.dto.EmployeeDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee_DB_Controller implements EmployeeService {

    @Override
    public void addEmployeeDetails(String employeeId, String name, String nic, String dob, String position, double salary, String contactName, String address, String joinedDate, String status) {
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
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

            int i = preparedStatement.executeUpdate();
            if (i>0){
                JOptionPane.showMessageDialog(null, "Add Successfully!");
            }else{
                JOptionPane.showMessageDialog(null, "Add Unsuccessful!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String empId){
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE EmployeeID = ? ");
            preparedStatement.setObject(1,empId);
            int i = preparedStatement.executeUpdate();

            if (i>0){
                JOptionPane.showMessageDialog(null,"Deleted success");
            }else{
                JOptionPane.showMessageDialog(null,"Deleted Unsuccess");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String employeeId, String name, String nic, String dob, String position, double salary, String contactName, String address, String joinedDate, String status) {
        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
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

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<EmployeeDTO> loadTable(){

        ObservableList<EmployeeDTO> employeeDTOS = javafx.collections.FXCollections.observableArrayList();

        try {
            Connection connection = ConnectionOB.getInstance().getConnection();
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeeDTOS;
    }
}
