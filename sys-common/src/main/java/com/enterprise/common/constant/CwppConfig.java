package com.enterprise.common.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "cwpp")
public class CwppConfig
{
    /** 上传路径 */
    private static String path;

    public static String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        CwppConfig.path = path;
    }

    public static String getDownloadPath()
    {
        return getPath() + "/download/";
    }

    public static String getUploadPath()
    {
        return getPath() + "/upload";
    }
}
