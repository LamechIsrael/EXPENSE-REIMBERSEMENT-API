package dev.israel.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    private int id;
    private String itemName;
    private Double itemCost;
    private String status;
    private int purchasingEmployeeId;


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemCost=" + itemCost +
                ", status='" + status + '\'' +
                ", purchasingEmployeeId=" + purchasingEmployeeId +
                '}';
    }
}




