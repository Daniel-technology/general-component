//package com.general.component.common.resource;
//
//
//import com.general.component.common.resource.model.ResourceInfo;
//
//import java.io.InputStream;
//import java.util.List;
//
///**
// * @description: 资源解析
// * @author: liuyan
// * @create: 2022−10-26 4:19 PM
// */
//public interface ResourceResolver {
//
//    /**
//     * 日志解析
//     *
//     * @param path  路径
//     * @param clazz 类型
//     * @param <T>   类型
//     * @return T
//     * @throws Exception e
//     */
//    <T extends ResourceInfo> List<T> load(String path, Class<T> clazz) throws Exception;
//
//    /**
//     * 日志解析
//     *
//     * @param inputStream 流
//     * @param clazz       类型
//     * @param <T>         类型
//     * @return T
//     */
//    <T extends ResourceInfo> List<T> load(InputStream inputStream, Class<T> clazz);
//}
