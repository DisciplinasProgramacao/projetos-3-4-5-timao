import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Aplicacao {
    public static void main(String[] args) {
        ABB<Frota> listadefrotas = new ABB<Frota>();
        String nomefrota = null;
        Scanner sc = new Scanner(System.in);
        int valor;
        int caso = -3;
        int caso2 = -3;
        String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
        while (caso != -1) {
            try {
                System.out.println("\nSelecione ou crie uma frota:");
                System.out.println("0 - Criar Frota.\n" + "1 - Selecionar Frota Existente.\n"
                        + "------------- Digite -1 para voltar ----------------\n");
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
                        try {
                            System.out.println("\nFrotas existentes: ");
                            for (Frota frota : listadefrotas.allElements(new Frota[listadefrotas.size()])) {
                                System.out.println(frota.getNomefrota());
                            }
                            System.out.println("\nNome da Frota: ");
                            nomefrota = sc.next();
                            if (listadefrotas.find(nomefrota) != null) {
                                System.out.println("Frota selecionada!\n");
                            }
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            nomefrota = null;
                        }
                        break;
                    default:
                        System.out.println("Opção inexistente! Tente novamente!");
                        break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                if (nomefrota != null) {
                    while (caso2 != -1) {

                        System.out.println("\nSelecione uma das opções:");
                        System.out.println(
                                "1 - Carregar um conjunto de veiculos de um arquivo.\n" +
                                        "2 - Salvar o conjunto de veiculos em um arquivo.\n" +
                                        "3 - Incluir um novo veiculo.\n" +
                                        "4 - Incluir rotas para um veiculo.\n" +
                                        "5 - Localizar um veiculo da frota.\n" +
                                        "6 - Imprimir um relatorio do veiculo com seus gastos até agora.\n" +
                                        "7 - Quilometragem média das rotas.\n" +
                                        "8 - Os 3 veículos com mais rotas.\n" +
                                        "9 - Lista em ordem descrescente de custos de cada veículo.\n" +
                                        "10 - Buscar rota pela data.\n" +
                                        "11 - Adicionar custo de manutenção em um veiculo.\n" +
                                        "------------- Digite -1 para voltar ----------------\n");
                        System.out.print("Digite um número: ");
                        caso2 = sc.nextInt();
                        switch (caso2) {
                            case 1:
                                System.out.println("\nNome do Arquivo: ");
                                try {
                                    listadefrotas.find(nomefrota).carregar("./" + sc.next() + ".txt");
                                    System.out.println("Conjunto Carregado!\n");
                                } catch (FileNotFoundException | ExcecaoCombustivelInvalido | ExcecaoCarregar e) {
                                    System.out.println(e.toString());
                                }
                                break;
                            case 2:
                                System.out.println("\nNome do Arquivo: ");
                                try {
                                    listadefrotas.find(nomefrota).salvar("./" + sc.next() + ".txt");
                                    System.out.println("Arquivo Criado!\n");
                                } catch (IOException e) {
                                    System.out.println(e.toString());
                                }
                                break;
                            case 3:
                                int selectVeiculo = 0;
                                System.out.println("\nPlaca do Veículo:");
                                String placa = sc.next();
                                System.out.println("\nValor de venda do veículo:");
                                float valordevenda = sc.nextFloat();
                                do {
                                    System.out
                                            .println("\nSelecione o tipo de veiculo:\n" + "1 - Carro.\n" + "2 - Van.\n"
                                                    + "3 - Furgao.\n" + "4 - Caminhão.\n");
                                    selectVeiculo = sc.nextInt();
                                    switch (selectVeiculo) {
                                        case 1:
                                            valor = 0;
                                            do {
                                                try {
                                                    System.out.println(
                                                            "\nSelecione o tipo de Combustivel: " + "\n1 - Gasolina."
                                                                    + "\n2 - Etanol.");
                                                    valor = sc.nextInt();
                                                    switch (valor) {
                                                        case 1:
                                                            listadefrotas.find(nomefrota)
                                                                    .incluirveiculo(
                                                                            new Carro(placa, valordevenda,
                                                                                    Combustivel.GASOLINA));
                                                            break;
                                                        case 2:
                                                            listadefrotas.find(nomefrota)
                                                                    .incluirveiculo(
                                                                            new Carro(placa, valordevenda,
                                                                                    Combustivel.ETANOL));
                                                            break;
                                                        default:
                                                            System.out.println("Número inválido!");
                                                            break;
                                                    }
                                                } catch (RuntimeException | ExcecaoCombustivelInvalido e) {
                                                    System.out.println(e.toString());
                                                }
                                            } while (valor < 1 || valor > 2);
                                            break;
                                        case 2:
                                            valor = 0;
                                            do {
                                                try {
                                                    System.out.println(
                                                            "\nSelecione o tipo de Combustivel: " + "\n1 - Gasolina."
                                                                    + "\n2 - Diesel.");
                                                    valor = sc.nextInt();
                                                    switch (valor) {
                                                        case 1:
                                                            listadefrotas.find(nomefrota)
                                                                    .incluirveiculo(
                                                                            new Van(placa, valordevenda,
                                                                                    Combustivel.GASOLINA));
                                                            break;
                                                        case 2:
                                                            listadefrotas.find(nomefrota)
                                                                    .incluirveiculo(
                                                                            new Van(placa, valordevenda,
                                                                                    Combustivel.DIESEL));
                                                            break;
                                                        default:
                                                            System.out.println("Número inválido!");
                                                            break;
                                                    }
                                                } catch (RuntimeException | ExcecaoCombustivelInvalido e) {
                                                    System.out.println(e.toString());
                                                }
                                            } while (valor < 1 || valor > 2);
                                            break;
                                        case 3:
                                            try {
                                                listadefrotas.find(nomefrota)
                                                        .incluirveiculo(
                                                                new Furgao(placa, valordevenda, Combustivel.GASOLINA));
                                            } catch (ExcecaoCombustivelInvalido e) {
                                                System.out.println(e.toString());
                                            }
                                            break;
                                        case 4:
                                            try {
                                                listadefrotas.find(nomefrota)
                                                        .incluirveiculo(
                                                                new Caminhao(placa, valordevenda, Combustivel.DIESEL));
                                            } catch (ExcecaoCombustivelInvalido e) {
                                                System.out.println(e.toString());
                                            }
                                            break;
                                        default:
                                            System.out.println("Número Inválido!");
                                            break;

                                    }
                                } while (selectVeiculo < 1 || selectVeiculo > 4);
                                System.out.println("Veículo Adicionado!\n");
                                break;
                            case 4:
                                System.out.println("\nData da rota: ");
                                String data = sc.next();
                                if (data.matches(datePattern) == true) {
                                    System.out.println("Distancia da rota: ");
                                    float distancia = sc.nextFloat();
                                    System.out.println("\nPlacas existentes:");
                                    for (Veiculo veiculo : listadefrotas.find(nomefrota).getListaveiculos().allElements(
                                            new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])) {
                                        System.out.println(veiculo.getTipoveiculo() + ": " + veiculo.getPlaca());
                                    }
                                    System.out.println("\nPlaca do veiculo: ");
                                    placa = sc.next();
                                    if (listadefrotas.find(nomefrota).localizarveiculo(placa) != null) {
                                        listadefrotas.find(nomefrota).incluirrota(placa, new Rota(data, distancia));
                                        System.out.println("Rota Adicionada!\n");
                                    } else {
                                        System.out.println("\nVeículo não localizado!");
                                    }
                                    break;
                                } else {
                                    System.out.println("\nTente colocar a data no formato: dd/mm/yyyy\n");
                                    break;
                                }

                            case 5:
                                System.out.println("\nPlacas existentes:");
                                for (Veiculo veiculo : listadefrotas.find(nomefrota).getListaveiculos().allElements(
                                        new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])) {
                                    System.out.println(veiculo.getTipoveiculo() + ": " + veiculo.getPlaca());
                                }
                                System.out.println("\nPlaca do veículo: ");
                                placa = sc.next();
                                if (listadefrotas.find(nomefrota).localizarveiculo(placa) != null) {
                                    System.out.println(
                                            "Veículo Localizado: "
                                                    + listadefrotas.find(nomefrota).localizarveiculo(placa));
                                } else {
                                    System.out.println("Veiculo não Encontrado!");
                                }
                                break;
                            case 6:
                                System.out.println("\nPlacas existentes:");
                                for (Veiculo veiculo : listadefrotas.find(nomefrota).getListaveiculos().allElements(
                                        new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])) {
                                    System.out.println(veiculo.getTipoveiculo() + ": " + veiculo.getPlaca());
                                }
                                System.out.println("\nPlaca do Veiculo: ");
                                placa = sc.next();
                                if (listadefrotas.find(nomefrota).localizarveiculo(placa) != null) {
                                    System.out.println(listadefrotas.find(nomefrota).imprimirrelatorio(placa));
                                } else {
                                    System.out.println("Veículo não encontrado!");
                                }
                                break;
                            case 7:
                                System.out.println("\nQuilometragem média rotas: "
                                        + listadefrotas.find(nomefrota).quilometragemMediaRotas());
                                break;
                            case 8:
                                int i = 0;
                                System.out.println("\nLista dos 3 veiculos com mais rotas: ");
                                for (Veiculo veiculo : listadefrotas.find(nomefrota).maxQuantidadeRotas()) {
                                    if (i < 3) {
                                        System.out.println("Veículo: {\n" + "  tipo: " + veiculo.getTipoveiculo()
                                                + ",\n  placa: " + veiculo.getPlaca() + ",\n  tamanho_rotas: "
                                                + veiculo.getListarotas().size() + "\n}\n");
                                    }
                                    i++;
                                }
                                break;
                            case 9:
                                System.out.println("\nLista em ordem decrescente dos custos por carro: ");
                                for (Veiculo veiculo : listadefrotas.find(nomefrota).maxListaPorCusto()) {
                                    System.out.println("Veículo: {\n" + "  tipo: " + veiculo.getTipoveiculo()
                                            + ",\n  placa: " + veiculo.getPlaca() + ",\n  custo_gerais: "
                                            + veiculo.calcularCustosGerais() + "\n}\n");
                                }
                                break;
                            case 10:
                                System.out.println("\nData da rota:");
                                data = sc.next();
                                if (listadefrotas.find(nomefrota).buscaRotas(data) != null) {
                                    System.out.println(listadefrotas.find(nomefrota).buscaRotas(data).toString());
                                    System.out.println("Rota Localizada!\n");
                                } else {
                                    System.out.println("Nenhuma rota foi encontrada.");
                                }
                                break;
                            case 11:
                                System.out.println("\nPlacas existentes:");
                                for (Veiculo veiculo : listadefrotas.find(nomefrota).getListaveiculos().allElements(
                                        new Veiculo[listadefrotas.find(nomefrota).getListaveiculos().size()])) {
                                    System.out.println(veiculo.getTipoveiculo() + ": " + veiculo.getPlaca());
                                }
                                System.out.println("\nPlaca do veiculo: ");
                                String placa2 = sc.next();
                                System.out.println("Custo de manutenção: ");
                                listadefrotas.find(nomefrota).getListaveiculos().find(placa2)
                                        .adicionarCustoManutencao(sc.nextFloat());
                                System.out.println("Custo de Manutenção Adicionado!\n");
                                break;
                            case -1:
                                continue;
                            default:
                                System.out.println("\nNúmero invalido!");
                                break;
                        }
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                sc.next();
            }
        }
        sc.close();
    }
}
