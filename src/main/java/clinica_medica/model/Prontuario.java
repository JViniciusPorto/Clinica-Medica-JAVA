package clinica_medica.model;

import java.util.ArrayList;
import java.util.List;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.model.enums.EstadoConsulta;

public class Prontuario {
    private List<Consulta> consultas;

    public Prontuario() {
        consultas = new ArrayList<>();
    }


    public void adicionarConsulta(Consulta consulta){
        if(consulta == null){
            throw new DadosInvalidosException("A consulta não pode estar vazia!");
        }

        if(consulta.getEstado() != EstadoConsulta.REALIZADA){
            throw new DadosInvalidosException("Não é possível adicionar uma consulta não realizada!");
        }
        //é preciso conferir esse método, pois dependendo do que seria a mesma 
        //consulta, ele pode falhar
        if(consultas.contains(consulta)){
            throw new DadosInvalidosException("Essa consulta já foi adicionada ao prontuário!");
        }
        consultas.add(consulta);
    }

    public List<Consulta> getConsultas(){
        return List.copyOf(consultas);
    }
    
}
