package cn.originmc.plugins.origincore.listener.cooldown.object;


import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoolDownQueue {
    private List<CoolDown> coolDownList=new ArrayList<>();
    public void clear(){
        getCoolDownList().clear();
    }
    public boolean add(CoolDown coolDown){
        if (hasCoolDown(coolDown.getId())){
            return false;
        }else {
            coolDownList.add(coolDown);
            return true;
        }
    }
    public boolean add(long time,UUID id,String sId){
        if (hasCoolDown(id,sId)){
            return false;
        }else {
            coolDownList.add(new CoolDown(time,id,sId));
            return true;
        }
    }
    public boolean remove(UUID id){
        for (int i=0;i<coolDownList.size();i++){
            CoolDown coolDown= coolDownList.get(i);
            if (coolDown.getId()==id){
                coolDownList.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean remove(UUID id,String sId){
        for (int i=0;i<coolDownList.size();i++){
            CoolDown coolDown= coolDownList.get(i);
            if (coolDown.getId()==id){
                if (coolDown.getsId().equalsIgnoreCase(sId)){
                    coolDownList.remove(i);
                }
                return true;
            }
        }
        return false;
    }
    public boolean hasCoolDown(UUID id,String sId){
        for (CoolDown coolDown : coolDownList) {
            if (coolDown.getsId().toString().equalsIgnoreCase(id.toString())){
                if (coolDown.getsId().equalsIgnoreCase(sId)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean hasCoolDown(UUID id){
        for (CoolDown coolDown : coolDownList) {
            if (coolDown.getId().toString().equalsIgnoreCase(id.toString())){
                return true;
            }
        }
        return false;
    }
    public CoolDown getCoolDown(UUID id){
        for (CoolDown coolDown : coolDownList) {
            if (coolDown.getId()==id){
                return coolDown;
            }
        }
        return null;
    }
    public CoolDown getCoolDown(UUID id,String sId){
        for (CoolDown coolDown : coolDownList) {
            if (coolDown.getId().toString().equalsIgnoreCase(id.toString())){
                if (coolDown.getsId().equalsIgnoreCase(sId)){
                    return coolDown;
                }
            }
        }
        return null;
    }
    public List<CoolDown> getCoolDownList() {
        return coolDownList;
    }

    public void setCoolDownList(List<CoolDown> coolDownList) {
        this.coolDownList = coolDownList;
    }
    public void goTime(){
        List<CoolDown> clone=new ArrayList<>();
        for (CoolDown coolDown : getCoolDownList()) {
            if(coolDown.go()){
                clone.add(coolDown);
            }
        }
        setCoolDownList(clone);
    }
    public boolean createCoolDown(long time,UUID id){
        if (hasCoolDown(id)){
            return false;
        }else {
            CoolDown coolDown=new CoolDown(time,id);
            coolDownList.add(coolDown);
            return true;
        }
    }
    public long getTime(UUID id,String sId){
        return getCoolDown(id,sId).getTime();
    }
    public long getTime(UUID id){
        return getCoolDown(id).getTime();
    }
}
