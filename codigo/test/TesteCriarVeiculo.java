import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCriarVeiculo {
    @Test
    public void testCriarCarro(){
        Carro carro1 = new Carro("PMX0345", 5, 20000);
        assertEquals(1300, carro1.calcularSeguro());
        assertEquals(800, carro1.calcularIPVA());
        assertEquals(250, carro1.calcularAutonomia(50));
    }
    @Test
    public void testCriarVan(){
        Van van1 = new Van("FGX8645", 10, 30000);
        assertEquals(900, van1.calcularSeguro());
        assertEquals(900, van1.calcularIPVA());
        assertEquals(360, van1.calcularAutonomia(60));
    }
    @Test
    public void testCriarFurgao(){
        Furgao furgao1 = new Furgao("THJ8946", 7, 5000);
        assertEquals(150, furgao1.calcularSeguro());
        assertEquals(150, furgao1.calcularIPVA());
        assertEquals(560, furgao1.calcularAutonomia(80));
    }
    @Test
    public void testCriarCaminhao(){
        Caminhao caminhao1 = new Caminhao("YVC7135", 4, 60000);
        assertEquals(3200, caminhao1.calcularSeguro());
        assertEquals(600, caminhao1.calcularIPVA());
        assertEquals(1000, caminhao1.calcularAutonomia(250));
    }
}
