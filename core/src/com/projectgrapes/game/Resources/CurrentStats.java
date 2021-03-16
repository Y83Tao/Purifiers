package com.projectgrapes.game.Resources;

public class CurrentStats {
    private int defenseInt, offenseInt, watcherInt, VaultInt, scienceInt, citycareInt, guard1Int, guard2Int, chefInt, selectedIndex;
    public static int citySecurity, chanceToRepelAttack, personelSecurity, attackEfficency, firepowerBonus, enemyPlan, enemyStat,
            dailyReduction, bankSecurity, innovationChance, upgradeScale, overallAssassinationBlock, assassinationBlock, personnelBonus, personnelBonus2, assassinationBlockBonus, familySatisfaction, cityHappinessBonus, cityHappinessPercentBonus;
    public CurrentStats(){
        for (int i = 0; i < Manager.peoples.size(); i++) {
            String set = Manager.peoples.get(i).getOccupation();
            if (set == "Department of Defense"){defenseInt = i;}if (set == "General of Offense"){offenseInt = i;}
            if (set == "The Watcher"){watcherInt = i;}if (set == "Vault Manager"){VaultInt = i;}
            if (set == "Scientist"){scienceInt = i;}if (set == "City Carer"){citycareInt = i;}
            if (set == "Personal Guard"){guard1Int = i;}if (set == "Secondary Guard"){guard2Int = i;}if (set == "Chef"){chefInt = i;}
        }
        citySecurity = Manager.peoples.get(defenseInt).getStrategy() * 11;
        chanceToRepelAttack = Manager.peoples.get(defenseInt).getStrategy() * 8 + (Manager.peoples.get(defenseInt).getManagement() * 3);
        personelSecurity = Manager.peoples.get(defenseInt).getStrategy() * 10 + (Manager.peoples.get(defenseInt).getManagement() * 1);

        attackEfficency = Manager.peoples.get(offenseInt).getStrategy() * 11;
        firepowerBonus = Math.round(Manager.peoples.get(offenseInt).getStrategy() / 2) + Math.round(Manager.peoples.get(offenseInt).getManagement() /2);

        enemyPlan = Manager.peoples.get(watcherInt).getStrategy() * 2;
        enemyStat = Manager.peoples.get(watcherInt).getStrategy() + Manager.peoples.get(watcherInt).getManagement();

        dailyReduction = Manager.peoples.get(VaultInt).getManagement() * 2;
        bankSecurity = Manager.peoples.get(VaultInt).getManagement() * 6 + (Manager.peoples.get(VaultInt).getStrategy() * 5);

        innovationChance = Manager.peoples.get(scienceInt).getScience() * 5;
        upgradeScale = Manager.peoples.get(scienceInt).getScience() * 2;

        cityHappinessBonus = Manager.peoples.get(citycareInt).getManagement() * 2;
        cityHappinessPercentBonus = Manager.peoples.get(citycareInt).getManagement() * 1;

        assassinationBlock = Manager.peoples.get(guard1Int).getStrength() * 7;
        personnelBonus = Manager.peoples.get(guard1Int).getStrength() * 2;
        assassinationBlockBonus = Manager.peoples.get(guard2Int).getStrength() * 5;
        personnelBonus2 = Manager.peoples.get(guard2Int).getStrength() * 2;
        overallAssassinationBlock = assassinationBlock + assassinationBlockBonus;
        familySatisfaction = Manager.peoples.get(chefInt).getCooking() * 11;

    }
}
