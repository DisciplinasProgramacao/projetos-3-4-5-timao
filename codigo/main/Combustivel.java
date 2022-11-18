public enum Combustivel {
    GASOLINA(12.0f,4.80f),
    ETANOL(8.0f,3.65f),
    DIESEL(3.5f,6.65f);
    private float kmlitro;

    private float precoplitro;

    Combustivel(float kmlitro, float precoplitro) {
        this.kmlitro = kmlitro;
        this.precoplitro = precoplitro;
    }

    public float getPrecoplitro() {
        return precoplitro;
    }

    public float getKmlitro() {
        return kmlitro;
    }
}
