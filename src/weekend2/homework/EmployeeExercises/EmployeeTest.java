package weekend2.homework.EmployeeExercises;

public class EmployeeTest {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Employee employee = Employee.hire();
            employee.show();
        }
    }
}
