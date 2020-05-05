package ClinicSoftware;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;

public class RecordFile extends ClinicFile{
    private String folderName = "Records/";
    private String[] header = {"Name","Phone No.","Age","First Date","Latest Date","Description","Money","Heart Condition","Allergy","Diabetes","Blood Pressure","Amount Paid"};
    private String fileName = "";

    public RecordFile(Record r)
    {
        Exception e=null;
        fileName=r.getFileName();
        if(!isFilePresent(dir,folderName,fileName))
            if((e=createFile(r))==null)
                System.out.println("File created successfully");
            else
                e.printStackTrace();
    }

    public RecordFile(String fileName)
    {
        this.fileName=fileName;
    }

    Exception createFile(Record patient) {
        try {
            FileWriter fw = new FileWriter(dir + folderName + patient.getFileName() + ".csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(header);
            String[] recordDetails = {patient.getName(), "" + patient.getPhone(), "" + patient.getAge(), patient.getFirstAppointmentFile(), patient.getLatestAppointmentFile(), patient.getDesc(), patient.getMoney() + "", patient.getHeartCondition() + "", patient.getAllergy() + "", patient.getDiabetes() + "", patient.getBloodPressure() + "",patient.getPaid()+""};
            writer.writeNext(recordDetails);
            writer.close();
            fw.close();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    public Record readFile() throws IOException {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            reader.readNext();
            String arr[] = reader.readNext();
            Record r = new Record(arr[0], arr[1]);
            r.setMoney(Double.valueOf(arr[6]));
            r.setAge(Integer.valueOf(arr[2]));
            r.setFirstAppointmentFile(arr[3]);
            r.setLatestAppointmentFile(arr[4]);
            r.setDesc(arr[5]);
            r.setHeartCondition(Boolean.valueOf(arr[7]));
            r.setAllergy(Boolean.valueOf(arr[8]));
            r.setDiabetes(Boolean.valueOf(arr[9]));
            r.setBloodPressure(Boolean.valueOf(arr[10]));
            r.setPaid(Double.valueOf(arr[11]));
            return r;
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
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteFile()
    {
        File file = new File(dir+folderName + fileName + ".csv");
        return file.delete();
    }

    Exception editFile(int index,String value)
    {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName);
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
            File file=new File(dir+folderName+fileName);
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

    public Exception editFile(int index, boolean condition)
    {
        return editFile(index,condition+"");
    }

    public Exception editFile(int index, double money)
    {
        return editFile(index,money+"");
    }

    public Exception editFile(int index, int  age)
    {
        return editFile(index,age+"");
    }


}