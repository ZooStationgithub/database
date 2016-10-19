package nl.zoostation.database.service.listeners;

/**
 * @author valentinnastasi
 */
public class AfterTransactionCommitAsyncEvent extends Event {
    public AfterTransactionCommitAsyncEvent(EventExecutor eventExecutor) {
        super(eventExecutor);
    }
}
