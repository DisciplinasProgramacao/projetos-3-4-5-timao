import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PseudoColumnUsage;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
        ABB<Frota> listadefrotas= new ABB<Frota>();
        String nomefrota;
        Scanner sc= new Scanner(System.in);
        int caso=-3;
        while(caso!=-1){
            System.out.println("\nSelecione uma das opções:");
            System.out.println("0 - Criar Frota\n"+
                    "1 - carregar um conjunto de veiculos de um arquivo\n" +
                    "2 - Salvar um conjunto de veiculos de um arquivo\n" +
                    "3 - Incluir um novo veiculo\n" +
                    "4 - Incluir rotas para um veiculo\n" +
                    "5 - Localizar um veiculo da frota\n" +
                    "6 - Imprimir um relatorio do veiculo com seus gastos até agora\n" +
                    "7 - Quilometragem média das rotas\n"+
                    "8 - Os 3 veículos com mais rotas\n"+
                    "9 - Lista descrescente de custos de cada veículo\n"+
                    "10 - Buscar rota pela data\n"+
                    "11 - Adicionar custo de manutenção\n"+
                    "------------- Digite -1 para sair ----------------\n");
            caso = sc.nextInt();
            switch(caso){
                case 0:
                    System.out.println("Nome da Frota: ");
                    nomefrota = sc.next();
                    listadefrotas.add(nomefrota,new Frota(nomefrota));
                    System.out.println("Frota Criada!\n");
                    break;
                case 1:
                    System.out.println("Nome da Frota: ");
                    nomefrota = sc.next();
                    System.out.println("Nome do Arquivo: ");
                    listadefrotas.find(nomefrota).carregar("./"+sc.next()+".txt");
                    System.out.println("Conjunto Carregado!\n");
                    break;
                case 2:
                    System.out.println("Informe o nome da frota:");
                    nomefrota = sc.next();
                    System.out.println("Nome do Arquivo: ");
                    listadefrotas.find(nomefrota).salvar("./"+sc.next()+".txt");
                    System.out.println("Arquivo Criado!\n");
                    break;
                case 3:
                    System.out.println("Nome da Frota: ");
                    nomefrota = sc.next();
                    System.out.println("Placa do veículo:");
                    String placa = sc.next();
                    System.out.println("Valor de venda do veículo:");
                    float valordevenda = sc.nextFloat();
                    System.out.println("\nSelecione o tipo de veiculo:\n" +
                            "1 - Carro\n" +
                            "2 - Van\n" +
                            "3 - Furgao\n" +
                            "4 - Caminhão\n"
                    );
                    int selectVeiculo = sc.nextInt();
                    switch (selectVeiculo){
                        case 1:
                            System.out.println("\nSelecione o tipo de Combustivel: "+
                                    "\n1 - Gasolina" +
                                    "\n2 - Etanol");
                            if(sc.nextInt()==1){
                                listadefrotas.find(nomefrota).incluirveiculo(new Carro(placa,valordevenda, Combustivel.GASOLINA));
                            }
                            else{
                                listadefrotas.find(nomefrota).incluirveiculo(new Carro(placa,valordevenda, Combustivel.ETANOL));
                            }
                            break;
                        case 2:
                            System.out.println("\nSelecione o tipo de Combustivel: "+
                                    "\n1 - Gasolina" +
                                    "\n2 - Diesel");
                            if(sc.nextInt()==1){
                                listadefrotas.find(nomefrota).incluirveiculo(new Van(placa,valordevenda, Combustivel.GASOLINA));
                            }
                            else{
                                listadefrotas.find(nomefrota).incluirveiculo(new Van(placa,valordevenda, Combustivel.DIESEL));
                            }
                            break;
                        case 3:
                            listadefrotas.find(nomefrota).incluirveiculo(new Furgao(placa,valordevenda, Combustivel.GASOLINA));
                            break;
                        case 4:
                            listadefrotas.find(nomefrota).incluirveiculo(new Caminhao(placa,valordevenda, Combustivel.DIESEL));
                            break;
                    }
                    System.out.println("Veículo Adicionado!\n");
                    break;
                case 4:
                    System.out.println("Data da rota: ");
                    String data = sc.next();
                    System.out.println("Distancia da rota: ");
                    float distancia = sc.nextFloat();
                    System.out.println("Nome da Frota: ");
                    nomefrota = sc.next();;
                    System.out.println("Placa do veiculo: ");
                    listadefrotas.find(nomefrota).incluirrota(sc.next(), new Rota(data,distancia));
                    System.out.println("Rota Adicionada!\n");
                    break;
                case 5:
                    System.out.println("Nome da Frota: ");
                    nomefrota = sc.next();
                    System.out.println("Placa do veículo: ");
                    System.out.println("Veículo Localizado: "+ listadefrotas.find(nomefrota).localizarveiculo(sc.next()));
                    break;
                case 6:
                    System.out.println("Nome da Frota: ");
                    nomefrota = sc.next();
                    System.out.println("Placa do Veiculo: ");
                    System.out.println(listadefrotas.find(nomefrota).imprimirrelatorio(sc.next()));
                    break;
                case 7:
                    System.out.println("Nome de Frota: ");
                    nomefrota = sc.next();
                    System.out.println(listadefrotas.find(nomefrota).quilometragemMediaRotas());
                    break;
                case 8:
                    System.out.println("Nome de Frota: ");
                    nomefrota = sc.next();
                    System.out.println(listadefrotas.find(nomefrota).quantidadeRotas());
                    break;
                case 9:
                    System.out.println("Nome de Frota: ");
                    nomefrota = sc.next();
                    System.out.println(listadefrotas.find(nomefrota).listaPorCusto());
                    break;
                case 10:
                    System.out.println("Nome de Frota: ");
                    nomefrota = sc.next();
                    System.out.println("Data da rota:");
                    System.out.println(listadefrotas.find(nomefrota).buscaRotas(sc.next()));
                    System.out.println("Rota Localizada!\n");
                    break;
                case 11:
                    System.out.println("Nome de Frota: ");
                    nomefrota = sc.next();
                    System.out.println("Placa do veiculo: ");
                    String placa2 = sc.next();
                    System.out.println("Custo de manutenção: ");
                    listadefrotas.find(nomefrota).getListaveiculos().find(placa2).adicionarCustoManutencao(sc.nextFloat());
                    System.out.println("Custo de Manutenção Adicionado!\n");
                    break;
            }

        }
    }
}
