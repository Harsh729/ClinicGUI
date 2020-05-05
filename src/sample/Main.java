package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application {
    String title="Dr. Parul Doshi";
    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox vBox = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(vBox, 800, 500));
        primaryStage.show();
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public void setUserSignature(int userSignature)
    {
        MainWindowController.userSignature=userSignature;
    }
    public static int ctr=0;
    public static void open()
    {
        try {
            ctr++;
            Stage stage = new Stage();
            Main obj = new Main();
            obj.start(stage);
        }
        catch(Exception e)
        {
            System.err.println("An unknown Exception occurred:");
            e.printStackTrace();
        }
    }

    public void Switch()
    {
        title="Assistant Doctor";
        setUserSignature(1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
