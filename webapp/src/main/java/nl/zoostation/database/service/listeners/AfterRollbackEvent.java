package nl.zoostation.database.service.listeners;

/**
 * @author valentinnastasi
 */
public class AfterRollbackEvent extends Event {

    public AfterRollbackEvent(Task task) {
        super(task);
    }
}
