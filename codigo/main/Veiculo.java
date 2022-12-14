import java.util.ArrayList;

public abstract class Veiculo implements Subject {
    private ArrayList<Rota> listarotas;

    private ArrayList<Combustivel> listacombustivelvalido;
    private final String placa;
    private float kmplitro;
    private float valordevenda;
    private float pctipva;
    private float pctseguro;
    private float autonomia;
    private String tipoveiculo;
    private Combustivel combustivel;
    private float capacidade;
    private float tanque;
    private float custosadicionais = 0;

    private float custorota;

    private ArrayList<Observer> observadores = new ArrayList<Observer>();

    Veiculo(String placa, float valordevenda, float pctseguro, float pctipva, float capacidade, String tipoveiculo,
            Combustivel combustivel, ArrayList<Combustivel> listacombustivel) throws ExcecaoCombustivelInvalido {
        this.listarotas = new ArrayList<Rota>();
        this.placa = placa;
        this.valordevenda = valordevenda;
        this.tanque = capacidade;
        this.pctipva = pctipva;
        this.capacidade = capacidade;
        this.pctseguro = pctseguro;
        this.tipoveiculo = tipoveiculo;
        this.combustivel = combustivel;
        this.kmplitro = combustivel.getKmlitro();
        this.autonomia = calcularAutonomia();
        this.listacombustivelvalido = new ArrayList<Combustivel>(listacombustivel);
        if (!listacombustivelvalido.contains(combustivel)) {
            throw new ExcecaoCombustivelInvalido(combustivel);
        }

    }

    @Override
    public void assinar(Observer frota) {
        observadores.add(frota);
    }

    @Override
    public void desistir(Observer frota) {
        observadores.remove(frota);
    }

    @Override
    public void notificar() {

        for (Observer ob : observadores) {
            ob.update();
        }

    }

    public void reabastecer() {
        float restante = capacidade - tanque;
        custorota += restante * combustivel.getPrecoplitro();
        this.tanque = this.capacidade;
    }

    public float calcularAutonomia() {
        return tanque * kmplitro;
    }

    public float calcularIPVA() {
        return valordevenda * pctipva;
    }

    public float calcularSeguro() {
        return this.valordevenda * pctseguro;
    }

    public abstract float calcularCustos();

    public float adicionarCustoManutencao(float preco) {
        return custosadicionais += preco;
    }

    public void addRota(Rota rota) {
        calcularCustoRota(rota);
        listarotas.add(rota);
        notificar();
    }

    public float calcularTotalKm() {
        int total = 0;
        for (Rota rota : listarotas) {
            total += rota.getDistanciatotal();
        }
        return total;
    }

    public void calcularCustoRota(Rota rota) {
        float totalitros = rota.getDistanciatotal() / combustivel.getKmlitro();
        if(totalitros > 0) {
            if (tanque < totalitros) {
                totalitros -= tanque;
                tanque = 0;
                reabastecer();
            } else {
                tanque -= totalitros;
                totalitros = 0;
            }
        }
    }

    public int tamanhoListarotas() {
        return this.listarotas.size();
    }

    public float calcularCustosGerais() {
        return custorota + calcularCustos() + calcularIPVA() + calcularSeguro() + custosadicionais;
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

    public float getCustorota() {
        return custorota;
    }

    public float getCustosadicionais() {
        return custosadicionais;
    }

}
