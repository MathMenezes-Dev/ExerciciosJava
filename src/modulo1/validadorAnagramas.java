package modulo1;
import java.util.Arrays; 

public class validadorAnagramas { 
    public static boolean saoAnagramas(String s1, String s2) { 
        if (s1 == null || s2 == null) return false; 
        
        char[] a1 = s1.replaceAll("\\s+", "").toLowerCase().toCharArray(); 
        char[] a2 = s2.replaceAll("\\s+", "").toLowerCase().toCharArray(); 
        
        if (a1.length != a2.length) return false; 
        
        Arrays.sort(a1); 
        Arrays.sort(a2); 
        
        return Arrays.equals(a1, a2); 
    } 
}