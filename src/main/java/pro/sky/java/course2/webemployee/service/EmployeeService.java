package pro.sky.java.course2.webemployee.service;

import pro.sky.java.course2.webemployee.data.Employee;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeService {
    Employee findEmployee(String firstName, String lastName);

    void addEmployee(String firstName, String lastName, int departmentID, int salary);

    void removeEmployee(String firstName, String lastName);

    Collection<Employee> getEmployees();

    long countSumSalary();

    Employee findMinSalary();

    Employee findMaxSalary();
}
