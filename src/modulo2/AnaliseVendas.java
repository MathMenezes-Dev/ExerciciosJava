package modulo2;
import java.util.List; 
import java.util.Map; 
import java.util.stream.Collectors; 

public class AnaliseVendas { 
    public record Venda(String vendedor, String categoria, double valor) {} 

    public static Map<String, Double> totalPorCategoria(List<Venda> vendas) { 
        return vendas.stream()
                .filter(v -> v.valor() > 0.0) 
                .collect(Collectors.groupingBy(
                        Venda::categoria, 
                        Collectors.summingDouble(Venda::valor)
                )); 
    } 
}