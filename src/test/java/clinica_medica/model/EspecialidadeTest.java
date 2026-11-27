package clinica_medica.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.exceptions.EspecialidadeErradaException;
import clinica_medica.model.enums.Genero;
import clinica_medica.model.enums.PlanoSaude;
import clinica_medica.model.especialidade.CardioLogia;
import clinica_medica.model.especialidade.Dermatologia;
import clinica_medica.model.especialidade.Especialidade;
import clinica_medica.model.especialidade.Geriatria;
import clinica_medica.model.especialidade.Ginecologia;
import clinica_medica.model.especialidade.Pediatria;
import clinica_medica.model.especialidade.Radiologia;

public class EspecialidadeTest {
    static Stream<Arguments> valoresInvalidos(){
        return Stream.of(
            Arguments.of((BigDecimal) null),
            Arguments.of(BigDecimal.ZERO),
            Arguments.of(new BigDecimal("-100"))
        );
    }
    //os métodos abaixo mexem com datas, então conforme vão se passando os dias, podem ser que 
    //alguns testes falhem, por isso é importante conferir as datas usadas
    //datas abaixos passaram no teste do dia 27/11/2026
    static Stream<Arguments> idadesInvalidasGeriatria(){
        return Stream.of(
            Arguments.of(LocalDate.of(1966,12,31)),
            Arguments.of(LocalDate.of(1967, 11, 27)),
            Arguments.of(LocalDate.of(1968, 11, 27)),
            Arguments.of(LocalDate.of(1969, 11, 27))
        );
    }

    static Stream<Arguments> idadesValidasGeriatria(){
        return Stream.of(
            Arguments.of(LocalDate.of(1966,10,27)),
            Arguments.of(LocalDate.of(1966, 11, 27)),
            Arguments.of(LocalDate.of(1965,11,27))
        );
    }

    static Stream<Arguments> idadesInvalidasPediatria(){
        return Stream.of(
            Arguments.of(LocalDate.of(2008,11,27)),
            Arguments.of(LocalDate.of(2007,7,16)),
            Arguments.of(LocalDate.of(2008,10,27))
        );
    }

    static Stream<Arguments> idadesValidasPediatria(){
        return Stream.of(
            Arguments.of(LocalDate.of(2009, 11, 27)),
            Arguments.of(LocalDate.of(2008,12,31))
        );
    }

    @Test 
    void deveRetornarLimiteDeConsultasDeCardiologia(){
        Especialidade especialidade = new CardioLogia();
        assertEquals(2, especialidade.limiteConsultas());
    }

    @Test 
    void deveRetornarValorConsultaDeCardiologiaQuandoValorValido(){
        Especialidade especialidade = new CardioLogia();
        BigDecimal valorBase = new BigDecimal("100.0");
        assertEquals(130.0,especialidade.valorConsulta(valorBase).doubleValue());
    }

    @ParameterizedTest 
    @MethodSource("valoresInvalidos")
    void deveLancarExcecaoQuandoValorConsultaCardiologiaInvalido(BigDecimal valor){
        Especialidade especialidade = new CardioLogia();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.valorConsulta(valor)
        );
    }

    @Test
    void naoDeveLancarExcecaoQuandoValidarPacienteCardiologiaValido(){
        Especialidade especialidade = new CardioLogia();
        Paciente paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.MASCULINO, LocalDate.of(2007,7,16), PlanoSaude.NAO_TENHO);
        assertDoesNotThrow(
            () -> especialidade.validarPaciente(paciente)
        );
    }

    @Test 
    void deveLancarExcecaoQuandoValidarPacienteCardiologiaNull(){
        Especialidade especialidade = new CardioLogia();
        assertThrows(DadosInvalidosException.class, 
            () -> especialidade.validarPaciente(null)
        );
    }

    @Test 
    void deveRetornarBooleanoQuandoAceitaPlanoCardiologiaValido(){
        Especialidade especialidade = new CardioLogia();
        assertEquals(true, especialidade.aceitaPlano(PlanoSaude.HAPVIDA));
    }

    @Test
    void deveLancarExcecaoQuandoAceitaPlanoCardiologiaNull(){
        Especialidade especialidade = new CardioLogia();
        assertThrows(
            DadosInvalidosException.class,
            () -> especialidade.aceitaPlano(null)
        );
    }

    @Test 
    void deveRetornarLimiteDeConsultasDeDermatologia(){
        Especialidade especialidade = new Dermatologia();
        assertEquals(3, especialidade.limiteConsultas());
    }

    @Test 
    void deveRetornarValorConsultaDeDermatologiaQuandoValorValido(){
        Especialidade especialidade = new Dermatologia();
        BigDecimal valorBase = new BigDecimal("100.0");
        //só é válido se os parâmetros são do mesmo tipo, mesmo que seja 0 e 0.0 dá erro
        assertEquals(100.0, especialidade.valorConsulta(valorBase).doubleValue());
    }

    @ParameterizedTest 
    @MethodSource("valoresInvalidos") 
    void deveLancarExcecaoQuandoValorConsultaDermatologiaInvalido(BigDecimal valor){
        Especialidade especialidade = new Dermatologia();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.valorConsulta(valor)
        );
    }

    @Test
    void naoDeveLancarExcecaoQuandoValidarPacienteDermatologiaValido(){
        Especialidade especialidade = new Dermatologia();
        Paciente paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.MASCULINO, LocalDate.of(2007,7,16), PlanoSaude.NAO_TENHO);
        assertDoesNotThrow(
            () -> especialidade.validarPaciente(paciente)
        );
    }

    @Test 
    void deveLancarExcecaoQuandoValidarPacienteDermatologiaNull(){
        Especialidade especialidade = new Dermatologia();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.validarPaciente(null)
        );
    }

    @Test
    void deveRetornarBooleanoQuandoAceitaPlanoDermatologiaValido(){
        Especialidade especialidade = new Dermatologia();
        assertEquals(true, especialidade.aceitaPlano(PlanoSaude.BRADESCO));
        assertEquals(false, especialidade.aceitaPlano(PlanoSaude.HAPVIDA));
    }

    @Test
    void deveLancarExcecaoQuandoAceitaPlanoDermatologiaNull(){
        Especialidade especialidade = new Dermatologia();
        assertThrows(
            DadosInvalidosException.class,
            () -> especialidade.aceitaPlano(null)
        );
    }

    @Test 
    void deveRetornarLimiteDeConsultasDeGeriatria(){
        Especialidade especialidade = new Geriatria();
        assertEquals(3,especialidade.limiteConsultas());
    }

    @Test 
    void deveRetornarValorConsultaDeGeriatriaQuandoValorValido(){
        Especialidade especialidade = new Geriatria();
        BigDecimal valorBase = new BigDecimal("100.0");
        assertEquals(100.0, especialidade.valorConsulta(valorBase).doubleValue());
    }

    @ParameterizedTest 
    @MethodSource("valoresInvalidos")
    void deveLancarExcecaoQuandoValorConsultaGeriatriaInvalido(BigDecimal valor){
        Especialidade especialidade = new Geriatria();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.valorConsulta(valor)
        );
    }

    @ParameterizedTest 
    @MethodSource("idadesValidasGeriatria")
    void naoDeveLancarExcecaoQuandoValidarPacienteGeriatriaValido(LocalDate data){
        Especialidade especialidade = new Geriatria();
        Paciente paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.MASCULINO,data, PlanoSaude.NAO_TENHO);
        assertDoesNotThrow(
            () -> especialidade.validarPaciente(paciente)
        );
    }

    @Test 
    void deveLancarExcecaoQuandoValidarPacienteGeriatriaNull(){
        Especialidade especialidade = new Geriatria();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.validarPaciente(null)
        );
    }

    @ParameterizedTest 
    @MethodSource("idadesInvalidasGeriatria")
    void deveLancarExcecaoQuandoValidarPacienteGeriatriaIdadeInvalida(LocalDate data){
        Especialidade especialidade = new Geriatria();
        Paciente paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.MASCULINO, data, PlanoSaude.NAO_TENHO);
        assertThrows(
            EspecialidadeErradaException.class, 
            () -> especialidade.validarPaciente(paciente)
        );
    }

    @Test 
    void deveRetornarBooleanoQuandoAceitaPlanoGeriatriaValido(){
        Especialidade especialidade = new Geriatria();
        assertEquals(true, especialidade.aceitaPlano(PlanoSaude.HAPVIDA));
    }

    @Test 
    void deveLancarExcecaoQuandoAceitaPlanoGeriatriaNull(){
        Especialidade especialidade = new Geriatria();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.aceitaPlano(null)
        );
    }

    @Test 
    void deveRetornarLimiteDeConsultasDeGinecologia(){
        Especialidade especialidade = new Ginecologia();
        assertEquals(3, especialidade.limiteConsultas());
    }

    @Test 
    void deveRetornarValorConsultaDeGinecologiaQuandoValorValido(){
        Especialidade especialidade = new Ginecologia();
        BigDecimal valorBase = new BigDecimal("100.0");
        assertEquals(100.0, especialidade.valorConsulta(valorBase).doubleValue());
    }

    @ParameterizedTest 
    @MethodSource("valoresInvalidos")
    void deveLancarExcecaoQuandoValorConsultaGinecologiaInvalido(BigDecimal valor){
        Especialidade especialidade = new Ginecologia();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.valorConsulta(valor)
        );
    }

    @Test 
    void naoDeveLancarExcecaoQuandoValidarPacienteGinecologiaValido(){
        Especialidade especialidade = new Ginecologia();
        Paciente paciente = new Paciente("Carla", "carla@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.FEMININO, LocalDate.of(2007,7,16), PlanoSaude.NAO_TENHO);
        assertDoesNotThrow(
            () -> especialidade.validarPaciente(paciente)
        );
    }

    @Test
    void deveLancarExcecaoQuandoValidarPacienteGinecologiaNull(){
        Especialidade especialidade = new Ginecologia();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.validarPaciente(null)
        );
    }

    @Test 
    void deveLancarExcecaoQuandoValidarPacienteGinecologiaGeneroNaoFeminino(){
        Especialidade especialidade = new Ginecologia();
        Paciente paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.MASCULINO, LocalDate.of(2007,7,16), PlanoSaude.NAO_TENHO);
        assertThrows(
            EspecialidadeErradaException.class, 
            () -> especialidade.validarPaciente(paciente)
        );
    }

    @Test 
    void deveRetornarBooleanoQuandoAceitaPlanoGinecologiaValido(){
        Especialidade especialidade = new Ginecologia();
        assertEquals(true, especialidade.aceitaPlano(PlanoSaude.HAPVIDA));
    }

    @Test 
    void deveLancarExcecaoQuandoAceitaPlanoGinecologiaNull(){
        Especialidade especialidade = new Ginecologia();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.aceitaPlano(null)
        );
    }

    @Test 
    void deveRetornarLimiteDeConsultasDePediatria(){
        Especialidade especialidade = new Pediatria();
        assertEquals(2,especialidade.limiteConsultas());
    }
    
    @Test 
    void deveRetornarValorConsultaDePediatriaQuandoValorValido(){
        Especialidade especialidade = new Pediatria();
        BigDecimal valorBase = new BigDecimal("100.0");
        assertEquals(115.0, especialidade.valorConsulta(valorBase).doubleValue());
    }

    @ParameterizedTest 
    @MethodSource("valoresInvalidos")
    void deveLancarExcecaoQuandoValorConsultaPediatriaInvalido(BigDecimal valor){
        Especialidade especialidade = new Pediatria();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.valorConsulta(valor)
        );
    }

    @ParameterizedTest 
    @MethodSource("idadesValidasPediatria")
    void naoDeveLancarExcecaoQuandoValidarPacientePediatriaValido(LocalDate data){
        Especialidade especialidade = new Pediatria();
        Paciente paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.MASCULINO,data, PlanoSaude.NAO_TENHO);
        assertDoesNotThrow(
            () -> especialidade.validarPaciente(paciente)
        );
    }

    @Test 
    void deveLancarExcecaoQuandoValidarPacientePediatriaNull(){
        Especialidade especialidade = new Pediatria();
        assertThrows(
            DadosInvalidosException.class,
            () -> especialidade.validarPaciente(null)
        );
    }

    @ParameterizedTest 
    @MethodSource("idadesInvalidasPediatria")
    void deveLancarExcecaoQuandoValidarPacientePediatriaIdadeInvalida(LocalDate data){
        Especialidade especialidade = new Pediatria();
        Paciente paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.MASCULINO, data, PlanoSaude.NAO_TENHO);
        assertThrows(
            EspecialidadeErradaException.class, 
            () -> especialidade.validarPaciente(paciente)
        );
    }

    @Test
    void deveRetornarBooleanoQuandoAceitaPlanoPediatriaValido(){
        Especialidade especialidade = new Pediatria();
        assertEquals(true, especialidade.aceitaPlano(PlanoSaude.HAPVIDA));
    }

    @Test 
    void deveLancarExcecaoQuandoAceitaPlanoPediatriaNull(){
        Especialidade especialidade = new Pediatria();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.aceitaPlano(null)
        );
    }

    @Test 
    void deveRetornarLimiteDeConsultasDeRadiologia(){
        Especialidade especialidade = new Radiologia();
        assertEquals(1, especialidade.limiteConsultas());
    }

    @Test 
    void deveRetornarValorConsultaDeRadiologiaQuandoValorValido(){
        Especialidade especialidade = new Radiologia();
        BigDecimal valorBase = new BigDecimal("100.0");
        assertEquals(140.0, especialidade.valorConsulta(valorBase).doubleValue());
    }

    @ParameterizedTest 
    @MethodSource("valoresInvalidos")
    void deveLancarExcecaoQuandoValorConsultaRadiologiaInvalido(BigDecimal valor){
        Especialidade especialidade = new Radiologia();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.valorConsulta(valor)
        );
    }

    @Test 
    void naoDeveLancarExcecaoQuandoValidarPacienteRadiologiaValido(){
        Especialidade especialidade = new Radiologia();
        Paciente paciente = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "15777023010", "85989860278", Genero.MASCULINO, LocalDate.of(2007,7,16), PlanoSaude.NAO_TENHO);
        assertDoesNotThrow(
            () -> especialidade.validarPaciente(paciente)
        );   
    }

    @Test 
    void deveLancarExcecaoQuandoValidarPacienteRadiologiaNull(){
        Especialidade especialidade = new Radiologia();
        assertThrows(
            DadosInvalidosException.class,
            () -> especialidade.validarPaciente(null)
        );
    }

    @Test 
    void deveRetornarBooleanoQuandoAceitaPlanoRadiologiaValido(){
        Especialidade especialidade = new Radiologia();
        assertEquals(true, especialidade.aceitaPlano(PlanoSaude.HAPVIDA));
    }

    @Test
    void deveLancarExcecaoQuandoAceitaPlanoRadiologiaNull(){
        Especialidade especialidade = new Radiologia();
        assertThrows(
            DadosInvalidosException.class, 
            () -> especialidade.aceitaPlano(null)
        );
    }
}
