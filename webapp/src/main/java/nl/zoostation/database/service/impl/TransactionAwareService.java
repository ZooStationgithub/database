package nl.zoostation.database.service.impl;

import nl.zoostation.database.service.listeners.AfterCommitEvent;
import nl.zoostation.database.service.listeners.AfterRollbackEvent;
import nl.zoostation.database.service.listeners.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author valentinnastasi
 */
class TransactionAwareService implements ApplicationEventPublisherAware {

    private static final Logger logger = LogManager.getLogger(TransactionAwareService.class);

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    protected void doAfterCommit(Task task) {
        logger.debug("Publishing a new after commit event");
        applicationEventPublisher.publishEvent(new AfterCommitEvent(task));
    }

    protected void doAfterRollback(Task task) {
        logger.debug("Publishing a new after rollback event");
        applicationEventPublisher.publishEvent(new AfterRollbackEvent(task));
    }

}
