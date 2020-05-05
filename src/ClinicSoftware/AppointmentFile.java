package ClinicSoftware;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
public class AppointmentFile extends ClinicFile{
    private String folderName = "Appointments/";
    private String[] header = {"Record","Date","Procedure","Price","LabWork","Slot","Prescription","Amount paid","User Signature"};
    private String fileName = "";
    public AppointmentFile(Appointment a)throws IOException
    {
        Exception e=null;
        fileName=a.getFileName();
        if(!isFilePresent(dir,folderName,fileName))
            if((e=createFile(a))==null)
                System.out.println("File created successfully");
            else
                e.printStackTrace();
        else
            System.out.println("Exists");
    }

    public AppointmentFile(String fileName)
    {
        this.fileName=fileName;
    }

    public Exception createFile(Appointment a) {
        try {
            FileWriter fw = new FileWriter(dir + folderName + a.getFileName() + ".csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(header);
            String[] dat = {a.getRecord().getFileName(), a.getDate(), a.getProcedure(), "" + a.getPrice(), a.getLabFileName(),a.getTime().displaySlot(), a.getPrescriptionFileName(),a.getPaid()+"",a.getUserSignature()+""};
            writer.writeNext(dat);
            writer.close();
            fw.close();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    public Appointment readFile() throws IOException {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            reader.readNext();
            String arr[] = reader.readNext();
            Slot s = new Slot();
            s = s.toSlot(arr[5]);
            RecordFile rf = new RecordFile(arr[0]);
            Appointment a = new Appointment(rf.readFile(), arr[1], s);
            a.setPrice(Double.valueOf(arr[3]));
            LabWorkFile lf = new LabWorkFile(arr[4]);
            a.setLab(lf.readFile());
            a.setProcedure(arr[2]);
            PrescriptionFile pf = new PrescriptionFile(arr[6]);
            a.setPaid(Double.valueOf(arr[7]));
            a.setPrescription(pf.readFile());
            a.setUserSignature(Integer.valueOf(arr[8]));
            return a;
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Exception occurred: File not found");
            //e.printStackTrace();
            return null;
        }
        catch(Exception e)
        {
            System.err.println("An unknown exception occurred:");
            //e.printStackTrace();
            return null;
        }
    }

    public boolean delete() {
        File file = new File(dir + folderName + fileName + ".csv");
        return file.delete();
    }

    public Exception editFile(int index,String value)
    {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName+".csv");
            CSVReader reader = new CSVReader(fr);
            FileWriter fw=new FileWriter(dir+folderName+"temp.csv");
            CSVWriter writer=new CSVWriter(fw);
            reader.readNext();
            String edit[]=reader.readNext();
            reader.close();
            fw.close();
            edit[index]=value;
            writer.writeNext(header);
            writer.writeNext(edit);
            writer.close();
            fw.close();
            File file=new File(dir+folderName+fileName+".csv");
            file.delete();
            File nFile=new File(dir+folderName+"temp.csv");
            nFile.renameTo(file);
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    public Exception editFile(int index, Record r)
    {
        return editFile(index,r.getFileName());
    }

    public Exception editFile(int index, Slot time)
    {
        return editFile(index,time.displaySlot());
    }

    public Exception editFile(int index, int  age)
    {
        return editFile(index,age+"");
    }
}
