package nl.zoostation.database.service.listeners;

/**
 * @author valentinnastasi
 */
public abstract class Event {

    private final Task task;

    public Event(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
