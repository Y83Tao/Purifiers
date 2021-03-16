package com.projectgrapes.game.Resources;

import com.projectgrapes.game.MainMenu;

import static com.projectgrapes.game.MainMenu.prefs;

public class Factions {
    private int FirePower;
    private int troops;
    private int perUnitFirePower;
    private String name;
    private String playerStatus;
    private int tradeInput;
    private int tradeOutput;
    private String resourceRich;
    private String status;
    private String encountered;

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
        prefs.putString(keyName + " tradeStatus", tradeStatus);
        prefs.flush();
    }

    private String tradeStatus;
    private String resourceNeeds;
    private int dailyTroopIncrease;
    private String keyName;
    Factions(String name, String playerStatus, String resourceRich, String resourceNeeds, String tradeStatus, int tradeInput, int tradeOutput, int perUnitFirePower, int troops, String status, String encountered, String keyName){
        this.name = name;
        this.tradeStatus = tradeStatus;
        this.playerStatus = playerStatus;
        this.resourceRich = resourceRich;
        this.resourceNeeds = resourceNeeds;
        this.perUnitFirePower = perUnitFirePower;
        this.tradeInput = tradeInput;
        this.tradeOutput = tradeOutput;
        this.FirePower = this.perUnitFirePower * troops;
        this.troops = troops;
        this.status = status;
        this.encountered = encountered;
        this.keyName = keyName;
    }
    public String getname(){
        return name;
    }
    public int getFirePower(){
        return FirePower;
    }
    public void updateFirePower(){
        FirePower = perUnitFirePower * troops;
    }
    public String getResourceRich(){
        return resourceRich;
    }
    public void setResourceRich(String setResourceRich){
        resourceRich = setResourceRich;
    }
    public int getTroops(){
        return troops;
    }
    public void addTroops(int change){
        troops += change;
        prefs.putInteger(keyName + " troops", ((prefs.getInteger(keyName + " troops") + change)));
        prefs.flush();
    }
    public String getKeyName(){
        return keyName;
    }
    public int getTradeInput() {
        return tradeInput; }
    public void setTradeInput(int tradeInput) {
        this.tradeInput = tradeInput; }
    public int getTradeOutput() {
        return tradeOutput; }
    public void setTradeOutput(int tradeOutput) {
        this.tradeOutput = tradeOutput; }
    public String getStatus() {
        return status; }
    public void setStatus(String status) {
        this.status = status;
        prefs.putString(keyName + " status", status);
        prefs.flush();}
    public String getResourceNeeds() {
        return resourceNeeds; }
    public void setResourceNeeds(String resourceNeeds) {
        this.resourceNeeds = resourceNeeds; }
    public String getEncountered() {
        return encountered; }

    public int getPerUnitFirePower(){
        return perUnitFirePower;
    }
    public void setEncountered(String encountered) {
        this.encountered = encountered;
        prefs.putString(keyName + " encountered", encountered);}

    public String getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(String playerStatus) {
        this.playerStatus = playerStatus;
        prefs.putString(keyName + " playerStatus", playerStatus);
        prefs.flush();
    }

}
