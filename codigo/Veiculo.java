public abstract class Veiculo {
    private final String placa;
    private float kmplitro;
    private float valordevenda;
    private float ipva;
    private float seguro;
    private float autonomia;


    Veiculo(String placa,float kmplitro, float valordevenda, float pctSeguro, float pctIpva){
        this.placa=placa;
        this.kmplitro=kmplitro;
        this.valordevenda=valordevenda;
        autonomia=calcularAutonomia();
        ipva=calcularIPVA();
        seguro = pctSeguro;

    }

    private float calcularAutonomia(){
        return autonomia;
    }

    private float calcularIPVA(){
        return ipva;
    }

    public float calcularSeguro(){
        return this.valordevenda*seguro;
    }

    public abstract float calcularCustos();

    public void addRota(){
    }

    public float calcularTotalKm(){return 0;}


}
