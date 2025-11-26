package edu.fiuba.algo3.modelo.Constructores;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Recurso.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ConstructorBanca {
    public Banca crearBanca(){
        List<Recurso> recursos = new ArrayList();
        agregar(recursos, Madera::new, 19);
        agregar(recursos, Ladrillo::new, 19);
        agregar(recursos, Lana::new, 19);
        agregar(recursos, Grano::new, 19);
        agregar(recursos, Mineral::new, 19);
        return new Banca(recursos);
    }
    public void agregar(List<Recurso> lista, Supplier<Recurso> fabrica, int cantidad){
        for( int i = 0; i < cantidad; i++){
            lista.add(fabrica.get());
        }
    }
}
