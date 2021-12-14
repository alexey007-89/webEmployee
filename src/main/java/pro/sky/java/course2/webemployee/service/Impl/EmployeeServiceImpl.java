package pro.sky.java.course2.webemployee.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeAlreadyExistException;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.webemployee.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<Employee, Integer> employees;
    Integer id = 0;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        for (Employee employee: employees.keySet()) {
            if (newEmployee.equals(employee)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    @Override
    public void addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(newEmployee)) {
            throw new EmployeeAlreadyExistException("Employee already exist");
        } else {
            employees.put(newEmployee, id);
        }
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (!employees.remove(newEmployee, id)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public Set<Employee> getEmployees() {
        return employees.keySet();
    }
}
