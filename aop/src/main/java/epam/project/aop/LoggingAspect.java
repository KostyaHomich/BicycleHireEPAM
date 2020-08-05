package epam.project.aop;

import epam.project.command.CommandException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* epam.project.controller..*(..)) " +
            "&&" +
            "execution(* epam.project.util..*(..))",
            throwing = "e"
    )
    public void log(CommandException e) {
        LOGGER.error(e);
    }
}
