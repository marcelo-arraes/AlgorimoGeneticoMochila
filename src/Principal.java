public class Principal {

    public static void main(String[] args) {

        Objeto[] objetos = {
            new Objeto(3, 5),
            new Objeto(3, 4),
            new Objeto(2, 7),
            new Objeto(4, 8),
            new Objeto(2, 4),
            new Objeto(3, 4),
            new Objeto(5, 6),
            new Objeto(2, 8)
        };
        
        Mochila mochila = new Mochila(25, objetos, 50, 100);
        Cromossomo melhor = mochila.executar();
        
        System.out.println("Melhor: "+melhor.toString());
        System.out.println("Peso Total: "+melhor.getPesoTotal());
        System.out.println("Soma Beneficio: "+melhor.avaliacao());
    }
}
