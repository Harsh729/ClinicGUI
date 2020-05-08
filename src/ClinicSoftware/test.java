package ClinicSoftware;

import sample.Main;

import java.io.File;
import java.io.IOException;

public class test extends Patient {

    public static void test(String[] args)throws IOException {
        Patient p1=new Patient("Name","1234567890");
        Patient p2=new Patient("Name2","999999999");
        System.out.println(p1.getName());

        Slot t1=new Slot(0.5,16);
        Appointment a1=new Appointment(p1,"12-1-2019",t1,0);
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

    public static void main(String args[])
    {
        //Main asst = new Main();

    }

}
