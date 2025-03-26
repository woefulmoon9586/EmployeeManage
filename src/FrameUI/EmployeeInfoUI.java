package FrameUI;

//员工信息管理界面
/*
 * 1、要求能展示二十名员工的信息
 * 2、顶部居中位置有一个搜索框
 * 3、展示员工的信息有：工号、姓名、性别、年龄、部门、职位、入职时间、工资、手机号、学历、备注
 * 4、对员工所在行右键会出一个菜单，可以进行编辑与删除
 * 5、在搜索框右侧添加一个“添加员工”按钮
 * */

import bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EmployeeInfoUI extends JFrame {
    private String nickname;

    // 定义表格模型和表格
    private DefaultTableModel tableModel;
    private JTable employeeTable;

    // 定义搜索框
    private JTextField searchField;

    private static ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeInfoUI(String nickname) {
        this.nickname = nickname;
        // 设置窗口标题
        setTitle("欢迎 " + nickname + " 登录员工管理系统");

        // 设置窗口大小
        setSize(1000, 600);

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 创建顶部面板，用于放置搜索框和按钮
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 添加内边距
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 居中对齐

        // 创建搜索框
        searchField = new JTextField(30);
        searchField.setFont(new Font("楷体", Font.PLAIN, 14));
        topPanel.add(searchField);

        // 创建搜索按钮
        JButton searchButton = new JButton("搜索");
        searchButton.setFont(new Font("楷体", Font.BOLD, 14));
        searchButton.setBackground(new Color(0, 153, 204));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployee();
            }
        });
        topPanel.add(searchButton);

        // 创建“添加员工”按钮
        JButton addButton = new JButton("添加员工");
        addButton.setFont(new Font("楷体", Font.BOLD, 14));
        addButton.setBackground(new Color(76, 175, 80)); // 绿色背景
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeeUI(EmployeeInfoUI.this).setVisible(true);
            }
        });
        topPanel.add(addButton);

        // 将顶部面板添加到窗口中
        add(topPanel, BorderLayout.NORTH);

        // 创建表格模型
        String[] columnNames = {"工号", "姓名", "性别", "年龄", "部门", "职位", "入职时间", "工资", "手机号", "学历", "备注"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // 创建表格
        employeeTable = new JTable(tableModel);
        employeeTable.setFont(new Font("楷体", Font.PLAIN, 14));
        employeeTable.setRowHeight(25); // 设置行高
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 单项选择

        // 添加滚动面板
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // 添加右键菜单
        JPopupMenu popupMenu = new JPopupMenu();

        // 添加“编辑”菜单项
        JMenuItem editMenuItem = new JMenuItem("编辑");
        editMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editEmployee();
            }
        });
        popupMenu.add(editMenuItem);

        // 添加“删除”菜单项
        JMenuItem deleteMenuItem = new JMenuItem("删除");
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });
        popupMenu.add(deleteMenuItem);

        // 为表格添加右键点击事件
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(employeeTable, e.getX(), e.getY());
                }
            }
        });

    }

    // 搜索员工
    private void searchEmployee() {
        String keyword = searchField.getText().trim().toLowerCase();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            boolean match = false;
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                if (tableModel.getValueAt(i, j).toString().toLowerCase().contains(keyword)) {
                    match = true;
                    employeeTable.setRowSelectionInterval(i, i);
                    break;
                }
            }
            if(match)
            {
                break;
            }

        }
    }


    // 编辑员工
    private void editEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            //获得该行ArrayList中的索引，从而直接修改employees中的数据
            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            for(int i=0;i<employees.size();i++)
            {
                if(employees.get(i).getId()==id)
                {
                    new EditEmployeeUI(this,employees.get(i)).setVisible(true);
                    break;
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "请选择一行进行编辑！", "提示", JOptionPane.WARNING_MESSAGE);
        }
    }

    // 删除员工
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            for(int i=0;i<employees.size();i++)
            {
                if(employees.get(i).getId()==Integer.parseInt(tableModel.getValueAt(selectedRow,0).toString()))
                {
                    employees.remove(i);
                    break;
                }
            }
            renderTable();
        } else {
            JOptionPane.showMessageDialog(this, "请选择一行进行删除！", "提示", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        renderTable();
    }
    //写一个渲染表格的方法，每次将employees中的数据渲染到表格中
    public void renderTable()
    {
        tableModel.setRowCount(0);
        for(Employee employee:employees)
        {
            tableModel.addRow(new Object[]{
                    employee.getId(),
                    employee.getName(),
                    employee.getSex(),
                    employee.getAge(),
                    employee.getDepartment(),
                    employee.getPosition(),
                    employee.getEntryDate(),
                    employee.getSalary(),
                    employee.getPhone(),
                    employee.getEducation(),
                    employee.getNote()
            });
        }
    }
}
