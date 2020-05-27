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

    String title = "Assistant Doctor";
    int userSignature = 1;
    static int ctr = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setUserSignature(this.userSignature);
        VBox vBox = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(vBox, 800, 500));
        primaryStage.show();
        if(ctr==0)
        {
            ctr++;
            open();
        }
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public void setUS(int US)
    {
        this.userSignature = US;
    }

    public void setUserSignature(int userSignature)
    {
        MainWindowController.userSignature=userSignature;
    }

    public static void open()
    {
        try {
            Stage stage = new Stage();
            Main mom = new Main();
            mom.setTitle("Dr. Parul Doshi");
            mom.userSignature = 0;
            mom.start(stage);
            MainWindowController.openLabWorkReminderWindow();
        }
        catch(Exception e)
        {
            System.err.println("An unknown Exception occurred:");
            e.printStackTrace();
        }
    }

    public static void openAsst()
    {
        try {
            Stage stage = new Stage();
            Main obj = new Main();
            obj.setTitle("Assistant Doctor");
            obj.userSignature = 1;
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
