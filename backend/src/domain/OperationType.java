package domain;

/**
 * Created by rfischer on 11.05.16.
 */
public enum OperationType {
    FireEmergency(0), MedicalEmergency(1), PoliceEmergency(2);

    private int num;

    OperationType(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
