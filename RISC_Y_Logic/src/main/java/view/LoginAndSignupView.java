package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInteractor;
import use_case.signup.SignupInteractor;
import data_access.InMemoryUserRepository;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.BorderLayout;

public class LoginAndSignupView {
    private final InMemoryUserRepository userRepository
            = new InMemoryUserRepository();

    private final LoginViewModel loginViewModel = new LoginViewModel();

    private final SignupViewModel signupViewModel = new SignupViewModel();
    private final LoginPresenter loginPresenter
            = new LoginPresenter(loginViewModel);
    private final SignupPresenter signupPresenter
            = new SignupPresenter(signupViewModel);
    private final LoginInteractor loginInteractor
            = new LoginInteractor(userRepository, loginPresenter);
    private final SignupInteractor signupInteractor
            = new SignupInteractor(userRepository, signupPresenter);
    private final LoginController loginController
            = new LoginController(loginInteractor);
    private final SignupController signupController
            = new SignupController(signupInteractor);
    JLabel loginLabel = new JLabel("Login");
    JLabel username = new JLabel("Username:");
    JLabel password = new JLabel("Password:");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JLabel signUpLabel = new JLabel("Signup");
    JLabel newUsername = new JLabel("New Username:");
    JLabel newPassword = new JLabel("New Password:");

    JTextField newUsernameField = new JTextField();
    JPasswordField newPasswordField = new JPasswordField();
    JButton signUpButton = new JButton("Signup");
    JPanel panel = new JPanel();

    JFrame frame = new JFrame("Log in or Sign up");
    public LoginAndSignupView() {

        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));
        panel.setPreferredSize(new Dimension(600, 300));

        loginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginLabel.setBounds(150, 50, 100, 30);
        loginLabel.setForeground(new Color(50, 50, 50));

        username.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        username.setBounds(30, 100, 100, 30);
        username.setForeground(new Color(50, 50, 50));

        password.setBounds(30, 150, 100, 30);
        password.setForeground(new Color(50, 50, 50));

        usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        usernameField.setBounds(100, 100, 150, 30);
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createLineBorder
                (new Color(180, 180, 180), 1));

        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordField.setBounds(100, 150, 150, 30);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginButton.setBounds(120, 200, 100, 50);
        loginButton.setForeground(new Color(70, 130, 180));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username or password cannot be empty.");
                return;
            }

            loginController.login(username, password);
        });


        loginViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                String message = loginViewModel.getState().getMessage();
                if (message.contains("Welcome")) {
                    JOptionPane.showMessageDialog(frame, message);
                    frame.dispose();
                    new MainMenuView();
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed: " + message);
                }
            }
        });

        signUpLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signUpLabel.setBounds(400, 50, 100, 30);
        signUpLabel.setForeground(new Color(50, 50, 50));

        newUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newUsername.setBounds(275, 100, 100, 30);
        newUsername.setForeground(new Color(50, 50, 50));

        newPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newPassword.setBounds(275, 150, 100, 30);
        newPassword.setForeground(new Color(50, 50, 50));

        newUsernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newUsernameField.setBounds(400, 100, 150, 30);
        newUsernameField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        newPasswordField.setBounds(400, 150, 150, 30);
        newPasswordField.setBackground(Color.WHITE);
        newPasswordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        signUpButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        signUpButton.setBounds(420, 200, 100, 50);
        signUpButton.setForeground(new Color(70, 130, 180));

        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.addActionListener(e -> {
            String username = newUsernameField.getText();
            String password = new String(newPasswordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username or password cannot be empty.");
                return;
            }
            signupController.signup(username, password);
        });

        signupViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                String message = signupViewModel.getState().getMessage();
                if (message.contains("Welcome")) {
                    JOptionPane.showMessageDialog(frame, message);
                    frame.dispose();
                    new MainMenuView();
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Signup failed: " + message);
                }
            }
        });

        panel.add(loginLabel);
        panel.add(signUpLabel);
        panel.add(username);
        panel.add(password);
        panel.add(newUsername);
        panel.add(newPassword);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(newUsernameField);
        panel.add(newPasswordField);
        panel.add(loginButton);
        panel.add(signUpButton);

        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
