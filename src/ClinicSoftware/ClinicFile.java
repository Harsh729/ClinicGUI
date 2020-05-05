package ClinicSoftware;

import java.io.*;

public class ClinicFile {
    //TODO: to make a separate directory for asst.
    String dir = getDirectory();


    String getDirectory()
    {
        String tempdir=System.getProperty("user.dir");
        tempdir=tempdir+"\\Directories\\";
        return tempdir;
    }

    boolean isFilePresent(String dir,String folderName,String fileName)
    {
        System.out.println(this.dir);
        try
        {
            FileReader fr=new FileReader(dir+folderName+fileName);
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
}
