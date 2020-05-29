package ClinicSoftware;
import java.io.*;
import com.opencsv.*;
public class PrescriptionFile extends ClinicFile
{
    private String prescriptionHeader[]={"Medicine Name","Instruction"};

    @Override
    public String getFolderName() {
        return folderName;
    }

    private String folderName ="Prescriptions/";
    private String fileName;
    private String dir=super.getDirectory(-1);

    public PrescriptionFile(String fileName)
    {

        super(-1);
        this.fileName=fileName;
    }

    public PrescriptionFile(Prescription prescription)
    {
        super(-1);
        fileName=prescription.getFileName();
        if(!isFilePresent(dir, folderName,fileName))
        {
            System.out.println(createFileWithMessage(prescription));
        }
    }

    public String getDirectory()
    {
        return dir+ folderName +fileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String createFileWithMessage(Prescription prescription)
    {
        Exception e;
        if((e=createFile(prescription))==null)
            return "File Created Successfully!";
        else
           return e.getMessage();
    }

    public Exception createFile(Prescription prescription)
    {
        if(createCount==0) {
            try {
                FileWriter fw = new FileWriter(dir + folderName + prescription.getPatientName() + " " + prescription.getDate() + ".csv");
                CSVWriter writer = new CSVWriter(fw);
                String preHeader[] = {prescription.getPatientName(), prescription.getDate()};
                writer.writeNext(preHeader);
                writer.writeNext(prescriptionHeader);
                for (int i = 0; i < prescription.getMedicines().size(); i++) {

                    String temp[] = {prescription.getMedicines().get(i), prescription.getInstructions().get(i)};
                    writer.writeNext(temp);
                }
                writer.close();
                fw.close();
            } catch (Exception e) {
                return e;
            }
            createCount++;
        }
        else
            System.out.println("Already created: PrF");
        return null;
    }

    public Prescription readFile()throws IOException
    {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            String details[]=reader.readNext();
            reader.readNext();
            Prescription pre = new Prescription();
            pre.setPatientName(details[0]);
            pre.setDate(details[1]);
            String temp[];
            while ((temp = reader.readNext()) != null) {
                pre.addMedicine(temp[0]);
                pre.addInstruction(temp[1]);
            }
            return pre;
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

    public boolean deleteFile() {
        File file = new File(dir + folderName + fileName + ".csv");
        createCount = 0;
        System.gc();
        return file.delete();
    }

    public Exception deleteEntry(int index)throws IOException
    {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            FileWriter fw = new FileWriter(dir + folderName + "temp.csv");
            CSVWriter writer = new CSVWriter(fw);
            int ctr = -1;
            String temp2[];
            while ((temp2 = reader.readNext()) != null) {
                if (index != ctr) {
                    writer.writeNext(temp2);
                }
                ctr++;
            }
            boolean flag = true;
            reader.close();
            fr.close();
            writer.close();
            fw.close();
            File file = new File(dir + folderName + fileName + ".csv");
            File newFile = new File(dir + folderName + "temp.csv");
            deleteFile();
            newFile.renameTo(file);
        }
        catch(Exception e) {
            return e;
        }
        createCount = 0;
        return null;
    }


    public Exception addEntry(String medicine,String instruction,int predecessorIndex)throws IOException
    {
        boolean flag =true;
        try
        {
            FileWriter fw = new FileWriter(dir+ folderName +"temp.csv");
            CSVWriter writer = new CSVWriter(fw);
            FileReader fr=new FileReader(dir+ folderName +fileName+".csv");
            CSVReader reader=new CSVReader(fr);
            String temp[];
            int ctr=0;
            writer.writeNext(reader.readNext());
            writer.writeNext(reader.readNext());
            String medicinesArr[] = {medicine, instruction};
            while(true)
            {
                temp=reader.readNext();
                if(ctr==predecessorIndex) {
                    writer.writeNext(medicinesArr);
                    ctr++;
                    writer.writeNext(temp);
                }
                else
                {
                    writer.writeNext(temp);
                    ctr++;
                }
                if(temp==null) {
                    if(ctr<=predecessorIndex)
                        writer.writeNext(medicinesArr);
                    break;
                }
            }
            writer.close();
            fw.close();
            reader.close();
            fr.close();
            File file=new File(dir+ folderName +fileName+".csv");
            if(!file.delete())
                flag=false;
            File temp2=new File(dir+ folderName +"temp.csv");
            temp2.renameTo(file);
        }
        catch(Exception e)
        {
           return e;
        }
        createCount = 0;
        return null;
    }

    public Exception editEntry(String fileName,String medDel,String medEnter,String instructionEnter)throws IOException
    {
        Exception exception;
        int index=getIndex(fileName,medDel);
        if(!((exception=deleteEntry(index))==null))
            return exception;
        if(!((exception=addEntry(medEnter,instructionEnter,index))==null))
            return exception;
        createCount = 0;
        return null;
    }

    public int getIndex(String fileName,String medicine)throws IOException
    {
        int ctr=1;
        FileReader fr=new FileReader(dir+ folderName +fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        String temp[];
        reader.readNext();
        while((temp=reader.readNext())!=null)
        {
            if(temp[0].equals(medicine)) {
                reader.close();
                fr.close();
                return ctr;
            }
            else
                ctr++;
        }
        reader.close();
        fr.close();
        return ctr;
    }

    public int getLastIndex(String fileName)throws IOException
    {
        FileReader fr=new FileReader(dir+ folderName +fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        int ctr=0;
        reader.readNext();
        while(reader.readNext()!=null)
        {
            ctr++;
        }
        return ctr;
    }
}