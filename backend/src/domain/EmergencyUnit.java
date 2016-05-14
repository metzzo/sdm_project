package domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rfischer on 13.05.16.
 */
@Entity
@Table(name = "sdm_emergenyunit")
public class EmergencyUnit extends User {
    private OperationType unitType;

    public EmergencyUnit(String uname, String pw, String email, OperationType unitType, Long id) {
        super(uname, pw, email, id);

        this.unitType = unitType;
    }

    public EmergencyUnit() {
    }

    public OperationType getUnitType() {
        return unitType;
    }

    public void setUnitType(OperationType unitType) {
        this.unitType = unitType;
    }
}
