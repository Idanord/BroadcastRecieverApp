package com.example.will.broadcasereciever;

/**
 * Created by Will on 5/28/2018.
 */

public class IncomingNumber {

    private int id;
    private String number;

    //constructor
    public IncomingNumber(int id, String number){
        this.setId(id);
        this.setNumber(number);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
