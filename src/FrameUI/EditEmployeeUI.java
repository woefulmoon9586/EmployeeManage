package FrameUI;

import bean.Employee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEmployeeUI extends JFrame {
    // 定义输入框
    private JTextField empIdField;
    private JTextField nameField;
    private JComboBox<String> genderComboBox;
    private JTextField ageField;
    private JTextField departmentField;
    private JTextField positionField;
    private JTextField hireDateField;
    private JTextField salaryField;
    private JTextField phoneField;
    private JComboBox<String> educationComboBox;
    private JTextField remarkField;

    private Employee employee; // 要编辑的员工对象
    private EmployeeInfoUI employeeInfoUI; // 主界面对象

    public EditEmployeeUI(EmployeeInfoUI employeeInfoUI, Employee employee) {
        this.employeeInfoUI = employeeInfoUI;
        this.employee = employee;

        // 设置窗口标题
        setTitle("编辑员工");

        // 设置窗口大小
        setSize(400, 500);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(12, 2, 10, 10)); // 12 行 2 列，间距为 10
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 设置内边距

        // 添加工号输入框
        mainPanel.add(new JLabel("工号:"));
        empIdField = new JTextField();
        empIdField.setEditable(false); // 工号不可编辑
        mainPanel.add(empIdField);

        // 添加姓名输入框
        mainPanel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        mainPanel.add(nameField);

        // 添加性别选择框
        mainPanel.add(new JLabel("性别:"));
        String[] genders = {"男", "女"};
        genderComboBox = new JComboBox<>(genders);
        mainPanel.add(genderComboBox);

        // 添加年龄输入框
        mainPanel.add(new JLabel("年龄:"));
        ageField = new JTextField();
        mainPanel.add(ageField);

        // 添加部门输入框
        mainPanel.add(new JLabel("部门:"));
        departmentField = new JTextField();
        mainPanel.add(departmentField);

        // 添加职位输入框
        mainPanel.add(new JLabel("职位:"));
        positionField = new JTextField();
        mainPanel.add(positionField);

        // 添加入职时间输入框
        mainPanel.add(new JLabel("入职时间:"));
        hireDateField = new JTextField();
        mainPanel.add(hireDateField);

        // 添加工资输入框
        mainPanel.add(new JLabel("工资:"));
        salaryField = new JTextField();
        mainPanel.add(salaryField);

        // 添加手机号输入框
        mainPanel.add(new JLabel("手机号:"));
        phoneField = new JTextField();
        mainPanel.add(phoneField);

        // 添加学历选择框
        mainPanel.add(new JLabel("学历:"));
        String[] educations = {"本科", "硕士", "博士", "其他"};
        educationComboBox = new JComboBox<>(educations);
        mainPanel.add(educationComboBox);

        // 添加备注输入框
        mainPanel.add(new JLabel("备注:"));
        remarkField = new JTextField();
        mainPanel.add(remarkField);

        // 初始化输入框内容
        initializeFields();

        // 添加按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // 添加“完成”按钮
        JButton finishButton = new JButton("完成");
        finishButton.setBackground(new Color(0, 153, 204));
        finishButton.setForeground(Color.WHITE);
        finishButton.setFocusPainted(false);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });
        buttonPanel.add(finishButton);

        // 添加“取消”按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.setBackground(new Color(204, 0, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 关闭窗口
            }
        });
        buttonPanel.add(cancelButton);

        // 将主面板和按钮面板添加到窗口
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 显示窗口
        setVisible(true);
    }

    // 初始化输入框内容
    private void initializeFields() {
        empIdField.setText(Integer.toString(employee.getId()));
        nameField.setText(employee.getName());
        genderComboBox.setSelectedItem(employee.getSex());
        ageField.setText(String.valueOf(employee.getAge()));
        departmentField.setText(employee.getDepartment());
        positionField.setText(employee.getPosition());
        hireDateField.setText(employee.getEntryDate());
        salaryField.setText(String.valueOf(employee.getSalary()));
        phoneField.setText(employee.getPhone());
        educationComboBox.setSelectedItem(employee.getEducation());
        remarkField.setText(employee.getNote());
    }

    // 更新员工信息
    private void updateEmployee() {
        // 获取输入框的值
        String name = nameField.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        int age = Integer.parseInt(ageField.getText());
        String department = departmentField.getText();
        String position = positionField.getText();
        String hireDate = hireDateField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        String phone = phoneField.getText();
        String education = (String) educationComboBox.getSelectedItem();
        String remark = remarkField.getText();

        // 更新员工对象
        employee.setName(name);
        employee.setSex(gender);
        employee.setAge(age);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setEntryDate(hireDate);
        employee.setSalary(salary);
        employee.setPhone(phone);
        employee.setEducation(education);
        employee.setNote(remark);

        // 提示更新成功
        JOptionPane.showMessageDialog(this, "员工信息更新成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

        // 关闭窗口
        dispose();

        // 刷新主界面表格
        employeeInfoUI.renderTable();
    }
}
