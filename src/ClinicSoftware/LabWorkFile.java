package ClinicSoftware;
import java.io.*;
import java.util.*;
import com.opencsv.*;
public class LabWorkFile extends ClinicFile
{
    private String labHeader[]={"Patient Name","Sent Date","Received Date","Lab Name","Description"};
    private String labFolder="Lab Work/";
    private String fileName;
    private String dir=super.getDirectory();

    public LabWorkFile(String fileName)
    {
        this.fileName=fileName;
    }

    public LabWorkFile(LabWork labWork)
    {
        fileName=labWork.getFileName();
        if(!isFilePresent(dir,labFolder,fileName))
            System.out.println(createFileWithMessage(labWork));
    }

    public String getDirectory()
    {
        return dir+labFolder+fileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String createFileWithMessage(LabWork labWork)
    {
        Exception e;
        if((e=createLabFile(labWork))==null)
            return "File Created Successfully!";
        else
           return e.getMessage();
    }

    public Exception createLabFile(LabWork lab)
    {
        try {
            FileWriter fw = new FileWriter(dir + labFolder + lab.getPatientName() + " " + lab.getSentDate() + ".csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(labHeader);
            String labDetails[] = {lab.getPatientName(), lab.getSentDate(), lab.getReceivedDate(), lab.getLabName(), lab.getWork()};
            writer.writeNext(labDetails);
            writer.close();
            fw.close();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    public LabWork readFile()
    {
        try {
            FileReader fr = new FileReader(dir + labFolder + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            String LabWork[];
            reader.readNext();

            LabWork = reader.readNext();

            LabWork lab = new LabWork();
            lab.setPatientName(LabWork[0]);
            lab.setSentDate(LabWork[1]);
            lab.setReceivedDate(LabWork[2]);
            lab.setLabName(LabWork[3]);
            lab.setWork(LabWork[4]);
            return lab;
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

    public boolean deleteFile()
    {
        File file =new File(dir+labFolder+fileName+".csv");
        return file.delete();
    }

    public Exception editFile(int index, String entry)
    {
        try {
            FileReader fr = new FileReader(dir + labFolder + fileName + ".csv");
            CSVReader reader=new CSVReader(fr);
            FileWriter fw=new FileWriter(dir+labFolder+"temp.csv");
            CSVWriter writer=new CSVWriter(fw);
            writer.writeNext(reader.readNext());
            String temp[]=reader.readNext();
            temp[index]=entry;
            writer.writeNext(temp);
            File file=new File(dir+labFolder+fileName+".csv");
            file.delete();
            File newFile=new File(dir+labFolder+"temp.csv");
            newFile.renameTo(file);
            reader.close();
            fr.close();
            writer.close();
            fw.close();
        }
        catch (Exception e)
        {
            return e;
        }
        return null;
    }

    public int getIndex(String title)throws IOException
    {
        FileReader fr=new FileReader(dir+labFolder+fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        String temp[]=reader.readNext();
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i].equals(title))
                return i;
        }
        return -999;
    }
}