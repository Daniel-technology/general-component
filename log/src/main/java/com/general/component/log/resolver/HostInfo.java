package com.general.component.log.resolver;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @description: HostInfo
 * @author: liuyan
 * @create: 2022−10-23 2:48 PM
 */
public class HostInfo {

    private static final Logger log = LoggerFactory.getLogger(HostInfo.class);

    /**
     * ip转地址
     */
    private static Searcher searcher;


    /**
     * 获取实例
     *
     * @return Searcher
     * @throws IOException
     */
    public static Searcher getSearcherInstance() throws IOException {
        if (searcher == null) {
            synchronized (HostInfo.class) {
                if (searcher == null) {
                    InputStream url = HostInfo.class.getClassLoader().getResourceAsStream("data_ip2region.xdb");
                    byte[] byteArray = IOUtils.toByteArray(url);
                    searcher = Searcher.newWithBuffer(byteArray);
                }
            }
        }
        return searcher;
    }


    /**
     * 获取IP地址
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = "";
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("获取访问ip异常",
                    e);
        }
        //使用代理，则获取第一个IP地址
        if (StringUtils.isEmpty(ip)) {
            if (Objects.requireNonNull(ip).indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
