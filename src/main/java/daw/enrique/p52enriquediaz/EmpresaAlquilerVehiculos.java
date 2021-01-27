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
    private ArrayList<Cliente> clientes;
    /* Atributos para controlar el total de vehiculos disponibles en 
 alquiler que tiene la empresa y array de almacenamiento para los objetos Vehiculo */
    private ArrayList<Vehiculo> vehiculos;
    /* Atributos para controlar el histórico de alquileres: total de alquileres realizados y array de almacenamiento para los objetos VehiculoAlquilado */
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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
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
        this.clientes = new ArrayList<Cliente>(50); // apuntan a null
// Incialmente no hay vehiculos creados en la empresa
        this.vehiculos = new ArrayList<Vehiculo>(50); // apuntan a null
// Incialmente no hay histórico de alquileres en la empresa
        this.alquileres = new ArrayList<VehiculoAlquilado>(100); // apuntan a null
    }

    public void registrarCliente(Cliente nuevo) {
        clientes.add(nuevo);
    }

    public int clientesTotales() {
        return clientes.size();
    }

    public void ordenarNIF() {
        Comparator<Cliente> criterio = (c1, c2) -> c1.getNif().compareTo(c2.getNif());
        Collections.sort(clientes, criterio);
    }

    public int buscarClienteSecuencial(String nif) {
        ordenarNIF();
        int posicion = 0;
        for (Cliente cliente : clientes) {
            if (cliente.getNif().compareToIgnoreCase(nif) == 0) {
                return posicion;
            }
            posicion++;
        }
        return -1;

    }

    public void imprimirClientes() {
        System.out.println("NIF\tNombre\tApeliidos\n");
        clientes.forEach(System.out::println);
    }

    public void registrarVehiculo(Vehiculo nuevo) {
        vehiculos.add(nuevo);
    }

    public int vehiculosTotales() {
        return vehiculos.size();
    }

    public void ordenarMatricula() {
        Comparator<Vehiculo> criterio = (v1, v2) -> v1.getMatricula().compareTo(v2.getMatricula());
        Collections.sort(vehiculos, criterio);
    }

    public int buscarVehiculoSecuencial(String matricula) {
        ordenarMatricula();
        int posicion = 0;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().compareToIgnoreCase(matricula) == 0) {
                return posicion;
            }
            posicion++;
        }
        return -1;

    }

    public void imprimirVehiculos() {
        System.out.println("Matricula\tModelo\tColor\tImporte\tDisponible\n");

        // Uso de bucle foreach
        for (Vehiculo v : vehiculos) {
            System.out.println(v);
        }
    }

    public boolean alquilarVehiculo(String matricula, String nif, int dias) {

        if (buscarClienteSecuencial(nif) != 0 && buscarVehiculoSecuencial(matricula) != 0) {
            alquileres.add(new VehiculoAlquilado(clientes.get(buscarClienteSecuencial(nif)), vehiculos.get(buscarVehiculoSecuencial(matricula)), LocalDate.now(), dias));
            vehiculos.get(buscarVehiculoSecuencial(matricula)).setDisponible(false);
            return true;
        }
        return false;
    }

    public int alquileresTotales() {
        return alquileres.size();
    }

    // Con este método entiendo que es para que el cliente pueda comprobar si el coche que desea alquilar esta disponible para recibirlo o no.
    public String recibirVehiculo(String matricula) {
        String recibidoSiNo = "";

        Vehiculo vehiculo = vehiculos.get(buscarVehiculoSecuencial(matricula));
        if (vehiculo != null && vehiculo.equals(true)) {
            recibidoSiNo = "El vehículo puede ser sido recibido";
            return recibidoSiNo;
        } else {
            recibidoSiNo = "El vehículo no puede ser recibido";
            return recibidoSiNo;
        }
    }

    public void fechaFinAlquileres() {
        LocalDate f = LocalDate.now();
        for (VehiculoAlquilado vehiculo : alquileres) {
            f = f.plusDays(vehiculo.getTotalDiasAlquiler());
            System.out.println("Vehículo con matrícula: " + vehiculo.getVehiculo().getMatricula() + " dispone de hasta el " + f + " de alquiler.");
            
            f = LocalDate.now();
        }
    }
    
      public void ordenarDiasAlquiler() {
        Comparator<VehiculoAlquilado> criterio = (va1, va2) -> va1.getTotalDiasAlquiler().compareTo(va2.getTotalDiasAlquiler());
        Collections.sort(alquileres, criterio);
    }

    public int buscarFechasSecuenciales(int dias) {
        ordenarDiasAlquiler();
        int posicion = 0;
        for (VehiculoAlquilado vehiculoAlquilado : alquileres) {
            if (vehiculoAlquilado.getTotalDiasAlquiler().compareTo(dias) == 0) {
                return posicion;
            }
            posicion++;
        }
        return -1;

    }

    public String fechaFinAlquilerVehiculo(String matricula, int dia) {
        String finalquiler = "";
        ordenarDiasAlquiler();

        LocalDate f = LocalDate.now();
        f = f.plusDays(alquileres.get(buscarFechasSecuenciales(dia)).getTotalDiasAlquiler());
        finalquiler = "El vehículo de matrícula " + matricula + " dispone de hasta el " + f + " días de alquiler";

        return finalquiler;

    }

    private Cliente getCliente(String nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Vehiculo getVehiculo(String matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "EmpresaAlquilerVehiculos{" + "cif=" + cif + ", nombre=" + nombre + ", paginaWeb=" + paginaWeb + ", totalClientes=" + clientes.size() + ", clientes=" + clientes + ", totalVehiculos=" + vehiculos.size() + ", vehiculos=" + vehiculos + ", totalAlquileres=" + alquileres.size() + ", alquileres=" + alquileres + '}';
    }

}
