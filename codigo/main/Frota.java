import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Frota implements Observer {
    private String nomefrota;
    private ABB<Veiculo> listaveiculos;

    Frota(String nome) {
        this.nomefrota = nome;
        this.listaveiculos = new ABB<Veiculo>();
    }

    @Override
    public void update() {
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        ArrayList<Veiculo> lista = new ArrayList<>(Arrays.asList(arrayveiculos));
        if (lista.size() > 3) {
            this.maxQuantidadeRotas();
        }
    }

    public void carregar(String nomeArquivo) throws FileNotFoundException, ExcecaoCombustivelInvalido, ExcecaoCarregar {
        String tipoveiculo;
        String placa;
        String combustivel;
        float valordevenda;
        Scanner leitor = new Scanner(new File(nomeArquivo));
        while (leitor.hasNextLine()) {
            String linha = leitor.nextLine();
            String[] detalhes = linha.split(";");
            tipoveiculo = detalhes[0];
            placa = detalhes[1];
            valordevenda = Float.parseFloat(detalhes[2]);
            combustivel = detalhes[3];
            switch (tipoveiculo) {
                case "carro":
                    this.listaveiculos.add(placa, new Carro(placa, valordevenda, Combustivel.valueOf(combustivel)));
                    break;
                case "van":
                    this.listaveiculos.add(placa, new Van(placa, valordevenda, Combustivel.valueOf(combustivel)));
                    break;
                case "furgao":
                    this.listaveiculos.add(placa, new Furgao(placa, valordevenda, Combustivel.valueOf(combustivel)));
                    break;
                case "caminhao":
                    this.listaveiculos.add(placa, new Caminhao(placa, valordevenda, Combustivel.valueOf(combustivel)));
                    break;
                default:
                    throw new ExcecaoCarregar(tipoveiculo);

            }

        }
    }

    public void salvar(String nomeArquivo) throws IOException {
        FileWriter myWriter = new FileWriter(nomeArquivo);
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        for (Veiculo veiculo : arrayveiculos) {
            myWriter.write(veiculo.getTipoveiculo());
            myWriter.write(";");
            myWriter.write(veiculo.getPlaca());
            myWriter.write(";");
            myWriter.write(Float.toString(veiculo.getKmplitro()));
            myWriter.write(";");
            myWriter.write(veiculo.getCombustivel().toString());
            myWriter.write("\n");
        }
        myWriter.close();
    }

    public float quilometragemMediaRotas() {
        double count = 0;
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        double mediatotal = 0;
        for (Veiculo veiculo : arrayveiculos) {
            mediatotal += veiculo.getListarotas().stream().mapToDouble(r -> r.getDistanciatotal()).sum();
            count += veiculo.getListarotas().size();
        }
        return (float) (mediatotal / count);
    }

    public void incluirveiculo(Veiculo veiculo) {
        this.listaveiculos.add(veiculo.getPlaca(), veiculo);
        veiculo.assinar(this);
    }

    public boolean incluirrota(String placa, Rota rota) {
        if (rota != null) {
            if (rota.getDistanciatotal() < listaveiculos.find(placa).calcularAutonomia()) {
                listaveiculos.find(placa).addRota(rota);
                return true;
            } else {
                listaveiculos.find(placa).reabastecer();
                listaveiculos.find(placa).addRota(rota);
                return true;
            }
        }
        return false;
    }

    public Veiculo localizarveiculo(String placa) {
        return this.listaveiculos.find(placa);
    }

    public String imprimirrelatorio(String placa) {
        Veiculo veiculo = this.listaveiculos.find(placa);
        return "\nIPVA: " + veiculo.calcularIPVA() + "\nSeguro: "
                + veiculo.calcularSeguro() + "\nCusto Manutenção: " + veiculo.getCustosadicionais() +
                "\nCusto das Rotas: " + veiculo.getCustorota() + "\nOutros Custos: "
                + veiculo.calcularCustos() + "\nTotal: " + veiculo.calcularCustosGerais();

    }

    public ArrayList<Veiculo> maxQuantidadeRotas() {

        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        ArrayList<Veiculo> lista = new ArrayList<>(Arrays.asList(arrayveiculos));
        ArrayList<Veiculo> result = new ArrayList<>();
        int tamanho = lista.size();

        while (result.size() < tamanho) {
            Veiculo v = lista.stream().max(Comparator.comparing(Veiculo::tamanhoListarotas)).get();
            result.add(v);
            lista.remove(v);
        }

        return result;
    }

    public ArrayList<Veiculo> maxListaPorCusto() {

        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        ArrayList<Veiculo> lista = new ArrayList<>(Arrays.asList(arrayveiculos));
        ArrayList<Veiculo> result = new ArrayList<>();
        int tamanho = lista.size();

        while (result.size() < tamanho) {
            Veiculo v = lista.stream().max(Comparator.comparing(Veiculo::calcularCustosGerais)).get();
            result.add(v);
            lista.remove(v);
        }

        return result;
    }

    public ArrayList<Rota> buscaRotas(String data) {
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        ArrayList<Rota> result = new ArrayList<Rota>();
        for (Veiculo veiculo : arrayveiculos) {
            result = (ArrayList<Rota>) veiculo.getListarotas().stream().filter(r -> r.getData().equals(data))
                    .collect(Collectors.toList());
        }
        if (result.size() == 0) {
            return null;
        }
        return result;
    }

    public ABB<Veiculo> getListaveiculos() {
        return listaveiculos;
    }

    public String getNomefrota() {
        return nomefrota;
    }

}
