package clinica_medica.model;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.model.enums.Genero;

public abstract class Usuario {
    private String nomeUsuario;
    private String email;
    private String senha;
    private String CPF;
    private String telefone;
    private Genero genero;
    
    public Usuario(String nomeUsuario, String email, String senha, String CPF, String telefone, Genero genero) {
        validarNomeUsuario(nomeUsuario);
        validarEmail(email);
        validarSenha(senha);
        validarCPF(CPF);
        validarTelefone(telefone);
        validarGenero(genero);
        
        this.nomeUsuario = nomeUsuario.trim().replaceAll("\\s+", " ");
        this.email = email.trim();
        this.senha = senha;
        this.CPF = CPF.replaceAll("\\D","");
        this.telefone = telefone.replaceAll("\\D", "");
        this.genero = genero;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public Genero getGenero() {
        return genero;
    }

    public void alterarNomeUsuario(String nomeUsuario) {
        validarNomeUsuario(nomeUsuario);
        this.nomeUsuario = nomeUsuario.trim().replaceAll("\\s+"," ");
    }

    public void alterarEmail(String email) {
        validarEmail(email);
        this.email = email.trim();
    }

    public void alterarSenha(String senha) {
        validarSenha(senha);
        this.senha = senha;
    }

    public void alterarTelefone(String telefone) {
        validarTelefone(telefone);
        this.telefone = telefone.replaceAll("\\D","");
    }

    public void alterarGenero(Genero genero){
        validarGenero(genero);
        this.genero = genero;
    }

    private void validarNomeUsuario(String nomeUsuario){
        if(nomeUsuario == null || nomeUsuario.isBlank()){
            throw new DadosInvalidosException("O nome não pode ser vazio!");
        }
    }

    private void validarEmail(String email){
        if(email == null || email.isBlank()){
            throw new DadosInvalidosException("O email não pode ser vazio!");
        }

        email = email.trim();

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if(!email.matches(regex)){
            throw new DadosInvalidosException("Formato de email inválido!");
        }
    }

    private void validarSenha(String senha){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&*!?_-])[A-Za-z\\d@#$%&*!?_-]{8,}$";
        if(senha==null || !senha.matches(regex)){
            throw new DadosInvalidosException("A senha deve conter pelo menos 8 caracteres!");
        }
    }

    private void validarCPF(String CPF){
        if(CPF==null){
            throw new DadosInvalidosException("CPF não informado!");
        }

        CPF = CPF.replaceAll("\\D","");

        if(CPF.length()!=11){
            throw new DadosInvalidosException("O CPF deve ter 11 dígitos!");
        }

        if(CPF.matches("(\\d)\\1{10}")){
            throw new DadosInvalidosException("CPF inválido!");
        }

        int primeiroDigito = calcularDigitoCPF(CPF, 9);
        int segundoDigito = calcularDigitoCPF(CPF, 10);

        if(CPF.charAt(9) - '0' != primeiroDigito || CPF.charAt(10) - '0' != segundoDigito){
            throw new DadosInvalidosException("O CPF possui digítos verificadores inválidos");
        }
        
    }
    
    private void validarTelefone(String telefone) {
        if (telefone == null) {
            throw new DadosInvalidosException("Telefone não informado!");
        }

        String numeros = telefone.replaceAll("\\D", "");

        if (numeros.length() != 10 && numeros.length() != 11) {
            throw new DadosInvalidosException("Telefone deve possuir DDD e número válido!");
        }
    }

    private void validarGenero(Genero genero){
        if(genero == null){
            throw new DadosInvalidosException("Informe seu genêro!");
        }
    }

    private int calcularDigitoCPF(String CPF, int quantidade) {
        int soma = 0;

        for (int i = 0; i < quantidade; i++) {
            soma += (CPF.charAt(i) - '0') * (quantidade + 1 - i);
        }

        int resto = (soma * 10) % 11;

        return resto == 10 ? 0 : resto;
    }

}
