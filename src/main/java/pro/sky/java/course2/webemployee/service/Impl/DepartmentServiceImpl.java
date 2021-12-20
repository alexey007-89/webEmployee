package pro.sky.java.course2.webemployee.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.webemployee.service.DepartmentService;
import pro.sky.java.course2.webemployee.service.EmployeeService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public long countSumSalary(int departmentID) {
        return employeeService.getEmployees().stream()
                .filter(employee -> departmentID == employee.getDepartmentId())
                .mapToInt(Employee::getSalary).sum();
    }

    @Override
    public Employee findMinSalary(int departmentID) {
        return employeeService.getEmployees().stream().
                filter(employee -> departmentID == employee.getDepartmentId())
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Работник для отдела №" + departmentID + "не найден"));
    }

    @Override
    public Employee findMaxSalary(int departmentID) {
        return employeeService.getEmployees().stream()
                .filter(employee -> departmentID == employee.getDepartmentId())
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Работник для отдела №" + departmentID + "не найден"));
    }

    @Override
    public Collection<Employee> getAll(int departmentID) {
        return employeeService.getEmployees().stream().
                filter(employee -> departmentID == employee.getDepartmentId())
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
    return  employeeService.getEmployees().stream().
            collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
