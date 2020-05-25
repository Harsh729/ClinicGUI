package ClinicSoftware;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
import java.util.*;

public class PatientFile extends ClinicFile {
    private String folderName = "Records/";
    private String[] header = {"Name", "Phone No.", "Age", "First Date", "Latest Date", "Description", "Money", "Heart Condition", "Allergy", "Diabetes", "Blood Pressure", "Amount Paid", "App Ctr"};
    private String fileName = "";

    public PatientFile(Patient r) {
        super(-1);
        Exception e = null;
        fileName = r.getFileName();
        if (!isFilePresent(dir, folderName, fileName))
            if ((e = createFile(r)) == null)
                System.out.println("File created successfully");
            else
                e.printStackTrace();
        else
            System.out.println("PF: Exists");
    }

    public PatientFile(String fileName) {
        super(-1);
        this.fileName = fileName;
    }

    public Exception createFile(Patient patient) {
        if(createCount==0) {
            try {
                FileWriter fw = new FileWriter(dir + folderName + patient.getFileName() + ".csv");
                CSVWriter writer = new CSVWriter(fw);
                writer.writeNext(header);
                String[] recordDetails = {patient.getName(), "" + patient.getPhone(), "" + patient.getAge(), patient.getFirstAppointmentFile(), patient.getLatestAppointmentFile(), patient.getDesc(), patient.getMoney() + "", patient.getHeartCondition() + "", patient.getAllergy() + "", patient.getDiabetes() + "", patient.getBloodPressure() + "", patient.getPaid() + "", patient.getAppointmentCounter() + ""};
                writer.writeNext(recordDetails);
                String apps[] = toStringArray(patient.getApps());
                writer.writeNext(apps);
                writer.close();
                fw.close();
            } catch (Exception e) {
                return e;
            }
            createCount++;
        }
        else
            System.out.println("Already created: PF");
        return null;
    }

    public Patient readFile() throws IOException {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            reader.readNext();
            String arr[] = reader.readNext();
            Patient r = new Patient();
            if (arr != null) {
                r = new Patient(arr[0], arr[1]);
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
                if (arr.length > 12)
                    r.setAppointmentCounter(Integer.valueOf(arr[12]));

                String apps[] = reader.readNext();
                if (apps != null) {
                    Stack app = toStack(apps);
                    r.setApps(app);
                }
            }
            return r;
        } catch (FileNotFoundException e) {
            System.err.println("Exception occurred: File not found");
            //e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("An unknown exception occurred:");
            e.printStackTrace();
            return null;
        }
    }

    public Stack toStack(String[] data) {
        Stack s = new Stack<String>();
        for (String str : data) {
            if(!str.equals(""))
            s.push(str);
        }
        return s;
    }

    public String[] toStringArray(Stack st)
    {
        Stack s = (Stack)st.clone();
        String str[] = new String[s.size()];
        for(int i=s.size()-1;i>=0;i--)
        {
            if(!s.empty())
                str[i] = s.pop().toString();
            else
                break;
        }
        return str;
    }

    public boolean deleteFile()
    {
        File file = new File(dir+folderName + fileName + ".csv");
        createCount = 0;
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
        createCount = 0;
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