# URL Shortener

A simple Java Swing application that allows users to shorten long URLs using the TinyURL API.

## Features

- User-friendly graphical interface
- Quick URL shortening using TinyURL service
- Simple and intuitive design
- Error handling for invalid URLs

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Dependencies

- Apache Commons IO 2.12.0

## Building the Project

1. Clone the repository:
```bash
git clone [your-repository-url]
cd URL_shortner
```

2. Build the project using Maven:
```bash
mvn clean package
```

## Running the Application

After building, you can run the application using:

```bash
java -jar target/ShortURL-1.0-SNAPSHOT.jar
```

Or directly through Maven:

```bash
mvn exec:java
```

## Usage

1. Launch the application
2. Enter the long URL you want to shorten in the "Original URL" field
3. Click the "Shorten" button
4. The shortened URL will appear in the text field below
5. Copy the shortened URL to use it

## Project Structure

```
URL_shortner/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── mycompany/
│                   └── shorturl/
│                       └── ShortURL.java
├── pom.xml
└── README.md
```

## License

This project is licensed under the terms specified in the LICENSE file.

## Contributing

Feel free to submit issues and enhancement requests! 