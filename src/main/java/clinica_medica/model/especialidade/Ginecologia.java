package clinica_medica.model.especialidade;

import java.math.BigDecimal;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.exceptions.EspecialidadeErradaException;
import clinica_medica.model.Paciente;
import clinica_medica.model.enums.Genero;
import clinica_medica.model.enums.PlanoSaude;

public class Ginecologia implements Especialidade{
    @Override
    public int limiteConsultas() {
        return 3;
    }

    @Override
    public BigDecimal valorConsulta(BigDecimal valorBase) {
        if(valorBase == null || valorBase.compareTo(BigDecimal.ZERO)<=0){
            throw new DadosInvalidosException("Valor base inválido!");
        }
        
        return valorBase;
    }

    @Override
    public void validarPaciente(Paciente paciente) {
        if(paciente == null){
            throw new DadosInvalidosException("Nenhum paciente informado!");
        }

        if(paciente.getGenero() != Genero.FEMININO){
            throw new EspecialidadeErradaException("A ginecologia atende somente pacientes mulheres");
        }
    }

    @Override
    public boolean aceitaPlano(PlanoSaude planoSaude) {
        if(planoSaude == null){
            throw new DadosInvalidosException("Nenhum plano de saúde informado!");
        }
        return true;
    }
    
}
