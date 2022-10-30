package fr.endide.bedwars.arena;

public class item {
    private String name;
    private int price;
    private String money;
    private String type;
    private int slot;

    private int amount;


    public item(String name, int price, String money, String type, int slot, int amount) {
        this.name = name;
        this.price = price;
        this.money = money;
        this.type = type;
        this.slot = slot;
        this.amount = amount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getSlot() {
        return slot;
    }
    public void setSlot(int slot) {
        this.slot = slot;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
