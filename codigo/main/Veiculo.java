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
    private Combustivel combustivel;
    private float capacidade;
    private float custosadicionais=0;



    Veiculo(String placa, float valordevenda, float pctseguro, float pctipva, float capacidade, String tipoveiculo, Combustivel combustivel){
        this.listarotas = new ArrayList<Rota>();
        this.placa=placa;
        this.valordevenda=valordevenda;
        this.capacidade = capacidade;
        this.pctipva=pctipva;
        this.pctseguro = pctseguro;
        this.tipoveiculo = tipoveiculo;
        this.combustivel = combustivel;
        this.kmplitro= combustivel.getKmlitro();
        this.autonomia=calcularAutonomia();
    }


    public float calcularAutonomia(){
        return capacidade*kmplitro;
    }

    public float calcularIPVA(){
        return valordevenda*pctipva;
    }

    public float calcularSeguro(){
        return this.valordevenda*pctseguro;
    }

    public abstract float calcularCustos();

    public float adicionarCustoManutencao(float preco){
        return custosadicionais+=preco;
    }


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
    public float calcularCustoRota(){
        float totalitros=0;
        float custo;
        for(Rota rota:listarotas){
            if(calcularAutonomia()<rota.getDistanciatotal()){
                totalitros+= (rota.getDistanciatotal()/calcularAutonomia()*capacidade)-capacidade;
            }
        }
        custo = Math.round(totalitros)*combustivel.getPrecoplitro();
        return custo;
    }

    public float calcularCustosGerais(){
        return calcularCustoRota()+calcularCustos()+calcularIPVA()+calcularSeguro()+custosadicionais;
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

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public ArrayList<Rota> getListarotas() {
        return listarotas;
    }
}
