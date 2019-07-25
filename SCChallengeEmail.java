//InputOutput library
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Net library
import java.net.URL;

//Util library

import javax.sound.sampled.Line;
import java.util.Scanner;

public class SCChallengeEmail{

    public static Scanner sc = new Scanner(System.in);
    public static final String BASEURL = "https://www.ecs.soton.ac.uk/people/";

    public static String username = "";
    public static String name = "";
    public static String telephone = "";
    public static String email = "@ecs.soton.ac.uk";

    public static final String IMP = "&amp;title=";
    public static void main (String[] args){

        System.out.println("Enter the ECS staff's username below:");
        System.out.println("Type \"quit\" to exit!\n");

        System.out.println("Username:");

        String username = sc.nextLine();

        String finalURL = BASEURL + username;

        if (username.equals("quit")){

            System.out.println("The program will now quit");

        }else{

            URLReader(finalURL);

            if (name.equals("") && telephone.equals("")){
                System.out.println("The details you entered were invalid. The program will now quit.");
            } else{
                System.out.println("Name: " + name);
                System.out.println("Telephone: " + telephone);
                System.out.println("Email: " + username + email);
            }
        }
    }

    public static void URLReader (String finalURL){
        try {
            URL myURL = new URL(finalURL);

            BufferedReader br  = new BufferedReader(new InputStreamReader(myURL.openStream()));

            String line;

            while ((line = br.readLine()) != null){
                line = br.readLine();

                if (line.contains(finalURL + IMP)){

                    String url = myURL + IMP;
                    line = line.toString();
                    line = line.split(url)[1];

                    for (int i = 0; line.charAt(i) != '\"'; i++) {
                        name += line.charAt(i);
                    }
                    break;
                }

                if (line.contains("tel:")){
                    String tel = line.split("tel:")[1];

                    for (int i=0; i<13; i++){
                        telephone += tel.charAt(i);
                    }
                }

            }
            
            br.close();
            
            System.out.println("");
            

        } catch(Exception e){
            System.out.println("Could not access website" + e.getMessage());
        }
    }
}
