package clinica_medica.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.model.enums.Genero;
import clinica_medica.model.enums.PlanoSaude;

public class PacienteTest {
    private Paciente paciente;

    @BeforeEach 
    void inicializarObjetoPaciente(){
        paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "31689702044", "85989860278", Genero.MASCULINO, LocalDate.of(2007, 7, 16), PlanoSaude.NAO_TENHO);
    }

    @Test
    @DisplayName("Caso Feliz: Deve criar o objeto paciente corretamente e as validações não vão lançar nenhuma exceção")
    void deveCriarPacienteQuandoDadosValidos(){
        Paciente pacienteValido = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "31689702044", "85989860278", Genero.MASCULINO, LocalDate.of(2007, 7, 16), PlanoSaude.NAO_TENHO);
        assertNotNull(pacienteValido);
        assertEquals(LocalDate.of(2007, 07, 16),pacienteValido.getDataNascimento());
        assertEquals(PlanoSaude.NAO_TENHO, pacienteValido.getPlanoSaude());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a data de nascimento seja um LocalDate não instanciado")
    void deveLancarExcecaoQuandoDataNascimentoNull(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, 
            () -> paciente.alterarDataNascimento(null)
        );
        assertEquals("Data de nascimento não informada!", excecao.getMessage());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a data de nascimento seja superior a data atual") 
    void deveLancarExcecaoCasoDataNascimentoForSuperiorDataAtual(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, 
            () -> paciente.alterarDataNascimento(LocalDate.of(2026,11,26))
        );
        assertEquals("Data de nascimento inválida!", excecao.getMessage());
    }

    @Test
    @DisplayName("Caso Limite: Deve alterar a data de nascimento sem lançar nenhuma exceção")
    void deveAlterarDataNascimentoQuandoDadoValido(){
        assertDoesNotThrow(() -> paciente.alterarDataNascimento(LocalDate.of(2016, 10, 16)));
        assertEquals(LocalDate.of(2016, 10, 16), paciente.getDataNascimento());
    }

    @Test
    @DisplayName("Caso Limite: Deve retornar a idade do paciente, quando o objeto foi recém instanciado")
    void deveRetornarIdadePaciente(){
        assertEquals(19, paciente.calcularIdade());
    }

    @Test 
    @DisplayName("Caso Limite: Deve retornar a idade do paciente atualizada, conforme a data de nascimento atribuída")
    void deveRetornarIdadePacienteAtualizadaQuandoDataNascimentoAlterada(){
        paciente.alterarDataNascimento(LocalDate.of(2007,12,16));
        assertEquals(18,paciente.calcularIdade());
    }

    @Test 
    void deveLancarExcecaoQuandoPlanoSaudeNull(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, 
            () -> paciente.alterarPlanoSaude(null)
        );
        assertEquals("Plano não informado!", excecao.getMessage());
    }
}
