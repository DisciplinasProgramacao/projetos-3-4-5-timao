import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;

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
        String combustivel;
        float valordevenda;
        Scanner leitor = new Scanner(new File(nomeArquivo));
        while(leitor.hasNextLine()){
            String linha = leitor.nextLine();
            String [] detalhes = linha.split(";");
            tipoveiculo = detalhes[0];
            placa = detalhes[1];
            valordevenda = Float.parseFloat(detalhes[2]);
            combustivel = detalhes[3];
            if(tipoveiculo.equals("carro")){
                this.listaveiculos.add(placa,new Carro(placa,valordevenda,Combustivel.valueOf(combustivel)));
            }
            if(tipoveiculo.equals("van")){
                this.listaveiculos.add(placa,new Van(placa,valordevenda,Combustivel.valueOf(combustivel)));
            }
            if(tipoveiculo.equals("furgao")){
                this.listaveiculos.add(placa,new Furgao(placa,valordevenda,Combustivel.valueOf(combustivel)));
            }
            else{
                this.listaveiculos.add(placa,new Caminhao(placa,valordevenda,Combustivel.valueOf(combustivel)));
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
            myWriter.write(veiculo.getCombustivel().toString());
            myWriter.write("\n");
        }
        myWriter.close();
    }

    public float quilometragemMediaRotas(){
        float total=0;
        float count=0;
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        for(Veiculo veiculo:arrayveiculos){
            for(Rota rota:veiculo.getListarotas()){
                total+=rota.getDistanciatotal();
                count++;
            }
        }
        return total/count;
    }

    public void incluirveiculo(Veiculo veiculo){
        this.listaveiculos.add(veiculo.getPlaca(),veiculo);
    }
    public boolean incluirrota(String placa,Rota rota){
        if(rota!=null){
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
    public ArrayList<Veiculo> quantidadeRotas(){
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        ArrayList<Veiculo> lista = new ArrayList<>(Arrays.asList(arrayveiculos));
        ArrayList<Veiculo> result = new ArrayList<>();
        int temp = MIN_VALUE;
        int temp2=0;
        int i,j;
        for(i=0;i<3;i++){
            for(j=0;j<lista.size();j++){
                if(lista.get(j).getListarotas().size()>temp){
                    temp=lista.get(j).getListarotas().size();
                    temp2=j;
                }
            }
            result.add(lista.get(temp2));
            lista.remove(temp2);
            temp = MIN_VALUE;
        }
        return result;
    }

    public ArrayList<Veiculo> listaPorCusto(){
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        ArrayList<Veiculo> lista = new ArrayList<>(Arrays.asList(arrayveiculos));
        ArrayList<Veiculo> result = new ArrayList<>();
        Veiculo temp2 = arrayveiculos[0];
        float temp = MIN_VALUE;
        int i;
        for(i=0;i<lista.size();i++){
            for(Veiculo veiculo:lista){
                if(veiculo.calcularCustosGerais()>temp){
                    temp=veiculo.calcularCustosGerais();
                    temp2=veiculo;
                }
            }
            result.add(temp2);
            lista.remove(temp2);
            temp = MIN_VALUE;
            i--;
        }
        return result;
    }

    public ArrayList<Rota> buscaRotas(String data){
        Veiculo[] arrayveiculos = this.listaveiculos.allElements(new Veiculo[this.listaveiculos.size()]);
        ArrayList<Rota> result = new ArrayList<>();
        for(Veiculo veiculo:arrayveiculos){
            for(Rota rota:veiculo.getListarotas()){
                if(rota.getData().equals(data)){
                    result.add(rota);
                }
            }
        }
        return result;
    }

    public ABB<Veiculo> getListaveiculos() {
        return listaveiculos;
    }

}
