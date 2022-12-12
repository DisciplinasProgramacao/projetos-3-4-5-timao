import java.util.ArrayList;
import java.util.Arrays;

public class Van extends Veiculo{

    private static final ArrayList<Combustivel> LISTACOMBUSTIVEL = new ArrayList<Combustivel>(Arrays.asList(Combustivel.GASOLINA,Combustivel.DIESEL));
    private static final float CAPACIDADE = 60;
    private static final float PCT_SEGURO = 0.03f;
    private static final float PCT_IPVA = 0.03f;
    private static final float ALINHAMENTO = 120;
    private static final float VISTORIA = 500;



    public Van (String placa, float valordevenda, Combustivel combustivel) throws ExcecaoCombustivelInvalido{
        super(placa, valordevenda, PCT_SEGURO, PCT_IPVA, CAPACIDADE,"van", combustivel,LISTACOMBUSTIVEL);
    }

    public float calcularSeguro() {
        float seguro = super.calcularSeguro();
        return seguro;
    }

    @Override
    public float calcularCustos(){
        float totalkm = super.calcularTotalKm();
        float kmdividido = totalkm%10000;
        float total = 0;
        if(totalkm/10000 >= 1){
            total += (totalkm-kmdividido)/10000 * ALINHAMENTO;
            total += (totalkm-kmdividido)/10000 * VISTORIA;
            return total;
        }
        return total;
    }
}
