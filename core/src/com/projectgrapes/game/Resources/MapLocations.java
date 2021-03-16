package com.projectgrapes.game.Resources;

import static com.projectgrapes.game.MainMenu.prefs;

public class MapLocations {
    private String RegionName;
    private String area1;
    private String area2;
    private String area3;
    private String area4;
    private String area5;
    private int area1Amount;
    private int area2Amount;
    private int area3Amount;
    private int area4Amount;
    private int area5Amount;
    private int ResourceAmount;
    private int territorialRule;

    public int getYourTroopCount() {
        return yourTroopCount;
    }

    public void changeYourTroopCount(int yourTroopCount) {
        this.yourTroopCount += yourTroopCount;
        if (this.yourTroopCount < 0){
            this.yourTroopCount = 0;
        }
        prefs.putInteger(getRegionName()+ " yourTroopCount", this.yourTroopCount );
        prefs.flush();
    }

    private int yourTroopCount;



    private String engagement;
    private int AmountPer;
    MapLocations(String RegionName, String area1, int area1Amount, String area2, int area2Amount, String area3, int area3Amount, String area4, int area4Amount, String area5, int area5Amount, int ResourceAmount,
                 int AmountPer, int yourTroopCount, String engagement){
        this.RegionName = RegionName;
        this.area1 = area1;
        this.area2 = area2;
        this.area3 = area3;
        this.area4 = area4;
        this.area5 = area5;

        this.area1Amount = area1Amount;
        this.area2Amount = area2Amount;
        this.area3Amount = area3Amount;
        this.area4Amount = area4Amount;
        this.area5Amount = area5Amount;

        this.ResourceAmount = ResourceAmount;
        this.AmountPer = AmountPer;
        this.yourTroopCount = yourTroopCount;
        this.engagement = engagement;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public String getArea3() {
        return area3;
    }

    public void setArea3(String area3) {
        this.area3 = area3;
    }

    public String getArea4() {
        return area4;
    }

    public void setArea4(String area4) {
        this.area4 = area4;
    }

    public String getArea5() {
        return area5;
    }

    public void setArea5(String area5) {
        this.area5 = area5;
    }

    public int getArea1Amount() {
        return area1Amount;
    }

    public void setArea1Amount(int area1Amount) {

        this.area1Amount += area1Amount;
        prefs.putInteger(getRegionName() + " area1Amount", prefs.getInteger(getRegionName() + " area1Amount") + area1Amount);
        prefs.flush();

    }

    public int getArea2Amount() {
        return area2Amount;
    }

    public void setArea2Amount(int area2Amount) {
        this.area2Amount += area2Amount;
        prefs.putInteger(getRegionName() + " area2Amount", prefs.getInteger(getRegionName() + " area2Amount") + area2Amount);
        prefs.flush();
    }

    public int getArea3Amount() {
        return area3Amount;
    }

    public void setArea3Amount(int area3Amount) {
        this.area3Amount += area3Amount;
        prefs.putInteger(getRegionName() + " area3Amount", prefs.getInteger(getRegionName() + " area3Amount") + area3Amount);
        prefs.flush();

    }

    public int getArea4Amount() {
        return area4Amount;
    }

    public void setArea4Amount(int area4Amount) {
        this.area4Amount += area4Amount;
        prefs.putInteger(getRegionName() + " area4Amount", prefs.getInteger(getRegionName() + " area4Amount") + area4Amount);
        prefs.flush();
    }

    public int getArea5Amount() {
        return area5Amount;
    }

    public void setArea5Amount(int area5Amount) {

        this.area5Amount += area5Amount;
        prefs.putInteger(getRegionName() + " area5Amount", prefs.getInteger(getRegionName() + " area5Amount") + area5Amount);
        prefs.flush();
    }

    public int getResourceAmount() {
        return ResourceAmount;
    }

    public void setResourceAmount(int resourceAmount) {
        ResourceAmount += resourceAmount;
        prefs.putInteger(getRegionName() + " ResourceAmount", prefs.getInteger(getRegionName() + " ResourceAmount") + resourceAmount);
        prefs.flush();
    }

    public int getAmountPer() {
        return AmountPer;
    }

    public void setAmountPer(int amountPer) {
        AmountPer = amountPer;
    }

    public String getEngagement() {
        return engagement;
    }

    public void setEngagement(String engagement) {
        this.engagement = engagement;
        prefs.putString(getRegionName() + " engagement", engagement);
        prefs.flush();
    }
}
