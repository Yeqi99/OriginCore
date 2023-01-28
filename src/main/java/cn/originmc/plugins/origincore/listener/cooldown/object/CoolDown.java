package cn.originmc.plugins.origincore.listener.cooldown.object;

import java.util.UUID;

public class CoolDown {
    private UUID id;
    private String sId;
    private long time;
    private String remarks;
    public CoolDown(long time){
        setId(UUID.randomUUID());
        setTime(time);
    }
    public CoolDown(long time,UUID id){
        setId(id);
        setTime(time);
    }
    public CoolDown(long time,UUID id,String sId){
        setId(id);
        setsId(sId);
        setTime(time);
    }
    public boolean go(){
        if (time>0){
            time--;
            return true;
        }else {
            time=0;
            return false;
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }
}
