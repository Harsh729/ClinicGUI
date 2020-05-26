package ClinicSoftware;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ClinicSoftware.Patient;

public class PatientTableWrapper
{
    private ImageView heart;
    private ImageView bp;
    private ImageView allergy;
    private ImageView diabetes;
    private String latest;

    private Patient patient;

    private String name;
    private String phone;
    private int age;
    private String desc;
    private double money;


    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getDesc() {
        return desc;
    }

    public double getMoney() {
        return money;
    }

    public PatientTableWrapper(Patient patient)
    {
        this.patient = patient;
        try {
            latest = Patient.getDateFromAppointment(patient.getLatestAppointmentFile());
        }
        catch (NullPointerException e)
        {
            System.out.println("Null Pointer exception in getting latest appointment.");
        }
        heart = setImage(patient.getHeartCondition());
        bp = setImage(patient.getBloodPressure());
        allergy = setImage(patient.getAllergy());
        diabetes = setImage(patient.getDiabetes());

        name = patient.getName();
        phone = patient.getPhone();
        age = patient.getAge();
        desc = patient.getDesc();
        money = patient.getMoney();
    }

    public ImageView setImage(boolean value)
    {
        String dir = "file:///" + System.getProperty("user.dir")+"\\Directories\\";
        //System.out.println(dir);
        if(value)
        {
            return new ImageView(new Image(dir+"right.jpg"));
        }
        else
            return new ImageView(new Image(dir+"wrong.jpg"));
    }

    public void setHeart(ImageView heart) {
        this.heart = heart;
    }

    public void setBp(ImageView bp) {
        this.bp = bp;
    }

    public void setAllergy(ImageView allergy) {
        this.allergy = allergy;
    }

    public void setDiabetes(ImageView diabetes) {
        this.diabetes = diabetes;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public ImageView getHeart() {
        return heart;
    }

    public ImageView getBp() {
        return bp;
    }

    public ImageView getAllergy() {
        return allergy;
    }

    public ImageView getDiabetes() {
        return diabetes;
    }

    public String getLatest() {
        return latest;
    }

    public String getFileName()
    {
        return name+" "+phone;
    }
}