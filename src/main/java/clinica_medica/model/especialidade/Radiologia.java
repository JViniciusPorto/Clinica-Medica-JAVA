package clinica_medica.model.especialidade;

import java.math.BigDecimal;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.model.Paciente;
import clinica_medica.model.enums.PlanoSaude;

public class Radiologia implements Especialidade{
    @Override
    public int limiteConsultas() {
        return 1;
    }

    @Override
    public BigDecimal valorConsulta(BigDecimal valorBase) {
        if(valorBase == null || valorBase.compareTo(BigDecimal.ZERO)<=0){
            throw new DadosInvalidosException("Valor base inválido!");
        }
        BigDecimal percentual = new BigDecimal("0.40");
        return valorBase.multiply(BigDecimal.ONE.add(percentual));
    }

    @Override
    public void validarPaciente(Paciente paciente) {
        if(paciente == null){
            throw new DadosInvalidosException("Nenhum paciente informado!");
        }
    }

    @Override
    public boolean aceitaPlano(PlanoSaude planoSaude) {
        return true;
    }
}
