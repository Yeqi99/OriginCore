package cn.originmc.plugins.origincore.util.data.yaml;


import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YamlManager {
    private String dirName;
    private List<YamlElement> yamlElements=new ArrayList<>();
    private JavaPlugin plugin;
    private File path;
    public YamlManager(JavaPlugin plugin,String dirName){
        setPlugin(plugin);
        setPath(getPlugin().getDataFolder());
        setDirName(dirName);
        getData();
    }
    public YamlManager(JavaPlugin plugin,String path,String dirName){
        File file=new File(path);
        setPath(file);
        setPlugin(plugin);
        setDirName(dirName);
        getData();
    }
    public void getData(){
        yamlElements.clear();
        File dir = new File(path, dirName);
        if (!dir.exists()) {
            dir.mkdirs();
            return;
        }
        getAllElement(dir, yamlElements);
    }
    public void getAllElement(File fileInput, List<YamlElement> elements) {
        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                getAllElement(file, elements);
            } else {
                //收集配置文件数据
                YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
                YamlElement ye=new YamlElement(file.getName().replace(".yml",""),file,yml);
                elements.add(ye);
            }
        }
    }
    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public List<YamlElement> getYamlElements() {
        return yamlElements;
    }

    public void setYamlElements(List<YamlElement> yamlElements) {
        this.yamlElements = yamlElements;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public List<String> getIdList(){
        List<String> idList=new ArrayList<>();
        for (YamlElement yamlElement : yamlElements) {
            idList.add(yamlElement.getId());
        }
        return idList;
    }
    public int getAmount(){
        return yamlElements.size();
    }
    public YamlElement getElement(String id){
        for (YamlElement yamlElement : yamlElements) {
            if (yamlElement.getId().equalsIgnoreCase(id)){
                return yamlElement;
            }
        }
        return null;
    }
    public boolean setElement(YamlElement ye){
        for (int i=0;i<yamlElements.size();i++){
            if (yamlElements.get(i).getId().equalsIgnoreCase(ye.getId())){
                yamlElements.set(i,ye);
                return true;
            }
        }
        return false;
    }
    public boolean delElement(String id){
        for (int i=0;i<yamlElements.size();i++){
            if (yamlElements.get(i).getId().equalsIgnoreCase(id)){
                if(!yamlElements.get(i).getFile().delete()){
                    return false;
                }
                yamlElements.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean hasElement(String id){
        for (YamlElement yamlElement : yamlElements) {
            if (yamlElement.getId().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }
    public YamlConfiguration getYaml(String id){
        return getElement(id).getYml();
    }
    public boolean setYaml(String id,YamlConfiguration yml){
        YamlElement ye=getElement(id);
        if (ye==null){
            return false;
        }
        ye.setYml(yml);
        return setElement(ye);
    }
    public boolean set(String id,String key,Object value){
        YamlElement ye=getElement(id);
        YamlConfiguration ym=ye.getYml();
        ym.set(key,value);
        ye.setYml(ym);
        return setElement(ye);
    }
    public Object get(String id,String key){
        return getElement(id).getYml().get(key,null);
    }
    public void save(String id){
        getElement(id).save();
    }
    public void saveAll(){
        for (YamlElement yamlElement : yamlElements) {
            yamlElement.save();
        }
    }
    public boolean create(String id){
        if (hasElement(id)){
            return false;
        }
        File file=new File(path,dirName+"/"+id+".yml");
        try {
            if(!file.createNewFile()){
                return false;
            }
            YamlElement ye=new YamlElement(id,file,YamlConfiguration.loadConfiguration(file));
            yamlElements.add(ye);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPath(File path) {
        this.path = path;
    }
    public String getPath(){
        return path.getPath();
    }
}
