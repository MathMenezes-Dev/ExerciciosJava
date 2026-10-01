package modulo1;

public class ClassificadorEmprestimo {
    public enum PerfilCliente { VIP, REGULAR, RISCO }

    public static double calcularJurosTotal(PerfilCliente perfil, double valor) {
        double taxa = switch (perfil) {
            case VIP -> {
                if (valor > 100000.0) {
                    throw new IllegalArgumentException("Limite VIP excedido");
                }
                yield 0.015;
            }
            case REGULAR -> {
                if (valor > 30000.0) {
                    throw new IllegalArgumentException("Limite Regular excedido");
                }
                yield 0.038;
            }
            case RISCO -> {
                if (valor > 5000.0) {
                    throw new IllegalArgumentException("Limite de Risco excedido");
                }
                yield 0.075;
            }
        };

        return valor * taxa;
    }
}