package clinica_medica.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.exceptions.EspecialidadeErradaException;
import clinica_medica.model.enums.Genero;
import clinica_medica.model.enums.PlanoSaude;
import clinica_medica.model.especialidade.Especialidade;

public class Medico extends Usuario{
    private Especialidade especialidade;
    private Set<PlanoSaude> planosAceitos;
    private BigDecimal valorBase;
    private List<Avaliacao> avaliacoesRecebidas;

    public Medico(String nomeUsuario, String email, String senha, String CPF, String telefone, Genero genero,
            Especialidade especialidade, Set<PlanoSaude> planosAceitos, BigDecimal valorBase) {
        super(nomeUsuario, email, senha, CPF, telefone, genero);

        validarEspecialidade(especialidade);
        this.especialidade = especialidade;

        validarPlanosAceitos(planosAceitos);
        this.planosAceitos = new HashSet<>(planosAceitos);

        validarValorBase(valorBase);
        this.valorBase = valorBase;

        adicionarPlanoAceito(PlanoSaude.NAO_TENHO);
        this.avaliacoesRecebidas = new ArrayList<>();
    }

    public boolean adicionarPlanoAceito(PlanoSaude planoSaude){
        if(planoSaude == null){
            throw new DadosInvalidosException("Nenhum plano de saúde informado!");
        }

        if(especialidade.aceitaPlano(planoSaude)){
            return planosAceitos.add(planoSaude);
        }

        return false;
    }

    public boolean removerPlano(PlanoSaude planoSaude){
        if(planoSaude == null){
            throw new DadosInvalidosException("Nenhum plano informado!");
        }

        if(planoSaude == PlanoSaude.NAO_TENHO){
            throw new DadosInvalidosException("Não é possível remover essa opção!");
        }
        
        return planosAceitos.remove(planoSaude);
    }

    public boolean aceitaPlano(PlanoSaude planoSaude){
        return planosAceitos.contains(planoSaude);
    }

    public BigDecimal calcularValorConsulta(){
        return especialidade.valorConsulta(valorBase);
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao == null) {
            throw new DadosInvalidosException("Nenhuma avaliação informada!");
        }

        if (avaliacao.getConsulta().getMedico() != this) {
            throw new DadosInvalidosException("Essa avaliação não pertence a este médico!");
        }

        avaliacao.getConsulta().associarAvaliacao(avaliacao);

        avaliacoesRecebidas.add(avaliacao);
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }
    
    public Set<PlanoSaude> getPlanosAceitos(){
        return Set.copyOf(planosAceitos);
    }

    public List<Avaliacao> getAvaliacoesRecebidas() {
        return List.copyOf(avaliacoesRecebidas);
    }

    public double calcularMediaAvaliacoes() {
        return avaliacoesRecebidas.stream().mapToInt(Avaliacao::getNota).average().orElse(0.0);
    }

    public int quantidadeDeAvaliacoes(){
        return avaliacoesRecebidas.size();
    }

    private void validarEspecialidade(Especialidade especialidade){
        if(especialidade == null){
            throw new DadosInvalidosException("Nenhuma especialidade informada!");
        }
    }

    private void validarPlanosAceitos(Set<PlanoSaude> planosAceitos){
        if(planosAceitos == null){
            throw new DadosInvalidosException("Nenhuma lista de planos foi passada!");
        }

        if(planosAceitos.contains(null)){
            throw new DadosInvalidosException("Há um elemento nulo na lista de planos");
        }

        for (PlanoSaude planoSaude : planosAceitos) {
            if(!especialidade.aceitaPlano(planoSaude)){
                throw new EspecialidadeErradaException("Devido as políticas da clínica, essa especialidade não aceita o plano "+planoSaude);
            }
        }
    }

    private void validarValorBase(BigDecimal valorBase){
        if(valorBase == null || valorBase.compareTo(BigDecimal.ZERO)<=0){
            throw new DadosInvalidosException("Valor base inválido!");
        }
    }
}
