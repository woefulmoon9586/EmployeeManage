package FrameUI;

import bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register_UI extends JFrame {
    private JTextField usernameField; // 用户名输入框
    private JPasswordField passwordField; // 密码输入框
    private JTextField nicknameField; // 昵称输入框
    private Login_UI loginUI; // 关联的登录界面对象

    public Register_UI(Login_UI loginUI) {
        this.loginUI = loginUI;

        // 设置窗口标题
        setTitle("用户注册");

        // 设置窗口大小
        setSize(400, 300);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 行 2 列，间距为 10
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 设置内边距

        // 添加用户名输入框
        mainPanel.add(new JLabel("用户名:"));
        usernameField = new JTextField();
        mainPanel.add(usernameField);

        // 添加密码输入框
        mainPanel.add(new JLabel("密码:"));
        passwordField = new JPasswordField();
        mainPanel.add(passwordField);

        // 添加昵称输入框
        mainPanel.add(new JLabel("昵称:"));
        nicknameField = new JTextField();
        mainPanel.add(nicknameField);

        // 添加按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // 添加“注册”按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBackground(new Color(0, 153, 204));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        buttonPanel.add(registerButton);

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

    // 注册用户逻辑
    private void registerUser() {
        // 获取输入框的值
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String nickname = nicknameField.getText().trim();

        // 校验输入是否为空
        if (username.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名、密码和昵称不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 检查用户名是否已存在
        for (User user : loginUI.getAllUser()) {
            if (user.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "用户名已存在，请使用其他用户名！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // 创建新用户并添加到集合中
        User newUser = new User(username, password, nickname);
        loginUI.getAllUser().add(newUser);

        // 提示注册成功
        JOptionPane.showMessageDialog(this, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

        // 关闭窗口
        dispose();
    }
}
