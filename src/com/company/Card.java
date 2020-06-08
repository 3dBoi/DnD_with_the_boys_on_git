package com.company;

public class Card {
    
    private int id;
    private String name;
    private CardsE enumC;
    
    public Card(int id, String name){
    this.id = id;
    this.name = name;
        }
    
    public int getId(){
    return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(int id){
    this.id = id;}

    public CardsE getEnumC() {
        return enumC;
    }
    public void  setEnumC(CardsE enumC){
        this.enumC = enumC;
    }
}
