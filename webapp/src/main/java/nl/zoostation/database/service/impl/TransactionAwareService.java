package nl.zoostation.database.service.impl;

import nl.zoostation.database.service.listeners.AfterCommitEvent;
import nl.zoostation.database.service.listeners.AfterRollbackEvent;
import nl.zoostation.database.service.listeners.Task;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author valentinnastasi
 */
class TransactionAwareService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    protected void doAfterCommit(Task task) {
        applicationEventPublisher.publishEvent(new AfterCommitEvent(task));
    }

    protected void doAfterRollback(Task task) {
        applicationEventPublisher.publishEvent(new AfterRollbackEvent(task));
    }

}
