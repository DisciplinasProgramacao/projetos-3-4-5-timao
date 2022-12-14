public class Rota {

    private String data;
    private float distanciatotal;

    Rota(String data, float distanciatotal) {
        this.data = data;
        this.distanciatotal = distanciatotal;
    }

    public boolean checkAutonomia(Veiculo veiculo) {
        if (veiculo.getAutonomia() >= distanciatotal) {
            return true;
        }
        return false;
    }

    public float getDistanciatotal() {
        return distanciatotal;
    }

    public String getData() {
        return data;
    }
}
