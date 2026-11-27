package clinica_medica.model.especialidade;

import java.math.BigDecimal;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.exceptions.EspecialidadeErradaException;
import clinica_medica.model.Paciente;
import clinica_medica.model.enums.PlanoSaude;

public class Pediatria implements Especialidade {
    @Override
    public int limiteConsultas() {
        return 2;
    }

    @Override
    public BigDecimal valorConsulta(BigDecimal valorBase) {
        if(valorBase == null || valorBase.compareTo(BigDecimal.ZERO)<=0){
            throw new DadosInvalidosException("Valor base inválido!");
        }
        BigDecimal percentual = new BigDecimal("0.15");
        return valorBase.multiply(BigDecimal.ONE.add(percentual));
    }

    @Override
    public void validarPaciente(Paciente paciente) {
        if(paciente == null){
            throw new DadosInvalidosException("Nenhum paciente informado!");
        }

        if(paciente.calcularIdade()>=18){
            throw new EspecialidadeErradaException("A pediatria atende somente pacientes com até 17 anos!");
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
