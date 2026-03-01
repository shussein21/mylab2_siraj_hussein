package snakebyte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Viewer {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setUndecorated(true); // remove system title bar
            frame.setLayout(new BorderLayout());

            // Title bar panel
            JPanel titleBar = new JPanel();
            titleBar.setBackground(Color.DARK_GRAY);
            titleBar.setLayout(null);
            titleBar.setPreferredSize(new Dimension(GamePanel.WIDTH, 30));

            // Circles on the far left
            JPanel red = new JPanel(); red.setBackground(Color.RED); red.setBounds(10, 5, 15, 15);
            JPanel orange = new JPanel(); orange.setBackground(Color.ORANGE); orange.setBounds(30, 5, 15, 15);
            JPanel green = new JPanel(); green.setBackground(Color.GREEN); green.setBounds(50, 5, 15, 15);
            titleBar.add(red); titleBar.add(orange); titleBar.add(green);

            // Title text
            JLabel title = new JLabel("Snake Game");
            title.setForeground(Color.WHITE);
            title.setBounds(80, 5, 200, 20);
            titleBar.add(title);

            // Exit button on the far right
            JLabel exit = new JLabel("×");
            exit.setForeground(Color.WHITE);
            exit.setFont(new Font("Arial", Font.BOLD, 20));
            exit.setBounds(GamePanel.WIDTH - 40, 0, 40, 30); // far right
            exit.setHorizontalAlignment(SwingConstants.CENTER);
            exit.setVerticalAlignment(SwingConstants.CENTER);
            exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            exit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0); // close the game
                }
            });
            titleBar.add(exit);

            // Add panels to frame
            frame.add(titleBar, BorderLayout.NORTH);
            frame.add(new GamePanel(), BorderLayout.CENTER);

            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}