package clinica_medica.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import clinica_medica.exceptions.ConsultaInvalidaException;
import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.model.enums.EstadoConsulta;

public class Consulta {
    private Medico medico;
    private Paciente paciente;
    private LocalDate data;
    private EstadoConsulta estado;
    private String sintomas;
    private String diagnostico;
    private String tratamento;
    private String medicamentos;
    private String exames;
    private String observacoes;
    private BigDecimal valorCobrado;

    private Avaliacao avaliacao;

    public Consulta(Medico medico, Paciente paciente, LocalDate data, BigDecimal valorCobrado) {
        validarMedico(medico);
        validarPaciente(paciente);
        validarData(data);
        validarValorCobrado(valorCobrado);

        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.valorCobrado = valorCobrado;
        this.estado = EstadoConsulta.AGENDADA;
    }

    
    public Medico getMedico() {
        return medico;
    }


    public Paciente getPaciente() {
        return paciente;
    }


    public LocalDate getData() {
        return data;
    }


    public EstadoConsulta getEstado() {
        return estado;
    }


    public String getSintomas() {
        return sintomas;
    }


    public String getDiagnostico() {
        return diagnostico;
    }


    public String getTratamento() {
        return tratamento;
    }


    public String getMedicamentos() {
        return medicamentos;
    }


    public String getExames() {
        return exames;
    }


    public String getObservacoes() {
        return observacoes;
    }


    public BigDecimal getValorCobrado() {
        return valorCobrado;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void realizarConsulta(String sintomas, String diagnostico, String tratamento, String medicamentos, String exames, String observacoes){
        validarDadosMedicos(sintomas, diagnostico, tratamento, medicamentos, exames, observacoes);

        if(estado == EstadoConsulta.AGENDADA){
            this.sintomas = sintomas;
            this.diagnostico = diagnostico;
            this.tratamento = tratamento;
            this.medicamentos = medicamentos;
            this.exames = exames;
            this.observacoes = observacoes;
            this.estado = EstadoConsulta.REALIZADA;
        }else{
            throw new ConsultaInvalidaException("Não é possível alterar consultas canceladas ou realizadas!");
        }
    }

    public void cancelarConsulta(){
        if(estado == EstadoConsulta.AGENDADA){
            estado = EstadoConsulta.CANCELADA;
        }else{
            throw new ConsultaInvalidaException("Não é possível cancelar uma consulta já cancelada ou realizada!");
        }
    }

    //fazer alguns testes, pois há uma situação em que o código quebra!
    public void associarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao == null) {
            throw new DadosInvalidosException("Nenhuma avaliação informada!");
        }

        if (this.estado != EstadoConsulta.REALIZADA) {
            throw new ConsultaInvalidaException("A consulta ainda não foi realizada!");
        }

        if (this.avaliacao != null) {
            throw new DadosInvalidosException("Esta consulta já possui uma avaliação!");
        }

        if (avaliacao.getConsulta() != this) {
            throw new DadosInvalidosException("A avaliação não pertence a esta consulta!");
        }

        this.avaliacao = avaliacao;
    }


    private void validarDadosMedicos(String sintomas, String diagnostico, String tratamento, String medicamentos, String exames, String observacoes){
        if(sintomas == null || sintomas.isBlank()){
            throw new DadosInvalidosException("O campo de sintomas está vazio!");
        }
        if(diagnostico == null || diagnostico.isBlank()){
            throw new DadosInvalidosException("O campo de diagnóstico está vazio");
        }
        if(tratamento == null || tratamento.isBlank()){
            throw new DadosInvalidosException("O campo de tratamento está vazio!");
        }

        //as verificações abaixo podem ser obrigatórias ou opcionais
        //deixei como obrigatórias, pois o arquivo do trabalho exige
        //essas informações, então para caso do médico não precisar usar
        //esses campos, a service irá cuidar disso.
        if(medicamentos == null || medicamentos.isBlank()){
            throw new DadosInvalidosException("O campo de medicamentos está vazio!");
        }
        if(exames == null || exames.isBlank()){
            throw new DadosInvalidosException("O campo de exames está vazio!");
        }
        if(observacoes == null || observacoes.isBlank()){
            throw new DadosInvalidosException("O campo de observações está vazio!");
        }
    }

    private void validarMedico(Medico medico){
        if(medico == null){
            throw new DadosInvalidosException("Médico não informado!");
        }
    }

    private void validarPaciente(Paciente paciente){
        if(paciente == null){
            throw new DadosInvalidosException("Paciente não informado!");
        }
    }
    
    private void validarData(LocalDate data){
        if(data == null){
            throw new DadosInvalidosException("Data não informada!");
        }

        if(data.isBefore(LocalDate.now())){
            throw new DadosInvalidosException("Data inválida!");
        }
    }

    private void validarValorCobrado(BigDecimal valorCobrado){
        if(valorCobrado == null){
            throw new DadosInvalidosException("Valor não informado!");
        }

        if(valorCobrado.compareTo(BigDecimal.ZERO)<0){
            throw new DadosInvalidosException("O valor da consulta não pode ser negativo!");
        }
    }
}
