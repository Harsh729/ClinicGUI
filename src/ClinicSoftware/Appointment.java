package ClinicSoftware;

public class Appointment {
    Patient patient;
    String date;
    String procedure;
    double price;
    double paid;
    Slot time;
    LabWork lab;
    Prescription prescription;
    int userSignature;

    public Appointment(Patient patient, String date, String procedure, double price, Slot time, int userSignature) {
        this.patient = patient;
        this.date = date;
        this.procedure = procedure;
        this.price = price;
        //patient.updateMoney(price);
        paid=0;
        if(time!=null)
            this.time=time;
        else
            this.time=new Slot();
        lab=new LabWork();
        prescription = new Prescription();
        this.userSignature=userSignature;

        try{
            if(patient.getAppointmentCounter()==0)
                patient.setFirstAppointmentFile(this.getFileName());
            patient.setLatestAppointmentFile(this.getFileName());
            reflect();
        }
        catch(NullPointerException e)
        {
            System.out.println("Patient is null in Appointment constructor.");
        }
    }

    public Appointment() {
        this(Patient.defaultPatient(), "", "", 0,null,0);
    }

    public void setUserSignature(int userSignature) {
        this.userSignature = userSignature;
    }

    public int getUserSignature() {
        return userSignature;
    }

    public Appointment(Patient patient, String date, Slot t1, int userSignature) {
        this(patient, date, "", 0,t1,userSignature);
    }

    public Patient getRecord() {
        return patient;
    }

    public String getDate() {
        return date;
    }

    public String getProcedure()
    {
        return procedure;
    }

    public double getPrice()
    {
        return price;
    }

    public double getPaid()
    {
        return paid;
    }

    public double getPending()
    {
        return (price-paid);
    }

    public Slot getTime()
    {
        return time;
    }

    public String getFileName(){return patient.getName()+" "+patient.getPhone()+" "+getDate();}

    public void setPatient(Patient patient)
    {
        this.patient=patient;
    }

    public void setProcedure(String procedure)
    {
        this.procedure=procedure;
    }

    public void setPrice(double price)
    {
        this.price=price;
    }

    public void reflect()
    {
        patient.updateMoney(price);
        patient.updatePatient();
    }

    public void pay()
    {
        patient.pay(paid);
        patient.updatePatient();
    }

    public void setPaid(double paid)
    {
        this.paid=paid;
    }

    public void setTime(Slot time)
    {
        this.time=time;
    }

    public String displayAppointment()
    {
        String s=patient.getName()+"\t\t\t"+procedure;
        return s;
    }

    public void setLab(String sentDate,String receivedDate,String labName,String work)
    {
        lab.setSentDate(sentDate);
        lab.setReceivedDate(receivedDate);
        lab.setLabName(labName);
        lab.setWork(work);
        lab.setPatientName(patient.getName());
    }
    public void setLab(LabWork lab)
    {
        this.lab=lab;
    }

    public void setPrescription(String date)
    {
        prescription.setDate(date);
        prescription.setPatientName(patient.getName());
    }

    public void setPrescription(Prescription p)
    {
        this.prescription=p;
    }

    public LabWork getLab()
    {
        return lab;
    }

    public Prescription getPrescription()
    {
        return prescription;
    }

    public String getLabFileName()
    {
        if(lab!=null)
            return lab.getFileName();
        else
        {
            System.out.println("Assign lab first.");
            return "";
        }
    }

    public String getPrescriptionFileName()
    {
        if(prescription!=null)
            return prescription.getFileName();
        else
        {
            System.out.println("Assign Prescription first.");
            return "";
        }

    }

    public void display()
    {
        patient.display();
        System.out.println("\n"+date+"\n"+time.displaySlot());
        prescription.display();
    }

    public void updateAppointment()
    {
        try {
            AppointmentFile file = new AppointmentFile(this);
            file.createFile(this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void delete()
    {
        try {
            AppointmentFile af = new AppointmentFile(this);
            af.delete();
            this.patient.updateLatestAppointment(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}