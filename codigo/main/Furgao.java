public class Furgao extends Veiculo{
    private static final float CAPACIDADE = 80;
    private static final float PCT_SEGURO = 0.03f;
    private static final float PCT_IPVA = 0.03f;
    private static final float ALINHAMENTO = 120;
    private static final float VISTORIA = 500;



    public Furgao (String placa, float kmplitro, float valordevenda){
        super(placa, kmplitro, valordevenda, PCT_SEGURO, PCT_IPVA, CAPACIDADE,"furgao");
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

