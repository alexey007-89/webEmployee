package pro.sky.java.course2.webemployee.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeAlreadyExistException;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.webemployee.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        if (employees.containsKey(firstName + lastName)) {
            return employees.get(firstName + lastName);
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public void addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyExistException("Employee already exist");
        } else {
            employees.put(firstName + lastName, newEmployee);
        }
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (!employees.remove(firstName + lastName, newEmployee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public Collection<Employee> getEmployees() {
        return employees.values();
    }
}
