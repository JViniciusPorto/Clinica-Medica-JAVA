package clinica_medica.exceptions;

public class DadosInvalidosException extends IllegalArgumentException{
    public DadosInvalidosException(String mensagem){
        super(mensagem);
    }
}
