package view.main_menu;

import javax.swing.*;
import java.awt.*;

public class MainMenuUIComponentFactory {

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 50));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.BLACK);
        return button;
    }

    public JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        return label;
    }

    public JPanel createPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        return panel;
    }
}
