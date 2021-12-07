package pro.sky.java.course2.webemployee.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeAlreadyExist;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFound;
import pro.sky.java.course2.webemployee.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName())) {
                return employee;
            }
        }
        throw new EmployeeNotFound("Employee not found");
    }

    @Override
    public void addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyExist("Employee already exist");
        } else {
            employees.add(newEmployee);
        }
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (!employees.remove(newEmployee)) {
            throw new EmployeeNotFound("Employee not found");
        }
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }
}
