import java.util.ArrayList;

public abstract class Veiculo {
    private ArrayList<Rota> listarotas;
    private final String placa;
    private float kmplitro;
    private float valordevenda;
    private float pctipva;
    private float pctseguro;
    private float autonomia;
    private String tipoveiculo;


    Veiculo(String placa, float kmplitro, float valordevenda, float pctseguro, float pctipva, float capacidade, String tipoveiculo){
        this.listarotas = new ArrayList<Rota>();
        this.placa=placa;
        this.kmplitro=kmplitro;
        this.valordevenda=valordevenda;
        this.autonomia=calcularAutonomia(capacidade);
        this.pctipva=pctipva;
        this.pctseguro = pctseguro;
        this.tipoveiculo = tipoveiculo;

    }

    public float calcularAutonomia(float capacidade){
        return capacidade*kmplitro;
    }

    public float calcularIPVA(){
        return valordevenda*pctipva;
    }

    public float calcularSeguro(){
        return this.valordevenda*pctseguro;
    }

    public abstract float calcularCustos();

    public void addRota(Rota rota){

        listarotas.add(rota);
    }

    public float calcularTotalKm(){
        int total = 0;
        for(Rota rota:listarotas){
             total+=rota.getDistanciatotal();
        }
        return total;
    }

    public float getAutonomia() {
        return autonomia;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoveiculo() {
        return tipoveiculo;
    }


    public float getKmplitro() {
        return kmplitro;
    }

    public float getValordevenda() {
        return valordevenda;
    }


}
