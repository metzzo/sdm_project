package domain;

import javax.persistence.*;

/**
 * Created by rfischer on 11.05.16.
 */

@Entity
@Table(name="sdm_operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OperationType type;
    private OperationPriority priority;
    private Address address;
    private String who;
    private String what;
    private String additionalInfo;

    @ManyToOne
    private Dispatcher initializer;

    public Operation() {
    }

    public Operation(OperationType type, OperationPriority priority, Address address, String who, String what, String additionalInfo, Dispatcher initializer) {
        this.type = type;
        this.priority = priority;
        this.address = address;
        this.who = who;
        this.what = what;
        this.additionalInfo = additionalInfo;
        this.initializer = initializer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public OperationPriority getPriority() {
        return priority;
    }

    public void setPriority(OperationPriority priority) {
        this.priority = priority;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Dispatcher getInitializer() {
        return initializer;
    }

    public void setInitializer(Dispatcher initializer) {
        this.initializer = initializer;
    }
}
