package controller.item;

import javafx.collections.ObservableList;
import model.dto.ItemDTO;

public interface ItemService {
    public void add(String itemCode,String description,String category,int qtyOnHand,double unitPrice);
    public void delete(String itemCode);
    public void update(String itemCode,String description,String category,int qtyOnHand,double unitPrice);
    public ObservableList<ItemDTO> loadTable();


}
