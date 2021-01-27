/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.enrique.p52enriquediaz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author enrique
 */
public class EmpresaAlquilerVehiculos {
// Atributos de la empresa

    private String cif;
    private String nombre;
    private String paginaWeb;

    /* Atributos para controlar el total de clientes que tiene la empresa y array de almacenamiento para los objetos Cliente */
    private int totalClientes;
    private ArrayList<Cliente> clientes;

    /* Atributos para controlar el total de vehiculos disponibles en 
 alquiler que tiene la empresa y array de almacenamiento para los objetos Vehiculo */
    private int totalVehiculos;
    private ArrayList<Vehiculo> vehiculos;

    /* Atributos para controlar el histórico de alquileres: total de alquileres realizados y array de almacenamiento para los objetos VehiculoAlquilado */
    private int totalAlquileres;
    private ArrayList<VehiculoAlquilado> alquileres;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public int getTotalClientes() {
        return totalClientes;
    }

    public void setTotalClientes(int totalClientes) {
        this.totalClientes = totalClientes;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public int getTotalVehiculos() {
        return totalVehiculos;
    }

    public void setTotalVehiculos(int totalVehiculos) {
        this.totalVehiculos = totalVehiculos;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public int getTotalAlquileres() {
        return totalAlquileres;
    }

    public void setTotalAlquileres(int totalAlquileres) {
        this.totalAlquileres = totalAlquileres;
    }

    public ArrayList<VehiculoAlquilado> getAlquileres() {
        return alquileres;
    }

// incluir métodos ‘get’,‘set’
    public void setAlquileres(ArrayList<VehiculoAlquilado> alquileres) {
        this.alquileres = alquileres;
    }

    /* Constructor parametrizado donde se establece que el total de clientes será 50, igual que el total de vehiculos disponibles. El histórico de vehiculos puede contener hasta 100 elementos */
    EmpresaAlquilerVehiculos(String cif, String nombre, String paginaWeb) {
        this.cif = cif;
        this.nombre = nombre;
        this.paginaWeb = paginaWeb;
// Incialmente no hay clientes creados en la empresa
        this.totalClientes = 0;
        this.clientes = new ArrayList<Cliente>(50); // apuntan a null
// Incialmente no hay vehiculos creados en la empresa
        this.totalVehiculos = 0;
        this.vehiculos = new ArrayList<Vehiculo>(50); // apuntan a null
// Incialmente no hay histórico de alquileres en la empresa
        this.totalAlquileres = 0;
        this.alquileres = new ArrayList<VehiculoAlquilado>(100); // apuntan a null
    }

    public void registrarCliente(Cliente nuevo) {
        clientes.add(nuevo);
        this.totalClientes++;

    }

    public void ordenarNIF() {
        Comparator<Cliente> criterio = (c1, c2) -> c1.getNif().compareTo(c2.getNif());
        Collections.sort(clientes, criterio);
    }

    public int buscarCliente(Cliente nif) {

        //        for (int i = 0; i < this.totalClientes; i++) {
//
//            if (this.clientes[i].getNif().equals(nif)) {
//                return this.clientes[i];
//            }
//        }
//        return null;
        ordenarNIF();
        Comparator<Cliente> criterio = (c1, c2) -> c1.getNif().compareTo(c2.getNif());

        int posicion = Collections.binarySearch(clientes, nif, criterio);

        return posicion;

    }

    public void imprimirClientes() {
        clientes.forEach(System.out::println);
    }

    public void registrarVehiculo(Vehiculo nuevo) {
        vehiculos.add(nuevo);
        this.totalVehiculos++;
    }

    public void ordenarMatricula() {
        Comparator<Vehiculo> criterio = (v1, v2) -> v1.getMatricula().compareTo(v2.getMatricula());
        Collections.sort(vehiculos, criterio);
    }

    public int buscarVehiculo(Vehiculo matricula) {
//for (int i = 0; i < this.totalVehiculos; i++) {
//
//            if (this.vehiculos[i].getMatricula().equals(matricula)) {
//                return this.vehiculos[i];
//            }
//        }
//
//        return null;
        ordenarMatricula();
        Comparator<Vehiculo> criterio = (v1, v2) -> v1.getMatricula().compareTo(v2.getMatricula());

        int posicion = Collections.binarySearch(vehiculos, matricula, criterio);

        return posicion;

    }

    public void imprimirVehiculos() {
        System.out.println("Matricula\tModelo\tColor\tImporte\tDisponible\n");

        // Uso de bucle foreach
        for (Vehiculo v : vehiculos) {
            System.out.println(v);
        }
    }

    public boolean alquilarVehiculo(String matricula, String nif, int dias) {
        // busca el cliente a partir del nif 
        Cliente cliente = getCliente(nif);
        // busca el vehículo a partir de la matrícula
        Vehiculo vehiculo = getVehiculo(matricula);

        if (cliente != null && vehiculo != null) {
            if (vehiculo.isDisponible()) {
                vehiculo.setDisponible(false);

                this.alquileres[this.totalAlquileres] = new VehiculoAlquilado(cliente, vehiculo, LocalDate.now(), dias);

                this.totalAlquileres++;
                return true; // El alquiler se realiza correctamente
            }
        }
        return false; // No se puede alquilar el vehiculo por el cliente
    }

    public void recibirVehiculo(String matricula) {

// busca el vehículo con la matrícula dada en el
// array vehiculos y modifica su disponibilidad
// para que se pueda alquilar de nuevo
        ordenarMatricula();
        Vehiculo vehiculo = buscarVehiculo(vehiculo.getMatricula());

        if (vehiculo != null) {
            vehiculo.setDisponible(true);
        }

    }

    private Cliente getCliente(String nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Vehiculo getVehiculo(String matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "EmpresaAlquilerVehiculos{" + "cif=" + cif + ", nombre=" + nombre + ", paginaWeb=" + paginaWeb + ", totalClientes=" + totalClientes + ", clientes=" + clientes + ", totalVehiculos=" + totalVehiculos + ", vehiculos=" + vehiculos + ", totalAlquileres=" + totalAlquileres + ", alquileres=" + alquileres + '}';
    }

}
