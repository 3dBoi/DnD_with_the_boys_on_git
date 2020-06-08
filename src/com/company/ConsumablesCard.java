package com.company;

import com.sun.corba.se.spi.ior.IdentifiableFactory;

import java.util.ArrayList;

public class ConsumablesCard extends ItemCard {
    private int healing;
    private int dmg;
    private double defence;
    private int effectDuration;
    private boolean overheal;


    public ConsumablesCard(String name,int id, int dmg,  int healing, boolean overheal, int effectDuration, double defence){

        super(id,name);
        super.setEnumC(CardsE.CONSUMABLECARD);
        this.dmg= dmg;
        this.healing=healing;
        this.overheal=overheal;
        this.effectDuration= effectDuration;
        this.defence = defence;
    }

    public int getHealing(){return healing;}
    public void setHealing(int healing){this.healing=healing;}

    public int getDmg(){return dmg;}
    public void setDmg(int dmg){this.dmg=dmg;}

    public double getDefence(){return defence;}
    public void setHealing(double defence){this.defence=defence;}

    public int getEffectDuration(){return effectDuration;}
    public void setEffectDuration(int effectDuration){this.effectDuration=effectDuration;}

    //healing, overHeal, dmg, boost class, boost defence

    public void overHeal(int healing){
        FXMLNameMenuController.player.setHealth(FXMLNameMenuController.player.getHealth() + healing);
    }

    public void heal(int healing){
            if(FXMLNameMenuController.player.getHealth() + healing<=FXMLNameMenuController.player.getMaxhealth()){
                FXMLNameMenuController.player.setHealth(FXMLNameMenuController.player.getHealth() + healing);
            }
            else{FXMLNameMenuController.player.setHealth(FXMLNameMenuController.player.getMaxhealth());}
    }

    public void doDmg(int dmg, EnemyCard enemy){
      enemy.setHealth(enemy.getHealth()-dmg);
    }

    public void defenceCon(double defence, EnemyCard enemy){
    enemy.setAttack((int)(enemy.getAttack()*(1-defence)));
    }

    public void useConsumable(ConsumablesCard Consumable, EnemyCard enemy){
       if(Consumable.effectDuration >= 1) {
           if (Consumable.healing > 0) {
               if (Consumable.overheal) {
                   overHeal(Consumable.healing);
               } else {
                   heal(Consumable.healing);
               }
           }
           if (Consumable.dmg >= 1){doDmg(Consumable.dmg, enemy);}
           if (Consumable.defence > 0){defenceCon(Consumable.defence, enemy);}
       }
       else{
           if(Consumable.defence > 0){defenceCon(enemy.getAttack()/Consumable.defence, enemy);}
           FXMLCombatController.ConsumablesInUse.remove(Consumable);}

       --Consumable.effectDuration;
    }

}
