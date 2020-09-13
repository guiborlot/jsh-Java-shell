package br.unifil.dc.sisop;

import java.io.File;
import java.util.Optional;


/**
 * Write a description of class ComandosInternos here.
 *
 * @author Guilherme Borlot Oliveira
 * @version 180823
 */
public final class ComandosInternos {

    /**
     * Função simples que retorna a data e hora local do sistema.
     */
    public static int exibirRelogio() {
        System.out.println(java.time.LocalDate.now());
        System.out.println(java.time.LocalTime.now());
        return (1);
    }

    /**
     * Recebe um array de strings que representa o path aonde o usuário está localizado.
     * Através da função .get() eu busco o path e passo como parâmetro para inicializar a classe File.
     * depois disso passo a lista de arquivos e diretórios para o array "afile", para essa operação foi usado
     * a função listFiles().
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
     * Funciona basicamente como a função acima, mas em vez de mkdir() para criar a pasta eu executo a função delete()
     * para deleta-la.
     */
    public static int apagarDiretorio(String nomeDir, String nomePasta) {
        File file = new File(nomeDir + "/" + nomePasta);
        if ((file.exists()) && (file.isDirectory())) {
            file.delete();
        }
        return (1);
    }

    public static int mudarDiretorioTrabalho(String nomeDir) {
        throw new RuntimeException("Método ainda não implementado");
    }

    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {
    }
}
