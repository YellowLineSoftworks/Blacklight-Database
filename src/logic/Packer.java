package logic;
    
import display.MainGUI;
import display.NewForm;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
    
public class Packer {
    //Serialized code:
    
    public static void pack(Database db) {
    
        try {
            FileOutputStream fileOut = new FileOutputStream(db.name + ".bdb");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(db);
            out.close();
            fileOut.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
    
    }
    
    public static void unpack (File f) {
    
      Database db = null;
      try {
         FileInputStream fileIn = new FileInputStream(f);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         db = (Database)in.readObject();
         in.close();
         fileIn.close();
      } catch(IOException i) {
         i.printStackTrace();
         return;
      } catch(ClassNotFoundException c) {
         System.out.println("Class not found.");
         c.printStackTrace();
         return;
      }
      if (db != null) {
        if (NewForm.isFirst) {
            MainGUI.selectedDatabase = db;
            NewForm.isFirst = false;
        }
        Database.opendatabases.add(db);
        MainGUI.choice1.add(db.name);
        MainGUI.jButton6.setEnabled(true);
        for (int x = 0; x < db.people.size(); x++) {
        Person currentPerson = db.people.get(x);
        int counter = 0;
        while (counter < Database.opendatabases.size()) {
            if (Database.opendatabases.get(counter) == db) {
                MainGUI.model.addElement(currentPerson.name);
            }
            counter++;
        }
        }
      }
      
    }
    
    /* Nonserialized code:
    public static Writer writer;
    
    public static void pack(Database db) {
        System.out.println("Packing...");
        File a = db.database;
        String b = System.getProperty("user.dir") + "\\" + a.getPath();
        a.delete();
        File d = new File(b);
        try {
            writer = new BufferedWriter(new FileWriter(d));
            writer.write("Blacklight Database Standard Format: Created with BDB Alpha 1.0.  BDB © Yellow Line Softworks, 2012 - 2013\na1fstrt:");
        } catch (Exception e) {
            System.err.println("Failed to initialize PrintWriter to output file");
        }
        db.database = d;
        int counter = 0;
        while (counter < db.people.size()) {
        System.out.println("Packing person: " + db.people.get(counter).name);
        Person r = db.people.get(counter);
        try {
        writer.write("###" + r.name);
        writer.write(".^" + r.gender);
        writer.write(".^" + r.occupation);
        writer.write(".^" + r.phonenumber);
        writer.write(".^" + r.email);
        writer.write(".^" + r.workplaceorschool);
        writer.write(".^" + r.address1);
        writer.write(".^" + r.address2);
        writer.write(".^" + r.address3);
        if (r.pictureFile != null) {
            writer.write(".^" + r.pictureFile.getPath().substring(r.pictureFile.getPath().lastIndexOf("\\") + 1, r.pictureFile.getPath().length()));
        }
        writer.write(".^%&<>:]^");
        if (r.pictureFile != null) {
            BufferedReader read = new BufferedReader(new FileReader(r.pictureFile));
            String readLine = read.readLine();
            while (readLine != null) {
                writer.write(readLine);
                readLine = read.readLine();
            }
            read.close();
        }
        int counter2 = 0;
        while (counter2 < r.fields1.size()) {
            writer.write(".^" + r.fields1.get(counter2) + ".^" + r.fields2.get(counter2));
            counter2++;
        }
        writer.write("&&&");
        counter++;
        } catch (Exception e) {
            System.err.println("Could not write to file!" + "\nMessage: " + e.getMessage());
        }
        System.out.println("Packed!");
        }
        try {
            writer.close();
        } catch(Exception e) {
            System.err.println("Failed to close writer!");
        }
    }
    
    public static void unpack(File f) {
        
        String fileName = f.getName().substring(0, f.getName().lastIndexOf(".bdb"));
        Database db = new Database(fileName);
        try{
        System.out.println("Unpacking file...");
        String fileText = "";
        BufferedReader read = new BufferedReader(new FileReader(f));
        String readLine = read.readLine();
        while (readLine != null) {
            fileText += readLine;
            readLine = read.readLine();
        }
        int curIndex = 0;
        if(fileText.indexOf("a1fstrt:", curIndex) != -1) {
        while (fileText.indexOf("###", curIndex) != -1 && fileText.indexOf("###", curIndex) > curIndex) {
        Person currentPerson = new Person();                                                                                                                                            
        curIndex = fileText.indexOf("###", curIndex) + 3;
        int end = fileText.indexOf(".^", curIndex);
        currentPerson.name = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        String genderStr = fileText.substring(curIndex, end);
        currentPerson.gender = true;
        if (genderStr.equals("male")) { currentPerson.gender = false; }
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        currentPerson.occupation = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        currentPerson.phonenumber = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        currentPerson.email = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        currentPerson.workplaceorschool = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        currentPerson.address1 = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        currentPerson.address2 = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        currentPerson.address3 = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^", curIndex) + 2;
        end = fileText.indexOf(".^", curIndex);
        String imageFileName = fileText.substring(curIndex, end);
        curIndex = fileText.indexOf(".^%&<>:]^", curIndex) + 2;
        boolean fieldsPresent = false;
        if (fileText.indexOf(".^", curIndex) < fileText.indexOf("&&&") && fileText.indexOf(".^", curIndex) != 0) {
            end = fileText.indexOf("&&&", curIndex);
        } else {
            end = fileText.indexOf(".^", curIndex);
            fieldsPresent = true;
        }
        File tmpImageLoader = new File(imageFileName);
        BufferedWriter imageWriter = new BufferedWriter(new FileWriter(tmpImageLoader));
        imageWriter.write(fileText.substring(curIndex, end));
        imageWriter.close();
        currentPerson.picture = new ImageIcon(tmpImageLoader.getPath()).getImage();
        tmpImageLoader.delete();
        if (NewForm.isFirst) {
            MainGUI.selectedDatabase = db;
            NewForm.isFirst = false;
        }
        MainGUI.choice1.add(fileName);
        MainGUI.jButton6.setEnabled(true);
        if (fieldsPresent) {
            //Add fields loading code here, once the feature is integrated
        }
        int counter = 0;
        while (counter < Database.opendatabases.size()) {
            if (Database.opendatabases.get(counter) == db) {
                MainGUI.model.addElement(currentPerson.name);
                Database.opendatabases.get(counter).people.add(currentPerson);
            }
            counter++;
        }
        }
        }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    */
}