package ClinicSoftware;

import sample.Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class test {

    public static void test(String[] args)throws IOException {
        Patient p1=new Patient("Name","1234567890");
        Patient p2=new Patient("Name2","999999999");
        System.out.println(p1.getName());

        Slot t1=new Slot(0.5,16);
        Appointment a1=new Appointment(p1,"12-01-2019",t1,0);
        Schedule s1=new Schedule("15-01-2019",0);
        s1.add(a1);
        s1.addBreak();
        a1.setLab(a1.getDate(),"","","");
        a1.setPrescription(a1.getDate());
        Slot t2=new Slot(19);
        Slot t3=new Slot(18.5);
        ScheduleFile sf=new ScheduleFile(s1);
        Appointment a2=new Appointment(p2,"14-01-2019",t2,0);
        Appointment a3 = new Appointment(p2, "13-01-2019", t3,0);
        sf.addEntry(a3);
        sf.addEntry(a2);
        s1.add(a3);
        s1.add(a2);
        //s1.display();
        File temp=new File("C:/Anand/Code Projects!/Directories/Schedules/temp.csv");
        File file=new File("C:/Anand/Code Projects!/Directories/Schedules/27-05-2019.csv");
        if(temp.renameTo(file))
        {
            System.out.println("File renamed successfully.");
        }

//        String fileName="Name 12-01-2019";

    }

    public static void test2()
    {
        Slot one = new Slot(17.5);
        System.out.println(Slot.format(one));
    }

    public static void main(String args[])throws IOException
    {
        //Main asst = new Main();
        chronoTest();

    }

    public static void fileTest()throws IOException
    {
        String dir = System.getProperty("user.dir")+"\\test.csv";
        FileWriter fw = new FileWriter(dir);
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(null);
        FileReader fr = new FileReader(dir);
        CSVReader reader = new CSVReader(fr);
        String line1[] = reader.readNext();
        String line2[] = reader.readNext();
        System.out.println(line1);
    }

    public static void chronoTest()
    {
        String date1 = "25-05-2020";
        String date2 = "24-06-2019";
        Patient p = new Patient("Name", "9879");
        Slot s = new Slot(17);
        Appointment a = new Appointment(p,date1,s,0);
        System.out.println(p.getLatestAppointmentFile());
        Appointment a2 = new Appointment(p,date2,s,0);
        System.out.println(p.getLatestAppointmentFile());
    }

}
