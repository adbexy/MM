package de.adbe.mm;


public class Item {

    private int id;
    private String title;
    private long date;
    private int value;

    boolean idReset = false;

    public Item(int id, String title, long date, int value){
        this.id = id;
        this.title = title;
        this.date = date;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void resetId(int id) {
        if(!idReset){
            this.id = id;
        }
        idReset = true;
    }

    public String getTitle() {
        return title;
    }

    public long getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
