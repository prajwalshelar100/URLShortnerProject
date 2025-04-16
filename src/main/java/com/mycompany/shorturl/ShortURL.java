package com.mycompany.shorturl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import javax.swing.border.EmptyBorder;
import java.awt.geom.RoundRectangle2D;

public class ShortURL {

    // TinyURL API endpoint for creating short URLs
    private static final String TINYURL_API_URL = "https://tinyurl.com/api-create.php?url=";
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color TEXT_COLOR = new Color(44, 62, 80);
    private static final Color FOOTER_COLOR = new Color(236, 240, 241);

    private JFrame frame;
    private JTextField originalUrlField;
    private JTextField shortUrlField;
    private JButton btnShorten;
    private JLabel statusLabel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start the Swing application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Create an instance of the ShortURL application
                ShortURL window = new ShortURL();
                // Set the application window to be visible
                window.frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error starting application: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public ShortURL() {
        initialize();
    }

    // Initialize the GUI components
    private void initialize() {
        frame = new JFrame("URL Shortener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 350);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        // Content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("URL Shortener");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_COLOR);
        contentPanel.add(titleLabel, gbc);

        // Original URL
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lblOriginalUrl = new JLabel("Original URL:");
        lblOriginalUrl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblOriginalUrl.setForeground(TEXT_COLOR);
        contentPanel.add(lblOriginalUrl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        originalUrlField = createStyledTextField();
        contentPanel.add(originalUrlField, gbc);

        // Shorten Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        btnShorten = createStyledButton("Shorten URL");
        contentPanel.add(btnShorten, gbc);

        // Shortened URL
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        shortUrlField = createStyledTextField();
        shortUrlField.setEditable(false);
        contentPanel.add(shortUrlField, gbc);

        // Status Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(statusLabel, gbc);

        // Footer Panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(FOOTER_COLOR);
        footerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        JLabel footerLabel = new JLabel("@2024 Prajwal Shelar. All rights reserved.");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(TEXT_COLOR);
        footerPanel.add(footerLabel);

        // Add panels to main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        btnShorten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shortenUrl();
            }
        });

        frame.add(mainPanel);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                    super.paintComponent(g2);
                    g2.dispose();
                } else {
                    super.paintComponent(g);
                }
            }
        };
        textField.setOpaque(false);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? PRIMARY_COLOR.darker() : PRIMARY_COLOR);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    // Method to handle URL shortening
    private void shortenUrl() {
        String originalUrl = originalUrlField.getText().trim();
        if (originalUrl.isEmpty()) {
            statusLabel.setText("Please enter a URL");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "http://" + originalUrl;
        }

        try {
            statusLabel.setText("Shortening URL...");
            statusLabel.setForeground(TEXT_COLOR);
            btnShorten.setEnabled(false);
            String shortenedUrl = generateShortUrl(originalUrl);
            if (shortenedUrl != null) {
                shortUrlField.setText(shortenedUrl);
                statusLabel.setText("URL shortened successfully!");
                statusLabel.setForeground(new Color(39, 174, 96));
            } else {
                statusLabel.setText("Failed to shorten URL. Please try again.");
                statusLabel.setForeground(Color.RED);
            }
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            statusLabel.setForeground(Color.RED);
        } finally {
            btnShorten.setEnabled(true);
        }
    }

    // Method to generate a shortened URL using the TinyURL API
    private String generateShortUrl(String originalUrl) {
        try {
            URL url = new URL(TINYURL_API_URL + originalUrl);
            String result = IOUtils.toString(url, "UTF-8").trim();
            if (result.startsWith("http")) {
                return result;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to TinyURL service: " + e.getMessage());
        }
    }
}
