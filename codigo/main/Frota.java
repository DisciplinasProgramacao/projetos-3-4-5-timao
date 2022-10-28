import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Frota {
    private String nomefrota;
    private ABB<Veiculo> listaveiculos;
    Frota(String nome){
        this.nomefrota = nome;
        this.listaveiculos = new ABB<Veiculo>();
    }
    public void carregar(String nomeArquivo) throws FileNotFoundException {
        String tipoveiculo;
        String placa;
        float kmporlitro;
        float valordevenda;
        Scanner leitor = new Scanner(new File(nomeArquivo));
        while(leitor.hasNextLine()){
            String linha = leitor.nextLine();
            String [] detalhes = linha.split(";");
            tipoveiculo = detalhes[0];
            placa = detalhes[1];
            kmporlitro = Float.parseFloat(detalhes[2]);
            valordevenda = Float.parseFloat(detalhes[3]);
            if(tipoveiculo.equals("carro")){
                this.listaveiculos.add(placa,new Carro(placa,kmporlitro,valordevenda));
            }
            if(tipoveiculo.equals("van")){
                this.listaveiculos.add(placa,new Van(placa,kmporlitro,valordevenda));
            }
            if(tipoveiculo.equals("furgao")){
                this.listaveiculos.add(placa,new Furgao(placa,kmporlitro,valordevenda));
            }
            else{
                this.listaveiculos.add(placa,new Caminhao(placa,kmporlitro,valordevenda));
            }
        }
    }

    public void salvar(String nomeArquivo) throws IOException {
        FileWriter myWriter = new FileWriter(nomeArquivo);
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        for(Veiculo veiculo: arrayveiculos){
            myWriter.write(veiculo.getTipoveiculo());
            myWriter.write(";");
            myWriter.write(veiculo.getPlaca());
            myWriter.write(";");
            myWriter.write(Float.toString(veiculo.getKmplitro()));
            myWriter.write(";");
            myWriter.write(Float.toString(veiculo.getValordevenda()));
            myWriter.write("\n");
        }
        myWriter.close();
    }

    public void incluirveiculo(Veiculo veiculo){
        this.listaveiculos.add(veiculo.getPlaca(),veiculo);
    }
    public boolean incluirrota(String placa,Rota rota){
        if(rota.checkAutonomia(this.listaveiculos.find(placa))==true){
            listaveiculos.find(placa).addRota(rota);
            return true;
        }
        return false;
    }
    public Veiculo localizarveiculo(String placa){
        return this.listaveiculos.find(placa);
    }
    public String imprimirrelatorio (String placa){
        Veiculo veiculo = this.listaveiculos.find(placa);
        float total = veiculo.calcularCustos() + veiculo.calcularSeguro() + veiculo.calcularIPVA();
        return "IPVA: " + veiculo.calcularIPVA() + "\n Seguro: "
                + veiculo.calcularSeguro() + "\n Outros Custos: "
                + veiculo.calcularCustos() + "\n Total: " + total;

    }

    public ABB<Veiculo> getListaveiculos() {
        return listaveiculos;
    }

}
