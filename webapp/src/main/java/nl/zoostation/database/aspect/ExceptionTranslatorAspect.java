package nl.zoostation.database.aspect;

import nl.zoostation.database.exception.DatabaseException;
import nl.zoostation.database.exception.ErrorMessage;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;

/**
 * @author valentinnastasi
 */
@Aspect
public class ExceptionTranslatorAspect {

    @Pointcut("within(nl.zoostation.database.dao..*)")
    public void daoPublicMethod() {
    }

    @Around("execution(public * nl.zoostation.database.service..* (..))")
    public Object translateToDatabaseException(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o;
        try {
            o = joinPoint.proceed();
        } catch (Throwable e) {
            if (e.getCause() != null && e.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException hibernateException = (ConstraintViolationException) e.getCause();
                SQLException sqlException = hibernateException.getSQLException();
                if (StringUtils.containsIgnoreCase(sqlException.getMessage(), "foreign key")) {
                    throw new DatabaseException(ErrorMessage.FOREIGN_KEY_VIOLATION, e);
                }
            }
            throw new DatabaseException(e);
        }
        return o;
    }


}
