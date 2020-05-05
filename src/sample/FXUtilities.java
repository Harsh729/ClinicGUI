package sample;

import ClinicSoftware.Schedule;
import ClinicSoftware.Slot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.Iterator;
import java.util.LinkedList;

public class FXUtilities {
    ComboBox slotChooserStart;
    ComboBox slotChooserEnd;
    Schedule schedule;
    ObservableList<String> data;
    String slotStart;
    String slotEnd;

    public FXUtilities(ComboBox slotChooserStart,ComboBox slotChooserEnd,Schedule schedule,ObservableList<String> data,String slotStart,String slotEnd)
    {
        this.slotChooserStart=slotChooserStart;
        this.slotChooserEnd=slotChooserEnd;
        this.schedule=schedule;
        this.data=data;
        this.slotStart=slotStart;
        this.slotEnd=slotEnd;
    }


    public LinkedList<String> generateAllSlots()
    {
        LinkedList<String> availableSlots=new LinkedList<>();
        double ctr=15;
        while(ctr<21)
        {
            double minutes[]={0.00,0.25,0.50,0.75};
            for(int i=0;i<3;i++)
            {
                String slot = (ctr+minutes[i])+" - "+(ctr+minutes[i+1]);
                availableSlots.add(slot);
            }
            String slot = (ctr+minutes[3])+" - "+(++ctr+minutes[0]);
            availableSlots.add(slot);
        }
        return availableSlots;
    }

    public LinkedList<String> generateOccupiedSlots()
    {
        LinkedList<Slot> slots=new LinkedList<>();
        try
        {
            slots=schedule.getSlots();
        }
        catch(NullPointerException e)
        {
            System.err.println("Null pointer exception, probably because File was not found");
        }
        LinkedList<String> occupiedSlots=new LinkedList<>();
        Iterator itr=slots.iterator();
        while(itr.hasNext())
        {
            occupiedSlots.add(((Slot)itr.next()).displaySlot());
        }
        return occupiedSlots;
    }

    public LinkedList<String> generateAvailableSlots(LinkedList<String> availableSlots,LinkedList<String> occupiedSlots)
    {
        for(int i=0;i<occupiedSlots.size();i++)
        {
            for(int j=0;j<availableSlots.size();j++)
            {
                if(occupiedSlots.get(i).equals(availableSlots.get(j)))
                {
                    availableSlots.remove(j);
                    break;
                }
            }
        }
        return availableSlots;
    }

    public ObservableList<String> getValidSlots(String slot)
    {
        ObservableList<String> dataCopy= FXCollections.observableArrayList(data);
        int n=dataCopy.size();
        for(int i=0;i<n;i++)
        {
            if(slot.equals(dataCopy.get(0)))
            {
                break;
            }
            dataCopy.remove(0);
        }
        LinkedList<String> d = new LinkedList<>(dataCopy);
        d = convertToConvention(d);

        return compressSlot(d,true);
    }

    public LinkedList<String> convertToConvention(LinkedList<String> availableSlots)
    {
        LinkedList<String> convention = new LinkedList<>();
        for(int i=0; i<availableSlots.size();i++)
        {
            convention.add(Slot.format(Slot.toSlot(availableSlots.get(i))));
        }
        return convention;
    }

    public ObservableList<String> compressSlot(LinkedList<String> availableSlots, boolean isEnd) {
        LinkedList<String> compressed = new LinkedList<>();
        for(int i=0;i<availableSlots.size();i++)
        {
            String times[] = availableSlots.get(i).split(" - ");
            if(isEnd)
            {
                compressed.add(times[1]);
            }
            else
                compressed.add(times[0]);
        }
        return FXCollections.observableList(compressed);
    }

    public void createComboBoxItems()
    {
        LinkedList<String> occupiedSlots=generateOccupiedSlots();

        LinkedList<String> availableSlots=generateAllSlots();

        availableSlots=generateAvailableSlots(availableSlots,occupiedSlots);

        data=FXCollections.observableList(availableSlots);

        availableSlots = convertToConvention(availableSlots);

        try {
            slotChooserStart.setPromptText("Start Time");
            slotChooserEnd.setPromptText("End Time");
            slotChooserStart.setItems(compressSlot(availableSlots, false));
            slotChooserEnd.setItems(compressSlot(availableSlots, true));
        }

        catch(NullPointerException e)
        {
            System.err.println("Null pointer exception, probably because File was not found");
        }
    }

    public LinkedList<String> getTimeSlot()
    {
        LinkedList<String> allSlots=generateAllSlots();
        LinkedList<String> selectedSlots=new LinkedList<>();

        int start=allSlots.indexOf(slotStart);
        int end=allSlots.indexOf(slotEnd);

        for(int i=start;i<=end;i++)
        {
            selectedSlots.add(allSlots.get(i));
        }
        return selectedSlots;
    }

    public void setSlots(String slotStart,String slotEnd)
    {
        this.slotStart=slotStart;
        this.slotEnd=slotEnd;
    }


    public String decompressSlot(String time, boolean isEnd)
    {
        String times[] = time.split(":");
        String time2 = "";
        int hour = Integer.valueOf(times[0]);
        int min = Integer.valueOf(times[1]);
        if(isEnd)
        {
            if(min==0) {
                hour--;
                min=60;
            }
            time2 = hour+":"+(min-15);
            return time2 + " - " + time;
        }
        else
        {
            if(min==45)
            {
                hour++;
            }
            time2 = hour + ":" + (min+15)%60;
            return time + " - " + time2;
        }
    }
}
