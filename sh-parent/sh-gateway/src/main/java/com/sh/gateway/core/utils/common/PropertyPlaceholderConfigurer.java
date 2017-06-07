package com.sh.gateway.core.utils.common;

import com.sh.utils.logger.LogHelper;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Fanjiuyi on 2017/4/19.
 */
public class PropertyPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {


    public void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        try {
        String jdbcd = props.getProperty("jdbc.driverClassName");
            LogHelper.info(jdbcd);
        }catch (Exception e){
        }
    }
    }
