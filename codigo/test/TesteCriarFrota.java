import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class TesteCriarFrota {
    Frota frota1;
    Carro carro1;
    Rota rota1, rota2;

    @BeforeEach
    public void criarFrota() throws ExcecaoCombustivelInvalido {
        frota1 = new Frota("frotinha");
        carro1 = new Carro("PLM1234", 20000, Combustivel.GASOLINA);
        rota1 = new Rota("0/0/0", 150);
        rota2 = new Rota("0/0/0", 80);
    }

    @Test
    public void testarIncluirVeiculo() {
        assertEquals(0, frota1.getListaveiculos().size());
        frota1.incluirveiculo(carro1);
        assertEquals(1, frota1.getListaveiculos().size());
    }

    @Test
    public void testarIncluirRotaFalse() {
        frota1.incluirveiculo(carro1);
        assertFalse(frota1.incluirrota("PLM1234", rota1));

    }

    @Test
    public void testarIncluirRotaTrue() {
        frota1.incluirveiculo(carro1);
        assertTrue(frota1.incluirrota("PLM1234", rota2));
    }

    @Test
    public void testarLocalizarVeiculo() {
        frota1.incluirveiculo(carro1);
        assertEquals(carro1, frota1.localizarveiculo("PLM1234"));
    }

    @Test
    public void testarCarregarVeiculo() throws FileNotFoundException, ExcecaoCombustivelInvalido, ExcecaoCarregar {
        frota1.carregar("./Teste.txt");
    }

    @Test
    public void testarSalvarVeiculo() throws IOException {
        frota1.salvar("./Salvar.txt");
    }

    @Test
    public void testarmaxQuantidadeRotas() throws ExcecaoCombustivelInvalido {
        Carro carro2 = new Carro("a", 20000, Combustivel.GASOLINA);
        Carro carro3 = new Carro("e", 20000, Combustivel.GASOLINA);
        Carro carro4 = new Carro("i", 20000, Combustivel.GASOLINA);
        frota1.incluirveiculo(carro2);
        frota1.incluirveiculo(carro3);
        frota1.incluirveiculo(carro4);
        carro1.addRota(rota1);
        carro1.addRota(rota1);
        carro1.addRota(rota1);
        carro1.addRota(rota1);
        carro2.addRota(rota1);
        carro2.addRota(rota1);
        assertEquals(3, frota1.maxQuantidadeRotas().size());
    }

}
