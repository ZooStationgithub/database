package nl.zoostation.database.service.listeners;

/**
 * @author valentinnastasi
 */
@FunctionalInterface
public interface Task {

    void execute() throws Exception;

}
