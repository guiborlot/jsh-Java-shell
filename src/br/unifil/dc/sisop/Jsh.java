package br.unifil.dc.sisop;
import java.io.*;
import java.util.*;

/**
 * Write a description of class Jsh here.
 *
 * @author Guilherme Borlot Oliveira
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
        /**
         * Armazena na variável o nome do usuŕio da máquina.
         */
        userName = System.getProperty("user.name");

        /**
         * Armazena na variável o path atual do usuário.
         */
        userDir = System.getProperty("user.dir");

        /**
         * Armazena na variável o User ID atual.
         */
        userID = getUID();

        /**
         * Printa todas as variáveis acima.
         */
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
        /**
         * Como na classe ComandosPrompt eu trato a variavel argumentos separando as palavras em um array, eu pego a
         * primeira palavra que foi digitada para fazer o switch. por ex: "cd pasta" aonde "cd" é a primeira palavra
         * ou seja, tem o indice 0 no array, é o "cd" que será usado para fazer o switch e a "pasta" será usada como
         * argumento secundário que será tratada dependendo do case, neste exemplo será usado para determinar o nome do
         * diretório que foi criado.
         */
        switch(comando.getArgumentos().get(0)){
            /**
             * Retorna 0 fazendo com que o programa encerre.
             */
            case("encerrar"):{
                System.exit(0);
                break;
            }
            /**
             * retorna a data e hora local.
             */
            case("relogio"):{
                ComandosInternos.exibirRelogio();
                break;
            }
            /**
             * Lista todos os arquivos e diretórios naquele path.
             */
            case("la"):{
                ComandosInternos.escreverListaArquivos(Optional.of(userDir));
                break;
            }
            /**
             * cria um novo diretório com o nome digitado
             * Ex: "cd 'nome+do+diretório'".
             */
            case("cd"):{
                ComandosInternos.criarNovoDiretorio(userDir, comando.getArgumentos().get(1));
                break;
            }
            /**
             * Apaga o diretório especificado
             * Ex: "ad 'nome+do+diretório'".
             */
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

    /**
     * Este metodo retorna o UID do usuário
     */
    public static String getUID(){
        Runtime r = Runtime.getRuntime();
        Process p;
        /**
         * Concatena a string id -u com o user.name e uso a função exec para executá-la,
         * depois armazeno o resultado da função numa string e faço o retorno.
         */
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
