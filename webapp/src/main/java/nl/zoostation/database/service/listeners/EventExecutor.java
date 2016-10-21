package nl.zoostation.database.service.listeners;

/**
 * @author valentinnastasi
 */
@FunctionalInterface
public interface EventExecutor {

    void execute() throws Exception;

}
