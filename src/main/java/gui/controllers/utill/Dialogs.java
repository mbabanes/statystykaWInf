package gui.controllers.utill;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class Dialogs
{
    public static void errorDialog(String message)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Błąd");
        errorAlert.setHeaderText(message);

        errorAlert.showAndWait();
    }
}
