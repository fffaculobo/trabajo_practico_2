package ar.edu.unju.fi.ejercicio6.main;

import ar.edu.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {
    public static void main(String[] args) {
        FelinoDomestico gato = new FelinoDomestico("Garfield", (byte) 45, 12f);
        Converter<FelinoDomestico, FelinoSalvaje> converterSalvaje = x -> new FelinoSalvaje("Salvaje " + x.getNombre(), x.getEdad(), x.getPeso());
        FelinoSalvaje felinoSalvaje = converterSalvaje.convertir(gato);
        converterSalvaje.imprimir(felinoSalvaje);

        FelinoSalvaje felinoSalvajeTanner = new FelinoSalvaje("Tanner", (byte) 20, 186f);
        if (Converter.isNotNull(felinoSalvajeTanner)) {
            Converter<FelinoSalvaje, FelinoDomestico> converterDomestico = x -> new FelinoDomestico("Domestico " + x.getNombre(), x.getEdad(), x.getPeso());
            FelinoDomestico felinoDomesticoTanner = converterDomestico.convertir(felinoSalvajeTanner);
            converterDomestico.imprimir(felinoDomesticoTanner);
        } else {
            System.out.println("El objeto FelinoSalvaje es nulo.");
        }
    }
}
