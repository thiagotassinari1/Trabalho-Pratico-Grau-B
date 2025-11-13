import java.util.ArrayList;

public class PrintingProcess extends Processo {
    private ArrayList<Processo> filaProcessos;

    public PrintingProcess(int pid, ArrayList<Processo> filaProcessos) {
        super(pid, 4);
        this.filaProcessos = filaProcessos;
    }

    // construtor sobrecarregado pra quando for criado o processo pela leitura do arquivo fila.txt
    public PrintingProcess(int pid, int tipo, ArrayList<Processo> filaProcessos) {
        super(pid, tipo);
        this.filaProcessos = filaProcessos;
    }

    @Override
    public void execute() {
        // verifica se o array ta vazio antes de executar o for
        if (filaProcessos.isEmpty()) {
            System.out.println("Fila de processos vazia.");
            return;
        }

        for (int i = 0; i < filaProcessos.size(); i++) {
            filaProcessos.get(i).imprimeInfo(filaProcessos.get(i).getPid());
        }

    }

    @Override
    public void imprimeInfo(int pid) {
        String infos = String.format("Pid: %d - Nome: Printing Process | Fila de processos", pid);
        System.out.println(infos);
    }
}
