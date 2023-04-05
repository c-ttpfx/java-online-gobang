package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

/**
 * @author ttpfx
 * @date 2023/3/22
 */
public class TestProperties {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("src\\server.properties"));
        String ip = properties.getProperty("ip");
        String port = properties.getProperty("port");
        System.out.println(ip);
        System.out.println(port);
    }
}
