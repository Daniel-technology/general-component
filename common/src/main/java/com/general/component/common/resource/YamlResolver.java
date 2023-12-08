//package com.general.component.common.resource;
//
//import com.general.component.common.jackson.JacksonSupport;
//import com.general.component.common.resource.BaseModel.ResourceInfo;
//import org.yaml.snakeyaml.DumperOptions;
//import org.yaml.snakeyaml.Yaml;
//
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.*;
//
///**
// * @description: yaml 解析器
// * @author: liuyan
// * @create: 2022−10-26 11:40 AM
// */
//public class YamlResolver implements ResourceResolver {
//
//    public static final DumperOptions OPTIONS = new DumperOptions();
//
//
//    /**
//     * 解析Yaml
//     *
//     * @param path  文件路径
//     * @param clazz 转换的类型
//     * @param <T>   转换的类型
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public <T extends ResourceInfo> List<T> load(String path, Class<T> clazz) throws Exception {
//        Yaml yaml = new Yaml();
//        try {
//            InputStream inputStream = Files.newInputStream(Paths.get(path));
//            Iterable<Object> iterable = yaml.loadAll(inputStream);
//            LinkedList<T> list = new LinkedList<>();
//            iterable.forEach(obj -> list.add(JacksonSupport.mapToObj((LinkedHashMap<String, Object>) obj, clazz)));
//            return list;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    /**
//     * 日志解析
//     *
//     * @param inputStream 流
//     * @param clazz       类型
//     * @return T
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public <T extends ResourceInfo> List<T> load(InputStream inputStream, Class<T> clazz) {
//        Yaml yaml = new Yaml();
//        try {
//            Iterable<Object> iterable = yaml.loadAll(inputStream);
//            LinkedList<T> list = new LinkedList<>();
//            iterable.forEach(obj -> list.add(JacksonSupport.mapToObj((LinkedHashMap<String, Object>) obj, clazz)));
//            return list;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//
//    /**
//     * 通过key获取value从yml配置文件
//     *
//     * @param fileName Yml文件名
//     * @return value或者map本身
//     */
//    @SuppressWarnings("unchecked")
//    public static Map<String, Object> getFromYml(String fileName) {
//        // 创建一个Yaml对象
//        Yaml yaml = new Yaml(OPTIONS);
//        // 获得流
//        InputStream inputStream = YamlResolver.class.getClassLoader().getResourceAsStream(fileName);
//        return (HashMap<String, Object>) yaml.loadAs(inputStream, HashMap.class);
//    }
//}
