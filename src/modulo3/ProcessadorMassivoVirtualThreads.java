package modulo3;

import java.time.Duration; 
import java.time.Instant; 
import java.util.concurrent.Executors; 

public class ProcessadorMassivoVirtualThreads { 
    public static void main(String[] args) { 
        Instant inicio = Instant.now(); 
        int totalTarefas = 50_000; 

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) { 
            for (int i = 0; i < totalTarefas; i++) { 
                int taskId = i; 
                executor.submit(() -> { 
                    // Simulação de chamada I/O (Ex: Banco de Dados ou API)
                    Thread.sleep(Duration.ofMillis(100)); 
                    return taskId; 
                }); 
            } 
        } // O fechamento do try-with-resources aguarda a conclusão de TODAS as Virtual Threads 

        Instant fim = Instant.now(); 
        System.out.println("Concluídas " + totalTarefas + " tarefas em: " + Duration.between(inicio, fim).toMillis() + " ms"); 
    } 
}