package com.lei.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

@Slf4j
public class IPUtil {

    /**
     * @param request
     * @return could be multiple ip split by comma
     */
    public static String getIPs(HttpServletRequest request) {
        // get proxy ip
        String ipAddress = request.getHeader("x-forwarded-for");

        // get proxy ip
        if (isUnknown(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        // get proxy ip
        if (isUnknown(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        // get client ip
        if (isUnknown(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            // get real ip if client machine is localhost.
            if (isLoopBack(ipAddress)) {
                ipAddress = getIPAddressWhenLoopBack(ipAddress);
            }
        }

        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }

    private static boolean isUnknown(String ipAddress) {
        return ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress);
    }

    private static boolean isLoopBack(String ipAddress) {
        return ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1");
    }

    private static String getIPAddressWhenLoopBack(String ipAddress) {
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.debug("trace-log", e.getStackTrace());
        }

        return ipAddress;
    }
}