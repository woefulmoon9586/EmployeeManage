package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    int id;
    String name;
    String sex;
    int age;
    String department;
    String position;
    String entryDate;
    double salary;
    String phone;
    String education;
    String note;

}
