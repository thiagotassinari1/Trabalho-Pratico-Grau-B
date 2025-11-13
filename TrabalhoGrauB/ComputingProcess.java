public class ComputingProcess extends Processo {
    private String expressao;

    public ComputingProcess(int pid, String expressao) {
        super(pid, 1);
        this.expressao = expressao;
    }

    // construtor sobrecarregado pra quando for criado o processo pela leitura do arquivo fila.txt
    public ComputingProcess(int pid, int tipo, String expressao) {
        super(pid, tipo);
        this.expressao = expressao;
    }

    public String getExpressao() {
        return this.expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public double soma(double n1, double n2) {
        return n1 + n2;
    }

    public double subtracao(double n1, double n2) {
        return n1 - n2;
    }

    public double multiplicacao(double n1, double n2) {
        return n1 * n2;
    }

    public double divisao(double n1, double n2) {
        return n1 / n2;
    }

    @Override
    public void execute() {
        String[] expressaoDividida = expressao.split(" ");

        double n1 = Double.parseDouble(expressaoDividida[0]);
        double n2 = Double.parseDouble(expressaoDividida[2]);
        char operador = expressaoDividida[1].charAt(0);

        if (operador == '+') {
            System.out.println("\nSoma: %.2f + %.2f = %.2f".formatted(n1, n2, soma(n1, n2)));
        } else if (operador == '-') {
            System.out.println("\nSubtração: %.2f - %.2f = %.2f".formatted(n1, n2, subtracao(n1, n2)));
        } else if (operador == '*') {
            System.out.println("\nMultiplicação: %.2f * %.2f = %.2f".formatted(n1, n2, multiplicacao(n1, n2)));
        } else if (operador == '/') {
            System.out.println("\nDivisão: %.2f / %.2f = %.2f".formatted(n1, n2, divisao(n1, n2)));
        } else {
            System.out.println("\nOperação inválida");
        }
    }

    @Override
    public void imprimeInfo(int pid) {
        String infos = String.format("Pid: %d - Tipo: Computing Process | Expressão: %s", pid, expressao);
        System.out.println(infos);
    }

}