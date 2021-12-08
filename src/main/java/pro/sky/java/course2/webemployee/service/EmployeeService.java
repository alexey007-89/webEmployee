package pro.sky.java.course2.webemployee.service;

import pro.sky.java.course2.webemployee.data.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findEmployee(String firstName, String lastName);

    void addEmployee(String firstName, String lastName);

    void removeEmployee(String firstName, String lastName);

    List<Employee> getEmployees();
}
