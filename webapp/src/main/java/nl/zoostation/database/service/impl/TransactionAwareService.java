package nl.zoostation.database.service.impl;

import nl.zoostation.database.service.listeners.AfterTransactionCommitAsyncEvent;
import nl.zoostation.database.service.listeners.AfterTransactionCommitEvent;
import nl.zoostation.database.service.listeners.EventExecutor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author valentinnastasi
 */
public class TransactionAwareService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    protected void doAfterCommit(EventExecutor eventExecutor) {
        applicationEventPublisher.publishEvent(new AfterTransactionCommitEvent(eventExecutor));
    }

    protected void doAfterCommitAsync(EventExecutor eventExecutor) {
        applicationEventPublisher.publishEvent(new AfterTransactionCommitAsyncEvent(eventExecutor));
    }

}
