package nl.zoostation.database.service.listeners;

/**
 * @author valentinnastasi
 */
public class AfterCommitEvent extends Event {

    public AfterCommitEvent(Task task) {
        super(task);
    }
}
