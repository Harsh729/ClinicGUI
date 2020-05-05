package ClinicSoftware;

import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

class first
{
    public static void main(String args[])throws Exception
    {
        Main obj = new Main();
        Main.main(args);
        obj.setTitle("Assistant Doctor");
        obj.setUS(1);
        Main.main(args);
    }
}