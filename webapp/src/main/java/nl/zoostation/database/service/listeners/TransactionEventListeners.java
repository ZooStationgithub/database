package nl.zoostation.database.service.listeners;

import nl.zoostation.database.annotations.validation.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author valentinnastasi
 */
public class TransactionEventListeners {

    private static final Logger logger = LogManager.getLogger(TransactionEventListeners.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAfterCommitEvent(@NotNull AfterCommitEvent event) throws Exception {
        logger.trace("Now handling AfterCommitEvent");
        event.getTask().execute();
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleAfterRollbackEvent(@NotNull AfterRollbackEvent event) throws Exception {
        logger.trace("Now handling AfterRollbackEvent");
        event.getTask().execute();
    }

}
