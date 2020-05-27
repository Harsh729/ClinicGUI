package ClinicSoftware;

public class Medicine {

    String medicine;
    String instruction;

    public Medicine(String medicine, String instruction)
    {
        this.medicine = medicine;
        this.instruction = instruction;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getInstruction() {
        return instruction;
    }
}
