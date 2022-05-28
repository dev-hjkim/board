package com.example.common.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    @Bean
    public Logger logger() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern(
                "%d{yyyy-MM-dd HH:mm:ss.SSS}  %highlight(%-5level) %magenta(%-4relative) --- [%thread{15}] %cyan(%logger{20}) : %msg%n"
        );
        ple.setContext(lc);
        ple.start();

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setEncoder(ple);
        consoleAppender.setContext(lc);
        consoleAppender.setName("console");
        consoleAppender.start();

        RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
        fileAppender.setFile("logs/logfile.log");
        fileAppender.setAppend(true);
        fileAppender.setEncoder(ple);
        fileAppender.setContext(lc);
        fileAppender.setName("file");

        TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<>();
        rollingPolicy.setContext(lc);
        rollingPolicy.setParent(fileAppender);
        rollingPolicy.setFileNamePattern("logs/logfile-%d{yyyy-MM-dd}.log");
        rollingPolicy.setMaxHistory(30);
        rollingPolicy.start();

        fileAppender.setRollingPolicy(rollingPolicy);
        fileAppender.start();



        Logger logger = lc.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.INFO);
        logger.addAppender(consoleAppender);
        logger.addAppender(fileAppender);

        return logger;
    }

}
