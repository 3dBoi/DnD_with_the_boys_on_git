/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import static com.company.CardsE.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author User
 */
public class LoadHashmaps {
    
    
    private HashMap<Integer,MainStoryCard> inUseStory= new HashMap<>();;
    private HashMap<Integer,EnemyCard> inUseEnemy= new HashMap<>();;
    private HashMap<Integer,ItemCard> inUseItem= new HashMap<>();;
    
    private HashMap<Integer,MainStoryCard> usedStory= new HashMap<>();;
    private HashMap<Integer,EnemyCard> usedEnemy= new HashMap<>();;
    private HashMap<Integer,ItemCard> usedItem= new HashMap<>();;
    
    private HashMap<Integer,MainStoryCard> undiscoveredStory= new HashMap<>();;
    private HashMap<Integer,EnemyCard> undiscoveredEnemy= new HashMap<>();;
    private HashMap<Integer,ItemCard> undiscoveredItem= new HashMap<>();;
    
    private HashMap<Enum<?>,HashMap<?,?>> inUse= new HashMap<>();
    private HashMap<Enum<?>,HashMap<?,?>>  used= new HashMap<>();;
    private HashMap<Enum<?>,HashMap<?,?>>  undiscovered= new HashMap<>();;
    
    public LoadHashmaps(){
    
        
        try {
            JSONTokener storyCardTokener = new JSONTokener(new FileReader("./src/resources/Datenbank.json"));
            JSONObject storyCardObject = new JSONObject(storyCardTokener);
            JSONArray storyCards = storyCardObject.getJSONArray("StoryCards");
            
            //Hier werden die StoryKarten eingelesen
            for (Object o: storyCards) {
                JSONObject json = (JSONObject) o;
                Integer id = (int) json.get("id");
                inUseStory.put(id, new MainStoryCard((int) json.get("id"), (String) json.get("name"), (String) json.get("main"), (String) json.get("sub"), (String) json.get("optA"), (String) json.get("optB")));
            }
            
            //Hier werden die EnemyKarten eingelesen
             JSONArray enemies = storyCardObject.getJSONArray("EnemyCards");
            for (Object o: enemies) {
                JSONObject json = (JSONObject) o;
                inUseEnemy.put((Integer) json.get("id"), new EnemyCard((int) json.get("id"), (String) json.get("name"), (int) json.get("health"), (int) json.get("maxhealth"), (double) json.get("defence"), (int) json.get("attack"), (int) json.get("maxattack"), (int) json.get("crit")));
            }
            
            //Hier werden die EquipmentCards eingelesen
             JSONArray equipment = storyCardObject.getJSONArray("Items");
            for (Object o: equipment) {
                JSONObject json = (JSONObject) o;
                inUseItem.put((Integer) json.get("id"), new EquipmentCard((String) json.get("name"), (int)json.get ("id"), (int) json.get("attack"), (double) json.get("defence"), SlotsE.valueOf((String) json.get("slot")), (int) json.get("critAdd")));
            }
            
            
        } catch (FileNotFoundException f) {
            System.out.println("FileNotFound: " + f.getLocalizedMessage());
        } catch (JSONException e) {
            System.out.println("JSONException: " + e.getLocalizedMessage());
        }
        
        
        inUse.put(STORYCARD, inUseStory);
        inUse.put(ENCOUNTERCARD, inUseEnemy);
        inUse.put(ITEMCARD, inUseItem);
        
        used.put(STORYCARD, usedStory);
        used.put(ENCOUNTERCARD, usedEnemy);
        used.put(ITEMCARD, usedItem);
        
        undiscovered.put(STORYCARD, undiscoveredStory);
        undiscovered.put(ENCOUNTERCARD, undiscoveredEnemy);
        undiscovered.put(ITEMCARD, undiscoveredItem);

    }
    
    public HashMap<?,?> getInUse(){
        return inUse;
    }
    
    public HashMap<?,?> getUsed(){
        return used;
    }
    
    public HashMap<?,?> getUndiscovered(){
        return undiscovered;
    }

}
