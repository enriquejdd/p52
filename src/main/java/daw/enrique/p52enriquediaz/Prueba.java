/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.enrique.p52enriquediaz;

/**
 *
 * @author enrique
 */
public class Prueba {

    public static void main(String[] args) {

// la instancia easydrive de la clase EmpresaAlquilerVehiculos
        EmpresaAlquilerVehiculos easydrive = new EmpresaAlquilerVehiculos("A-28-187189", "easy drive", "www.easydrive.com");

// registro de los clientes de la empresa
        Cliente c1 = new Cliente("X5618927C", "Juan", "González López");
        easydrive.registrarCliente(c1);
        Cliente c2 = new Cliente("Z7568991Y", "Luis", "Fernández Gómez");
        easydrive.registrarCliente(c2);
// registro de los vehículos de la empresa
        Vehiculo v1 = new Vehiculo("4060 TUR", "Skoda", "Fabia", "Blanco", 90.0, false);
        Vehiculo v2 = new Vehiculo("4070 DEP", "Ford", "Mustang", "Rojo", 150.0, true);
        Vehiculo v3 = new Vehiculo("4080 TUR", "VW", "GTI", "Azul", 110.0, false);
        Vehiculo v4 = new Vehiculo("4090 TUR", "SEAT", "Ibiza", "Blanco", 90.0, false);
        Vehiculo v5 = new Vehiculo("4100 FUR", "Fiat", "Ducato", "Azul", 80.0, true);
        easydrive.registrarVehiculo(v1);
        easydrive.registrarVehiculo(v2);
        easydrive.registrarVehiculo(v3);
        easydrive.registrarVehiculo(v4);
        easydrive.registrarVehiculo(v5);

// imprime la relación de clientes de easydrive easydrive.imprimirClientes();
// imprime el catálogo de vehículos de easydrive
        easydrive.imprimirVehiculos();

        // El cliente Juan alquilará por 3 días el fiat Ducato azul.
        easydrive.alquilarVehiculo("4100 FUR", "X5618927C", 3);
        easydrive.alquilarVehiculo("4100 FUR", "Z7568991Y", 1); // Yá está alquilado por lo tanto no puede volverse ha alquilar
        System.out.println("");
        System.out.println("Alquileres actuales");
        System.out.println(easydrive.alquileresTotales());
        
        // Añadimos más vehiculos y más clientes
        Vehiculo v6 = new Vehiculo("4110 KYM", "Peugeot", "308", "Blanco", 120.0, true);
        Vehiculo v7 = new Vehiculo("4120 HYR", "Peugeot", "3008 Hybrid4", "Azul", 120.0, true);
        easydrive.registrarVehiculo(new Vehiculo("4130 GTY", "Ford", "Focus", "Azul", 75.0, true));
        easydrive.registrarVehiculo(v6);
        easydrive.registrarVehiculo(v7);
        easydrive.registrarVehiculo(new Vehiculo("4400 EFG", "Ford", "Explorer", "Azul", 83.0, true));
        
        easydrive.registrarCliente(new Cliente("15425992-E", "Nacho", "Muñoz Fuentes"));
        easydrive.registrarCliente(new Cliente("741369258-Q", "Sonia", "Gil Quiros"));

        // Comprobamos que se pueda alquilar el vehículo con matricula 4070 DEP
        System.out.println(easydrive.recibirVehiculo("4070 DEP"));
        
        // Alquilamos un par más de vehiculos.
        easydrive.alquilarVehiculo("4070 DEP", "Z7568991Y", 2);        
        easydrive.alquilarVehiculo("4400 EFG", "741369258-Q", 5);
        easydrive.alquilarVehiculo("4400 EFG", "15425992-E", 7); // Yá está alquilado por lo tanto no puede volverse ha alquilar
        
        
        
        System.out.println("");
        System.out.println("Alquileres actuales");
        System.out.println(easydrive.alquileresTotales());

        System.out.println(easydrive.recibirVehiculo("4070 DEP"));
        
        // Comprobamos hasta que día están alquilados los vehículos
        System.out.println("");
        easydrive.fechaFinAlquileres();
        
        // Comprobamos el alquiler concreto de un par de vehiculos
        System.out.println("");
        System.out.println(easydrive.fechaFinAlquilerVehiculo("4070 DEP", 2));
        System.out.println(easydrive.fechaFinAlquilerVehiculo("4400 EFG",5));        
    }

}
