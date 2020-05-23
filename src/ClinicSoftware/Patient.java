package ClinicSoftware;

import java.io.FileNotFoundException;
import java.util.*;

public class Patient
{
    private String name;
    private String phone;
    private String firstAppointmentFile;
    private int age;
    private String desc;
    private String latestAppointmentFile;
    private double money;
    private double paid;

    private boolean heart_condition;
    private boolean allergy;
    private boolean blood_pressure;
    private boolean diabetes;

    private Appointment firstAppointment;
    private Appointment latestAppointment;
    private boolean appointmentsBuiltFlag=false;
    private int appointment_counter = 0;
    private Stack apps = new Stack<String>();

    public Patient(String name, String phone, String firstAppointmentFile, int age, String description, String latestAppointmentFile, boolean heart_condition, boolean allergy, boolean blood_pressure, boolean diabetes, int app_ctr)
    {
        this.name=name;
        this.phone=phone;
        this.firstAppointmentFile=firstAppointmentFile;
        this.age=age;
        this.desc=description;
        this.latestAppointmentFile=latestAppointmentFile;
        money=0;
        paid=0;
        this.heart_condition=heart_condition;
        this.allergy=allergy;
        this.blood_pressure=blood_pressure;
        this.diabetes=diabetes;
        this.appointment_counter = app_ctr;

    }

    public Patient(String name, String phone, String firstAppointmentFile, int age, String description, String latestAppointmentFile, boolean heart_condition, boolean allergy, boolean blood_pressure, boolean diabetes)
    {
        this(name, phone, firstAppointmentFile,age,description,latestAppointmentFile,heart_condition,allergy,blood_pressure,diabetes,0);
    }

    public Patient()
    {
        this("","","",-1,"","",false,false,false,false,0);
    }

    public Patient(String n, String p)
    {
        this(n, p, null, -1, "", null,false, false,false,false,0);
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getFirstAppointmentFile()
    {
        return firstAppointmentFile;
    }

    public int getAge()
    {
        return age;
    }

    public String getDesc()
    {
        return desc;
    }

    public String getLatestAppointmentFile()
    {
        return latestAppointmentFile;
    }

    public double getMoney()
    {
        return money;
    }

    public boolean getHeartCondition(){return heart_condition;}

    public boolean getAllergy(){ return allergy; }

    public boolean getBloodPressure(){ return blood_pressure; }

    public boolean getDiabetes(){ return diabetes; }

    public void setDesc(String d)
    {
        desc=d;
    }

    public void setFirstAppointmentFile(String firstAppointmentFile)
    {
        this.firstAppointmentFile=firstAppointmentFile;
    }

    public void setAppointmentCounter(int appointment_counter) {
        this.appointment_counter = appointment_counter;
    }

    public int getAppointmentCounter() {
        return appointment_counter;
    }

    public void setApps(Stack apps) {
        this.apps = apps;
    }

    public Stack getApps() {
        return apps;
    }

    public void setLatestAppointmentFile(String latestAppointmentFile)
    {
        this.latestAppointmentFile=latestAppointmentFile;
        this.apps.add(latestAppointmentFile);
        appointment_counter++;
    }

    public void updateLatestAppointment(Appointment app)
    {
        if(this.apps.peek().equals(app.getFileName()))
        {
            apps.pop();
            appointment_counter--;
            latestAppointmentFile = (String)apps.peek();
        }
        else
        {
            apps.remove(app);
        }
        this.updateRecord();
    }

    public void setAge(int a)
    {
        age=a;
    }

    public void setHeartCondition(boolean hc){ heart_condition=hc; }

    public void setAllergy(boolean all){ allergy=all; }

    public void setBloodPressure(boolean bp){ blood_pressure=bp; }

    public void setDiabetes(boolean dp){ diabetes=dp; }

    public void updateMoney(double mon)
    {
        money+=mon;
    }

    public void setMoney(double money)
    {
        this.money=money;
    }

    public void setPaid(double paid) {
        this.paid += paid;
    }

    public double getPaid() {
        return paid;
    }

    public void pay(double amount)
    {
        if(amount>=0)
        {
            money-=amount;
            setPaid(amount);
        }
        else
            updateMoney(-amount);
    }

    public void buildAppointments(int userSignature)
    {
        try {
            AppointmentFile firstAppointment = new AppointmentFile(firstAppointmentFile, userSignature);
            AppointmentFile latestAppointment = new AppointmentFile(latestAppointmentFile, userSignature);
            this.firstAppointment = firstAppointment.readFile();
            this.latestAppointment = latestAppointment.readFile();
            appointmentsBuiltFlag=true;
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Error occurred: File not found");
            appointmentsBuiltFlag=false;
        }
        catch(Exception e)
        {
            System.err.println(e);
            appointmentsBuiltFlag=false;
        }


    }

    public Appointment getFirstAppointment()
    {
        if(appointmentsBuiltFlag)
            return firstAppointment;
        else
        {
            System.out.println("Please build the appointments first.");
            return null;
        }
    }

    public Appointment getLatestAppointment()
    {
        if(appointmentsBuiltFlag)
            return latestAppointment;
        else
        {
            System.out.println("Please build the appointments first.");
            return null;
        }
    }

    public String getFileName()
    {
        return name+" "+phone;
    }

    public void display()
    {
        System.out.println("Name: "+name);
        System.out.println("Phone No.: "+phone);
        System.out.println("Age: "+age);
        if(appointmentsBuiltFlag) {
            System.out.println("First Date: " + firstAppointment.getDate());
            System.out.println("Latest Date:" + latestAppointment.getDate());
        }
        else
        {
            System.out.println("First Appointment File:"+firstAppointmentFile);
            System.out.println("Latest Appointment File:"+latestAppointmentFile);
        }
        System.out.println("Description: "+desc);
        System.out.println("Total Money Paid: "+money);
        System.out.println("Heart Condition: "+heart_condition);
        System.out.println("Allergies: "+allergy);
        System.out.println("Diabetes: "+diabetes);
        System.out.println("High Blood Pressure: "+blood_pressure);
    }

    public static Patient defaultRecord()
    {
        Patient patient =new Patient();
        return patient;
    }

    public void updateRecord()
    {
        PatientFile file=new PatientFile(this);
    }
}