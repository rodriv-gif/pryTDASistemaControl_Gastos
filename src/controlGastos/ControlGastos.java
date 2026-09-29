/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlGastos;

import modelo.Movimientos;
import modelo.Ingresos;
import modelo.Gastos;

/**
 *
 * @author ligoh
 */
public class ControlGastos {
    
    private Movimientos[] movimiento;
    private int cantidad;
    private float saldoInicial;
    
    //validamos en el contructor a que los campos no esten vacios o nuemros negativos
    public ControlGastos(int capacidad, float saldoInicial) {

        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0.");
        }

        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        //inicializamos 
        movimiento = new Movimientos[capacidad];
        cantidad = 0;
        this.saldoInicial = saldoInicial;
    }
    public void establecerSaldoInicial(float saldo) {

        if (cantidad > 0) {
            throw new IllegalArgumentException(
                "No puedes modificar el saldo inicial porque ya existen movimientos."
            );
        }

        if (saldo < 0) {
            throw new IllegalArgumentException(
                "El saldo inicial no puede ser negativo."
            );
        }

        saldoInicial = saldo;
    }   
    //metodo para registrar un ingreso o gasto,
    // verificando que exista espacio y saldo suficiente.
    public void registrarMovimiento(Movimientos movimiento) {
        //primero validamos si hay espacio
        if (cantidad >= this.movimiento.length) {
            throw new IllegalArgumentException(
               "No hay espacio para más movimientos."
            );
       }

       // Validamos si es un gasto
       if (movimiento instanceof Gastos) {

           if (movimiento.getCantidad() > calcularSaldo()) {
               throw new IllegalArgumentException(
                   "No tienes saldo suficiente para realizar este gasto."
               );
           }
       }

       // Guardamos el movimiento
       this.movimiento[cantidad] = movimiento;
       cantidad++;
    }
    //metodo para buscar un movimiento
    public Movimientos buscarMovimiento(String descripcion) {

        for (int i = 0; i < cantidad; i++) {

            if (movimiento[i].getDescripcion().equalsIgnoreCase(descripcion)) {
                return movimiento[i];
            }
        }

        return null;
    }
    //metodo para eliminar un movimiento
    public boolean eliminarMovimiento(String descripcion) {

        for (int i = 0; i < cantidad; i++) {

            if (movimiento[i].getDescripcion().equalsIgnoreCase(descripcion)) {

                for (int j = i; j < cantidad - 1; j++) {
                    movimiento[j] = movimiento[j + 1];
                }

                movimiento[cantidad - 1] = null;
                cantidad--;

                return true;
            }
        }

        return false;
    }
    // Método para actualizar un movimiento existente,
    // validando que el nuevo gasto no exceda el saldo.
    public boolean actualizarMovimiento(String descripcion, Movimientos nuevo) {

        for (int i = 0; i < cantidad; i++) {

            if (movimiento[i].getDescripcion().equalsIgnoreCase(descripcion)) {

                // Si el nuevo movimiento es un gasto
                if (nuevo instanceof Gastos) {

                    // Calculamos el saldo sin contar el movimiento anterior
                    float saldoSinAnterior = calcularSaldo();

                    if (movimiento[i] instanceof Ingresos) {
                        saldoSinAnterior -= movimiento[i].getCantidad();
                    } else if (movimiento[i] instanceof Gastos) {
                        saldoSinAnterior += movimiento[i].getCantidad();
                    }

                    // Verificamos si hay saldo suficiente para el nuevo gasto
                    if (nuevo.getCantidad() > saldoSinAnterior) {
                        throw new IllegalArgumentException(
                            "No tienes saldo suficiente para realizar este gasto."
                        );
                    }
                }

                // Actualizamos el movimiento
                movimiento[i] = nuevo;
                return true;
            }
        }

        return false;
    }
    //metodo para calcular el total de ingresos
    public float calcularTotalIngresos() {

        float total = 0;

        for (int i = 0; i < cantidad; i++) {

            if (movimiento[i] instanceof Ingresos) {
                total += movimiento[i].getCantidad();
            }
        }

        return total;
    }
    //metodo para calcular el total de gastos
    public float calcularTotalGastos() {

        float total = 0;

        for (int i = 0; i < cantidad; i++) {

            if (movimiento[i] instanceof Gastos) {
                total += movimiento[i].getCantidad();
            }
        }

        return total;
    }
    // Método para calcular el saldo disponible,
    // sumando ingresos y restando gastos al saldo inicial.
    public float calcularSaldo(){
        return saldoInicial + calcularTotalIngresos() - calcularTotalGastos();
    }
    //aplicamos recursividad para contar los movimientos
    public int contarMovimientosRecursivo() {
        return contarMovimientosRecursivo(0);
    }
    
    private int contarMovimientosRecursivo(int posicion) {
        //caso base
        if (posicion >= cantidad) {
            return 0;
        }
        //caso recursivo
        return 1 + contarMovimientosRecursivo(posicion + 1);
    }
    
    public int contarMovimientos() {
        return cantidad;
    }
    
    public Movimientos obtenerMovimiento(int posicion) {

        if (posicion < 0 || posicion >= cantidad) {
            throw new IllegalArgumentException("Posición inválida.");
        }

        return movimiento[posicion];
    }
}
