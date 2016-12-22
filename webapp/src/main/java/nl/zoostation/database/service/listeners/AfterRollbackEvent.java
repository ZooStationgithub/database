package nl.zoostation.database.service.listeners;

import nl.zoostation.database.annotations.NotNull;

/**
 * @author valentinnastasi
 */
public class AfterRollbackEvent extends Event {

    public AfterRollbackEvent(@NotNull Task task) {
        super(task);
    }
}
