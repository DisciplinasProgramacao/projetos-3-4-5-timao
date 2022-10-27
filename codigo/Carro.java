public class Carro extends Veiculo{
    private static final float CAPACIDADE = 50;
    private static final float PCT_SEGURO = 0.05f;
    private static final float PCT_IPVA = 0.04f;
    private static final float ADICIONAL_SEGURO = 300;
    private static final float ALINHAMENTO = 80;



    public Carro (String placa, float kmplitro, float valordevenda){
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
