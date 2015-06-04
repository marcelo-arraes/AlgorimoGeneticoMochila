public class Objeto {

    private double beneficio;
    private int peso;

    public Objeto() {
    }

    public Objeto(int beneficio, int peso) {
        this.beneficio = beneficio;
        this.peso = peso;
    }

    public double getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(double beneficio) {

        this.beneficio = beneficio;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}
