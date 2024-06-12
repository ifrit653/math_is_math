import java.util.Scanner;
public class RacineCubique {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double number = 0;
        double precision = 0;
        boolean inputValid = false;
        while(!inputValid){
            try{
                System.out.println("Veuillez entrer le nombre dont il faut calculer sa racine cubique: ");
                number = Double.parseDouble(scanner.nextLine());
                inputValid = true;
            }catch (NumberFormatException e){
                System.out.println("Entree invalide, Veuillez entrer un nombre valide");
            }
        }
        inputValid = false;
        while(!inputValid){
            try{
                System.out.println("Veuillez entrez un precision souhaiter (ex: 0.0001): ");
                precision = Double.parseDouble(scanner.nextLine());
                inputValid = true;
            }catch (NumberFormatException e){
                System.out.println("Entree invalide, Veuillez entrer une precision valide");
            }
        }
        scanner.close();
        double initialEstimation = getInitialEstimation(number);
        double root = calcRacine(precision, initialEstimation, number);
        double root2 = calcRacineTvi(number, precision);
        System.out.println("la racine cubique de "+ number + " est " + root +" en calculant avec la methode de Newton");
        System.out.println("la racine cubique de "+ number + " est " + root2 +" en calculant avec la methode de valeur intermediaire");
    }
    //fonction pour obtenir la valeur absolue d'un nombre
    public static double toAbs(double x){
        return (x < 0) ? -x : x;
    }
    //fonction pour obtenir l'estimation initial adequate
    public static double getInitialEstimation(double x){
        if(x > 0){
            double temp = x/3.0;
            return x/3.0;
        }
        else
        {
            double temp = x/3.0;
            return -toAbs(temp);
        }
    }
    //fonction pour calculer la racine cubique d'un nombre en utilisant l'algorithme de Newton implementer en tant que fonction recursive qui ittere jusqu'a obtenir une valeur qui se rapporche de la racine exacte
    public static double calcRacine(double precision, double estimation, double number){
        double nextEstimation = estimation - (((estimation * estimation * estimation) - number)/ (3 * (estimation * estimation)));
        double temp = toAbs(estimation - nextEstimation);
        if( temp < precision){
            return nextEstimation;
        }
        return calcRacine(precision, nextEstimation, number);
    }
    // fonction pour calculer la racine cubique
    public static double calcRacineTvi(double number, double precision){
        double a,b;
        if(number < 0){
            a = number;
            b = 0;
        }else{
            a = 0;
            b = number;
        }
        double c = 0;
        while((b-a) > precision){
            c = (a+b)/2.0;
            double temp = c*c*c;
            if(temp == number){
                return c;
            }else if(temp < number){
                a = c;
            }else{
                b = c;
            }
        }
        return c;
    }
}
