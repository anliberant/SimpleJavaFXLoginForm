package sample;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField countryField;

    @FXML
    private CheckBox checkBoxMan;

    @FXML
    private CheckBox checkBoxFemale;

    @FXML
    void initialize() {
        registrationButton.setOnAction(event -> {
                    signUpNewUser();
                }
        );
    }

    private void signUpNewUser() {
        DatabaseHandler handler = new DatabaseHandler();
        String firstName = nameField.getText();
        String lastName = lastNameField.getText();
        String userName = loginField.getText();
        String password = passwordField.getText();
        String location = countryField.getText();
        String gender = "";
        if (checkBoxMan.isSelected()) {
            gender = "Male";
        } else if (checkBoxFemale.isSelected()) {
            gender = "Female";
        }
        User user = new User(firstName, lastName, userName, password, location, gender);

        handler.signUpUser(user);
    }

}

