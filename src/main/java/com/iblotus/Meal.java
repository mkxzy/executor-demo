package com.iblotus;

/**
 * Created by xiezhiyan on 3/20/18.
 */
public class Meal {
    private final int orderNum;

    public Meal(int orderNum){
        this.orderNum = orderNum;
    }
    public String toString(){
        return "Meal " + orderNum;
    }
}
