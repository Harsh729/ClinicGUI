package ClinicSoftware;

import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

class first
{
    public static void main(String args[])throws Exception
    {
        Main parul=new Main();
        parul.setTitle("Dr. Parul Doshi");
        parul.setUserSignature(0);
        Main.main(args);

        Main asst=new Main();
        asst.setTitle("Dr. Chaitali");
        asst.setUserSignature(1);
        Main.open();
    }
}