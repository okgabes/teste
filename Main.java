/**
 * main
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {

            FileInputStream stream = new FileInputStream("arquivo.txt"); 
            InputStreamReader reader = new InputStreamReader(stream); 
            BufferedReader br = new BufferedReader(reader); 
            String linha = br.readLine(); 

            int indexVetores = 0;
            String[] codigo = new String[10];
            String[] nome = new String[10];
            String[] valor = new String[10];
            while(linha != null) { 
                
                codigo[indexVetores] = linha.substring(0, linha.indexOf('|')); 
                nome[indexVetores] = linha.substring(linha.indexOf('|') + 1); 
                valor[indexVetores] = linha.substring(linha.lastIndexOf('|') + 2); 
                linha = br.readLine(); 
                indexVetores++;
            }

            //transformando o valor em inteiro
            double[] valorInteiro = new double[valor.length];
            for (int l = 0; l < valorInteiro.length; l++) {
                
                if(valor[l] != null){
                    valorInteiro[l] = Double.parseDouble(valor[l]);
                }
            }
            
            
    
        //mostrar cardapio
        exibirListaComidas();

        //loop perguntando os itens da comanda junto do codigo do cliente

        //mostra o nome dos clientes
        exibirListaClientes();

        String valores = comanda();
        

        String valoresSeparados[] = valores.split(";");
        System.out.println(valoresSeparados[0]);    //codigo da compra
        System.out.println(valoresSeparados[1]);    //codigo do cliente
        System.out.println(valoresSeparados[2]);    //codigo da venda

        
        //criando um vetor de inteiros com os codigos
        char[] letr = null;
        String palavr = valoresSeparados[2];
        int[] vetorCodigo = new int[7]; 
        letr = palavr.toCharArray();
        for (int i = 0; i < letr.length; i++) {
            vetorCodigo[i] = Character.getNumericValue(letr[i]);
            System.out.println(vetorCodigo[i] +" - "+valorInteiro[vetorCodigo[i]-1]);
        }
        
        //valor do vetorCodigo[i] = (index do valorInteiro)-1
        double valorFinal[] = {0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < valorFinal.length; i++) {
            if(vetorCodigo[i]!=0){
                valorFinal[i] = valorFinal[i]+valorInteiro[vetorCodigo[i]-1];
            }
            
        }
        System.out.println("============================");
        for (int i = 0; i < valorFinal.length; i++) {
            System.out.println(valorFinal[i]);
        }

    }
    public static void exibirListaClientes() {
        //lista de clientes
        Cliente c1 = new Cliente("Diego Soares");
        Cliente c2 = new Cliente("Adriano Augusto");
        Cliente c3 = new Cliente("Gabriel Teixeira");
        Cliente c4 = new Cliente("Jamal Charanek");
        Cliente c5 = new Cliente("André Felipe");
        
        //exibindo nome dos Clientes
        System.out.println("Selecione o cliente pelo código ");
        System.out.println(" 1 | "+c1.getNome());
        System.out.println(" 2 | "+c2.getNome());
        System.out.println(" 3 | "+c3.getNome());
        System.out.println(" 4 | "+c4.getNome());
        System.out.println(" 5 | "+c5.getNome());
    }

    public static void exibirListaComidas() {
        Path arquivo = Paths.get("arquivo.txt");
        
        String arquivoInteiro ="";
        
        System.out.println("-------------Cardapio-----------------");
        try {
            List<String> linhas = Files.readAllLines(arquivo);
            
            for(String linha:linhas) {
            	arquivoInteiro= arquivoInteiro + linha;
            	arquivoInteiro= arquivoInteiro + ";";
                System.out.println(linha);
            }
            //linhas.forEach(linha -> System.out.println(linha));
        }catch(Exception e) {
            System.out.println("Errou algo");
        }
        
    }


    public static String comanda() {
        double valor[] = null;

        String dados ="";
        System.out.println("Deseja iniciar Comanda? \n s/n");
        System.out.print("  1 | Sim ");
        System.out.print("  2 | Não ");
        Scanner ler = new Scanner(System.in);
        int comanda = ler.nextInt();

        switch (comanda) {
            case 1:
                int[] codigos = new int[10];
                int[] clientes = new int[10];
                int indexCodigo = 0;

                int condicao = 1;

                do {
                    //exibe os itens do cardapio
                     System.out.println(" Selecione a comida pelo código ");
                     exibirListaComidas();
 
                     //le o codigo do cardapio
                     int codigoComida = ler.nextInt();
                     System.out.println(" ============================== ");
                     codigos[indexCodigo]= codigoComida;
 
                     //exibe os codigos do cliente
                     System.out.println(" Qual o codigo do cliente? ");
                     exibirListaClientes();
 
                     //le o codigo do cliente
                     int codigoCliente = ler.nextInt();
                     clientes[indexCodigo]= codigoCliente;
                     
                     indexCodigo++;
 
                     System.out.println(" ============================== ");
                     System.out.println("Deseja adicionar mais?");
                     System.out.print("  1 | Sim ");
                     System.out.print("  2 | Não ");
                     condicao = ler.nextInt();
                     System.out.println(" ============================== ");
                     
                 } while (condicao == 1);

                int codigoVenda = 0;
                int vetorCodigoVenda[]= new int[10];
                int vetorCodigoCliente[]= new int[10];
                int vetorCodigoPreco[]= new int[10];
                for (int i = 0; i < codigos.length; i++) {
                    codigoVenda = i+1;

                    vetorCodigoVenda[i] = codigoVenda;
                    vetorCodigoCliente[i] = clientes[i];
                    vetorCodigoPreco[i]  = codigos[i];

                    //dados += codigoVenda + " | " + clientes[i] + " | " + codigos[i]+"\n";


                }
                //juntar os dados
                for (int i = 0; i < vetorCodigoVenda.length; i++) {
                    dados+= vetorCodigoVenda[i];
                }
                dados+=";";
                for (int i = 0; i < vetorCodigoCliente.length; i++) {
                    dados+= vetorCodigoCliente[i];
                }
                dados+=";";
                for (int i = 0; i < vetorCodigoPreco.length; i++) {
                    dados+= vetorCodigoPreco[i];
                }
                
                //System.out.println(dados);

                break;
            
            case 2:
                System.out.println("Okay");
                break;
            default:
                break;
        }


        return dados;
    }
}