# Voldemort Server

Voldemort server that provides a database service. Clients can connect to this server to interact with the database.

## Prerequisites

- Java JDK 8

## Installing Java JDK

If the sever does not start properly, the Java JDK may not be compatible with the Voldemort server. To install the Java JDK, follow these steps:

1. Open a terminal window.

2. Run the following command to install the Java JDK:

   ```bash
   sudo apt-get install openjdk-8-jdk
   ```

3. If you have multiple versions of Java installed, run the following command to select the default version:

   ```bash
   sudo update-alternatives --config java
   ```

4. Run the following command to verify the installation:

   ```bash
   java -version
   ```

This command works for Ubuntu-based Linux distributions. For other operating systems, please refer to the [official Gradle installation guide](https://gradle.org/install/).

## How to Build and Run

1. Open a terminal window.

2. Navigate to the directory containing the Voldemort server project.

3. Start the server using the command:

   ```bash
   bin/voldemort-server.sh config/single_node_cluster
   ```

## Using the Client Shell

The client shell provides quick access to the store. A test store is already defined in the "single_node_cluster", with both key and value being Strings.

To use the client shell, open another terminal and navigate to the Voldemort project directory. Then, run the following command:

   ```bash
   bin/voldemort-shell.sh test tcp://localhost:6666/
   ```
