package modulo3;

import java.util.concurrent.atomic.AtomicLong; 

public class TokenBucketRateLimiter { 
    private final long capacidade; 
    private final long intervaloReabastecimentoNanos; 
    private final AtomicLong tokensDisponiveis; 
    private final AtomicLong ultimoReabastecimento; 

    public TokenBucketRateLimiter(long capacidade, long tokensPorSegundo) { 
        this.capacidade = capacidade; 
        this.tokensDisponiveis = new AtomicLong(capacidade); 
        this.intervaloReabastecimentoNanos = 1_000_000_000L / tokensPorSegundo; 
        this.ultimoReabastecimento = new AtomicLong(System.nanoTime()); 
    } 

    public boolean tryAcquire() { 
        reabastecer(); 
        while (true) { 
            long atuais = tokensDisponiveis.get(); 
            if (atuais <= 0) return false; 
            if (tokensDisponiveis.compareAndSet(atuais, atuais - 1)) { 
                return true; 
            } 
        } 
    } 

    private void reabastecer() { 
        long agora = System.nanoTime(); 
        long ultimo = ultimoReabastecimento.get(); 
        long decorrido = agora - ultimo; 

        if (decorrido > intervaloReabastecimentoNanos) { 
            long novosTokens = decorrido / intervaloReabastecimentoNanos; 
            if (novosTokens > 0 && ultimoReabastecimento.compareAndSet(ultimo, agora)) { 
                tokensDisponiveis.updateAndGet(t -> Math.min(capacidade, t + novosTokens)); 
            } 
        } 
    } 
}