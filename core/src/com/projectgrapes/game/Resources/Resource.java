package com.projectgrapes.game.Resources;

import static com.projectgrapes.game.MainMenu.prefs;

public class Resource {
    public static int gold, respect, troops, resource, wallHp;
    public int goldRateIncrease;
    public int goldRateDecrease;
    public int resourceRateIncrease;
    public int resourceRateDecrease;
    public int respectIncrease;

    public int troopRate;
    Manager setup = new Manager();
    CurrentStats statz = new CurrentStats();
    miscellaneousStats moreStats = new miscellaneousStats();


    public Resource(){
        gold = prefs.getInteger("Gold");
        wallHp = prefs.getInteger("wallsHealth");
        if (wallHp > 100) {wallHp = 100;}
        respect = prefs.getInteger("Respect") + (prefs.getInteger("Respect") * (statz.cityHappinessPercentBonus / 100)) + (statz.cityHappinessBonus );
        if (respect > 100) { respect = 100;}
        if (respect < 0) {respect = 0;}
        troops = prefs.getInteger("Troops");
        resource = prefs.getInteger("Resource");

        goldRateDecrease = prefs.getInteger("GoldRateDecrease");
        goldRateIncrease = prefs.getInteger("GoldRateIncrease");
        resourceRateDecrease = prefs.getInteger("ResourceRateDecrease");
        resourceRateIncrease = prefs.getInteger("ResourceRateIncrease");
        respectIncrease = moreStats.getRecreational() / 5;
    }
    public static void wallHpChange(int change){
        wallHp += change;
        prefs.putInteger("wallsHealth", wallHp);
        prefs.flush();
    }

    public void changeGold(int change){
        gold += change;
        prefs.putInteger("Gold", prefs.getInteger("Gold") + change);
        prefs.flush();
    }
    public void changeRespect(int change){
        respect += change;
        prefs.putInteger("Respect", prefs.getInteger("Respect") + change);
        prefs.flush();
    }
    public void changeTroops(int change){
        troops += change;
        prefs.putInteger("Troops", prefs.getInteger("Troops") + change);
        prefs.flush();
    }

    public void changeResource(int change){
        resource += change;
        prefs.putInteger("Resource", prefs.getInteger("Resource") + change);
        prefs.flush();
    }
    public void changeGoldRateIncrease(int change){
        goldRateIncrease += change;
        prefs.putInteger("GoldRateIncrease", prefs.getInteger("GoldRateIncrease") + change);
        prefs.flush();
    }
    public void changeGoldRateDecrease(int change){
        goldRateDecrease += change;
        prefs.putInteger("GoldRateDecrease", prefs.getInteger("GoldRateDecrease") + change);
        prefs.flush();
    }
    public void changeResourceRateIncrease(int change){
        resourceRateIncrease += change;
        prefs.putInteger("ResourceRateIncrease", prefs.getInteger("ResourceRateIncrease") + change);
        prefs.flush();
    }
    public void changeResourceRateDecrease(int change){
        resourceRateDecrease += change;
        prefs.putInteger("ResourceRateDecrease", prefs.getInteger("ResourceRateDecrease") + change);
        prefs.flush();
    }
    public int getGold(){
        return gold;
    }
    public int getRespect(){
        return respect;
    }
    public int getTroops(){
        return troops;
    }
    public int getResource(){
        return resource;
    }
    public int getGoldRateIncrease() {
        return goldRateIncrease;
    }
    public int getGoldRateDecrease(){
        return goldRateDecrease;
    }
    public int getResourceRateIncrease(){
        return resourceRateIncrease;
    }
    public int getResourceRateDecrease(){
        return resourceRateDecrease;
    }
    public int getRespectIncrease() {
        return respectIncrease;
    }

    public void updateRespectIncrease() {
        respectIncrease = moreStats.getRecreational() / 5;
    }




}
