import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PseudoColumnUsage;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
        ABB<Frota> listadefrotas = new ABB<Frota>();
        String nomefrota = null;
        Scanner sc = new Scanner(System.in);
        int caso = -3;
        int caso2 = -3;
        String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
        while (caso != -1) {
            System.out.println("\nSelecione ou crie uma frota:");
            System.out.println("0 - Criar Frota\n" + "1 - Selecionar Frota Existente\n" + "------------- Digite -1 para voltar ----------------\n");
            System.out.println("Digite um número: ");
            caso = sc.nextInt();
            caso2 = -3;
            switch (caso) {
                case 0:
                    System.out.println("Nome da Frota: ");
                    nomefrota = sc.next();
                    listadefrotas.add(nomefrota, new Frota(nomefrota));
                    System.out.println("Frota criada e selecionada!\n");
                    break;
                case 1:
                    System.out.println("\nFrotas existentes: ");
                    for(Frota frota:listadefrotas.allElements(new Frota[listadefrotas.size()])){
                        System.out.println(frota.getNomefrota());
                    }
                    System.out.println("\nNome da Frota: ");
                    nomefrota = sc.next();
                    if(listadefrotas.find(nomefrota)!=null){
                        System.out.println("Frota selecionada!\n");
                    }
                    else{
                        throw new RuntimeException("Esta frota não existe!");
                    }
                    break;
                default:
                    throw new RuntimeException("Número Inválido!");
            }
            TimeUnit.SECONDS.sleep(1);
            if(nomefrota!=null){
                while (caso2 != -1) {

                    System.out.println("\nSelecione uma das opções:");
                    System.out.println(
                            "1 - carregar um conjunto de veiculos de um arquivo\n" +
                            "2 - Salvar o conjunto de veiculos em um arquivo\n" +
                            "3 - Incluir um novo veiculo\n" +
                            "4 - Incluir rotas para um veiculo\n" +
                            "5 - Localizar um veiculo da frota\n" +
                            "6 - Imprimir um relatorio do veiculo com seus gastos até agora\n" +
                            "7 - Quilometragem média das rotas\n" +
                            "8 - Os 3 veículos com mais rotas\n" +
                            "9 - Lista descrescente de custos de cada veículo\n" +
                            "10 - Buscar rota pela data\n" +
                            "11 - Adicionar custo de manutenção em um veiculo\n" +
                            "------------- Digite -1 para voltar ----------------\n");
                    System.out.print("Digite um número: ");
                    caso2 = sc.nextInt();
                    switch (caso2) {
                        case 1:
                            System.out.println("\nNome do Arquivo: ");
                            listadefrotas.find(nomefrota).carregar("./" + sc.next() + ".txt");
                            System.out.println("Conjunto Carregado!\n");
                            break;
                        case 2:
                            System.out.println("\nNome do Arquivo: ");
                            listadefrotas.find(nomefrota).salvar("./" + sc.next() + ".txt");
                            System.out.println("Arquivo Criado!\n");
                            break;
                        case 3:
                            System.out.println("\nPlacas existentes:");
                            for(Veiculo veiculo:listadefrotas.find(nomefrota).getListaveiculos().allElements(new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])){
                                System.out.println(veiculo.getPlaca());
                            }
                            System.out.println("\nPlaca do Veículo:");
                            String placa = sc.next();
                            System.out.println("\nValor de venda do veículo:");
                            float valordevenda = sc.nextFloat();
                            System.out.println("\nSelecione o tipo de veiculo:\n" + "1 - Carro\n" + "2 - Van\n" + "3 - Furgao\n" + "4 - Caminhão\n");
                            int selectVeiculo = sc.nextInt();
                            switch (selectVeiculo) {
                                case 1:
                                    System.out.println("\nSelecione o tipo de Combustivel: " + "\n1 - Gasolina" + "\n2 - Etanol");
                                    if (sc.nextInt() == 1) {
                                        listadefrotas.find(nomefrota).incluirveiculo(new Carro(placa, valordevenda, Combustivel.GASOLINA));
                                    }
                                    if (sc.nextInt() == 2){
                                        listadefrotas.find(nomefrota).incluirveiculo(new Carro(placa, valordevenda, Combustivel.ETANOL));
                                    }
                                    else{
                                        throw new RuntimeException("Número Inválido!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("\nSelecione o tipo de Combustivel: " + "\n1 - Gasolina" + "\n2 - Diesel");
                                    if (sc.nextInt() == 1) {
                                        listadefrotas.find(nomefrota).incluirveiculo(new Van(placa, valordevenda, Combustivel.GASOLINA));
                                    }
                                    if (sc.nextInt() == 2) {
                                        listadefrotas.find(nomefrota).incluirveiculo(new Van(placa, valordevenda, Combustivel.DIESEL));
                                    }
                                    else{
                                        throw new RuntimeException("Número Inválido!");
                                    }
                                    break;
                                case 3:
                                    listadefrotas.find(nomefrota).incluirveiculo(new Furgao(placa, valordevenda, Combustivel.GASOLINA));
                                    break;
                                case 4:
                                    listadefrotas.find(nomefrota).incluirveiculo(new Caminhao(placa, valordevenda, Combustivel.DIESEL));
                                    break;
                                default:
                                    throw new RuntimeException("Número Inválido!");

                            }
                            System.out.println("Veículo Adicionado!\n");
                            break;
                        case 4:
                            System.out.println("\nData da rota: ");
                            String data = sc.next();
                            if(data.matches(datePattern)==true){
                                System.out.println("Distancia da rota: ");
                                float distancia = sc.nextFloat();
                                System.out.println("\nPlacas existentes:");
                                for(Veiculo veiculo:listadefrotas.find(nomefrota).getListaveiculos().allElements(new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])){
                                    System.out.println(veiculo.getPlaca());
                                }
                                System.out.println("\nPlaca do veiculo: ");
                                placa = sc.next();
                                if(listadefrotas.find(nomefrota).localizarveiculo(placa)!=null){
                                    listadefrotas.find(nomefrota).incluirrota(placa, new Rota(data, distancia));
                                    System.out.println("Rota Adicionada!\n");
                                }
                                else{
                                    System.out.println("\nVeículo não localizado!");
                                }
                                break;
                            }
                            else{
                                System.out.println("\nTente colocar a data no formato: dd/mm/yyyy\n");
                                break;
                            }

                        case 5:
                            System.out.println("\nPlacas existentes:");
                            for(Veiculo veiculo:listadefrotas.find(nomefrota).getListaveiculos().allElements(new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])){
                                System.out.println(veiculo.getPlaca());
                            }
                            System.out.println("\nPlaca do veículo: ");
                            placa = sc.next();
                            if(listadefrotas.find(nomefrota).localizarveiculo(placa)!=null){
                                System.out.println("Veículo Localizado: " + listadefrotas.find(nomefrota).localizarveiculo(placa));
                            }
                            else{
                                System.out.println("Veiculo não Encontrado!");
                            }
                            break;
                        case 6:
                            System.out.println("\nPlacas existentes:");
                            for(Veiculo veiculo:listadefrotas.find(nomefrota).getListaveiculos().allElements(new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])){
                                System.out.println(veiculo.getPlaca());
                            }
                            System.out.println("\nPlaca do Veiculo: ");
                            placa = sc.next();
                            if(listadefrotas.find(nomefrota).localizarveiculo(placa)!=null){
                                System.out.println(listadefrotas.find(nomefrota).imprimirrelatorio(placa));
                            }
                            else{
                                System.out.println("Veículo não encontrado!");
                            }
                            break;
                        case 7:
                            System.out.println(listadefrotas.find(nomefrota).quilometragemMediaRotas());
                            break;
                        case 8:
                            System.out.println(listadefrotas.find(nomefrota).quantidadeRotas());
                            break;
                        case 9:
                            System.out.println(listadefrotas.find(nomefrota).listaPorCusto());
                            break;
                        case 10:
                            System.out.println("\nData da rota:");
                            data = sc.next();
                            if(listadefrotas.find(nomefrota).buscaRotas(data).size()>0){
                                System.out.println(listadefrotas.find(nomefrota).buscaRotas(data).toString());
                                System.out.println("Rota Localizada!\n");
                            }
                            else{
                                System.out.println("Nenhuma rota foi encontrada.");
                            }
                            break;
                        case 11:
                            System.out.println("\nPlacas existentes:");
                            for(Veiculo veiculo:listadefrotas.find(nomefrota).getListaveiculos().allElements(new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])){
                                System.out.println(veiculo.getPlaca());
                            }
                            System.out.println("\nPlaca do veiculo: ");
                            String placa2 = sc.next();
                            System.out.println("Custo de manutenção: ");
                            listadefrotas.find(nomefrota).getListaveiculos().find(placa2).adicionarCustoManutencao(sc.nextFloat());
                            System.out.println("Custo de Manutenção Adicionado!\n");
                            break;
                        case -1:
                            continue;
                        default:
                            System.out.println("\nNúmero invalido!");
                            break;
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
            }

        }

    }
}
