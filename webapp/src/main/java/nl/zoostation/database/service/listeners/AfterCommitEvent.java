package nl.zoostation.database.service.listeners;

import nl.zoostation.database.annotations.NotNull;

/**
 * @author valentinnastasi
 */
public class AfterCommitEvent extends Event {

    public AfterCommitEvent(@NotNull Task task) {
        super(task);
    }
}
