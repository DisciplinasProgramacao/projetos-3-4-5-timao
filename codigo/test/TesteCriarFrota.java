import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteCriarFrota {
    Frota frota1;
    Carro carro1;
    Rota rota1,rota2;
    @BeforeEach
    public void criarFrota(){
        frota1 = new Frota("frotinha");
        carro1 = new Carro("PLM1234",2,20000);
        rota1 = new Rota("0/0/0", 150);
        rota2 = new Rota("0/0/0", 80);
    }
    @Test
    public void testarIncluirVeiculo(){
        assertEquals(0, frota1.getListaveiculos().size());
        frota1.incluirveiculo(carro1);
        assertEquals(1, frota1.getListaveiculos().size());
    }
    @Test
    public void testarIncluirRota(){
        frota1.incluirveiculo(carro1);
        assertFalse(frota1.incluirrota("PLM1234", rota1));
        assertTrue(frota1.incluirrota("PLM1234", rota2));
    }
    @Test
    public void testarLocalizarVeiculo(){
        frota1.incluirveiculo(carro1);
        assertEquals(carro1,frota1.localizarveiculo("PLM1234"));
    }

}
