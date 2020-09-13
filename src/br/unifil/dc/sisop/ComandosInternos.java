package br.unifil.dc.sisop;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * Write a description of class ComandosInternos here.
 *
 * @author Guilherme Borlot Oliveira
 * @version 180823
 */
public final class ComandosInternos {

    /**
     * Metodo simples que retorna a data e hora local do sistema.
     */
    public static int exibirRelogio() {
        System.out.println(java.time.LocalDate.now());
        System.out.println(java.time.LocalTime.now());
        return (1);
    }

    /**
     * Recebe um array de strings que representa o path aonde o usuário está localizado.
     * Através do metodo .get() eu busco o path e passo como parâmetro para inicializar a classe File.
     * depois disso passo a lista de arquivos e diretórios para o array "afile", para essa operação foi usado
     * o metodo listFiles().
     * Após isso printo na tela o conteudo do array "afile".
     */
    public static int escreverListaArquivos(Optional<String> nomeDir) {
        File file = new File(nomeDir.get());
        File afile[] = file.listFiles();
        for (int i = 0; i < afile.length; i++) {
            System.out.println(afile[i].getName());
        }
        return (1);
    }
    /**
     * Recebo como parametro do método o path e o nome da pasta que o usuário deseja criar.
     * Faço a concatenação do path com o nome da pasta e passo como parâmetro para a classe File.
     * Verifico se existe alguma pasta com aquele nome, se não tiver, é criada uma.
     */
    public static int criarNovoDiretorio(String nomeDir, String nomePasta) {
        File file = new File(nomeDir + "/" + nomePasta);
        if (!file.exists()) {
            file.mkdirs();
        }
        return (1);
    }
    /**
     * Funciona basicamente como o metodo acima, mas em vez de mkdir() para criar a pasta eu executo o metodo delete()
     * para deleta-la.
     */
    public static int apagarDiretorio(String nomeDir, String nomePasta) {
        File file = new File(nomeDir + "/" + nomePasta);
        if ((file.exists()) && (file.isDirectory())) {
            file.delete();
        }
        return (1);
    }

    /**
     * Método que muda o diretório atual, funciona basicamente igual o comando 'cd' do terminal.
     */
    public static int mudarDiretorioTrabalho(String nomeDir, String arg) {
        /**
         * a string nomeDir é o path em que o usuário está e o arg é pra onde ele deseja navegar.
         * caso ele digite 'mdt ..' ele volta um diretório, caso ele digite 'mdt nome+do+diretório' ele entra nesse diretório.
         * no caso do 'mdt ..' foi criado um array chamado caminho aonde ele divide cada diretório em uma lista de arrays,
         * depois eu concateno esses diretórios menos o ultimo para que o usuário volte um diretório.
         * já se ele passar outro parãmetro fora o '..' o programa verifica se o diretório passado como parâmetro existe,
         * se existir ele muda o diretório atual, se não existir é exibido uma mensagem de erro.
         */
        String caminho[] = (nomeDir.split("/"));
        String mdtCaminho = "";
        if(arg.equals("..")){
            for(int i=0; i<caminho.length - 1; i++){
                mdtCaminho += caminho[i] + "/";
            }
            System.setProperty("user.dir", mdtCaminho);

        } else{

            File file = new File(nomeDir + "/" + arg);
            if ((file.exists()) && (file.isDirectory())) {
                System.setProperty("user.dir", file.getAbsolutePath());
            } else if(!(file.exists()) && (file.isDirectory())){
                System.out.println("diretório não existe :(");
            }
        }

        return (1);
    }


    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {
    }
}
