package pro.sky.java.course2.webemployee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.webemployee.service.EmployeeService;
import pro.sky.java.course2.webemployee.data.Employee;

import java.util.Collection;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                      @RequestParam("departmentId") int departmentID, @RequestParam("salary") int salary) {
        employeeService.addEmployee(firstName, lastName, departmentID, salary);
        return message(firstName, lastName, "создан");
    }

    private String message(String firstName, String lastName, String action) {
        return "Сотрудник " + firstName + " " + lastName + " успешно " + action;
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        employeeService.removeEmployee(firstName, lastName);
        return message(firstName, lastName, "удален");
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/get-all")
    public Collection<Employee> getAll() {
        return employeeService.getEmployees();
    }

    @GetMapping("/sum-salary")
    public String sumSalary() {
        return "Сумма затрат на зарплаты в месяц: " + employeeService.countSumSalary() + " рублей";
    }

    @GetMapping("/min-salary")
    public String minSalary() {
        return String.format("Сотрудник с минимальной зарплатой: %s рублей",
                employeeService.findMinSalary());
    }

    @GetMapping("/max-salary")
    public String maxSalary() {
        return String.format("Сотрудник с минимальной зарплатой: %s рублей",
                employeeService.findMaxSalary());

    }
}
