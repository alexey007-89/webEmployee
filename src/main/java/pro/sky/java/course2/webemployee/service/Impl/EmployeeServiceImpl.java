package pro.sky.java.course2.webemployee.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeAlreadyExistException;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.webemployee.service.EmployeeService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName,0,0);
        List<Employee> select = employees.stream().filter(employee -> employee.equals(newEmployee))
                .distinct().collect(Collectors.toList());
        if (!select.isEmpty()) {
            return select.get(0);
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public void addEmployee(String firstName, String lastName, int departmentID, int salary) {
        Employee newEmployee = new Employee(firstName, lastName, departmentID, salary);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyExistException("Employee already exist");
        } else {
            employees.add(newEmployee);
        }
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName,0,0);
        if (!employees.remove(newEmployee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public Collection<Employee> getEmployees() {
        return employees;
    }

    @Override
    public long countSumSalary() {
        return employees.stream().mapToInt(Employee::getSalary).sum();
    }

    @Override
    public Optional<Employee> findMinSalary() {
        return employees.stream().min(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public Optional<Employee> findMaxSalary() {
        return employees.stream().max(Comparator.comparingInt(Employee::getSalary));
    }

}
