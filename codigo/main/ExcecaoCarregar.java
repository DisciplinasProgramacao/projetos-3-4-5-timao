public class ExcecaoCarregar extends Exception {
    public ExcecaoCarregar(String erro) {
        super("Motivo do erro: " + erro + "\n\nExemplo de formato:\n"
                + "tipodeveiculo;placa;valordevenda;COMBUSTIVEL\ncarro;PMX098;20000.0;GASOLINA\n\n");
    }
}
