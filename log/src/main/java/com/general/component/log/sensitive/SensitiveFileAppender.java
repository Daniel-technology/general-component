//package com.general.component.log.sensitive;
//
//import ch.qos.logback.core.FileAppender;
//import ch.qos.logback.classic.spi.LoggingEvent;
//
//
///**
// * @description: 日志脱敏
// * @author: liuyan
// * @create: 2023−02-03 4:37 PM
// */
//public class SensitiveFileAppender extends FileAppender {
//
//    @Override
//    protected void subAppend(Object event) {
//        DesensitizationAppender appender = new DesensitizationAppender();
//        appender.operation((LoggingEvent) event);
//        super.subAppend(event);
//    }
//}
