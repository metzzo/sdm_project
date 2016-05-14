package controller.json;

import com.google.gson.JsonElement;

import java.util.List;

/**
 * Created by rfischer on 14.05.16.
 */
public class LogsJson {
    private boolean success = true;
    private List<LogJson> logs;
    public LogsJson(List<LogJson> jsonLogs) {
        this.logs = jsonLogs;
    }
}
