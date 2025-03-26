package FrameUI;

import bean.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeUI extends JFrame {
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
    private EmployeeInfoUI employeeInfoUI;

    public AddEmployeeUI(EmployeeInfoUI employeeInfoUI) {

        this.employeeInfoUI = employeeInfoUI;
        // 设置窗口标题
        setTitle("添加员工");

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

        // 添加按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // 添加“添加”按钮
        JButton addButton = new JButton("添加");
        addButton.setBackground(new Color(0, 153, 204));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();

                employee.setId(Integer.parseInt(empIdField.getText()));
                employee.setName(nameField.getText());
                employee.setSex((String) genderComboBox.getSelectedItem());
                employee.setAge(Integer.parseInt(ageField.getText()));
                employee.setDepartment(departmentField.getText());
                employee.setPosition(positionField.getText());
                employee.setEntryDate(hireDateField.getText());
                employee.setSalary(Double.parseDouble(salaryField.getText()));
                employee.setPhone(phoneField.getText());
                employee.setEducation((String) educationComboBox.getSelectedItem());
                employee.setNote(remarkField.getText());
                employeeInfoUI.addEmployee(employee);
                JOptionPane.showMessageDialog(AddEmployeeUI.this, "员工信息添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        buttonPanel.add(addButton);

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



}
