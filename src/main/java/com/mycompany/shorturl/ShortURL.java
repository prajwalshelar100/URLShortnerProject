/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.shorturl;

/**
 *
 * @author prajw
 */import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public class ShortURL {

    // TinyURL API endpoint for creating short URLs
    private static final String TINYURL_API_URL = "https://tinyurl.com/api-create.php?url=";

    private JFrame frame;
    private JTextField originalUrlField;
    private JTextField shortUrlField;

    public static void main(String[] args) {
        // Start the Swing application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Create an instance of the ShortURL application
                ShortURL window = new ShortURL();
                // Set the application window to be visible
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ShortURL() {
        initialize();
    }

    // Initialize the GUI components
    private void initialize() {
        // JFrame setup
        frame = new JFrame("URL Shortener");
        frame.setBounds(100, 100, 450, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Label for Original URL
        JLabel lblOriginalUrl = new JLabel("Original URL:");
        lblOriginalUrl.setBounds(10, 20, 100, 20);
        frame.getContentPane().add(lblOriginalUrl);

        // Text field for entering the original URL
        originalUrlField = new JTextField();
        originalUrlField.setBounds(120, 20, 300, 20);
        frame.getContentPane().add(originalUrlField);
        originalUrlField.setColumns(10);

        // Button to initiate URL shortening
        JButton btnShorten = new JButton("Shorten");
        btnShorten.setBounds(10, 50, 100, 30);
        frame.getContentPane().add(btnShorten);

        // Text field to display the shortened URL
        shortUrlField = new JTextField();
        shortUrlField.setBounds(120, 50, 300, 20);
        frame.getContentPane().add(shortUrlField);
        shortUrlField.setColumns(10);

        // ActionListener for the "Shorten" button
        btnShorten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shortenUrl(); // Calls the method to shorten the URL
            }
        });
    }

    // Method to handle URL shortening
    private void shortenUrl() {
        String originalUrl = originalUrlField.getText(); // Get the original URL from the text field
        String shortenedUrl = generateShortUrl(originalUrl); // Generate the shortened URL
        if (shortenedUrl != null) {
            // If a shortened URL is obtained, display it in the text field
            shortUrlField.setText(shortenedUrl);
        } else {
            // If an error occurs during URL shortening, display an error message
            System.err.println("Error shortening URL: " + originalUrl);
        }
    }

    // Method to generate a shortened URL using the TinyURL API
    private String generateShortUrl(String originalUrl) {
        try {
            // Create a URL object with the TinyURL API endpoint and the original URL
            URL url = new URL(TINYURL_API_URL + originalUrl);
            // Read the response from the URL and trim any leading/trailing whitespace
            return IOUtils.toString(url, "UTF-8").trim();
        } catch (Exception e) {
            // Print stack trace in case of an exception and return null
            e.printStackTrace();
            return null;
        }
    }
}
