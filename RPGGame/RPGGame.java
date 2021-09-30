package RPGGame;

import java.util.*;

import java.io.*;

public class RPGGame {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////GENERAL CONTENT//////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<String> usernameDatabase = new ArrayList<String>();
    public static ArrayList<String> passwordDatabase = new ArrayList<String>();
    private static FileWriter writer;
    private Scanner fileScanner; 
    private Scanner myScanner;

    public static void main (String args[]) {
        new RPGGame().ReadFile();
    }

    public void StartScreen() {
        myScanner = new Scanner (System.in);
        File x = new File("./data/database.txt");

        if (x.exists()) {
            System.out.println("\nWhat would you like to do? \n " + 
                "Login (1) \n Setup (2) \n Exit (3)");
            int playerOption = myScanner.nextInt();
            switch (playerOption) {
                case 1:
                    new RPGGame().Login();
                    break;
                case 2:
                    new RPGGame().SetupUsername(); 
                    break;
                case 3:
                    new RPGGame().ShutDown(); 
                    break;
                default:
                    System.out.println("\nError. Unavailable Option. \n");
                    new RPGGame().StartScreen();
                    break;
            }
        } else {
            System.out.println("\n What would you like to do? \nSetup (1)\n Exit (2)");
            int playerOption = myScanner.nextInt();
            switch (playerOption) {
                case 1: 
                    new RPGGame().SetupUsername();
                    break; 
                case 2: 
                    new RPGGame().ShutDown();
                    break;
                default: 
                    System.out.println("\nError. Unavailable Option. \n"); 
                    new RPGGame().StartScreen();
                    break;
            }
        }
    }

    public void Login() {
        myScanner = new Scanner (System.in);
        String playerUsername; 
        String playerPassword; 

        System.out.println("\nWhat is your username?");
        playerUsername = myScanner.nextLine();
        System.out.println("\nWhat is your password?");
        playerPassword = myScanner.nextLine();

        for (String i : usernameDatabase) {
            if (playerUsername.equals(i)) {
                if (playerPassword.equals(passwordDatabase.get(usernameDatabase.indexOf(i)))) {
                    System.out.println("\nLogin Successful!\n");
                    LoadPlayerData(i);
                    System.out.println("\nWelcome " + i + ". \n"); 
                } else {
                    System.out.println("\nInvalid Password.\n");
                    new RPGGame().StartScreen();
                }
            } 
        }

        System.out.println("\nInvalid Username.\n");
        new RPGGame().StartScreen();
    }

    public void SetupUsername() {
        myScanner = new Scanner (System.in);

        System.out.println("\nWhat will be your username? (Note: Username can only be one word.)");
        String newUsername = myScanner.nextLine();

        if (newUsername.equalsIgnoreCase("Back")) {
            new RPGGame().StartScreen();
        }

        for (String i : usernameDatabase) {
            if (newUsername.equals(i)) {
                System.out.println("That username is taken. Please try again.");
                new RPGGame().SetupUsername();
            } else if (newUsername.contains(" ")) {
                System.out.println("Invalid Username. \n"); 
                new RPGGame().SetupUsername();
            }
        }
        usernameDatabase.add(newUsername);
        new RPGGame().SetupPassword(newUsername); 
    }

    public void SetupPassword(String newUsername) {
        myScanner = new Scanner (System.in);
        
        System.out.println("\nWhat will be your password? (Note: Password can only be one word.)");
        String newPassword = myScanner.nextLine();

        if (newPassword.equalsIgnoreCase("Back")) {
            new RPGGame().SetupUsername();
        }

        if (!newPassword.contains(" ")) {
            passwordDatabase.add(newPassword);
        } else {
            System.out.println("Invalid Password. \n"); 
            new RPGGame().SetupPassword(newUsername); 
        }
        WriteInFile(newUsername, newPassword);
        System.out.println("\nYour information has been added. Thank you. \n");

        new RPGGame().StartScreen();
    }

    public static void WriteInFile(String username, String password) {
        File x = new File ("./data/database.txt"); 
        if (x.exists()) {
            try {
                writer = new FileWriter("./data/database.txt", true);
                writer.write(username + " " + password + " \n");
                writer.close();
            } catch (Exception e) {
                System.out.println("\nError loading database.");
            }
        } 
    }
    
    public void ReadFile () {
        System.out.print("\nFileReading...");
        File x = new File ("./data/database.txt"); 

        if (!x.exists()) {
            try {
                writer = new FileWriter("./data/database.txt");
            } catch (Exception e) {
                System.out.println("\nError loading database.");
            }
        }
        
        try {
            fileScanner = new Scanner (new File ("./data/database.txt"));
        } catch (Exception e) {
            System.out.println("\nError loading database."); 
        }

        while (fileScanner.hasNext()) {
            usernameDatabase.add(fileScanner.next());
            passwordDatabase.add(fileScanner.next());
        }

        System.out.println(usernameDatabase.size() + "...Complete!");
        fileScanner.close();
        new RPGGame().StartScreen();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////STARTING YOUR GAME/////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String playerUsername; 

    public static String playerArmour; 
    public static int playerAttackLevel; 
    public static int playerDefenseLevel; 
    public static int playerExperience; 
    public static int playerHealth; 
    public static int playerLevel; 
    public static String playerLocation; 
    public static int playerMagicLevel; 
    public static String playerMagicSpell1; 
    public static String playerMagicSpell2; 
    public static String playerMagicSpell3; 
    public static String playerWeapon; 

    public static boolean currentlyFighting = false;
    public static String currentEnemyName; 
    public static int currentEnemyLevel; 
    public static int currentEnemyHealth = 999; 
    public static int currentEnemyAttack; 
    public static double currentEnemyAccuracy; 

    public static ArrayList<String> weaponNameDatabase = new ArrayList<String>();
    public static ArrayList<Integer> weaponDamageDatabase = new ArrayList<Integer>();
    public static ArrayList<Double> weaponCriticalDatabase = new ArrayList<Double>();
    public static ArrayList<Double> weaponAccuracyDatabase = new ArrayList<Double>();
    public static ArrayList<Integer> weaponRequisiteDatabase = new ArrayList<Integer>();

    public static ArrayList<String> locationNameDatabase = new ArrayList<String>(); 
    public static ArrayList<Double> locationSpawnRateDatabase = new ArrayList<Double>(); 

    public static ArrayList<String> enemyNameDatabase = new ArrayList<String>(); 
    public static ArrayList<Integer> enemyAttackDatabase = new ArrayList<Integer>(); 
    public static ArrayList<Integer> enemyHealthDatabase = new ArrayList<Integer>(); 
    public static ArrayList<Double> enemyAccuracyDatabase = new ArrayList<Double>(); 
    public static ArrayList<Integer> enemyLevelDatabase = new ArrayList<Integer>(); 
    public static ArrayList<Double> enemyEvasiveDatabase = new ArrayList<Double>(); 

    public static ArrayList<String> spellNameDatabase = new ArrayList<String>(); 
    public static ArrayList<Integer> spellTypeDatabase = new ArrayList<Integer>(); 
    public static ArrayList<Integer> spellMagnitudeDatabase = new ArrayList<Integer>(); 
    public static ArrayList<Double> spellCriticalDatabase = new ArrayList<Double>(); 
    public static ArrayList<Double> spellAccuracyDatabase = new ArrayList<Double>(); 
    public static ArrayList<Integer> spellRequisiteDatabase = new ArrayList<Integer>();

    public static ArrayList<Integer> experienceBoundries = new ArrayList<Integer>();

    public void LoadPlayerData(String username) {
        System.out.print("\nLoading " + username + "...");
        File x = new File ("./data/" + username + ".txt"); 

        if (!x.exists()) {
            try {
                writer = new FileWriter("./data/" + username + ".txt");
                writer.write(username + " null 1 1 0 20 1 null 1 null null null Hands");
                writer.close();
            } catch (Exception e) {
                System.out.println("\nError loading database.");
            }
        }
        
        try {
            fileScanner = new Scanner (new File ("./data/" + username + ".txt"));
        } catch (Exception e) {
            System.out.println("\nError loading database."); 
        }

        while (fileScanner.hasNext()) {
            playerUsername = fileScanner.next();
            playerArmour = fileScanner.next(); 
            playerAttackLevel = fileScanner.nextInt(); 
            playerDefenseLevel = fileScanner.nextInt();
            playerExperience = fileScanner.nextInt(); 
            playerHealth = fileScanner.nextInt();
            playerLevel = fileScanner.nextInt(); 
            String location = fileScanner.next(); 
            location = location.replace("-", " ");
            playerLocation = location; 
            playerMagicLevel = fileScanner.nextInt();
            playerMagicSpell1 = fileScanner.next(); 
            playerMagicSpell2 = fileScanner.next(); 
            playerMagicSpell3 = fileScanner.next(); 
            location = fileScanner.next();
            location = location.replace("-", " ");
            playerWeapon = location; 
        }

        if (weaponNameDatabase.size() == 0) {
            try {
                fileScanner = new Scanner (new File ("./data/weaponsdatabase.txt"));
            } catch (Exception e) {
                System.out.println("\nError loading weapons database."); 
            }
        }

        while (fileScanner.hasNext()) {
            String weaponName = fileScanner.next();
            weaponName = weaponName.replace("-", " ");
            weaponNameDatabase.add(weaponName);
            weaponDamageDatabase.add(fileScanner.nextInt());
            weaponCriticalDatabase.add(fileScanner.nextDouble());
            weaponAccuracyDatabase.add(fileScanner.nextDouble());
            weaponRequisiteDatabase.add(fileScanner.nextInt());
        }

        if (locationNameDatabase.size() == 0) {
            try {
                fileScanner = new Scanner (new File ("./data/locationdatabase.txt"));
            } catch (Exception e) {
                System.out.println("\nError loading location database."); 
            }
        }

        while (fileScanner.hasNext()) {
            locationNameDatabase.add(fileScanner.next());
            locationSpawnRateDatabase.add(fileScanner.nextDouble());
        }

        if (enemyNameDatabase.size() == 0) {
            try {
                fileScanner = new Scanner (new File ("./data/enemydatabase.txt"));
            } catch (Exception e) {
                System.out.println("\nError loading enemy database."); 
            }
        }

        while (fileScanner.hasNext()) {
            enemyNameDatabase.add(fileScanner.next());
            enemyLevelDatabase.add(fileScanner.nextInt());
            enemyAttackDatabase.add(fileScanner.nextInt());
            enemyHealthDatabase.add(fileScanner.nextInt());
            enemyAccuracyDatabase.add(fileScanner.nextDouble());
            enemyEvasiveDatabase.add(fileScanner.nextDouble());
        } 

        if (spellNameDatabase.size() == 0) {
            try {
                fileScanner = new Scanner (new File ("./data/spelldatabase.txt"));
            } catch (Exception e) {
                System.out.println("\nError loading enemy database."); 
            }
        }

        while (fileScanner.hasNext()) {
            spellNameDatabase.add(fileScanner.next());
            spellTypeDatabase.add(fileScanner.nextInt());
            spellMagnitudeDatabase.add(fileScanner.nextInt());
            spellCriticalDatabase.add(fileScanner.nextDouble());
            spellAccuracyDatabase.add(fileScanner.nextDouble());
            spellRequisiteDatabase.add(fileScanner.nextInt());
        } 

        if (experienceBoundries.size() == 0) {
            try {
                fileScanner = new Scanner (new File ("./data/experiencebound.txt"));
            } catch (Exception e) {
                System.out.println("\nError loading experience database."); 
            }
        }

        while (fileScanner.hasNext()) {
            experienceBoundries.add(fileScanner.nextInt());
        } 

        System.out.println("...Complete");
        fileScanner.close();
        new RPGGame().HomeScreen();
    }

    public void SavePlayerData() {
        File x = new File ("./data/" + playerUsername + ".txt"); 
        if (x.exists()) {
            try {
                playerLocation = playerLocation.replace(" ", "-");
                playerWeapon = playerWeapon.replace(" ", "-");
                playerMagicSpell1 = playerMagicSpell1.replace(" ", "-");
                playerMagicSpell1 = playerMagicSpell2.replace(" ", "-");
                playerMagicSpell1 = playerMagicSpell3.replace(" ", "-");
                writer = new FileWriter("./data/" + playerUsername + ".txt");
                writer.write(playerUsername + " " + playerArmour + " " + playerAttackLevel + " " + playerDefenseLevel 
                    + " " + playerExperience + " " + playerHealth + " " + playerLevel + " " + playerLocation 
                    + " " + playerMagicLevel + " " + playerMagicSpell1 + " " + playerMagicSpell2 
                    + " " + playerMagicSpell3 + " " + playerWeapon);
                    playerLocation = playerLocation.replace("-", " ");
                    playerWeapon = playerWeapon.replace("-", " ");
                    playerMagicSpell1 = playerMagicSpell1.replace("-", " ");
                    playerMagicSpell1 = playerMagicSpell2.replace("-", " ");
                    playerMagicSpell1 = playerMagicSpell3.replace("-", " ");
                writer.close();
            } catch (Exception e) {
                System.out.println("\nError loading database.");
            }
        } 
    }

    public void MovePlayer(Boolean first, Boolean message) {
        playerLocation = playerLocation.replaceAll("-", " ");
        if (message) {
            System.out.print("\nYou are in the " + playerLocation + ". ");
        }
        switch (playerLocation) {
            case "Telkyn Castle Streets": 
                new RPGGame().TelkynCastleStreets();
                break;
            case "Telkyn Hospital": 
                new RPGGame().TelkynHospital();
                break;
            case "Forest West Entrance": 
                new RPGGame().ForestWestEntrance(first);
                break;
            case "Forest West Side":
                new RPGGame().ForestWestSide(first);
                break;
            case "Forest Center": 
                new RPGGame().ForestCenter(first);
                break;
            case "Forest East Side": 
                new RPGGame().ForestEastSide(first);
                break;
            default: 
                System.out.println("\nError. Unavailable Option.");
                new RPGGame().HomeScreen();
                break;
        }
    }

    public void HomeScreen() {
        myScanner = new Scanner(System.in);
        int playerOption; 

        System.out.println("\nWhat would you like to do " + playerUsername + "?\n " +  
            "Begin Adventuring! (1)\n View databases (2)\n Log out(3)");
        playerOption = myScanner.nextInt(); 
        switch (playerOption) {
            case 1: 
                if (playerLocation.equals("null")) {
                    System.out.println("\n\n\nDoctor Luxen:\n     Ah! You're awake! Welcome to Telkyn, buddy! " 
                    + "I'm Doctor Luxen, and you're in quite the sticky situation, now aren't you!"); 
                    System.out.println("\n" + playerUsername + ":\n     I'm sorry, I think you have the wrong man..." 
                    + "to be honest, I don't even know how I got here."); 
                    System.out.println("\nDoctor Luxen:\n     Exactly, boy, neither do we. " 
                    + "You just showed up at our front doors last night asking for a sword."); 
                    System.out.println("\n\n **Your memories come flashing back**\n"); 
                    System.out.println("\n" + playerUsername + ":\n     Thyne! Dark lord Thyne! " 
                    + "He took everything from me and I must take everything from him."); 
                    System.out.println("\nDoctor Luxen:\n     Oh no...Nurses, he's just another man thinking he can "
                    + "kill the Dark lord. Check for brain damage, please."); 
                    System.out.println("\nNurse Lillie:\n     On it sir!"); 
                    System.out.println("\n\n **You frantically stand up**\n"); 
                    System.out.println("\n" + playerUsername + ":\n     I don't have any brain damage!"); 
                    System.out.println("\nDoctor Luxen:\n     That...is exactly what a man with brain damage says...");
                    System.out.println("\n" + playerUsername + ":\n     No, I'm fine! I'll be off now."); 
                    System.out.println("\nDoctor Luxen:\n     As you do. If you ever need wounds that need taking care of, " 
                    + "you know where to come, buddy.");
                    System.out.println("\n\n**You exit the Hospital**\n");
                    System.out.println("\n" + playerUsername + ":\n     I guess I'm on my own now.");
                    System.out.print("\nYou are in the Telkyn Castle Streets. ");
                    new RPGGame().TelkynCastleStreets();
                } else {
                    new RPGGame().MovePlayer(false, true);
                }
                break;
            case 2: 
                new RPGGame().ViewDatabaseScreen();
                break;
            case 3: 
                new RPGGame().SavePlayerData();
                new RPGGame().StartScreen();
                break;
            default: 
                System.out.println("\nError. Unavailbale Option."); 
                new RPGGame().HomeScreen();
                break;
        }
    }

    public void ViewDatabaseScreen() {
        myScanner = new Scanner(System.in);
        int playerOption; 

        System.out.println("\nWhat would you like to do?\n " + 
            "View player statistics (1)\n View weapon database (2)\n View armour database (3)\n " + 
            "View magic spell database (4)\n Head back (5)");
        playerOption = myScanner.nextInt(); 
        switch (playerOption) {
            case 1: 
                new RPGGame().ViewPlayerStatsScreen();
                new RPGGame().ViewDatabaseScreen();
                break; 
            case 2: 
                System.out.println("");
                for (String i : weaponNameDatabase) {
                    String message = "Name: " + i + ", Damage: " + weaponDamageDatabase.get(weaponNameDatabase.indexOf(i)) 
                    + ", Critical Hit Chance: " + (weaponCriticalDatabase.get(weaponNameDatabase.indexOf(i)) * 100) 
                    + "%, Accuracy: " + (weaponAccuracyDatabase.get(weaponNameDatabase.indexOf(i)) * 100);
                    System.out.println(message);
                }
                System.out.println("");
                new RPGGame().ViewDatabaseScreen();
                break;
            case 5: 
                new RPGGame().HomeScreen();
                break;
            default: 
                System.out.println("\nError. Unavailable Option.\n"); 
                new RPGGame().ViewDatabaseScreen();
                break;
        }
    }

    public void ViewPlayerStatsScreen() {
        String weaponString; 
        String armourString; 
        String magic1String; 
        String magic2String;
        String magic3String; 

        if (playerWeapon.equalsIgnoreCase("Hands")) {
            weaponString = "[Empty]";
        } else {
            playerWeapon = playerWeapon.replace("-", " ");
            weaponString = playerWeapon + ", Damage: " 
            + weaponDamageDatabase.get(weaponNameDatabase.indexOf(playerWeapon)) 
            + ", Critical Hit Chance: " + (weaponCriticalDatabase.get(weaponNameDatabase.indexOf(playerWeapon)) * 100) 
            + "%, Accuracy: " + (weaponAccuracyDatabase.get(weaponNameDatabase.indexOf(playerWeapon)) * 100);
            playerWeapon = playerWeapon.replace(" ", "-");
        }
        if (playerArmour.equalsIgnoreCase("null")) {
            armourString = "[Empty]";
        } else {
            armourString = "[Empty]";
        }

        System.out.println("CHECK1");

        if (playerMagicSpell1.equalsIgnoreCase("null")) {
            magic1String = "[Empty]";
        } else {
            String type; 
            if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) == 0) {
                type = ", Damage: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) 
                + ", Critical Chance: " + (spellCriticalDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) * 100);
            } else if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) == 1){
                type = ", Health Regained: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1));
            } else {
                type = ", Error: ";
            }
            magic1String = playerMagicSpell1.replaceAll("-", " ") + type 
            + ", Accuracy: " + (spellAccuracyDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) * 100) + "%";
        }

        System.out.println("CHECK2");

        if (playerMagicSpell2.equalsIgnoreCase("null")) {
            magic2String = "[Empty]";
        } else {
            String type; 
            if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) == 0) {
                type = ", Damage: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) 
                + ", Critical Chance: " + (spellCriticalDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) * 100);
            } else if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) == 1){
                type = ", Health Regained: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2));
            } else {
                type = ", Error: ";
            }
            magic2String = playerMagicSpell2.replaceAll("-", " ") + type 
            + ", Accuracy: " + (spellAccuracyDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) * 100) + "%";
        }

        System.out.println("CHECK3");

        if (playerMagicSpell3.equalsIgnoreCase("null")) {
            magic3String = "[Empty]";
        } else {
            String type; 
            if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) == 0) {
                type = ", Damage: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) 
                + ", Critical Chance: " + (spellCriticalDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) * 100);
            } else if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) == 1){
                type = ", Health Regained: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3));
            } else {
                type = ", Error: ";
            }
            magic3String = playerMagicSpell3.replaceAll("-", " ") + type 
            + ", Accuracy: " + (spellAccuracyDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) * 100) + "%";
        }
        String message = "\nUsername: " + playerUsername + "\nHealth: " + playerHealth 
            + "\nCurrent Level: " + playerLevel 
            + "\nCurrent Experience: " + playerExperience 
            + "\nAttack Level: " + playerAttackLevel 
            + "\nDefense Level: " + playerDefenseLevel 
            + "\nMagic Level: " + playerMagicLevel 
            + "\nEquipped Weapon: " + weaponString 
            + "\nEquipped Armour: " + armourString
            + "\nMagic Spell Slot 1: " + magic1String 
            + "\nMagic Spell Slot 2: " + magic2String 
            + "\nMagic Spell Slot 3: " + magic3String 
            + "\nCurrent Location: " + playerLocation; 
        message = message.replace("null", "[Empty]");
        message = message.replace("Hands", "[Empty]");
        System.out.println(message);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////LOCATIONS/////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void TelkynCastleStreets() {
        playerLocation = "Telkyn Castle Streets"; 
        myScanner = new Scanner(System.in);
        int playerOption; 

        new RPGGame().SavePlayerData();
        
        System.out.println("What would you like to do?\n " 
        + "Head to the Hospital (1)\n Head to the Telkyn Market Place (2)\n Head to the Forest (3)\n "
        + "View player statistics (4)\n Logout (5)");
        playerOption = myScanner.nextInt();
        switch (playerOption) {
            case 1: 
                System.out.print("\nYou are in the Telkyn Hospital. ");
                new RPGGame().TelkynHospital();
                break;
            case 3: 
                System.out.print("\nYou are in the Forest West Entrance. "); 
                new RPGGame().ForestWestEntrance(true);
                break;
            case 4: 
                new RPGGame().ViewPlayerStatsScreen();
                System.out.print("\nYou are in the " + playerLocation + ". ");
                new RPGGame().TelkynCastleStreets();
                break;
            case 5: 
                new RPGGame().SavePlayerData();
                new RPGGame().HomeScreen();
                break;
            default: 
                System.out.println("\nError. Unavailable Option.\n"); 
                System.out.print("\nYou are in the " + playerLocation + ". ");
                new RPGGame().TelkynCastleStreets();
                break;
        }
    }

    public void TelkynHospital() {
        playerLocation = "Telkyn Hospital"; 
        myScanner = new Scanner(System.in);
        int playerOption; 
        
        System.out.println("What would you like to do?\n " 
        + "Rest (1)\n Purchase medical supplies (2)\n Head to the Telkyn Castle Streets (3)\n "
        + "View player statistics (4)\n Logout (5)");
        playerOption = myScanner.nextInt();
        switch (playerOption) {
            case 1: 
                playerHealth = (playerLevel * 5) + 15;
                System.out.print("\nHealth has been restored to " + playerHealth + ". ");
                new RPGGame().SavePlayerData();
                new RPGGame().TelkynHospital();
            case 3: 
                System.out.print("\nYou are in the Telkyn Castle Streets. "); 
                new RPGGame().TelkynCastleStreets();
                break;
            case 4: 
                new RPGGame().ViewPlayerStatsScreen();
                System.out.print("\nYou are in the " + playerLocation + ". ");
                new RPGGame().TelkynHospital();
                break;
            case 5: 
                new RPGGame().SavePlayerData();
                new RPGGame().HomeScreen();
                break;
            default: 
                System.out.println("\nError. Unavailable Option.\n"); 
                System.out.print("\nYou are in the " + playerLocation + ". ");
                new RPGGame().TelkynHospital();
                break;
        }
    }

    public void ForestWestEntrance(boolean first) {
        playerLocation = "Forest West Entrance"; 
        myScanner = new Scanner(System.in);
        int playerOption; 

        if (Math.random() <= locationSpawnRateDatabase.get(locationNameDatabase.indexOf(playerLocation.replace(" ", "-"))) 
        && first == true || currentlyFighting == true) {
                if (currentEnemyHealth <= 0) {
                    currentlyFighting = false;
                    int experienceGained = (int)(Math.round(Math.random() * 10) * currentEnemyLevel); 
                    playerExperience += experienceGained;
                    System.out.println("\n\nYou have defeated the level " + currentEnemyLevel + " " + currentEnemyName 
                    + " and have gained " + experienceGained + " experience.");
                    currentEnemyHealth = 999; 
                    new RPGGame().GetLoot();
                    new RPGGame().LevelUp();
                    new RPGGame().MovePlayer(false, true);
                } else if (first) {
                    new RPGGame().SpawnEnemy();
                    System.out.print("You have encountered a level " + currentEnemyLevel + " " + currentEnemyName 
                    + " with " + currentEnemyHealth + " health. ");
                    first = false;
                    new RPGGame().Battle();
                } else {
                    new RPGGame().Battle();
                }
            } else {
            System.out.println("What would you like to do?\n " 
            + "Head to the Forest West Side (1)\n Head to the Telkyn Castle Streets (2)\n "
            + "View player statistics (3)");
            playerOption = myScanner.nextInt();
            switch (playerOption) {
                case 1: 
                    System.out.print("\nYou are in the Forest West Side. ");
                    new RPGGame().ForestWestSide(true);
                case 2: 
                    System.out.print("\nYou are in the Telkyn Castle Streets. "); 
                    new RPGGame().TelkynCastleStreets();
                    break;
                case 3: 
                    new RPGGame().ViewPlayerStatsScreen(); 
                    System.out.print("\nYou are in the " + playerLocation + ". ");
                    new RPGGame().ForestWestEntrance(false);
                    break;
                default: 
                    System.out.println("\nError. Unavailable Option.\n"); 
                    System.out.print("\nYou are in the " + playerLocation + ". ");
                    new RPGGame().ForestWestEntrance(false);
                    break;
            }
        }  
    }

    public void ForestWestSide(boolean first) {
        playerLocation = "Forest West Side"; 
        myScanner = new Scanner(System.in);
        int playerOption; 

        if (Math.random() <= locationSpawnRateDatabase.get(locationNameDatabase.indexOf(playerLocation.replace(" ", "-"))) 
        && first == true || currentlyFighting == true) {
                if (currentEnemyHealth <= 0) {
                    currentlyFighting = false;
                    int experienceGained = (int)(Math.round(Math.random() * 10) * currentEnemyLevel); 
                    playerExperience += experienceGained;
                    System.out.println("\n\nYou have defeated the level " + currentEnemyLevel + " " + currentEnemyName 
                    + " and have gained " + experienceGained + " experience.");
                    currentEnemyHealth = 999; 
                    new RPGGame().GetLoot();
                    new RPGGame().LevelUp();
                    new RPGGame().MovePlayer(false, true);
                } else if (first) {
                    new RPGGame().SpawnEnemy();
                    System.out.print("You have encountered a level " + currentEnemyLevel + " " + currentEnemyName 
                    + " with " + currentEnemyHealth + " health. ");
                    first = false;
                    new RPGGame().Battle();
                } else {
                    new RPGGame().Battle();
                }
            } else {
            System.out.println("What would you like to do?\n " 
            + "Head to the Forest Center (1)\n Head to the Forest West Entrance (2)\n "
            + "View player statistics (3)");
            playerOption = myScanner.nextInt();
            switch (playerOption) {
                case 1: 
                    System.out.print("\nYou are in the Forest Center. ");
                    new RPGGame().ForestCenter(true);
                    break;
                case 2: 
                    System.out.print("\nYou are in the Forest West Entrance. "); 
                    new RPGGame().ForestWestEntrance(true);
                    break;
                case 3: 
                    new RPGGame().ViewPlayerStatsScreen(); 
                    System.out.print("\nYou are in the " + playerLocation + ". ");
                    new RPGGame().ForestWestSide(false);
                    break;
                default: 
                    System.out.println("\nError. Unavailable Option.\n"); 
                    System.out.print("\nYou are in the " + playerLocation + ". ");
                    new RPGGame().ForestWestSide(false);
                    break;
            }
        }  
    }

    public void ForestCenter(boolean first) {
        playerLocation = "Forest Center"; 
        myScanner = new Scanner(System.in);
        int playerOption; 

        if (Math.random() <= locationSpawnRateDatabase.get(locationNameDatabase.indexOf(playerLocation.replace(" ", "-"))) 
        && first == true || currentlyFighting == true) {
                if (currentEnemyHealth <= 0) {
                    currentlyFighting = false;
                    int experienceGained = (int)(Math.round(Math.random() * 10) * currentEnemyLevel); 
                    playerExperience += experienceGained;
                    System.out.println("\n\nYou have defeated the level " + currentEnemyLevel + " " + currentEnemyName 
                    + " and have gained " + experienceGained + " experience.");
                    currentEnemyHealth = 999; 
                    new RPGGame().GetLoot();
                    new RPGGame().LevelUp();
                    new RPGGame().MovePlayer(false, true);
                } else if (first) {
                    new RPGGame().SpawnEnemy();
                    System.out.print("You have encountered a level " + currentEnemyLevel + " " + currentEnemyName 
                    + " with " + currentEnemyHealth + " health. ");
                    first = false;
                    new RPGGame().Battle();
                } else {
                    new RPGGame().Battle(); 
                }
            } else {
            System.out.println("What would you like to do?\n " 
            + "Head to the Forest East Side (1)\n Head to the Forest West Side (2)\n "
            + "View player statistics (3)");
            playerOption = myScanner.nextInt();
            switch (playerOption) {
                case 1: 
                    System.out.print("\nYou are in the Forest East Side. ");
                    new RPGGame().ForestEastSide(true);
                    break;
                case 2: 
                    System.out.print("\nYou are in the Forest West Side. "); 
                    new RPGGame().ForestWestSide(true);
                    break;
                case 3: 
                    new RPGGame().ViewPlayerStatsScreen(); 
                    System.out.print("\nYou are in the " + playerLocation + ". ");
                    new RPGGame().ForestCenter(false);
                    break;
                default: 
                    System.out.println("\nError. Unavailable Option.\n"); 
                    System.out.print("\nYou are in the " + playerLocation + ". ");
                    new RPGGame().ForestCenter(false);
                    break;
            }
        }  
    }

    public void ForestEastSide(boolean first) {
        playerLocation = "Forest East Side"; 
        myScanner = new Scanner(System.in);
        int playerOption; 

        if (Math.random() <= locationSpawnRateDatabase.get(locationNameDatabase.indexOf(playerLocation.replace(" ", "-"))) 
        && first == true || currentlyFighting == true) {
                if (currentEnemyHealth <= 0) {
                    currentlyFighting = false;
                    int experienceGained = (int)(Math.round(Math.random() * 10) * currentEnemyLevel); 
                    playerExperience += experienceGained;
                    System.out.println("\n\nYou have defeated the level " + currentEnemyLevel + " " + currentEnemyName 
                    + " and have gained " + experienceGained + " experience.");
                    currentEnemyHealth = 999; 
                    new RPGGame().GetLoot();
                    new RPGGame().LevelUp();
                    new RPGGame().MovePlayer(false, true);
                } else if (first) {
                    new RPGGame().SpawnEnemy();
                    System.out.print("You have encountered a level " + currentEnemyLevel + " " + currentEnemyName 
                    + " with " + currentEnemyHealth + " health. ");
                    first = false;
                    new RPGGame().Battle();
                } else {
                    new RPGGame().Battle();
                }
            } else {
            System.out.println("What would you like to do?\n " 
            + "Head to the Forest East Entrance (1)\n Head to the Forest Center (2)\n "
            + "View player statistics (3)");
            playerOption = myScanner.nextInt();
            switch (playerOption) {
                case 2: 
                    System.out.print("\nYou are in the Forest Center. "); 
                    new RPGGame().ForestCenter(true);
                    break;
                case 3: 
                    new RPGGame().ViewPlayerStatsScreen(); 
                    System.out.print("\nYou are in the " + playerLocation + ". ");
                    new RPGGame().ForestEastSide(false);
                    break;
                default: 
                    System.out.println("\nError. Unavailable Option.\n"); 
                    System.out.print("\nYou are in the " + playerLocation + ". ");
                    new RPGGame().ForestEastSide(false);
                    break;
            }
        }  
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////BATTLING//////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void SpawnEnemy() {
        double enemy = Math.random();
        int index; 
        switch (playerLocation) {
            case "Forest West Entrance": 
                if (enemy <= 0.15) {
                    index = 2;
                } else if (enemy <= 0.5) {
                    index = 1;
                } else {
                    index = 0;
                }
                break;
            case "Forest West Side": 
                if (enemy <= 0.1) {
                    index = 4; 
                } else if (enemy <= 0.25) {
                    index = 3; 
                } else if (enemy <= 0.80) {
                    index = 2;
                } else if (enemy <= 0.90) {
                    index = 1; 
                } else {
                    index = 0;
                }
                break;
            case "Forest Center": 
                if (enemy <= 0.1) {
                    index = 6; 
                } else if (enemy <= 0.25) {
                    index = 5; 
                } else if (enemy <= 0.80) {
                    index = 4;
                } else if (enemy <= 0.90) {
                    index = 3; 
                } else {
                    index = 2;
                }
                break;
            case "Forest East Side": 
                if (enemy <= 0.1) {
                    index = 8; 
                } else if (enemy <= 0.25) {
                    index = 7; 
                } else if (enemy <= 0.80) {
                    index = 6;
                } else if (enemy <= 0.90) {
                    index = 5; 
                } else {
                    index = 4;
                }
                break;
            case "Forest East Entrance": 
                if (enemy <= 0.40) {
                    index = 9;
                } else if (enemy <= 0.75) {
                    index = 8; 
                } else if (enemy <= 0.88) {
                    index = 7;
                } else {
                    index = 6; 
                }
                break;
            default: 
                index = 0;
                break;
        }

        currentEnemyName = enemyNameDatabase.get(index);
        currentEnemyLevel = enemyLevelDatabase.get(index);
        currentEnemyAttack = enemyAttackDatabase.get(index);
        currentEnemyHealth = enemyHealthDatabase.get(index);
        currentEnemyAccuracy = enemyAccuracyDatabase.get(index);
    }

    public void Battle() {
        myScanner = new Scanner(System.in);
        int playerOption;
        String weapon = playerWeapon.replace("-", " ");
        int weaponIndex = weaponNameDatabase.indexOf(weapon);

        boolean hasSpells = true;
        if (playerMagicSpell1.equals("null") && playerMagicSpell2.equals("null") && playerMagicSpell3.equals("null")) {
            hasSpells = false;
        } 

        currentlyFighting = true; 

        System.out.println("\n\nWhat would you like to do?\n " 
        + "Attack (1)\n Use Spells (2)\n Evade (3)\n "
        + "View player statistics (4)");
        playerOption = myScanner.nextInt();

        switch (playerOption) {
            case 1: 
                double hit = Math.random(); 
                if (hit <= weaponAccuracyDatabase.get(weaponIndex)) { 
                    int damageDealt = weaponDamageDatabase.get(weaponIndex);
                    if (Math.random() <= weaponCriticalDatabase.get(weaponIndex)) {
                        System.out.print("\nCritial hit! "); 
                        damageDealt += (int)(Math.ceil(weaponDamageDatabase.get(weaponIndex)/2));
                    } else {
                        System.out.print("\n");
                    }
                    currentEnemyHealth -= damageDealt;
                    System.out.print("Your attack hits and deals " + damageDealt + " damage. ");
                    if (currentEnemyHealth > 0) {
                        System.out.println("The level " + currentEnemyLevel + " " + currentEnemyName + " now has " 
                        + currentEnemyHealth + " health.\n ");
                    }
                } else {
                    System.out.println("\nYour attack misses.\n "); 
                }
                new RPGGame().EnemyBattle();
                break;
            case 2: 
                if (hasSpells) {
                    System.out.println("\nWhich spell would you like to use?\n " 
                    + playerMagicSpell1 + " (1)\n " + playerMagicSpell2 + " (2)\n "
                    + playerMagicSpell3 + " (3)\n Back (4)");
                    playerOption = myScanner.nextInt();
                    int spell = -1;
                    switch (playerOption) {
                        case 1: 
                            spell = spellNameDatabase.indexOf(playerMagicSpell1.replaceAll(" ", "-")); 
                            break; 
                        case 2: 
                            spell = spellNameDatabase.indexOf(playerMagicSpell2.replaceAll(" ", "-")); 
                            break; 
                        case 3: 
                            spell = spellNameDatabase.indexOf(playerMagicSpell3.replaceAll(" ", "-")); 
                            break;
                        default: 
                            System.out.println("\nError. Unavailable Option.\n"); 
                            new RPGGame().MovePlayer(true, false);
                            break;
                    }
                    int damageDealt = spellMagnitudeDatabase.get(spell); 
                            if (Math.random() <= spellCriticalDatabase.get(spell)) {
                                System.out.print("\nCritial hit! "); 
                                damageDealt += (int)(Math.ceil(spellMagnitudeDatabase.get(spell)));
                            } else {
                                System.out.print("\n");
                            }
                            if (Math.random() < spellAccuracyDatabase.get(spell)) {
                                if (spellTypeDatabase.get(spell) == 0) {
                                    System.out.print("You used " + spellNameDatabase.get(spell).replaceAll("-", " ") + " and dealt " 
                                    + damageDealt + " damage.");
                                    currentEnemyHealth -= damageDealt;
                                    if (currentEnemyHealth > 0) {
                                        System.out.println("The level " + currentEnemyLevel + " " 
                                        + currentEnemyName + " now has " + currentEnemyHealth + " health.\n ");
                                    }
                                } else if (spellTypeDatabase.get(spell) == 1){
                                    System.out.println("You used " + spellNameDatabase.get(spell).replaceAll("-", " ") + " and regained " 
                                    + damageDealt + " health.\n ");
                                    playerHealth += damageDealt;
                                    if (playerHealth > (playerHealth * 5) + 15) {
                                        playerHealth = (playerHealth * 5) + 15;
                                    }
                                }
                            } else {
                                System.out.println("Your spell fails.\n");
                            }
                    new RPGGame().EnemyBattle();
                } else {
                    System.out.print("\nYou have no spells equipped. ");
                    new RPGGame().Battle();
                }
                break;
            case 3: 
                if (Math.random() <= enemyEvasiveDatabase.get(enemyNameDatabase.indexOf(currentEnemyName))) {
                    System.out.println("\nYou have successfully evaded. ");
                    currentlyFighting = false;
                    new RPGGame().MovePlayer(false, true);
                } else {
                    System.out.print("\nYou try to evade but fail. "); 
                    new RPGGame().EnemyBattle();
                }
                break;
            case 4: 
                new RPGGame().ViewPlayerStatsScreen();
                new RPGGame().MovePlayer(false, false);
                break;
            default:  
                System.out.println("\nError. Unavailable Option.\n"); 
                new RPGGame().MovePlayer(true, false);
                break;
        }
    }

    public void EnemyBattle() {
        int enemyIndex = enemyNameDatabase.indexOf(currentEnemyName);

        if (currentEnemyHealth > 0) {
            if (Math.random() <= enemyAccuracyDatabase.get(enemyIndex)) {
                playerHealth -= enemyAttackDatabase.get(enemyIndex);
                System.out.print("The " + currentEnemyName + " attacks and deals " + enemyAttackDatabase.get(enemyIndex) + " damage. "); 
                if (playerHealth > 0) {
                    System.out.print("You now have " + playerHealth + " health. ");
                } else {
                    new RPGGame().CheckDeath();
                }
            } else {
                System.out.print("The " + currentEnemyName + " attacks but the attack misses.");
            }
        } 

        new RPGGame().MovePlayer(false, false);
    }

    public void CheckDeath() {
        if (playerHealth <= 0) {
            System.out.print("\n\nYou have died!\n\nYou wake up at the Telkyn Castle Hospital. ");
            playerWeapon = "Hands";
            playerArmour = "null";
            playerMagicSpell1 = "null";
            playerMagicSpell2 = "null";
            playerMagicSpell3 = "null";
            playerHealth = (playerLevel * 5) + 15;
            new RPGGame().SavePlayerData();
            new RPGGame().TelkynHospital();
        }
    }

    public void GetLoot() {
        int itemNumber = (int)(Math.ceil(Math.random() * currentEnemyLevel));
        if (itemNumber <= 0) {
            new RPGGame().GetLoot();
        }

        if (Math.random() <= (currentEnemyLevel/100) + 0.19) {
            int number = (int)(Math.ceil(Math.random() * 4));
            switch (number) {
                case 1: 
                    if (weaponRequisiteDatabase.get(itemNumber) <= playerAttackLevel) {
                        if (weaponNameDatabase.get(itemNumber).equals("Hands")) {
                            itemNumber++;
                        }
                        String name = weaponNameDatabase.get(itemNumber).replace("-", " ");
                        int currentIndex = weaponNameDatabase.indexOf(playerWeapon.replace("-", " ")); 
                        System.out.println(("\nYou found a " + name 
                        + ", Damage: "  + weaponDamageDatabase.get(itemNumber))  
                        + ", Critical Hit Chance: " + (weaponCriticalDatabase.get(itemNumber) * 100) 
                        + "%, Accuracy: " + (weaponAccuracyDatabase.get(itemNumber) * 100) 
                        + ".\nCurrently Equipped: " + playerWeapon 
                        + ", Damage: " + weaponDamageDatabase.get(currentIndex) 
                        + ", Critical Hit Chance: " + (weaponCriticalDatabase.get(currentIndex) * 100) 
                        + "%, Accuracy: " + (weaponAccuracyDatabase.get(currentIndex) * 100));
                        new RPGGame().TakeWeapon(itemNumber);
                    }
                    break;
                case 2: 
                    if (spellRequisiteDatabase.get(itemNumber) <= playerMagicLevel) {
                        String name = spellNameDatabase.get(itemNumber).replace("-", " ");
                        String foundMagicString;
                        String magic1String;
                        String magic2String;
                        String magic3String;
                        String type; 
                        if (spellTypeDatabase.get(itemNumber) == 0) {
                            type = " spell, Damage: " + spellMagnitudeDatabase.get(itemNumber) 
                            + ", Critical Chance: " + (spellCriticalDatabase.get(itemNumber) * 100);
                        } else if (spellTypeDatabase.get(itemNumber) == 1){
                            type = ", Health Regained: " + spellMagnitudeDatabase.get(itemNumber);
                        } else {
                            type = ", Error: ";
                        }
                        foundMagicString = name.replaceAll("-", " ") + type 
                        + ", Accuracy: " + (spellAccuracyDatabase.get(itemNumber) * 100) + "%";
                        if (playerMagicSpell1.equalsIgnoreCase("null")) {
                            magic1String = "[Empty]";
                        } else {
                            if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) == 0) {
                                type = ", Damage: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) 
                                + ", Critical Chance: " + (spellCriticalDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) * 100);
                            } else if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) == 1){
                                type = ", Health Regained: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1));
                            } else {
                                type = ", Error: ";
                            }
                            magic1String = playerMagicSpell1.replaceAll("-", " ") + type 
                            + ", Accuracy: " + (spellAccuracyDatabase.get(spellNameDatabase.indexOf(playerMagicSpell1)) * 100) + "%";
                        }
                        if (playerMagicSpell2.equalsIgnoreCase("null")) {
                            magic2String = "[Empty]";
                        } else {
                            if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) == 0) {
                                type = ", Damage: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) 
                                + ", Critical Chance: " + (spellCriticalDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) * 100);
                            } else if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) == 1){
                                type = ", Health Regained: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2));
                            } else {
                                type = ", Error: ";
                            }
                            magic2String = playerMagicSpell2.replaceAll("-", " ") + type 
                            + ", Accuracy: " + (spellAccuracyDatabase.get(spellNameDatabase.indexOf(playerMagicSpell2)) * 100) + "%";
                        }
                        if (playerMagicSpell3.equalsIgnoreCase("null")) {
                            magic3String = "[Empty]";
                        } else {
                            if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) == 0) {
                                type = ", Damage: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) 
                                + ", Critical Chance: " + (spellCriticalDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) * 100);
                            } else if (spellTypeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) == 1){
                                type = ", Health Regained: " + spellMagnitudeDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3));
                            } else {
                                type = ", Error: ";
                            }
                            magic3String = playerMagicSpell3.replaceAll("-", " ") + type 
                            + ", Accuracy: " + (spellAccuracyDatabase.get(spellNameDatabase.indexOf(playerMagicSpell3)) * 100) + "%";
                        }
                        System.out.println("\nYou found the " + foundMagicString
                        + "\nCurrent Magic Spell Slot 1: " + magic1String 
                        + "\nCurrent Magic Spell Slot 2: " + magic2String 
                        + "\nCurrent Magic Spell Slot 3: " + magic3String);
                        new RPGGame().TakeSpell(itemNumber);
                    }
                    break; 
                default:
                    break;  
            }
        }
    }

    public void TakeWeapon(int index) {
        myScanner = new Scanner(System.in);
        int playerOption; 
        
        System.out.println("\n\nWhat would you like to do?\n Equip it (1)\n Leave it (2)");
        playerOption = myScanner.nextInt();
        switch (playerOption) {
            case 1: 
                playerWeapon = weaponNameDatabase.get(index);
                playerWeapon = playerWeapon.replace("-", " ");
                System.out.println("\nYou have acquired a " + playerWeapon + "!");
                new RPGGame().MovePlayer(false, true);
                break; 
            case 2: 
                System.out.println("\nYou left the " + weaponNameDatabase.get(index) + ".");
                new RPGGame().MovePlayer(false, true);
                break; 
            default: 
                System.out.println("\nError. Unavailable Option."); 
                new RPGGame().TakeWeapon(index);
                break; 
        }
    }

    public void TakeArmour(int index) {

    }

    public void TakeSpell(int index) {
        myScanner = new Scanner(System.in);
        int playerOption; 

        System.out.println("\n\nWhat would you like to do?\n Equip it to slot 1 (1)\n Equip it to slot 2 (2)\n Equip it to slot 3 (3)\n Leave it (4)");
        playerOption = myScanner.nextInt();switch (playerOption) {
            case 1: 
                playerMagicSpell1 = spellNameDatabase.get(index);
                playerMagicSpell1 = playerMagicSpell1.replace("-", " ");
                System.out.println("\nYou have acquired the " + playerMagicSpell1 + " spell!");
                new RPGGame().MovePlayer(false, true);
                break; 
            case 2:
                playerMagicSpell2 = spellNameDatabase.get(index);
                playerMagicSpell2 = playerMagicSpell2.replace("-", " ");
                System.out.println("\nYou have acquired the " + playerMagicSpell2 + " spell!");
                new RPGGame().MovePlayer(false, true);
                break; 
            case 3:
                playerMagicSpell3 = spellNameDatabase.get(index);
                playerMagicSpell3 = playerMagicSpell3.replace("-", " ");
                System.out.println("\nYou have acquired the " + playerMagicSpell3 + " spell!");
                new RPGGame().MovePlayer(false, true);
                break; 
            case 4: 
                System.out.println("\nYou left the " + spellNameDatabase.get(index) + " spell.");
                new RPGGame().MovePlayer(false, true);
                break; 
            default: 
                System.out.println("\nError. Unavailable Option."); 
                new RPGGame().TakeWeapon(index);
                break; 
        }
    }

    public void LevelUp() {
        myScanner = new Scanner(System.in);
        int playerOption; 

        if (playerExperience >= experienceBoundries.get(playerLevel)) { 
            System.out.println("\nYou leveled up! You are now level " + (playerLevel + 1) + ".\n\n" 
            + "What would you like to upgrade?\n " + "Attack (1)\n Defense (2)\n Magic (3)");
            playerOption = myScanner.nextInt();
            switch (playerOption) {
                case 1: 
                    playerExperience -= experienceBoundries.get(playerLevel);
                    playerLevel++;
                    playerAttackLevel++;
                    System.out.println("\nYour attack level has increased to " + playerAttackLevel + "! ");
                    break;
                case 2: 
                    playerExperience -= experienceBoundries.get(playerLevel);
                    playerLevel++;
                    playerDefenseLevel++;
                    System.out.println("\nYour defense level has increased to " + playerDefenseLevel + "! ");
                    break;
                case 3: 
                    playerExperience -= experienceBoundries.get(playerLevel);
                    playerLevel++;
                    playerMagicLevel++;
                    System.out.println("\nYour magic level has increased to " + playerMagicLevel + "! ");
                    break;
                default: 
                    System.out.println("\nError. Unavailable Option.");
                    new RPGGame().LevelUp();
                    break;
            }
        }
    }

    public void ShutDown() {
        myScanner.close();
    }
}