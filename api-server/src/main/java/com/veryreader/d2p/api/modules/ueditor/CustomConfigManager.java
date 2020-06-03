//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.veryreader.d2p.api.modules.ueditor;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

@Slf4j
public final class CustomConfigManager {
    private final String rootPath;
    private final String originalPath;
    private final String contextPath;
    private static final String configFileName = "config.json";
    private String parentPath = null;
    private JSONObject jsonConfig = null;
    private static final String SCRAWL_FILE_NAME = "scrawl";
    private static final String REMOTE_FILE_NAME = "remote";

    private CustomConfigManager(String rootPath, String contextPath, String uri) throws FileNotFoundException, IOException {
        rootPath = rootPath.replace("\\", "/");
        this.rootPath = rootPath;
        this.contextPath = contextPath;
        if (contextPath.length() > 0) {
            this.originalPath = this.rootPath + uri.substring(contextPath.length());
        } else {
            this.originalPath = this.rootPath + uri;
        }

        this.initEnv();
    }

    public static CustomConfigManager getInstance(String rootPath, String contextPath, String uri) {
        try {
            return new CustomConfigManager(rootPath, contextPath, uri);
        } catch (Exception var4) {
            log.error(var4.getMessage(),var4);
            return null;
        }
    }

    public boolean valid() {
        return this.jsonConfig != null;
    }

    public JSONObject getAllConfig() {
        return this.jsonConfig;
    }

    public Map<String, Object> getConfig(int type) {
        Map<String, Object> conf = new HashMap();
        String savePath = null;
        switch(type) {
        case 1:
            conf.put("isBase64", "false");
            conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
            conf.put("allowFiles", this.getArray("imageAllowFiles"));
            conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
            savePath = this.jsonConfig.getString("imagePathFormat");
            break;
        case 2:
            conf.put("filename", "scrawl");
            conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
            conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
            conf.put("isBase64", "true");
            savePath = this.jsonConfig.getString("scrawlPathFormat");
            break;
        case 3:
            conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
            conf.put("allowFiles", this.getArray("videoAllowFiles"));
            conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
            savePath = this.jsonConfig.getString("videoPathFormat");
            break;
        case 4:
            conf.put("isBase64", "false");
            conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
            conf.put("allowFiles", this.getArray("fileAllowFiles"));
            conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
            savePath = this.jsonConfig.getString("filePathFormat");
            break;
        case 5:
            conf.put("filename", "remote");
            conf.put("filter", this.getArray("catcherLocalDomain"));
            conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
            conf.put("allowFiles", this.getArray("catcherAllowFiles"));
            conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
            savePath = this.jsonConfig.getString("catcherPathFormat");
            break;
        case 6:
            conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
            conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
            conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
            break;
        case 7:
            conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
            conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
            conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
        }

        conf.put("savePath", savePath);
        conf.put("rootPath", this.rootPath);
        return conf;
    }

    private void initEnv() throws FileNotFoundException, IOException {
        File file = new File(this.originalPath);
        if (!file.isAbsolute()) {
            file = new File(file.getAbsolutePath());
        }

        this.parentPath = file.getParent();
        URL resource = this.getClass().getClassLoader().getResource(configFileName);
        String configContent = this.readFile(resource);

        try {
            JSONObject jsonConfig = new JSONObject(configContent);
            this.jsonConfig = jsonConfig;
        } catch (Exception var4) {
            this.jsonConfig = null;
        }

    }

    private String getConfigPath() {
        return this.parentPath + File.separator + "config.json";
    }

    private String[] getArray(String key) {
        JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
        String[] result = new String[jsonArray.length()];
        int i = 0;

        for(int len = jsonArray.length(); i < len; ++i) {
            result[i] = jsonArray.getString(i);
        }

        return result;
    }

    private String readFile(URL source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try {
            InputStreamReader reader = new InputStreamReader(source.openStream());
            BufferedReader bfReader = new BufferedReader(reader);
            String tmpContent = null;

            while((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }

        return this.filter(builder.toString());
    }

    private String filter(String input) {
        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
    }
}
