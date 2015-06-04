import java.util.ArrayList;
import java.util.List;

public class Mochila {

    private int capacidadeMochila;
    private Objeto[] listaObjetos;
    private List<Cromossomo> populacao;
    private int numGeracoes;
    private int tamanhoPopulacao;
    private double somaBeneficio;
    private int geneCorte;

    public Mochila(int capacidadeMochila, Objeto[] listaObjetos, int numGeracoes, int tamanhoPopulacao) {
        this.capacidadeMochila = capacidadeMochila;
        this.listaObjetos = listaObjetos;
        this.numGeracoes = numGeracoes;
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.geneCorte = (int) Math.round(Math.random() * this.listaObjetos.length);
    }

    private void calculaSomaAvaliacoes() {
        this.somaBeneficio = 0;
        for (int i = 0; i < populacao.size(); ++i) {
            this.somaBeneficio += populacao.get(i).avaliacao();
        }
    }

    private void inicializaPopulacao() {
        int i;
        this.populacao = new ArrayList<Cromossomo>();
        for (i = 0; i < this.tamanhoPopulacao; i++) {
            Cromossomo crom = new Cromossomo(this.capacidadeMochila, this.listaObjetos, this.geneCorte);
            crom.inicializarAleatorio();
            this.populacao.add(crom);
        }
    }

    private int roleta() {
        double aux = 0;
        calculaSomaAvaliacoes();
        int i;
        double limite = Math.random() * this.somaBeneficio;
        for (i = 0; ((i < this.populacao.size()) && (aux < limite)); ++i) {
            aux += populacao.get(i).avaliacao();
        }
        /*Como somamos antes de testar, então tiramos 1 de i pois
         o anterior ao valor final consiste no elemento escolhido*/
        i--;
        return i;
    }

    private void novaGeracao() {
        List<Cromossomo> novaPopulacao = new ArrayList<Cromossomo>();
        Cromossomo pai1, pai2;
        Cromossomo[] filhos;

        for (int i = 0; i < this.populacao.size() / 2; i++) {
            pai1 = this.populacao.get(this.roleta());
            pai2 = this.populacao.get(this.roleta());
            filhos = pai1.crossover(pai2);
            filhos[0] = filhos[0].mutacao();
            filhos[1] = filhos[1].mutacao();

            novaPopulacao.add(filhos[0]);
            novaPopulacao.add(filhos[1]);
        }

        this.populacao = novaPopulacao;
    }

    private Cromossomo determinaMelhor() {
        Cromossomo melhorCromossomo = this.populacao.get(0);
        
        for (int i=1; i<this.populacao.size(); i++) {
            this.populacao.get(i);
            if (this.populacao.get(i).avaliacao() > melhorCromossomo.avaliacao()) {
               melhorCromossomo = this.populacao.get(i);
            }
        }
        return melhorCromossomo;
    }
    
    public Cromossomo executar(){
        this.inicializaPopulacao();
        for(int i=0; i<this.numGeracoes;i++){
            this.novaGeracao();
        }
        
        return this.determinaMelhor();
    }
}
