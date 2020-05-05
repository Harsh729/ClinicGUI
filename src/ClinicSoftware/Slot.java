package ClinicSoftware;

public class Slot
{
    double time_period;
    double start_time;

    public Slot(double time_period,double start_time)
    {
        this.time_period=time_period;
        this.start_time=start_time;
    }

    public Slot(String slot)
    {
        this.time_period=toSlot(slot).getTimePeriod();
        this.start_time=toSlot(slot).getStartTime();
    }

    public Slot(double start_time)
    {
        this(0.25,start_time);
    }

    public Slot()
    {
        this(0.25,0);
    }

    public double getTimePeriod()
    {
        return time_period;
    }

    public double getStartTime()
    {
        return start_time;
    }

    public double getEndTime()
    {
        return start_time+time_period;
    }

    public void setTimePeriod(double time_period)
    {
        this.time_period=time_period;
    }

    public void setStartTime(double start_time)
    {
        this.start_time=start_time;
    }

    public String displaySlot()
    {
        String s=""+start_time+" - "+(start_time+time_period);
        return s;
    }

    public boolean isGreater(Slot s)
    {
        if(start_time>s.getStartTime())
            return true;
        else
            return false;
    }

    public static Slot toSlot(String s)
    {
        String str[]=s.split("-");
        Slot slot=new Slot(Double.valueOf(str[1])-Double.valueOf(str[0]),Double.valueOf(str[0]));
        return slot;
    }

    public static String format(Slot slot)
    {
        String format = "";
        double start = slot.getStartTime();
        format += Slot.toConvention(start);
        format += " - ";
        double end = slot.getEndTime();
        format += Slot.toConvention(end);
        return format;
    }

    public static String toConvention(double time)
    {
        String slot = (int)time + ":" + (int)((time-(int)time)*60);
        if(slot.length()==4)
            slot+="0";
        return slot;
    }

    public static double fromConvention(String time)
    {
        String times[] = time.split(":");
        return Double.valueOf(times[0])+Double.valueOf(times[1])/60;
    }

    public static String toDouble(String slot)
    {
        String times[] = slot.split(" - ");
        double start = Slot.fromConvention(times[0]);
        double end = Slot.fromConvention(times[1]);
        return start + " - " + end;
    }
}