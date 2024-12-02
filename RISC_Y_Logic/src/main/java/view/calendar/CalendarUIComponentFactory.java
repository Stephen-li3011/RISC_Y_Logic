package view.calendar;

import javax.swing.*;
import java.awt.*;

public class CalendarUIComponentFactory {

    public static JButton createButton(String text, int fontSize, Color backGroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        button.setBackground(backGroundColor);
        button.setFocusPainted(true);
        return button;
    }

    public static JLabel createLabel(String text, int fontSize, Color foregroundColor) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setForeground(foregroundColor);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
}
