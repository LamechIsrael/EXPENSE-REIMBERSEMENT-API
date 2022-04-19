package dev.israel.entities;

public class Expense {

    private int id;
    private String itemName;
    private Double itemCost;
    private int itemQuantity;

    public Expense() {
    }

    public Expense(int id, String itemName, Double itemCost, int itemQuantity) {
        this.id = id;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemQuantity = itemQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemCost() {
        return itemCost;
    }

    public void setItemCost(Double itemCost) {
        this.itemCost = itemCost;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemCost=" + itemCost +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}



