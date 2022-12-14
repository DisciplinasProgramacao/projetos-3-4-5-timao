import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCriarVeiculo {
    @Test
    public void testCustos() throws ExcecaoCombustivelInvalido {
        Carro carro1 = new Carro("PLM1234", 20000, Combustivel.GASOLINA);
        int i;
        for (i = 0; i < 21; i++) {
            Rota nova = new Rota("0/0/0", 500);
            carro1.addRota(nova);
        }
        assertEquals(80, carro1.calcularCustos(), 0.01);
    }

    @Test
    public void testCriarCarro() throws ExcecaoCombustivelInvalido {
        Carro carro1 = new Carro("PMX0345", 20000, Combustivel.GASOLINA);
        assertEquals(1300, carro1.calcularSeguro());
        assertEquals(800, carro1.calcularIPVA());
        assertEquals(250, carro1.calcularAutonomia());
    }

    @Test
    public void testCriarVan() throws ExcecaoCombustivelInvalido {
        Van van1 = new Van("FGX8645", 30000, Combustivel.GASOLINA);
        assertEquals(900, van1.calcularSeguro());
        assertEquals(900, van1.calcularIPVA());
        assertEquals(360, van1.calcularAutonomia());
    }

    @Test
    public void testCriarFurgao() throws ExcecaoCombustivelInvalido {
        Furgao furgao1 = new Furgao("THJ8946", 7, Combustivel.GASOLINA);
        assertEquals(150, furgao1.calcularSeguro());
        assertEquals(150, furgao1.calcularIPVA());
        assertEquals(560, furgao1.calcularAutonomia());
    }

    @Test
    public void testCriarCaminhao() throws ExcecaoCombustivelInvalido {
        Caminhao caminhao1 = new Caminhao("YVC7135", 4, Combustivel.DIESEL);
        assertEquals(3200, caminhao1.calcularSeguro());
        assertEquals(600, caminhao1.calcularIPVA());
        assertEquals(1000, caminhao1.calcularAutonomia());
    }
}
