package controller.json;

import java.util.List;

/**
 * Created by rfischer on 13.05.16.
 */
public class OperationsJson {
    private List<OperationJson> operations;
    private boolean success = true;

    public OperationsJson(List<OperationJson> operations) {
        this.operations = operations;
    }
}
