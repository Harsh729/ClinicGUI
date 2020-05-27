package ClinicSoftware;

import java.io.IOException;

public class SingleScheduleEntry {
    private String time;
    private String patientName;
    private Slot slot;
    private Appointment appointment;
    private String description;

    public SingleScheduleEntry(Appointment appointment)
    {
        try {
            this.slot = appointment.getTime();
            this.appointment = appointment;
            appointment.setTime(slot);
            time = slot.newFormat();
            patientName = appointment.getRecord().getName();
            description = appointment.getProcedure();
        }
        catch(NullPointerException e)
        {
            System.out.println("Null Pointer Exception at SSE constructor");
        }
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getTime()
    {
        return time;
    }

    public String getPatientName() {
        return patientName;
    }

    public Slot getSlot() {
        return slot;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateAppointment()
    {
        String file = this.appointment.getFileName();
        try{
            AppointmentFile af = new AppointmentFile(file,this.appointment.getUserSignature());
            this.setAppointment(af.readFile());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}