package ClinicSoftware;

import java.time.Month;
import java.util.Date;

public class MyDate {

    private String date[]=new String[3];

    public MyDate()
    {
        Date date=new Date();
        this.date=getDate(date);
    }

    public MyDate(String date)
    {
        String[] temp=date.split("-");
        this.date[0]=temp[2];
        this.date[1]=temp[1];
        this.date[2]=temp[0];
    }

    String[] getDate(Date date)
    {
        String arr[]=date.toString().split(" ");
        int day=Integer.valueOf(arr[2]);
        int month=getMonthNumber(arr[1]);
        arr[1]=Integer.toString(month);
        int year=Integer.valueOf(arr[5]);
        String myDate[]={""+day,""+month,""+year};

        if(noOfDigits(day)==1)
            myDate[0]="0"+myDate[0];
        if(noOfDigits(month)==1)
            myDate[1]="0"+myDate[1];
        if(noOfDigits(year)==1)
            myDate[2]="0"+myDate[2];

        return myDate;
    }


    public String toString()
    {
        return date[0]+"-"+date[1]+"-"+date[2];
    }

    int noOfDigits(int n)
    {
        int ctr=0;
        while(n>0)
        {
            n=n/10;
            ctr++;
        }
        return ctr;
    }

    public int getMonthNumber(String month)
    {
        String months[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for(int i=0;i<months.length;i++)
        {
            if(month.equalsIgnoreCase(months[i]))
            {
                return i+1;
            }
        }
        return -999;
    }
}
