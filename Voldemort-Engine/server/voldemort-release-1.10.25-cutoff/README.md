# Voldemort Server

Voldemort server that provides a database service. Clients can connect to this server to interact with the database.

## Prerequisites

- Java JDK 8 or higher
- Gradle build tool

## Installing Gradle

If you do not have Gradle installed, you can install it using the following command:

```bash
sudo apt-get install gradle
```

This command works for Ubuntu-based Linux distributions. For other operating systems, please refer to the [official Gradle installation guide](https://gradle.org/install/).

## How to Build and Run

1. Open a terminal window.

2. Navigate to the directory containing the Voldemort server project.

3. Build the project using the following command:

   ```bash
   ./gradlew clean jar
   ```

4. Start the server using the command:

   ```bash
   bin/voldemort-server.sh config/single_node_cluster
   ```

## Using the Client Shell

The client shell provides quick access to the store. A test store is already defined in the "single_node_cluster", with both key and value being Strings.

To use the client shell, open another terminal and navigate to the Voldemort project directory. Then, run the following command:

   ```bash
   bin/voldemort-shell.sh test tcp://localhost:6666/
   ```
