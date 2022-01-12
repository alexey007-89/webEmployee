package pro.sky.java.course2.webemployee.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeAlreadyExistException;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.webemployee.exceptions.WrongRequestException;
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
        Employee newEmployee = createEmployee(firstName, lastName, 0, 0);
        List<Employee> select = employees.stream().filter(employee -> employee.equals(newEmployee))
                .distinct().collect(Collectors.toList());
        if (!select.isEmpty()) {
            return select.get(0);
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    private String safeCapitalize(String firstName) {
        String str = StringUtils.toRootLowerCase(firstName);
        str = StringUtils.capitalize(str);
        return str;
    }

    @Override
    public void addEmployee(String firstName, String lastName, int departmentID, int salary) {
        Employee newEmployee = createEmployee(firstName, lastName,departmentID,salary);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyExistException("Employee already exist");
        } else {
            employees.add(newEmployee);
        }
    }

    private Employee createEmployee(String firstName, String lastName, int departmentID, int salary) {
        if (StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName)) {
            firstName = safeCapitalize(firstName);
            lastName = safeCapitalize(lastName);
            return new Employee(firstName, lastName, departmentID, salary);
        } else {
            throw new WrongRequestException("Not valid firstname or lastname");
        }
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        Employee newEmployee = createEmployee(firstName, lastName, 0, 0);
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
    public Employee findMinSalary() {
        return employees.stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Работник не найден"));
    }

    @Override
    public Employee findMaxSalary() {
        return employees.stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Работник не найден"));
    }
}