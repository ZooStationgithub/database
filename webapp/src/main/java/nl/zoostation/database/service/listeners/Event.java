package nl.zoostation.database.service.listeners;

/**
 * @author valentinnastasi
 */
public abstract class Event {

    private final EventExecutor eventExecutor;

    protected Event(EventExecutor eventExecutor) {
        this.eventExecutor = eventExecutor;
    }

    public EventExecutor getEventExecutor() {
        return eventExecutor;
    }
}
