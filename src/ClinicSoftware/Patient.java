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
    private Stack appointment_stack = new Stack<String>();

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

    public void setAppointment_stack(Stack appointment_stack) {
        this.appointment_stack = appointment_stack;
    }

    public Stack getAppointmentStack() {
        return appointment_stack;
    }

    public void setLatestAppointmentFile(String latestAppointmentFile)
    {
        if(!appointment_stack.empty())
            if (appointment_stack.peek().equals(latestAppointmentFile))
                return;
            this.appointment_stack = chronoAdd(this.appointment_stack,latestAppointmentFile);
            if(!appointment_stack.isEmpty()) {
                this.latestAppointmentFile = (String) appointment_stack.peek();
                this.setFirstAppointmentFile((String) appointment_stack.get(0));
                appointment_counter++;
            }
    }

    public static String getDateFromAppointment(String file)
    {
        if(file!=null) {
            int start = file.indexOf('-') - 2;
            if (start != -3)
                return file.substring(start);
            else
                return "Date not found";
        }
        else
            return "Date not found";
    }

    public Stack chronoAdd(Stack s, String ele)//adding chronologically
    {//TODO: debug when not in chronological order
        int start = ele.indexOf('-') - 2;//dd-mm-yyyy
        if(start == -3)
            return s;
        String str[] = new String[s.size()];
        MyDate date = new MyDate(ele.substring(start));
        int ctr = 0;
        for (int i = 0; i < s.size(); i++) {
            str[ctr] = (String) s.peek();
            MyDate date2 = new MyDate(str[ctr].substring(start));
            if (date.isLaterThan(date2) && ele!=null) {
                s.push(ele);
                ctr--;
                break;
            }
            else {
                s.pop();
                ctr++;
            }
        }
        for (int i = ctr; i >= 0; i--) {
            if(s.isEmpty()) {
                s.push(ele);
                continue;
            }
            if(str[i] != null && !str[i].equals(s.peek()))
            s.push(str[i]);
        }
        return s;
    }

    public void updateLatestAppointment(Appointment app)//after deletion of latest appointment
    {
        if(!this.appointment_stack.empty()) {
            if (this.appointment_stack.peek().equals(app.getFileName())) {
                appointment_stack.pop();
                appointment_counter--;
                if(!appointment_stack.isEmpty())
                    latestAppointmentFile = (String) appointment_stack.peek();
                else
                {
                    if(appointment_counter!=0)
                        System.out.println("stack problem ln 196");
                    latestAppointmentFile = null;
                    firstAppointmentFile = null;
                }
            } else {
                appointment_stack.remove(app);
            }
            this.updatePatient();
        }
        else
            latestAppointmentFile = null;
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

    public static Patient defaultPatient()
    {
        Patient patient =new Patient();
        return patient;
    }

    public void updatePatient()
    {//TODO: debug this. Exception is prob not null.
        PatientFile file=new PatientFile(this);
        Exception e = file.createFile(this);
        if(e!=null)
            e.printStackTrace();
        else
            System.out.println("No exception at updatePatient");
    }
}