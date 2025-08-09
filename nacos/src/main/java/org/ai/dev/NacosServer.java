package org.ai.dev;

public class NacosServer {
    public static void main(String[] args) {
        // Set Nacos to standalone mode
        System.setProperty("nacos.standalone", "true");
        // Set the default cluster name
        System.setProperty("nacos.naming.cluster-name", "DEFAULT");

        try {
            // Start Nacos server
            com.alibaba.nacos.Nacos.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
