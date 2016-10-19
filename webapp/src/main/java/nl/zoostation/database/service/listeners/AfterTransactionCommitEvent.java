package nl.zoostation.database.service.listeners;

/**
 * @author valentinnastasi
 */
public class AfterTransactionCommitEvent extends Event {
    public AfterTransactionCommitEvent(EventExecutor eventExecutor) {
        super(eventExecutor);
    }
}
