package excepcions.ActivitatExceptions;

import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Model.CompteEstalvi;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Gestor g = new Gestor();
        int opcion = 5;
        int opcion2 = 5;

        while (opcion != 0) {
            System.out.println("Bienvenido al banco");
            System.out.println("-------------------");
            System.out.println(" ¿Que desea hacer?\n");
            System.out.println("1.- Añadir una cuenta nueva");
            System.out.println("2.- Acceder a una cuenta\n\n");
            System.out.println("0.- Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    g.nuevaCuenta();
                    break;
                case 2:
                    try {
                       CompteEstalvi cuenta= g.verificarCuenta();

                       if (cuenta!=null) {
                           while (opcion2 != 0) {
                               System.out.println(" Has accedido a la cuenta " + cuenta.getNumCompte());
                               System.out.println(" ¿Que desea hacer?\n");
                               System.out.println("1.- Ingresar Dinero");
                               System.out.println("2.- Retirar Dinero");
                               System.out.println("3.- Añadir una persona a la cuenta");
                               System.out.println("4.- Eliminar una persona a la cuenta");
                               System.out.println("5.- Transferencia a otra cuenta\n\n");
                               System.out.println("0.- Salir");
                               opcion2 = scanner.nextInt();
                               switch (opcion2) {
                                   case 1:
                                       g.ingreso(cuenta);
                                       break;
                                   case 2:
                                       g.retirada(cuenta);
                                       break;
                                   case 3:
                                       g.añadirpersona(cuenta);
                                       break;
                                   case 4:
                                       g.borrarpersona(cuenta);
                                       break;
                                   case 5:
                                       g.tranferir(cuenta);
                                       break;
                                   case 0:
                                       System.out.println("Saliendo de la cuenta");
                                       break;
                               }
                           }
                       }
                    } catch (BankAccountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del banco\n\n ¡Hasta la vista!");
                    break;
            }


        }
    }
}
