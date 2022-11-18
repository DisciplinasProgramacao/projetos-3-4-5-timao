import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
        Frota frota1 = new Frota ("frotinha");
        frota1.carregar("./Teste.txt");
        frota1.salvar("./Write.txt");
//        System.out.println(carrinho.getAutonomia());
//        Rota rota1 = new Rota("0/0/0",500);
//        System.out.println(frota1.incluirrota("FER678",rota1));
//        Carro carro1 = new Carro("PCE234", 20000, Combustivel.GASOLINA);
//        Carro carro2 = new Carro("o", 20000, Combustivel.GASOLINA);
//        Carro carro3 = new Carro("e", 20000, Combustivel.GASOLINA);
//        Carro carro4 = new Carro("i", 20000, Combustivel.GASOLINA);
//        Rota rota1 = new Rota("10/09/2002",2000);
//        Rota rota2 = new Rota("10",1000);
//        Rota rota3 = new Rota("10",1500);
//        Rota rota4 = new Rota("10",2000);
//        carro1.addRota(rota1);
//        carro2.addRota(rota2);
//        carro3.addRota(rota3);
//        carro4.addRota(rota4);
//        Frota frota1 = new Frota ("frotinha");
//        frota1.incluirveiculo(carro1);
//        frota1.incluirveiculo(carro2);
//        frota1.incluirveiculo(carro3);
//        frota1.incluirveiculo(carro4);
//        carro1.adicionarCustoManutencao(100);
//        System.out.println(frota1.buscaRotas("110"));
//        for(Veiculo veiculo:frota1.listaPorCusto()){
//            System.out.println(veiculo.calcularCustosGerais());
//        }


    }
}
