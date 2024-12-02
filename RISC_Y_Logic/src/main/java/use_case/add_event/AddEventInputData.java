package use_case.add_event;

import java.util.Set;

public class AddEventInputData {
    private final String name;
    private final String date;
    private final String time;
    private final String description;
    private final Set<String> tags;
    private final String source;

    public AddEventInputData(String name, String date, String time, String description, Set<String> tags, String source) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.tags = tags;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getSource() {
        return source;
    }
}
