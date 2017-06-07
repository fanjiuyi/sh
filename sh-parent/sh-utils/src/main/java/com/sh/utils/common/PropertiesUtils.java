/**
 * @(#) PropertiesUtils.java
 * @Package cap.capms.core.utils.common
 * <p>
 * Copyright © Longshine Corporation. All rights reserved.
 */

package com.sh.utils.common;

import java.io.*;
import java.util.Properties;

/**
 * 类描述：
 *
 * @author: Administrator
 * @version $Id: longshine-codetemplates.xml,v 1.1 2012/09/05 04:45:47
 *          administrator Exp $
 *
 *          History: Sep 30, 2014 12:50:10 PM Administrator Created.
 *
 */
public class PropertiesUtils {
    /**
     * 读取配置文件
     *
     * @return
     */
    public static Properties getProperties(String file) {
        Properties pro = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            pro = new Properties();
            pro.load(in);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pro;
    }

    public static Properties getProperties(InputStream fileIS) {
        Properties pro = null;
        try {
            pro = new Properties();
            pro.load(fileIS);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

    /**
     * 保存属性到文件中
     *
     * @param pro
     * @param file
     */
    public static void saveProperties(Properties pro, String file) {
        if (pro == null) {
            return;
        }
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(file, false);
            pro.store(oFile, "modify properties file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oFile != null) {
                    oFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 增加属性文件值
     *
     * @param key
     * @param value
     */
    public static void addProperties(String key[], String value[], String file) {
        Properties iniFile = getProperties(file);
        FileOutputStream oFile = null;
        try {
            iniFile.put(key, value);
            oFile = new FileOutputStream(file, true);
            iniFile.store(oFile, "modify properties file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oFile != null) {
                    oFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改属性文件
     *
     * @param key
     * @param value
     */
    public static void updateProperties(String key, String value, String file) {
        // key为空则返回
        if (key == null || "".equalsIgnoreCase(key)) {
            return;
        }
        Properties pro = getProperties(file);
        if (pro == null) {
            pro = new Properties();
        }
        pro.put(key, value);

        // 保存属性到文件中
        saveProperties(pro, file);
    }
}
