package nl.zoostation.database.exception;

/**
 * @author valentinnastasi
 */
public class ObjectNotFoundException extends ZooStationException {

    public ObjectNotFoundException(ObjectDescriptor objectDescriptor) {
        super(ErrorMessage.OBJECT_NOT_FOUND, objectDescriptor);
    }

}
