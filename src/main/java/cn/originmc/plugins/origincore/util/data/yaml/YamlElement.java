package cn.originmc.plugins.origincore.util.data.yaml;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlElement {
    public YamlElement(String id, File file, YamlConfiguration yml){
        setId(id);
        setFile(file);
        setYml(yml);
    }
    private String id;
    private YamlConfiguration yml;
    private File file;

    /**
     * 将数据保存到文件
     */
    public void save(){
        try {
            getYml().save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public YamlConfiguration getYml() {
        return yml;
    }

    public void setYml(YamlConfiguration yml) {
        this.yml = yml;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
