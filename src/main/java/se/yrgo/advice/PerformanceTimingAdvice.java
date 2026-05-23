package se.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceTimingAdvice {

    /**
     * Around advice som mäter exekveringstid för alla metoder i:
     * - se.yrgo.services.customers (och subpaket)
     * - se.yrgo.services.diary     (och subpaket)
     * - se.yrgo.services.calls     (och subpaket)
     * - se.yrgo.dataaccess         (och subpaket)
     *
     * OBS: services-paketet har tre delpaket, därför används || istället för
     * ett enda within(se.yrgo.services..*) som i BookStore-projektet.
     */
    @Around("within(se.yrgo.services.customers..*) || " +
            "within(se.yrgo.services.diary..*) || " +
            "within(se.yrgo.services.calls..*) || " +
            "within(se.yrgo.dataaccess..*)")

    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.nanoTime();

        Object result = joinPoint.proceed();

        long endTime = System.nanoTime();
        double milliseconds = (endTime - startTime) / 1_000_000.0;

        String methodName = joinPoint.getSignature().getName();
        String className  = joinPoint.getTarget().getClass().getName();

        System.out.printf("Time taken for the method %s from the class %s took %.4fms%n",
                methodName, className, milliseconds);

        return result;
    }
}