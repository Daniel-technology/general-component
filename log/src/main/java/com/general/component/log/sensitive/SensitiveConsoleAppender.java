//package com.general.component.log.sensitive;
//
//import ch.qos.logback.classic.spi.LoggingEvent;
//import ch.qos.logback.core.ConsoleAppender;
//
///**
// * @description: 日志脱敏
// * @author: liuyan
// * @create: 2023−02-03 4:35 PM
// */
//public class SensitiveConsoleAppender extends ConsoleAppender {
//
//    @Override
//    protected void subAppend(Object event) {
//        DesensitizationAppender appender = new DesensitizationAppender();
//        appender.operation((LoggingEvent)event);
//        super.subAppend(event);
//    }
//}
