package cn.originmc.plugins.origincore.listener.gui.object;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GuiFormat {
    private String id;
    private String title;
    private List<String> format=new ArrayList<>();
    private List<Button> buttons=new ArrayList<>();

    public Gui createGui(Player player){
        Gui gui=new Gui(id,player);
        int line=format.size();
        if (line>6){
            line=6;
        }
        if (line==1){
            return null;
        }
        Inventory inventory= Bukkit.createInventory(player,line*6,title);
        int index=0;
        for (int i=0;i<line;i++){
            if (format.get(i).length()==0){
                index+=9;
                continue;
            }
            for (int j=0;j<9;j++){
                char c=format.get(i).charAt(j);
                if (c != ' ') {
                    Button button = getButton(c);
                    inventory.setItem(index, button.getItem().getItemStack());
                }
                index++;
            }
        }
        gui.setInventory(inventory);
        return gui;
    }
    public Button getButton(char sign){
        for (Button button : buttons) {
            if (button.getSign()==sign){
                return button;
            }
        }
        return null;
    }

    public List<String> getFormat() {
        return format;
    }

    public void setFormat(List<String> format) {
        this.format = format;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
