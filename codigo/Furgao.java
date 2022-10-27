public class Furgao extends Veiculo{
    private static final float CAPACIDADE = 80;
    private static final float PCT_SEGURO = 0.03f;
    private static final float PCT_IPVA = 0.03f;
    private static final float ALINHAMENTO = 120;
    private static final float VISTORIA = 500;



    public Furgao (String placa, float kmplitro, float valordevenda){
        super(placa, kmplitro, valordevenda, PCT_SEGURO, PCT_IPVA);
    }

    public float calcularSeguro() {
        float seguro = super.calcularSeguro();
        return seguro;
    }

    @Override
    public float calcularCustos(){
        return 0;
    }
}

