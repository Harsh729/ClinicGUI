package ClinicSoftware;

import java.util.LinkedList;
public class Prescription
{
    private String date,patientName;
    private LinkedList<String> medicines;
    private LinkedList<String> instruction;

    public Prescription(String patientName,String date)
    {
        this.date=date;
        this.patientName=patientName;
        medicines=new LinkedList<>();
        instruction=new LinkedList<>();
    }

    public Prescription()
    {
        this("","");
    }

    public String getDate()
    {
        return date;
    }

    public String getPatientName() {return patientName;}

    public String getFileName(){return patientName+" "+date;}

    public LinkedList<String> getMedicines()
    {
        return medicines;
    }

    public LinkedList<String> getInstruction()
    {
        return instruction;
    }

    public void setDate(String date)
    {
        this.date=date;
    }

    public void setPatientName(String patientName) {this.patientName=patientName;}

    public void addMedicine(String medicine)
    {
        medicines.add(medicine);
    }

    public void addInstruction(String instruction)
    {
        this.instruction.add(instruction);
    }

    public void addMedicineEntry(String medicine,String instruction)
    {
        this.addMedicine(medicine);
        this.addInstruction(instruction);
    }

    public void remove(String medicine)
    {
        int index=medicines.indexOf(medicine);
        medicines.remove(medicine);
        instruction.remove(index);
    }

    public void display()
    {
        int i=1,j=1;
        System.out.println("Patient Name: "+getPatientName());
        System.out.println("Date: "+getDate());
        System.out.println("Medicines\t\tInstruction");
        for(int a=0;a<getMedicines().size();a++)
            System.out.println(getMedicines().get(a)+"\t\t"+getInstruction().get(a));
    }



}