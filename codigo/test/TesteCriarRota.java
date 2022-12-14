import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCriarRota {

    @Test
    public void testCriarRota() {
        Rota rota1 = new Rota("10/09/2021", 500f);
        assertEquals(500f, rota1.getDistanciatotal());
        assertEquals("10/09/2021", rota1.getData());
    }
}
