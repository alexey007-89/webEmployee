package pro.sky.java.course2.webemployee.constant;

import pro.sky.java.course2.webemployee.data.Employee;

public class EmployeeConstants {
    public static final Employee EMPLOYEE_1 = new Employee("Alexey", "Ivanov", 1, 50_000);
    public static final Employee EMPLOYEE_2 = new Employee("Ivan", "Kashtanov", 2, 60_000);
    public static final Employee EMPLOYEE_3 = new Employee("Kiril", "Kucherov", 3, 20_000);
    public static final Employee EMPLOYEE_2_1  = new Employee("Alexey", "Ivanov", 1, 50_000);
    public static final Employee EMPLOYEE_2_2  = new Employee("Ivan", "Kashtanov", 1, 60_000);
    public static final Employee EMPLOYEE_2_3  = new Employee("Kiril", "Kucherov", 1, -10_000);
    public static final Employee EMPLOYEE_2_4  = new Employee("Darya", "Prihodko", 2, -40_000);
    public static final Employee EMPLOYEE_2_5  = new Employee("Elena", "Golovach", 2, 0);
}
