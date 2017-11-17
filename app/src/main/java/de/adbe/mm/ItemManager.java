package de.adbe.mm;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ItemManager {

    private static ArrayList<Item> storedItems = new ArrayList<Item>();
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private int idCounter = 0;

    private AppCompatActivity activity;

    public ItemManager(AppCompatActivity superActivity){
        activity = superActivity;
    }

    public void addStoredItems(){
        for(Item i:ItemManager.storedItems){
            i.resetId(idCounter);
            idCounter++;
            itemList.add(i);
        }
    }

    public static void storeItem(String string, long date, int value){
        storedItems.add(new Item(0, string, date, value));
    }

    public ArrayList<Item> getAllItems() {
        return itemList;
    }

    private void addItem(Item item){
        itemList.add(item);
    }

    public void addItem(String title, long date, int value){
        addItem(new Item(idCounter, title, date, value));
        idCounter++;
    }

    public void removeItem(int id){
        itemSelection:
        for (int i = 0; i < this.itemList.size(); i++) {
            if(itemList.get(i).getId() == id){
                itemList.remove(i);
                break itemSelection;
            }
        }
    }

}
