import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static int pid = 0;

    public static void criarProcesso(Scanner sc, ArrayList<Processo> filaProcessos) {

        System.out.println("\nTipos de processos:");
        // numero do tipo do processo
        System.out.println("2 - Writing Process;");
        System.out.println("3 - Reading Process;");
        System.out.println("4 - Printing Process;");

        System.out.print("Escolha: ");
        int tipoProcesso = sc.nextInt();
        sc.nextLine();

        // identificar o tipo de processo
        // 2 = WritingProcess
        // 3 = ReadingProcess
        // 4 = PrintingProcess

        if (tipoProcesso == 2) {
            System.out.println(
                    "\nEscreva uma expressão com dois operandos e um operador, divididos por espaço (n1 + n2): ");
            System.out.println("Operadores possíveis: +, -, *, /");
            System.out.print("Expressão: ");
            String expressao = sc.nextLine();

            System.out.println("\nProcesso criado!");

            pid++;
            Processo novoProcesso = new WritingProcess(pid, expressao);
            filaProcessos.add(novoProcesso);

            System.out.println("\n-------------------------");
            System.out.println("Informações do processo: " + "\n" +
                    "Pid: " + novoProcesso.getPid() + "\n" +
                    "Tipo: Writing Process" + "\n" +
                    "Expressão: " + expressao);
            System.out.println("-------------------------");
        } else if (tipoProcesso == 3) {
            System.out.println("\nProcesso criado!");

            pid++;
            Processo novoProcesso = new ReadingProcess(pid, filaProcessos);
            filaProcessos.add(novoProcesso);

            System.out.println("\n-------------------------");
            System.out.println("Informações do processo: " + "\n" +
                    "Pid: " + novoProcesso.getPid() + "\n" +
                    "Tipo: Reading Process");
            System.out.println("-------------------------");
        } else if (tipoProcesso == 4) {
            System.out.println("\nProcesso criado!");

            pid++;
            Processo novoProcesso = new PrintingProcess(pid, filaProcessos);
            filaProcessos.add(novoProcesso);

            System.out.println("\n-------------------------");
            System.out.println("Informações do processo: " + "\n" +
                    "Pid: " + novoProcesso.getPid() + "\n" +
                    "Tipo: Printing Process");
            System.out.println("-------------------------");
        } else {
            System.out.println("Processo inválido.");
        }

    }

    public static void executaProximo(ArrayList<Processo> filaProcessos) {
        if (filaProcessos.isEmpty()) {
            System.out.println("\nLista vazia. Sem processos para executar.");
            return;
        }

        if (filaProcessos.get(0) instanceof ReadingProcess) {
            ReadingProcess processoReading = ((ReadingProcess) filaProcessos.get(0));

            processoReading.setPidMain(pid);
            processoReading.execute();
            filaProcessos.remove(0);

            // define de onde o main deve continuar a contagem do pid quando cria os
            // computingProcess por dentro do ReadingProcess
            pid = processoReading.getPidMain();
            return;
        } else {
            filaProcessos.get(0).execute();
            filaProcessos.remove(0);
        }

    }

    public static void executaEspecifico(Scanner sc, ArrayList<Processo> filaProcessos) {
        if (filaProcessos.isEmpty()) {
            System.out.println("\nLista vazia. Sem processos para executar.");
            return;
        }

        System.out.print("Informe o pid de um processo para executar: ");
        int pidInformado = sc.nextInt();

        for (int i = 0; i < filaProcessos.size(); i++) {
            Processo processo = filaProcessos.get(i);

            if (processo.getPid() == pidInformado) {
                if (processo instanceof ReadingProcess) {
                    ReadingProcess processoReading = ((ReadingProcess) filaProcessos.get(i));

                    processoReading.setPidMain(pid);
                    processoReading.execute();
                    filaProcessos.remove(i);
                    // define de onde o main deve continuar a contagem do pid quando cria os
                    // computingProcess por dentro do ReadingProcess
                    pid = processoReading.getPidMain();
                    return;
                } else {
                    processo.execute();
                    filaProcessos.remove(i);
                    return;
                }

            }
        }

        // Padrão pra caso não caia no if do for
        System.out.println("\nNenhum processo com pid %d encontrado.".formatted(pidInformado));
    }

    public static void salvarFila(ArrayList<Processo> filaProcessos) {
        if (filaProcessos.isEmpty()) {
            System.out.println("\nLista vazia. Arquivo não salvo.");
            return;
        }

        try {
            Locale.setDefault(Locale.ENGLISH);
            FileWriter writer = new FileWriter("fila.txt");
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("pid;tipo;atributos");
            buffer.write("\n");

            for (int i = 0; i < filaProcessos.size(); i++) {
                Processo processo = filaProcessos.get(i);

                String linha = "";

                if (processo instanceof ComputingProcess) {
                    linha = String.format(Locale.ENGLISH, "%d;%d;%s",
                            processo.getPid(),
                            processo.getTipo(),
                            ((ComputingProcess) processo).getExpressao());
                } else if (processo instanceof WritingProcess) {
                    linha = String.format(Locale.ENGLISH, "%d;%d;%s",
                            processo.getPid(),
                            processo.getTipo(),
                            ((WritingProcess) processo).getExpressao());
                } else if (processo instanceof ReadingProcess) {
                    linha = String.format(Locale.ENGLISH, "%d;%d;%s",
                            processo.getPid(),
                            processo.getTipo(),
                            " ");
                } else if (processo instanceof PrintingProcess) {
                    linha = String.format(Locale.ENGLISH, "%d;%d;%s",
                            processo.getPid(),
                            processo.getTipo(),
                            " ");
                }

                buffer.write(linha);
                buffer.write("\n");
            }

            System.out.println("\nFila salva com sucesso!");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar a fila: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void carregarFila(ArrayList<Processo> filaProcessos) {
        try (FileReader reader = new FileReader("fila.txt");
                Scanner sc = new Scanner(reader).useLocale(Locale.ENGLISH).useDelimiter(";|\\n")) {

            // pular linha do cabeçalho
            sc.nextLine();

            while (sc.hasNext()) {
                int pidArquivo = sc.nextInt();
                int tipo = sc.nextInt();
                String atributo = sc.next();

                // Cria um novo processo com base no tipo do arquivo e adiciona na fila de processos
                // define o pid estatico pro pid que leu no arquivo, pra sempre manter o pid atualizado com o ultimo pid do arquivo
                if (tipo == 1) {
                    Processo novoProcesso = new ComputingProcess(pidArquivo, tipo, atributo);
                    filaProcessos.add(novoProcesso);
                    pid = pidArquivo;

                } else if (tipo == 2) {
                    Processo novoProcesso = new WritingProcess(pidArquivo, tipo, atributo);
                    filaProcessos.add(novoProcesso);
                    pid = pidArquivo;

                } else if (tipo == 3) {
                    Processo novoProcesso = new ReadingProcess(pidArquivo, tipo, filaProcessos);
                    filaProcessos.add(novoProcesso);
                    pid = pidArquivo;

                } else if (tipo == 4) {
                    Processo novoProcesso = new PrintingProcess(pidArquivo, tipo, filaProcessos);
                    filaProcessos.add(novoProcesso);
                    pid = pidArquivo;

                }

            }

            System.out.println("Fila.txt lido com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo fila.txt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Processo> filaProcessos = new ArrayList<>();

        System.out.println("Escolha como iniciar o programa: ");
        System.out.println("1 - Começar novo: ");
        System.out.println("2 - Continuar (ler arquivo salvo): ");
        System.out.print("Escolha: ");
        int escolhaInicio = sc.nextInt();

        if (escolhaInicio == 1) {
            System.out.println("Novo sistema iniciado!");
        } else if (escolhaInicio == 2) {
            System.out.println("Continunado sistema salvo!");
            carregarFila(filaProcessos);
        }

        int escolha = -1;
        while (escolha != 0) {
            System.out.println("\nMenu de opções:");
            System.out.println("1 - Criar processo;");
            System.out.println("2 - Executar próximo;");
            System.out.println("3 - Executar processo específico;");
            System.out.println("4 - Salvar a fila de processos;");
            System.out.println("0 - Sair;");
            System.out.print("\nEscolha uma opção: ");
            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    criarProcesso(sc, filaProcessos);
                    break;
                case 2:
                    executaProximo(filaProcessos);
                    break;
                case 3:
                    executaEspecifico(sc, filaProcessos);
                    break;
                case 4:
                    salvarFila(filaProcessos);
                    break;
                case 5:

                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.print("Opção desconhecida.");
                    break;
            }
        }

        sc.close();
    }
}
