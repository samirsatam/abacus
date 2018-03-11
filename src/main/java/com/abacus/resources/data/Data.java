package com.abacus.resources.data;

public class Data implements IData {

    private long id;
    private String dataType;
    public Data() {}
    public Data(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getType() {return dataType;}

    public void setType(String dataType) {this.dataType = dataType;}
}
