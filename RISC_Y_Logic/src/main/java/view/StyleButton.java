package view;

import javax.swing.*;
import java.awt.*;

public class StyleButton extends JButton {
    public StyleButton(String text) {
        this.setText(text);
        this.setFont(new Font("SansSerif", Font.PLAIN, 18));
        this.setBackground(new Color(173, 216, 230));
        this.setFocusPainted(false);
    }
}
