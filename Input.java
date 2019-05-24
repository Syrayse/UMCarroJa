import static java.lang.System.in;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.*;

public class Input
{
    public static String leString() {
        Scanner input = new Scanner(in);
        boolean ok = false; 
        String txt = "";
        while(!ok) {
            try {
                txt = input.nextLine();
                ok = true;
            }
            catch(InputMismatchException e) 
                { 
                    input.nextLine(); 
                }
        }

        return txt;
    }

    public static int leInt() {
        Scanner input = new Scanner(in);
        boolean ok = false; 
        int i = 0;
        while(!ok) {
            try {
                i = input.nextInt();
                ok = true;
            }
            catch(InputMismatchException e) 
                { 
                    input.nextLine(); 
                }
        }

        return i;
    }
    
    public static double leDouble() {
        Scanner input = new Scanner(in);
        boolean ok = false; 
        double d = 0.0;
        while(!ok) {
            try {
                d = input.nextDouble();
                ok = true;
            }
            catch(InputMismatchException e) 
                { 
                    input.nextLine(); 
                }
        }

        return d;
    }
    
    public static LocalDate leData() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        String line = "";
        LocalDate data = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        while(!ok) {
            try {
                line = input.nextLine();
                data = LocalDate.parse(line, formatter);
                ok = true;
            } catch (DateTimeParseException exc) {
                input.nextLine();
            }
        }
        
        return data;
    }
}
