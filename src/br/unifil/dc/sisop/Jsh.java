package br.unifil.dc.sisop;
import java.io.*;
import java.util.*;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh {

    /**
     * Funcao principal do Jsh.
     */

    public static String userName;
    public static String userDir;
    public static String userID;

    public static void promptTerminal() {

        while (true) {
            exibirPrompt();
            ComandoPrompt comandoEntrado = lerComando();
            executarComando(comandoEntrado);
        }
    }

    /**
     * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
     * terminal está pronto para receber o próximo comando como entrada.
     */
    public static void exibirPrompt() {

        userName = System.getProperty("user.name");

        userDir = System.getProperty("user.dir");

        userID = getUID();


        System.out.print(userName + "#" + userID + ":" + userDir + "%");


    }

    /**
     * Preenche as strings comando e parametros com a entrada do usuario do terminal.
     * A primeira palavra digitada eh sempre o nome do comando desejado. Quaisquer
     * outras palavras subsequentes sao parametros para o comando. A palavras sao
     * separadas pelo caractere de espaco ' '. A leitura de um comando eh feita ate
     * que o usuario pressione a tecla <ENTER>, ou seja, ate que seja lido o caractere
     * EOL (End Of Line).
     *
     * @return
     */
    public static ComandoPrompt lerComando() {
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();

        ComandoPrompt cmd = new ComandoPrompt(command);
        return cmd;
    }

    /**
     * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
     * se for, o executa.
     * <p>
     * Se nao for, verifica se é o nome de um programa terceiro localizado no atual
     * diretorio de trabalho. Se for, cria um novo processo e o executa. Enquanto
     * esse processo executa, o processo do uniterm deve permanecer em espera.
     * <p>
     * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
     * programa desconhecido.
     */
    public static void executarComando(ComandoPrompt comando) {
        ProcessBuilder process = new ProcessBuilder();
        switch(comando.getArgumentos().get(0)){
            case("encerrar"):{
                System.exit(0);
                break;
            }
            case("relogio"):{
                ComandosInternos.exibirRelogio();
                break;
            }
            case("la"):{
                ComandosInternos.escreverListaArquivos(Optional.of(userDir));
                break;
            }
            case("cd"):{
                ComandosInternos.criarNovoDiretorio(userDir, comando.getArgumentos().get(1));
                break;
            }
            case("ad"):{
                ComandosInternos.apagarDiretorio(userDir, comando.getArgumentos().get(1));
            }
        }
    }

    public static int executarPrograma(ComandoPrompt comando) {
        throw new RuntimeException("Método ainda não implementado.");
    }


    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     */
    public static void main(String[] args) {

        promptTerminal();
    }

    public static String getUID(){
        Runtime r = Runtime.getRuntime();
        Process p;
        try {
            String userName = System.getProperty("user.name");
            String command = "id -u "+userName;
            p = r.exec(command);

            Scanner scanner = new Scanner(p.getInputStream());
            String result = scanner.useDelimiter("$$").next();
            return result;

        } catch (IOException e) {
            throw new RuntimeException("UID not found");
        }
    }



    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {
    }
}
