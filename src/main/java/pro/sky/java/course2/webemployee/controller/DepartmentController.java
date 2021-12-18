package pro.sky.java.course2.webemployee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.service.DepartmentService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/sum-salary")
    public String sumSalary(@RequestParam("departmentId") int departmentID) {
        return "Сумма затрат на зарплаты в месяц: " + departmentService.countSumSalary(departmentID) + " рублей";
    }

    @GetMapping("/min-salary")
    public String minSalary(@RequestParam("departmentId") int departmentID) {
        return String.format("Сотрудник с минимальной зарплатой: %s рублей",
                departmentService.findMinSalary(departmentID).map(Object::toString));
    }

    @GetMapping("/max-salary")
    public String maxSalary(@RequestParam("departmentId") int departmentID) {
        return String.format("Сотрудник с минимальной зарплатой: %s рублей",
                departmentService.findMaxSalary(departmentID).map(Object::toString));
    }

    @GetMapping("/all")
    public Collection<Employee> getAll(@RequestParam("departmentId") Optional<Integer> departmentID) {
        if (departmentID.isPresent()) {
            return departmentService.getAll(departmentID.get());
        } else {
            return departmentService.getAll();
        }
    }
}


