public class ExcecaoCombustivelInvalido extends Exception {
    public ExcecaoCombustivelInvalido(Combustivel combustivel) {
        super(combustivel + " Não é um tipo de combustivel válido para este veiculo!");
    }
}
