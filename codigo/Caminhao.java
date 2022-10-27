public class Caminhao extends Veiculo{

    private static final float CAPACIDADE = 250;
    private static final float PCT_SEGURO = 0.02f;
    private static final float PCT_IPVA = 0.01f;

    private static final float ADICIONAL_SEGURO = 2000;
    private static final float MANUTENCAO = 1000;
    private static final float VISTORIA = 1000;



    public Caminhao (String placa, float kmplitro, float valordevenda){
        super(placa, kmplitro, valordevenda, PCT_SEGURO, PCT_IPVA);
    }

    public float calcularSeguro() {
        float seguro = super.calcularSeguro();
        seguro += ADICIONAL_SEGURO;
        return seguro;
    }

    @Override
    public float calcularCustos(){
        return 0;
    }
}

