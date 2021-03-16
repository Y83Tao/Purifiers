package com.projectgrapes.game.Resources;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class People {

    public static String[] names = new String[]{
    "Le Presti", "Carlita Rupp",
    "Janis Halbrook",
    "Brandi Abarca",
    "Jessica Riviera",
    "Jordan Schueler",
    "Saturnina Taulbee",
    "Wilber Baer",
    "Laquita Loyd",
    "Giuseppe Jimison",
    "Barbera Obrian",
    "Jasper Shum",
    "Indira Phelan",
    "Andera Bremner",
    "Ardella Shrock",
    "Nila Spikes",
    "Angelyn Boulton",
    "Wilhelmina Schmelzer",
    "Nakita Muldrow",
    "Jamika Munsell"};


    private String name;
    private int Strength;
    private int Strategy;
    private int Management;
    private int Science;
    private int Cooking;
    private int corruption;
    private int occupation;
    private String occupationName;
    private String key;
    private String statKey;
    private String statKeyName;
    People(String name, String stats, String key, String statKey) {
        System.out.println(stats);
        this.name = name;
        this.key = key;
        this.statKey = stats;
        this.statKeyName = statKey;
        this.Strength = Integer.parseInt(Character.toString(stats.charAt(0)));
        this.Strategy = Integer.parseInt(Character.toString(stats.charAt(1)));
        this.Management = Integer.parseInt(Character.toString(stats.charAt(2)));
        this.Science = Integer.parseInt(Character.toString(stats.charAt(3)));
        this.Cooking = Integer.parseInt(Character.toString(stats.charAt(4)));
        this.corruption = Integer.parseInt(Character.toString(stats.charAt(5)));
        this.occupation = Integer.parseInt(Character.toString(stats.charAt(6)));
        if (occupation == 1){ occupationName = "Department of Defense";}
        if (occupation == 2){ occupationName = "General of Offense";}
        if (occupation == 3){ occupationName = "The Watcher";}
        if (occupation == 4){ occupationName = "Vault Manager";}
        if (occupation == 5){ occupationName = "Scientist";}
        if (occupation == 6){ occupationName = "City Carer";}
        if (occupation == 7){ occupationName = "Personal Guard";}
        if (occupation == 8){ occupationName = "Secondary Guard";}
        if (occupation == 9){ occupationName = "Chef";}
        if (occupation == 0) {occupationName = "Null occupation";}
    }

    public String getName(){
        return this.name;
    }

    public int getCorruption(){return corruption;}
    public int getStrength(){return Strength;}
    public int getStrategy(){return Strategy;}
    public int getManagement(){return Management;}
    public int getScience(){ return Science;}
    public int getCooking(){return Cooking;}
    public String getOccupation(){return occupationName;}
    public String getKey() { return key;}
    public String getStatKey(){ return statKey;}
    public String getStatKeyName(){return statKeyName;}

    public void setName(String newName){this.name = newName;}
    public void setStatKey(String newStatKey) {statKey = newStatKey;}
    public void changeSkills(String skills){
        this.Strength = Integer.parseInt(Character.toString(skills.charAt(0)));
        this.Strategy = Integer.parseInt(Character.toString(skills.charAt(1)));
        this.Management = Integer.parseInt(Character.toString(skills.charAt(2)));
        this.Science = Integer.parseInt(Character.toString(skills.charAt(3)));
        this.Cooking = Integer.parseInt(Character.toString(skills.charAt(4)));
        this.corruption = Integer.parseInt(Character.toString(skills.charAt(5)));
        this.occupation = Integer.parseInt(Character.toString(skills.charAt(6)));
    }
    public void setOccupationName (String occupationstring) {
        int occupationKey =  Integer.parseInt(Character.toString(occupationstring.charAt(6)));
        statKey = occupationstring;
        System.out.println("Occupation key = " + occupationKey);
        if (occupationKey == 1){ occupationName = "Department of Defense";}
        if (occupationKey == 2){ occupationName = "General of Offense";}
        if (occupationKey == 3){ occupationName = "The Watcher";}
        if (occupationKey == 4){ occupationName = "Vault Manager";}
        if (occupationKey == 5){ occupationName = "Scientist";}
        if (occupationKey == 6){ occupationName = "City Carer";}
        if (occupationKey == 7){ occupationName = "Personal Guard";}
        if (occupationKey == 8){ occupationName = "Secondary Guard";}
        if (occupationKey == 9){ occupationName = "Chef";}
        if (occupation == 0) {occupationName = "Null occupation";}
        System.out.println(name + "   " + occupationName);

    }


    public static String randomName(int num){
        return names[num];
    }


}

