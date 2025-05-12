# ChallengeApp

ChallengeApp is an Android application designed to generate random strings based on user input. It allows the user to specify the maximum length of the string and stores generated strings in a local database. The app also provides functionality to delete strings, both individually and in bulk.

## Features

- **Generate Random Strings**: Allows the user to generate random strings of a specified length.
- **Store Strings**: Saves the generated strings in a local SQLite database using Room.
- **Delete Strings**: Supports deleting generated strings either individually or all at once.
- **User Interface**: Built using Jetpack Compose for a modern and responsive UI.
- **Error Handling**: Displays error messages using Snackbars if any issues arise during operations.

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/maniyarpawan/ChallengeApp.git
   cd ChallengeApp

   2. **Technologies Used**

    Kotlin: The primary programming language used.
    Jetpack Compose: For building the UI.
    Room Database: For local data storage.
    Coroutine: For background tasks and database operations.