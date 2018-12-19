package excepcions.ActivitatExceptions;

import excepcions.ActivitatExceptions.Control.OperacionsBanc;
import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;
import excepcions.ActivitatExceptions.Exceptions.ExceptionMessage;
import excepcions.ActivitatExceptions.Model.Client;
import excepcions.ActivitatExceptions.Model.CompteEstalvi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestor {
    List<CompteEstalvi> comptes = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void nuevaCuenta(){
        System.out.println("Intoduce tus datos para crear una cuenta");
        System.out.println("Nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Apellido:");
        String cognom = scanner.nextLine();
        System.out.println("DNI:");
        String DNI = scanner.nextLine();
        try {
            Client client = new Client(nombre,cognom,DNI);
            System.out.println("Dame el numero que deseas tener de cuenta");
            String numCuenta = scanner.nextLine();
            CompteEstalvi cuenta = new CompteEstalvi(numCuenta);
            cuenta.addUser(client);
            comptes.add(cuenta);
        } catch (ClientAccountException e) {
            System.out.println(e.getMessage());
        }

    }

    public CompteEstalvi verificarCuenta() throws BankAccountException {

        System.out.println("Introduce el numero de la cuenta");
        String numero = scanner.nextLine();
        for (int i = 0; i <comptes.size() ; i++) {
            if (comptes.get(i).getNumCompte().equals(numero)){
                return comptes.get(i);
            }
        }
        throw new BankAccountException(ExceptionMessage.ACCOUNT_NOT_FOUND);
    }

    public void ingreso(CompteEstalvi compte){
        System.out.println("Introduce la cantidad de dinero que quieres ingresar");
        double ing = scanner.nextDouble();
        scanner.nextLine();
        compte.ingressar(ing);
    }

    public void retirada(CompteEstalvi compte){
        System.out.println("Introduce la cantidad de dinero que quieres retirar");
        double ret = scanner.nextDouble();
        scanner.nextLine();
        try {
            compte.treure(ret);
        } catch (BankAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    public void añadirpersona(CompteEstalvi compte){
        System.out.println("Por favor introduce los datos de la persona que quieres añadir");
        System.out.println();
        System.out.println("Intoduce tus datos para crear una cuenta");
        System.out.println("Nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Apellido:");
        String cognom = scanner.nextLine();
        System.out.println("DNI:");
        String DNI = scanner.nextLine();
        try {
            Client nclient = new Client(nombre, cognom, DNI);
            compte.addUser(nclient);
        } catch (ClientAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarpersona(CompteEstalvi compte){
        System.out.println("Estas a punto de borrar a una persona de la cuenta, si eres el unico\n" +
                "usuario de la misma no podras borrarte\n");
        scanner.nextLine();
        System.out.println("Introduce el DNI de la persona que quieres borrar");
        String DNI = scanner.nextLine();
        try {
            if(!OperacionsBanc.verifyDNI(DNI)) {
                throw new ClientAccountException(ExceptionMessage.WRONG_DNI);
            } else {
                compte.removeUser(DNI);
            }
        } catch (ClientAccountException e) {
            System.out.println(e.getMessage());
        } catch (BankAccountException e) {
            System.out.println(e.getMessage());
        }

    }

    public void tranferir(CompteEstalvi compte){
        System.out.println("Intoduce la cuenta a la que quieres igresar");
        try {
           CompteEstalvi compted = verificarCuenta();
            System.out.println("Introduce la cantidad que quieres transferir");
            double cantidad = scanner.nextInt();
            scanner.nextLine();
           Client client=null;
           client.transferencia(compte, compted, cantidad);
        } catch (BankAccountException e) {
            System.out.println(e.getMessage());
            e=new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
            System.out.println(e.getMessage());
        }


    }

}
