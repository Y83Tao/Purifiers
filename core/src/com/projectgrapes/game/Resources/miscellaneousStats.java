package com.projectgrapes.game.Resources;

import static com.projectgrapes.game.MainMenu.prefs;

public class miscellaneousStats {

    public String[] Districts = new String[9];
    public int firePower;
    public int weaponryPower;
    public int vehicles;
    public int armsUpgrade;
    public int MercenariesAvailable;

    public int landCapacity;
    public int residentialSpace;
    public int industries;
    public int recreational;
    public int landCost;
    public int wallsUpgrade;
    public int emergencyServices;


    CurrentStats statz = new CurrentStats();

    public miscellaneousStats(){
        for (int i = 0; i < prefs.getString("District").length(); i++) {
            String DistrictType = Character.toString(prefs.getString("District").charAt(i));
            if (DistrictType.contains("0")) {
                Districts[i] = "Good";
            } else if (DistrictType.contains("1")){
                Districts[i] = "Infested";
            } else if (DistrictType.contains("2")){
                Districts[i] = "Under Seige";
            } else if (DistrictType.contains("3")){
                Districts[i] = "Rioting";
            }
        }
        landCapacity = prefs.getInteger("landCapacity");
        residentialSpace = prefs.getInteger("residentialSpace");
        recreational = prefs.getInteger("recreational");
        industries = prefs.getInteger("Industries");
        landCost = prefs.getInteger("landCapacityPrice");

        weaponryPower = prefs.getInteger("WeaponryPower");
        vehicles = prefs.getInteger("MilitaryVehicles");
        firePower = weaponryPower + (vehicles * 5) + Resource.troops + statz.firepowerBonus;
        armsUpgrade = prefs.getInteger("ArmsUpgrades");
        MercenariesAvailable = prefs.getInteger("MercenaryForHire");


    }

    public int getArmsUpgrade() {
        return armsUpgrade;
    }

    public void addArmsUpgrade(int armsUpgrade) {
        this.armsUpgrade += armsUpgrade;
        prefs.putInteger("ArmsUpgrades", (prefs.getInteger("ArmsUpgrades") + armsUpgrade));
        prefs.flush();
    }

    public int getMercenariesAvailable() {
        return MercenariesAvailable;
    }

    public void addMercenariesAvailable(int mercenariesAvailable) {
        MercenariesAvailable += mercenariesAvailable;
        prefs.putInteger("MercenaryForHire", prefs.getInteger("MercenaryForHire") + mercenariesAvailable);
        prefs.flush();
    }

    public void firePowerUpdate(){
        firePower = weaponryPower + (vehicles * 5) + Resource.troops + statz.firepowerBonus;
    }
    public int getLandCost() {
        return landCost;
    }

    public int getWeaponryPower() {
        return weaponryPower;
    }

    public void addWeaponryPower(int weaponryPower) {
        this.weaponryPower += weaponryPower;
        prefs.putInteger("WeaponryPower", (prefs.getInteger("WeaponryPower") + weaponryPower));
        prefs.flush();
    }

    public int getVehicles() {
        return vehicles;
    }

    public void addVehicles(int vehicles) {
        this.vehicles += vehicles;
        prefs.getInteger("MilitaryVehicles", (prefs.getInteger("MilitaryVehicles") + vehicles));
        prefs.flush();
    }


    public void addLandCost(int landCost) {
        this.landCost += landCost;
        prefs.putInteger("landCapacityPrice", (prefs.getInteger("landCapacityPrice") + landCost));
        prefs.flush();
    }

    public String[] getDistricts() {
        return Districts;
    }

    public void setDistricts(String[] districts) {
        Districts = districts;
    }

    public int getFirePower() {
        return firePower;
    }

    public void addFirePower(int firePower) {
        this.firePower += firePower;
        //prefs.putInteger("")
    }

    public int getLandCapacity() {
        return landCapacity;
    }

    public void addLandCapacity(int landCapacity) {
        this.landCapacity += landCapacity;
        prefs.putInteger("landCapacity", (prefs.getInteger("landCapacity") + landCapacity));
        prefs.flush();
    }

    public int getResidentialSpace() {
        return residentialSpace;
    }

    public void addResidentialSpace(int residentialSpace) {
        this.residentialSpace += residentialSpace;
        prefs.putInteger("residentialSpace", (prefs.getInteger("residentialSpace") + residentialSpace));
        prefs.flush();
    }

    public int getIndustries() {
        return industries;
    }

    public void addIndustries(int industries) {
        this.industries += industries;
        prefs.putInteger("Industries", (prefs.getInteger("Industries") + industries));
        prefs.flush();
    }

    public int getRecreational() {
        return recreational;
    }

    public void addRecreational(int recreational) {
        this.recreational += recreational;
        prefs.putInteger("recreational", (prefs.getInteger("recreational") + recreational));
        prefs.flush();
    }
}
