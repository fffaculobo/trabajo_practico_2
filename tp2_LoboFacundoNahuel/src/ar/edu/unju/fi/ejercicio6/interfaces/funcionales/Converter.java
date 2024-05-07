package ar.edu.unju.fi.ejercicio6.interfaces.funcionales;

@FunctionalInterface
public interface Converter<T, T1>{
    T1 convertir(T t);

    static <T> Boolean isNotNull(T t){
        return t != null;
    }

    default void imprimir(T1 t1){
        System.out.println("Objeto "+t1.toString());
    }
}
