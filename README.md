# **Audio File API Documentation**

This document provides information on the Audio File API endpoints and instructions for building and running the project. The Audio File API allows clients to upload audio files, retrieve file information from a PostgreSQL database, and establish a WebSocket connection.

Project Overview

Framework: **Spring Boot**

Build Tool: **Maven**

Database: **PostgreSQL**

## API Endpoints

### **1. Upload Audio File**

Endpoint: POST /api/audio/upload

Description: Allows clients to upload an audio file with the filename in the format "DDMMYYYY_HHMMSS.format" (e.g., "15062022_110228.mp3"). The server will parse the filename and store the following data in the database columns: fileName (file name), fileDate (audio recording date), filePath (file path).

Request Body:
{
  "file": [audio file]
}

### **2. Get Audio File Information**

Endpoint: GET /api/audio/getFileInfo

Description: Retrieves information about a specific audio file from the database based on the provided file name. Clients can request file information and optionally download the file.

Request Parameters:

{"command": "fileInfo", "fileName": "25062022_110228.mp3"}

fileName (string): The name of the audio file to retrieve information for.

### **3. WebSocket Connection**

WebSocket: ws://localhost:8081/data

Description: Establishes a WebSocket connection for real-time communication with the server.

### **Building and Running the Project**

To build and run the project, follow these steps:

Clone the project repository from the provided source.

Ensure you have Java Development Kit (JDK) installed on your system.

Configure the PostgreSQL database connection settings in the project's configuration files (e.g., application.properties).

Open a terminal or command prompt and navigate to the project's root directory.

Build the project using Maven.

### **Sample Requests**

http://localhost:8081/api/audio/upload

http://localhost:8081/api/audio/getFileInfo


