package clinica_medica.model;

import java.time.LocalDate;
import java.time.Period;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.model.enums.Genero;
import clinica_medica.model.enums.PlanoSaude;

public class Paciente extends Usuario{
    private LocalDate dataNascimento;
    private PlanoSaude planoSaude;
    private Prontuario prontuario;

    public Paciente(String nomeUsuario, String email, String senha, String CPF, String telefone, Genero genero,
            LocalDate dataNascimento, PlanoSaude planoSaude) {
        super(nomeUsuario, email, senha, CPF, telefone, genero);
        validarDataNascimento(dataNascimento);
        validarPlanoSaude(planoSaude);

        this.dataNascimento = dataNascimento;
        this.planoSaude = planoSaude;
        this.prontuario = new Prontuario();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public PlanoSaude getPlanoSaude() {
        return planoSaude;
    }
    //não é retornado uma cópia, pois eu pensei na possibilidade de realizar operações
    //como adicionar consultas, porém podemos alterar isso, dependendo da implementação
    //da classe Agenda
    public Prontuario getProntuario() {
        return prontuario;
    }
    
    public int calcularIdade() {
        return Period.between(dataNascimento,LocalDate.now()).getYears();
    }

    public void alterarDataNascimento(LocalDate dataDeNascimento){
        validarDataNascimento(dataDeNascimento);
        this.dataNascimento = dataDeNascimento;
    }

    public void alterarPlanoSaude(PlanoSaude planoSaude){
        validarPlanoSaude(planoSaude);
        this.planoSaude = planoSaude;
    }

    private void validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new DadosInvalidosException("Data de nascimento não informada!");
        }

        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new DadosInvalidosException("Data de nascimento inválida!");
        }
    }
    
    private void validarPlanoSaude(PlanoSaude planoSaude) {
        if (planoSaude == null) {
            throw new DadosInvalidosException("Plano não informado!");
        }
    }

}
