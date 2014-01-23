package logic;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    
    public String header;
    public List<Person> people;
    public File database;
    public String name;
    public static List<Database> opendatabases = new ArrayList<>();
    
    public Database(String namez) {
        name = namez;
        header = "BDBv1.0Alpha";
        database = new File(namez+".bdb");
        people = new ArrayList<>();
        opendatabases.add(this);
    }
    
    public Database(File file) {
        database = file;
        header = "BDBv1.0Alpha";  
        opendatabases.add(this);
    }
    
    public static Person findPersonAllDBS(String name) {
        int counter1 = 0;
        Person finalResult = null;
        while (counter1 < opendatabases.size()) {
            int counter2 = 0;
            Database temp = opendatabases.get(counter1);
            while (counter2 < temp.people.size()) {
                if (name.equals(temp.people.get(counter2).name)) {
                    finalResult = temp.people.get(counter2);
                }
                counter2++;
            }
            counter1++;
        }
        return finalResult;
    }
    
    public static Person findPerson(String name, Database db) {
        Person finalResult = null;
        int counter2 = 0;
        while (counter2 < db.people.size()) {
            if (name.equals(db.people.get(counter2).name)) {
                finalResult = db.people.get(counter2);
            }
            counter2++;
        }
        return finalResult;
    }
    
    public Person findPerson(String name) {
        Person finalResult = null;
        int counter2 = 0;
        while (counter2 < people.size()) {
            if (name.equals(people.get(counter2).name)) {
                finalResult = people.get(counter2);
            }
            counter2++;
        }
        return finalResult;
    }
    
}