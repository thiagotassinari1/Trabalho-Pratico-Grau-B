import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ReadingProcess extends Processo {
    private ArrayList<Processo> filaProcessos;

    public ReadingProcess(int pid, ArrayList<Processo> filaProcessos) {
        super(pid, 3);
        this.filaProcessos = filaProcessos;
    }

    // construtor sobrecarregado pra quando for criado o processo pela leitura do arquivo fila.txt
    public ReadingProcess(int pid, int tipo, ArrayList<Processo> filaProcessos) {
        super(pid, tipo);
        this.filaProcessos = filaProcessos;
    }

    @Override
    public void execute() {
        try (FileReader reader = new FileReader("computation.txt");
                Scanner sc = new Scanner(reader).useLocale(Locale.ENGLISH).useDelimiter(";|\\n")) {

            while (sc.hasNext()) {

                String expressao = sc.next();

                // Cria o ComputingProcess e adiciona na fila de processos
                // ver como vou fazer isso, talvez salvar um pid no arquivo computation
                // ou receber o pid como parametro nessa clase, tipo "pidMain", pra dai dar um pidMain++ e enviar no construtor do ComputingProcess
                Processo novoProcesso = new ComputingProcess(expressao);
                filaProcessos.add(novoProcesso);

            }
            System.out.println("Computation.txt lido com sucesso!");

            // chama a função de limpar o arquivo logo quando termina de ler
            limparArquivo();

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo computation.txt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void limparArquivo() {
        try {
            FileWriter writer = new FileWriter("computation.txt");
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("");

            System.out.println("Computation.txt limpo com sucesso!");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Erro ao limpar os dados de computation.txt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void imprimeInfo(int pid) {
        String infos = String.format("Pid: %d - Tipo: Reading Process | Fila de processos: %s", pid, filaProcessos);
        System.out.println(infos);
    }
}
