package modulo2;

import java.util.LinkedHashMap; 
import java.util.Map; 

public class Cache<K, V> extends LinkedHashMap<K, V> { 
    private final int capacidade; 

    public Cache(int capacidade) { 
        // accessOrder = true garante ordenação por acesso
        super(capacidade, 0.75f, true); 
        this.capacidade = capacidade; 
    } 

    @Override 
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) { 
        return size() > capacidade; // Remove o item mais antigo ao ultrapassar a capacidade
    } 
}