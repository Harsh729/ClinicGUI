package ClinicSoftware;

import java.io.*;

public class ClinicFile {
    String dir = "";
    int userSignature = 0;
    int createCount = 0;
    ClinicFile(int userSignature)
    {
        this.userSignature = userSignature;
        dir = getDirectory(userSignature);
    }


    String getDirectory(int userSignature)
    {
        String tempdir=System.getProperty("user.dir");
        tempdir=tempdir+"\\Directories\\";
        if(userSignature==0)
            tempdir += "Owner\\";
        else if(userSignature==1)
            tempdir += "Asst\\";
        return tempdir;
    }

    boolean isFilePresent(String dir,String folderName,String fileName)
    {
        //System.out.println(this.dir);
        try
        {
            FileReader fr=new FileReader(dir+folderName+fileName+".csv");
        }
        catch(FileNotFoundException e)
        {
                return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    ClinicFile file;

    public String getFileName()
    {
        return "file";
    }

    public String getFolderName()
    {
        return "folder";
    }

    public boolean deleteFile()
    {
        if(file!=null)
        {
            return file.deleteFile();
        }
        return false;
    }
}
