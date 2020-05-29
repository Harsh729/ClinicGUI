package ClinicSoftware;
import java.io.*;

import com.opencsv.*;
public class LabWorkFile extends ClinicFile
{
    private String labHeader[]={"Patient Name","Sent Date","Received Date","Lab Name","Description"};
    private String folderName ="Lab Work/";
    private String fileName;
    private String dir=super.getDirectory(super.userSignature);

    public LabWorkFile(String fileName)
    {

        super(-1);
        this.fileName=fileName;
    }

    public LabWorkFile(LabWork labWork)
    {
        super(-1);
        dir = super.getDirectory(userSignature);
        fileName=labWork.getFileName();
        if(!isFilePresent(dir, folderName,fileName))
            System.out.println(createFileWithMessage(labWork));
        else
            System.out.println("LF: exists");
    }

    public String getDirectory()
    {
        return dir+ folderName +fileName;
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
        if(createCount==0) {
            try {
                FileWriter fw = new FileWriter(dir + folderName + lab.getPatientName() + " " + lab.getSentDate() + ".csv");
                CSVWriter writer = new CSVWriter(fw);
                writer.writeNext(labHeader);
                String labDetails[] = {lab.getPatientName(), lab.getSentDate(), lab.getReceivedDate(), lab.getLabName(), lab.getWork()};
                writer.writeNext(labDetails);
                writer.close();
                fw.close();
            } catch (Exception e) {
                return e;
            }
            createCount++;
        }
        else
            System.out.println("Already created: LF");
        return null;
    }

    public LabWork readFile()
    {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
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
        File file =new File(dir+ folderName +fileName+".csv");
        createCount = 0;
        System.gc();
        return file.delete();
    }

    public Exception editFile(int index, String entry)
    {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader=new CSVReader(fr);
            FileWriter fw=new FileWriter(dir+ folderName +"temp.csv");
            CSVWriter writer=new CSVWriter(fw);
            writer.writeNext(reader.readNext());
            String temp[]=reader.readNext();
            temp[index]=entry;
            writer.writeNext(temp);
            File file=new File(dir+ folderName +fileName+".csv");
            file.delete();
            File newFile=new File(dir+ folderName +"temp.csv");
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
        createCount = 0;
        return null;
    }

    public int getIndex(String title)throws IOException
    {
        FileReader fr=new FileReader(dir+ folderName +fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        String temp[]=reader.readNext();
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i].equals(title))
                return i;
        }
        return -999;
    }

    public String getFolderName()
    {
        return this.folderName;
    }
}