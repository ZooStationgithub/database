package nl.zoostation.database.aspect;

import nl.zoostation.database.annotations.NotEmpty;
import nl.zoostation.database.annotations.NotNull;
import nl.zoostation.database.exception.ErrorMessage;
import nl.zoostation.database.exception.InvalidParameterException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author valentinnastasi
 */
@Aspect
public class MethodParameterValidationAspect {

    @Pointcut("execution(* nl.zoostation.database.service..*(.., @nl.zoostation.database.annotations.NotNull (*),..))")
    public void notNullMethodArgumentInService() {
    }

    @Pointcut("execution(* nl.zoostation.database.dao..*(.., @nl.zoostation.database.annotations.NotNull (*),..))")
    public void notNullMethodArgumentInDAO() {
    }

    @Pointcut("execution(* nl.zoostation.database.service..*(.., @nl.zoostation.database.annotations.NotEmpty (*),..))")
    public void notEmptyMethodArgumentInService() {
    }

    @Pointcut("execution(* nl.zoostation.database.dao..*(.., @nl.zoostation.database.annotations.NotEmpty (*),..))")
    public void notEmptyMethodArgumentInDAO() {
    }

    @Before("notNullMethodArgumentInService() || notNullMethodArgumentInDAO()")
    public void checkNullParameter(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
        MethodArgument.of(joinPoint).forEach(a -> {
            if (a.hasAnnotation(NotNull.class) && Objects.isNull(a.getValue())) {
                throw new InvalidParameterException(ErrorMessage.NULL_PARAMETER, targetClassName, a.getName());
            }
        });
    }

    @Before("notEmptyMethodArgumentInService() || notEmptyMethodArgumentInDAO()")
    public void checkEmptyParameter(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
        MethodArgument.of(joinPoint).forEach(argument -> {
            if (!argument.hasAnnotation(NotEmpty.class)) {
                return;
            }
            Object value = argument.getValue();
            if (value instanceof CharSequence && StringUtils.isEmpty((CharSequence) value)) {
                throw new InvalidParameterException(ErrorMessage.EMPTY_STRING_PARAMETER, targetClassName, argument.getName());
            }
            if (value instanceof Collection && CollectionUtils.isEmpty((Collection) value)) {
                throw new InvalidParameterException(ErrorMessage.EMPTY_COLLECTION_PARAMETER, targetClassName, argument.getName());
            }
            if (value instanceof Map && MapUtils.isEmpty((Map) value)) {
                throw new InvalidParameterException(ErrorMessage.EMPTY_MAP_PARAMETER, targetClassName, argument.getName());
            }
        });
    }

    private static class MethodArgument {

        private final int index;
        private final String name;
        private final List<Annotation> annotations;
        private final Object value;

        private MethodArgument(int index, String name, List<Annotation> annotations, Object value) {
            this.index = index;
            this.name = name;
            this.annotations = Collections.unmodifiableList(annotations);
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public List<Annotation> getAnnotations() {
            return annotations;
        }

        public boolean hasAnnotation(Class<? extends Annotation> type) {
            for (Annotation annotation : annotations)
                if (annotation.annotationType().equals(type)) {
                    return true;
                }
            return false;
        }

        public Object getValue() {
            return value;
        }

        public static List<MethodArgument> of(JoinPoint joinPoint) {
            List<MethodArgument> arguments = new ArrayList<>();
            CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
            String[] names = codeSignature.getParameterNames();
            MethodSignature methodSignature =
                    (MethodSignature) joinPoint.getStaticPart().getSignature();
            Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
            Object[] values = joinPoint.getArgs();
            for (int i = 0; i < values.length; i++)
                arguments.add(new MethodArgument(i, names[i], Arrays.asList(annotations[i]), values[i]));
            return Collections.unmodifiableList(arguments);
        }

    }


}
