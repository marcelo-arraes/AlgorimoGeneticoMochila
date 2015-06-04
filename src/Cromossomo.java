
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcelo
 */
public class Cromossomo {
    private List<Integer> cromossomo;
    private final double taxaMutacao = 0.005; //0.5%
    private int geneCorte;
    private int capacidadeMochila;
    private Objeto[] listaObjetos;

    public Cromossomo(int geneCorte) {
        this.cromossomo = new ArrayList<Integer>();
        this.geneCorte = geneCorte;
    }

    public Cromossomo(List<Integer> cromossomo,int geneCorte) {
        this.cromossomo = cromossomo;
        this.geneCorte = geneCorte;
    }
    
    public Cromossomo(int capacidadeMochila, Objeto[] listaObjetos, int geneCorte) {
        this.cromossomo = new ArrayList<Integer>();
        this.geneCorte = geneCorte;
        this.capacidadeMochila = capacidadeMochila;
        this.listaObjetos = listaObjetos;
        //this.inicializarAleatorio();
    }
    
    public void inicializarAleatorio(){
        for (int i = 0; i < this.listaObjetos.length; i++) {
            if (Math.random() < 0.5) {
                this.append(0);
            } else {
                this.append(1);
            }
        }
    }
    
    public List<Integer> getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List<Integer> cromossomo) {
        this.cromossomo = cromossomo;
    }
    
    public void append(int gene){
        this.cromossomo.add(gene);
    }
    
    public int get(int index){
        return this.cromossomo.get(index);
    }
    
    public int size(){
        return this.cromossomo.size();
    }
    
     /**
     * Realiza a mutação gerando um filho para nova população com uma taxa definida na variavel taxaMutacao
     */
    public Cromossomo mutacao(){
        Cromossomo filho = new Cromossomo(capacidadeMochila, listaObjetos, geneCorte);

        for(int i=0; i<this.size(); i++){
            if(Math.random()<this.taxaMutacao){
                if(this.get(i) == 1){
                    filho.append(0);
                }else{
                    filho.append(1);
                }
            }else{
                filho.append(this.get(i));
            }
        }
        
        return filho;
    }
    
    public Cromossomo[] crossover(Cromossomo outroPai){
        Cromossomo[] filhos = new Cromossomo[2];
        filhos[0] = new Cromossomo(capacidadeMochila, listaObjetos, geneCorte);
        filhos[1] = new Cromossomo(capacidadeMochila, listaObjetos, geneCorte);
        
        for(int i=0; i<=this.geneCorte; i++){
            filhos[0].append(outroPai.get(i));
            filhos[1].append(this.get(i));
        }
        
        for(int i=this.geneCorte+1; i<this.size(); i++){
            filhos[0].append(this.get(i));
            filhos[1].append(outroPai.get(i));
        }
        
        return filhos;
    }
    
    /**
     * Avalia se o cromossomo não ultrapassou o limite de peso. Se tiver
     * ultrapassado retorna um valor bem baixo, caso contrario passou na
     * avaliaçao e retorna o valor total. Não será retornado zero, pois valores
     * de avaliação negativos ou zero não são bem tolerados por outros
     * componentes do Agente.
     */
    public double avaliacao() {
        double retorno = 0;
        int somaPesos = 0;

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) == 1) {
                retorno += this.listaObjetos[i].getBeneficio();
                somaPesos += this.listaObjetos[i].getPeso();
            }
        }

        if (somaPesos > this.capacidadeMochila) {
            retorno = 1;
        }

        return retorno;
    }

    public int getPesoTotal(){
        int somaPesos = 0;

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) == 1) {
                somaPesos += this.listaObjetos[i].getPeso();
            }
        }

        return somaPesos;
    }
    
    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        
        for (Integer gene : cromossomo) {
            ret.append(gene);
        }
        
        return ret.toString();
    }
    
    
}
