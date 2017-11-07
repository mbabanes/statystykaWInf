package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

public class FXMLUtils
{
    public static Pane fxmlLoader(String fxmlPath)
    {
        FXMLLoader loader = new FXMLLoader(FXMLUtils.class.getResource(fxmlPath));
        try
        {
            return loader.load();
        }
        catch (Exception e)
        {
//            DialogUtils.errorDialog(e.getMessage());
        }

        return null;
    }


}
