package FrameUI;

import bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login_UI extends JFrame {

    // 定义组件
    private JTextField usernameField; // 用户名输入框
    private JPasswordField passwordField; // 密码输入框
    private JButton loginButton; // 登录按钮
    private JButton registerButton; // 注册按钮
    ArrayList<User> allUser = new ArrayList<>();


    public Login_UI() {


        allUser.add(new User("admin","123456","财家♥"));
        // 设置窗口标题
        setTitle("登录");

        // 设置窗口大小
        setSize(400, 300);

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 创建主面板
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245)); // 设置背景颜色
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 添加内边距

        // 创建约束对象
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 组件间距更小
        gbc.fill = GridBagConstraints.HORIZONTAL; // 组件水平填充

        // 添加用户名标签
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("楷体", Font.PLAIN, 14)); // 设置字体
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START; // 标签左对齐
        mainPanel.add(usernameLabel, gbc);

        // 添加用户名输入框
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("楷体", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // 输入框水平扩展
        mainPanel.add(usernameField, gbc);

        // 添加密码标签
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(new Font("楷体", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START; // 标签左对齐
        mainPanel.add(passwordLabel, gbc);

        // 添加密码输入框
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("楷体", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // 输入框水平扩展
        passwordField.setEchoChar('*');
        mainPanel.add(passwordField, gbc);

        // 创建按钮面板，用于放置登录和注册按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 居中对齐，按钮间距为10
        buttonPanel.setBackground(new Color(245, 245, 245)); // 设置背景颜色

        // 添加登录按钮
        loginButton = new JButton("登录");
        loginButton.setFont(new Font("楷体", Font.BOLD, 14));
        loginButton.setBackground(new Color(76, 188, 234)); // 设置按钮背景颜色
        loginButton.setForeground(Color.WHITE); // 设置按钮文字颜色
        loginButton.setFocusPainted(false); // 取消焦点边框
        loginButton.setPreferredSize(new Dimension(100, 30)); // 设置按钮大小
        // 登录按钮点击事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                //遍历allUser判断用户名是否存在
                boolean isUsernameExist = false;
                for (int i = 0;i < allUser.size();i++) {
                    if (allUser.get(i).getUsername().equals(username)) {
                        isUsernameExist = true;
                        if (allUser.get(i).getPassword().equals(password)) {
                            JOptionPane.showMessageDialog(Login_UI.this, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                            //跳转到主界面
                            EmployeeInfoUI employeeInfoUI = new EmployeeInfoUI(allUser.get(i).getNickname());
                            employeeInfoUI.setVisible(true);
                            dispose();
                            break;
                        } else {
                            JOptionPane.showMessageDialog(Login_UI.this, "密码错误", "提示", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }
                }
                if (!isUsernameExist) {
                    JOptionPane.showMessageDialog(Login_UI.this, "用户名不存在", "提示", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        buttonPanel.add(loginButton);

        // 添加注册按钮
        registerButton = new JButton("注册");
        registerButton.setFont(new Font("楷体", Font.BOLD, 14));
        registerButton.setBackground(new Color(161, 239, 243)); // 设置按钮背景颜色
        registerButton.setForeground(Color.WHITE); // 设置按钮文字颜色
        registerButton.setFocusPainted(false); // 取消焦点边框
        registerButton.setPreferredSize(new Dimension(100, 30)); // 设置按钮大小
        // 注册按钮点击事件
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register_UI registerUI = new Register_UI(Login_UI.this);
            }
        });
        buttonPanel.add(registerButton);

        // 将按钮面板添加到主面板
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // 合并两列
        gbc.fill = GridBagConstraints.NONE; // 不填充
        gbc.anchor = GridBagConstraints.CENTER; // 按钮居中
        mainPanel.add(buttonPanel, gbc);

        // 将主面板添加到窗口中
        add(mainPanel);

        // 设置窗口可见
        setVisible(true);
    }

    public ArrayList<User> getAllUser() {
        return allUser;
    }
}
