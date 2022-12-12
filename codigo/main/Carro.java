import java.util.ArrayList;
import java.util.Arrays;

public class Carro extends Veiculo{

    private static final ArrayList<Combustivel> LISTACOMBUSTIVEL = new ArrayList<Combustivel>(Arrays.asList(Combustivel.GASOLINA,Combustivel.ETANOL));
    private static final float CAPACIDADE = 50;
    private static final float PCT_SEGURO = 0.05f;
    private static final float PCT_IPVA = 0.04f;
    private static final float ADICIONAL_SEGURO = 300;
    private static final float ALINHAMENTO = 80;

    public Carro (String placa, float valordevenda, Combustivel combustivel) throws ExcecaoCombustivelInvalido {
        super(placa, valordevenda, PCT_SEGURO, PCT_IPVA, CAPACIDADE, "carro",combustivel,LISTACOMBUSTIVEL);
    }

    public float calcularSeguro() {
        float seguro = super.calcularSeguro();
        seguro += ADICIONAL_SEGURO;
        return seguro;
    }

    @Override
    public float calcularCustos(){
        float totalkm = super.calcularTotalKm();
        float kmdividido = totalkm%10000;
        if(totalkm/10000 >= 1){
            return (totalkm-kmdividido)/10000 * ALINHAMENTO;
        }
        return 0;
    }
}
