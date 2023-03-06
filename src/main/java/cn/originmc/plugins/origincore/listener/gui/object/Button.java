package cn.originmc.plugins.origincore.listener.gui.object;

import cn.originmc.plugins.origincore.util.item.Item;

public class Button {
    private Item item;
    private char sign;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}
