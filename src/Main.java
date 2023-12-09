import java.lang.reflect.Field;
import java.util.*;
class Monster {
    String eyeColor;
    String featherColor;
    String magicalAbilities;
    String size;
    String strength;
    String durability;
    String weakness;
    String aggressionLevel;

    Monster() {
    }

    // Constructor to initialize Monster with user input
    public Monster(String eyeColor, String featherColor, String magicalAbilities, String size, String strength, String durability, String weakness, String aggressionLevel) {
        this.eyeColor = eyeColor;
        this.featherColor = featherColor;
        this.magicalAbilities = magicalAbilities;
        this.size = size;
        this.strength = strength;
        this.durability = durability;
        this.weakness = weakness;
        this.aggressionLevel = aggressionLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return Objects.equals(eyeColor, monster.eyeColor) && Objects.equals(featherColor, monster.featherColor) && Objects.equals(magicalAbilities, monster.magicalAbilities) && Objects.equals(size, monster.size) && Objects.equals(strength, monster.strength) && Objects.equals(durability, monster.durability) && Objects.equals(weakness, monster.weakness) && Objects.equals(aggressionLevel, monster.aggressionLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eyeColor, featherColor, magicalAbilities, size, strength, durability, weakness, aggressionLevel);
    }

    @Override
    public String toString() {
        return "{" +
                "eyeColor='" + eyeColor + '\'' +
                ", featherColor='" + featherColor + '\'' +
                ", magicalAbilities='" + magicalAbilities + '\'' +
                ", size='" + size + '\'' +
                ", strength='" + strength + '\'' +
                ", durability='" + durability + '\'' +
                ", weakness='" + weakness + '\'' +
                ", aggressionLevel='" + aggressionLevel + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Want to create a Monster:");
        Set<Monster> monsterSet = new LinkedHashSet<>();
        Set<Monster> babyMonsterSet = new LinkedHashSet<>();

        String response = sc.nextLine();
        Monster monster = null;

        if (response.equalsIgnoreCase("yes")) {
            monster = createMonster(sc);
        }else{
            System.out.println("Ok, bye!");
            System.exit(0);
        }

        if(monsterSet.contains(monster)) {
            System.out.println("Two Monsters cannot be equal!");
            System.exit(0);
        }else{
            monsterSet.add(monster);
        }

        while(true)
        {
            System.out.println("Want to create another Monster:");
            response = sc.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                monster = createMonster(sc);
            }else{
                System.out.println("Ok, bye!");
                break;
            }

            if(monsterSet.contains(monster)) {
                System.out.println("Two Monsters cannot be equal!");
                System.exit(0);
            }else{
                for(Monster m : monsterSet) {
                    Monster baby = createBabyMonster(m, monster);

                    if (!babyMonsterSet.contains(baby) && !monsterSet.contains(baby)) {
                        System.out.println("Baby Monster created successfully!");
                    }else{
                        System.out.println("Oops! Similar Baby Monster created!");
                    }
                    babyMonsterSet.add(baby);
                }

                monsterSet.add(monster);
            }
        }

        System.out.println("The Monsters are:");

        for(int i=1;i<=monsterSet.size();i++){
            System.out.println("Monster "+i+": "+monsterSet.toArray()[i-1]);
        }

        if(babyMonsterSet.size()>0)
        {
            System.out.println("The Baby Monsters are:");

            for(int i=1;i<=babyMonsterSet.size();i++){
                System.out.println("Baby Monster "+i+": "+babyMonsterSet.toArray()[i-1]);
            }
        }
    }

    // Method to create a baby Monster from two parents
    static Monster createBabyMonster(Monster parent1, Monster parent2) throws Exception {
        Monster baby = new Monster();
        // Loading the class
        Class c = Class.forName("Monster");
        //  Get all the declared fields of the Monster class using the getDeclaredFields() method. The fields are stored in an array.
        Field[] fields = c.getDeclaredFields();
        Random random = new Random();

        for (Field field : fields) {
            // Making the current field accessible, even if it's private. This is necessary to read and write the field's value.
            field.setAccessible(true);
            // Generating a random boolean. If it's true, the field will be copied from parent1. If it's false, the trait will be copied from parent2
            if (random.nextBoolean()) {
                // Set the value of the current field in the baby object to the value of the same field in parent1.
                field.set(baby, field.get(parent1));
            } else {
                // Set the value of the current field in the baby object to the value of the same field in parent2.
                field.set(baby, field.get(parent2));
            }
        }
        return baby;
    }

    static Monster createMonster(Scanner sc){

        System.out.println("Enter eye color (Brown | Blue | Yellow):");
        String eyeColor = sc.nextLine();
        // If eye color is not Brown or Blue, print error message and exit
        if (!eyeColor.equalsIgnoreCase("Brown") && !eyeColor.equalsIgnoreCase("Blue") && !eyeColor.equalsIgnoreCase("Yellow")) {
            System.out.println("Eye color must be Brown or Blue or Yellow!");
            System.exit(0);
        }

        System.out.println("Enter feather color (Black | Brown | White):");
        String featherColor = sc.nextLine();
        // If feather color is not Black or Brown or White, print error message and exit
        if (!featherColor.equalsIgnoreCase("Black") && !featherColor.equalsIgnoreCase("Brown") && !featherColor.equalsIgnoreCase("White")) {
            System.out.println("Feather color must be Black or Brown or White!");
            System.exit(0);
        }

        System.out.println("Enter magical abilities (Flight | SuperStrength | Invisibility):");
        String magicalAbilities = sc.nextLine();
        // If magical abilities is not Flight or SuperStrength or Invisibility, print error message and exit
        if (!magicalAbilities.equalsIgnoreCase("Flight") && !magicalAbilities.equalsIgnoreCase("SuperStrength") && !magicalAbilities.equalsIgnoreCase("Invisibility")) {
            System.out.println("Magical abilities must be Flight or SuperStrength or Invisibility!");
            System.exit(0);
        }

        System.out.println("Enter size (Small | Medium | Large):");
        String size = sc.nextLine();
        // If size is not Small or Medium or Large, print error message and exit
        if (!size.equalsIgnoreCase("Small") && !size.equalsIgnoreCase("Medium") && !size.equalsIgnoreCase("Large")) {
            System.out.println("Size must be Small or Medium or Large!");
            System.exit(0);
        }

        System.out.println("Enter strength (Weak | Medium | Strong):");
        String strength = sc.nextLine();
        // If strength is not Weak or Medium or Strong, print error message and exit
        if (!strength.equalsIgnoreCase("Weak") && !strength.equalsIgnoreCase("Medium") && !strength.equalsIgnoreCase("Strong")) {
            System.out.println("Strength must be Weak or Medium or Strong!");
            System.exit(0);
        }

        System.out.println("Enter durability (Low | Medium | High):");
        String durability = sc.nextLine();
        // If durability is not Low or Medium or High, print error message and exit
        if (!durability.equalsIgnoreCase("Low") && !durability.equalsIgnoreCase("Medium") && !durability.equalsIgnoreCase("High")) {
            System.out.println("Durability must be Low or Medium or High!");
            System.exit(0);
        }

        System.out.println("Enter weakness (Fire | Water | Spells):");
        String weakness = sc.nextLine();
        // If weakness is not Fire or Water or Spells, print error message and exit
        if (!weakness.equalsIgnoreCase("Fire") && !weakness.equalsIgnoreCase("Water") && !weakness.equalsIgnoreCase("Spells")) {
            System.out.println("Weakness must be Fire or Water or Spells!");
            System.exit(0);
        }

        System.out.println("Enter aggression level (Low | Medium | High):");
        String aggressionLevel = sc.nextLine();
        // If aggression level is not Low or Medium or High, print error message and exit
        if (!aggressionLevel.equalsIgnoreCase("Low") && !aggressionLevel.equalsIgnoreCase("Medium") && !aggressionLevel.equalsIgnoreCase("High")) {
            System.out.println("Aggression level must be Low or Medium or High!");
            System.exit(0);
        }

        Monster monster = new Monster(eyeColor, featherColor, magicalAbilities, size, strength, durability, weakness, aggressionLevel);
        System.out.println("Monster created!");

        return monster;
    }
}