package calendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dennis
 */
public class MyNote {
    
    private int day;
    private boolean active;
    private boolean hasSteps;
    private int[] categories;
    private int month;
    private int year;
    private String author;
    private String steps;
    
    public MyNote(int day, int month, int year, boolean active, String author){
        this.day = day;
        this.month = month + 1;
        this.year = year;
        this.active = active;
        this.author = author;
    }
    
    public int getNumber(){
        return day;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getYear(){
        return year;
    }
    
    public boolean getActivity(){
        return active;
    }

    public void addCategory(int i){
        
    }
    
    public int[] getCategories(){
        return categories;
    }
    public boolean hasNote() throws IOException{
        if (this.downloadText().equals(""))
            return false;
        else return true;
    }
    
    public void saveText(String text){
        File file = new File("accounts\\" + author + "\\" + day + "." + month + "." + year + ".txt");
        if(!file.exists())
            try {
                file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(MyNote.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FileWriter out = new FileWriter(file);
            out.write(text);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(MyNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String downloadText() throws FileNotFoundException, IOException{
        String text = "";
        File file = new File("accounts\\" + author + "\\" + day + "." + month + "." + year + ".txt");
        if(!file.exists())
            return "";
        else{
            BufferedReader in = new BufferedReader(new FileReader(file));
            String buffer = null;
            boolean check = true;
            while(check){
                buffer = in.readLine();
                if (buffer != null){
                    text += buffer + "\n";
                }
                else check = false;
            }
        }
        return text;
    }
    
    public void setSteps(String num){
        this.steps = num;
        this.hasSteps = true;
    }
    
    public boolean hasSteps() {
        return hasSteps;
    }
    
    public String getSteps(){
        return steps;
    }
}