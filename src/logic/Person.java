package logic;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Person implements Serializable {
    
    public ImageIcon picture = null;
    public File pictureFile = null;
    public String name = "";
    public boolean gender = true;
    //Males are true. Women are false.
    public String occupation = "";
    public String workplaceorschool = "";
    public String phonenumber = "";
    public String email = "";
    public String address1 = "";
    public String address2 = "";
    public String address3 = "";
    public List<Field> fields = new ArrayList<>();
        
}