package domain;

/**
 * Created by rfischer on 11.05.16.
 */
public enum OperationPriority {
    Low(0), Medium(1), High(2);

    private int num;

    OperationPriority(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
