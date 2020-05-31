package ClinicSoftware;

public class LabWork
{
    private String sentDate, receivedDate, labName, work, patientName;

    public LabWork(String sentDate,String receivedDate,String labName,String work, String patientName)
    {
        this.sentDate=sentDate;
        if(receivedDate.equals("01-01-1970"))
            this.receivedDate = "Date not entered";
        else
            this.receivedDate=receivedDate;
        this.labName=labName;
        this.work=work;
        this.patientName=patientName;
    }

    public LabWork()
    {
        this("","","","","");
    }

    public String getSentDate()
    {
        return sentDate;
    }

    public String getReceivedDate()
    {
        return receivedDate;
    }

    public String getLabName()
    {
        return labName;
    }

    public String getWork()
    {
        return work;
    }

    public String getPatientName(){return patientName;}

    public String getFileName(){return patientName+" "+sentDate;}

    //setters:

    public void setSentDate(String sentDate)
    {
        this.sentDate=sentDate;
    }

    public void setReceivedDate(String receivedDate)
    {
        this.receivedDate=receivedDate;
    }

    public void setLabName(String labName)
    {
        this.labName=labName;
    }

    public void setWork(String work)
    {
        this.work=work;
    }

    public void setPatientName(String patientName) {this.patientName=patientName;}


    public void display()
    {
        System.out.println("Patient Name: " +getPatientName());
        System.out.println("Sent Date: "+getSentDate());
        System.out.println("Received Date: "+getReceivedDate());
        System.out.println("Lab Name: "+getLabName());
        System.out.println("Work: "+getWork());
    }
}