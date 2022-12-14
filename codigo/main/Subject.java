public interface Subject {

    public void assinar(Observer observer);

    public void desistir(Observer observer);

    public void notificar();

}
