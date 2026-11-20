package clinica_medica.exceptions;

public class ConsultaInvalidaException extends IllegalArgumentException{
    public ConsultaInvalidaException(String mensagem) {
        super(mensagem);
    }  
}
