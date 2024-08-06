package com.example.animeapi;

import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimeController {

    // UI components
    @FXML
    private TextField animeNameField; // Input field for the anime name
    @FXML
    private Label resultLabel; // Label to display the anime title or error messages
    @FXML
    private ImageView animeImageView; // ImageView to show the anime image
    @FXML
    private Label imageDescriptionLabel; // Label to display the anime description

    // Method to handle the button click and fetch anime information
    @FXML
    private void handleFetchAnimeInfo() {
        // Get the anime name from the input field
        String animeName = animeNameField.getText().trim();

        // Run the API call in a new thread to avoid blocking the JavaFX Application Thread
        new Thread(() -> {
            try {
                // Call the JikanAPI to search for anime information
                JsonObject response = JikanAPI.searchAnime(animeName);
                // Update the UI on the JavaFX Application Thread
                Platform.runLater(() -> displayAnimeInfo(response));
            } catch (Exception ex) {
                // Handle any errors and update the UI with an error message
                Platform.runLater(() -> resultLabel.setText("Error fetching anime info"));
                ex.printStackTrace();
            }
        }).start();
    }

    // Method to display anime information on the UI
    private void displayAnimeInfo(JsonObject response) {
        // Check if the response is valid and contains the data
        if (response == null || !response.has("data")) {
            resultLabel.setText("No anime information found.");
            return;
        }

        // Extract the first anime data object from the response
        JsonObject data = response.getAsJsonArray("data").get(0).getAsJsonObject();
        String title = data.get("title").getAsString();
        String imageUrl = data.get("images").getAsJsonObject().get("jpg").getAsJsonObject().get("image_url").getAsString();
        String description = data.get("synopsis").getAsString();

        // Update the UI with the anime title
        resultLabel.setText("Title: " + title);

        // Load and display the anime image
        Image image = new Image(imageUrl);
        animeImageView.setImage(image);

        // Update the UI with the anime description
        imageDescriptionLabel.setText(description);
    }
}
