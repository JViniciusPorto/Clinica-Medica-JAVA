package clinica_medica.model.especialidade;

import java.math.BigDecimal;

import clinica_medica.model.Paciente;
import clinica_medica.model.enums.PlanoSaude;

public interface Especialidade {
    int limiteConsultas();
    BigDecimal valorConsulta(BigDecimal valorBase);
    void validarPaciente(Paciente paciente);
    boolean aceitaPlano(PlanoSaude planoSaude);
}
