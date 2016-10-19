package nl.zoostation.database.service.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author valentinnastasi
 */
public class TransactionEventListeners {

    private static final Logger logger = LogManager.getLogger(TransactionEventListeners.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAfterCommitEvent(AfterTransactionCommitEvent event) {
        logger.debug("Now handling AfterTransactionCommitEvent");
        event.getEventExecutor().execute();
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAfterCommitAsyncEvent(AfterTransactionCommitAsyncEvent event) {
        logger.debug("Now handling AfterTransactionCommitAsyncEvent");
        event.getEventExecutor().execute();
    }

}
