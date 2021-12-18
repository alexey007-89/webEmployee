package pro.sky.java.course2.webemployee.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.service.DepartmentService;
import pro.sky.java.course2.webemployee.service.EmployeeService;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public long countSumSalary(int departmentID) {
        return employeeService.getEmployees().stream().filter(employee -> departmentID == employee.getDepartmentId())
                .mapToInt(Employee::getSalary).sum();
    }

    @Override
    public Optional<Employee> findMinSalary(int departmentID) {
        return employeeService.getEmployees().stream().filter(employee -> departmentID == employee.getDepartmentId())
                .min(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public Optional<Employee> findMaxSalary(int departmentID) {
        return employeeService.getEmployees().stream().filter(employee -> departmentID == employee.getDepartmentId())
                .max(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public Collection<Employee> getAll(int departmentID) {
        return employeeService.getEmployees().stream().filter(employee -> departmentID == employee.getDepartmentId())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Employee> getAll() {
    return  employeeService.getEmployees().stream().sorted(Comparator
            .comparingInt(Employee::getDepartmentId)).collect(Collectors.toList());
    }
}
