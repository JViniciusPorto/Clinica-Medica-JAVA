package clinica_medica.model;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.model.enums.EstadoConsulta;

public class Avaliacao {

    private Consulta consulta;
    private String texto;
    private int nota;

    public Avaliacao(Consulta consulta, String texto, int nota) {
        validarConsulta(consulta);
        validarTexto(texto);
        validarNota(nota);

        this.consulta = consulta;
        this.texto = texto.trim();
        this.nota = nota;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public String getTexto() {
        return texto;
    }

    public int getNota() {
        return nota;
    }

    private void validarConsulta(Consulta consulta) {
        if (consulta == null) {
            throw new DadosInvalidosException("Nenhuma consulta foi informada!");
        }

        if (consulta.getEstado() != EstadoConsulta.REALIZADA) {
            throw new DadosInvalidosException("Somente consultas realizadas podem ser avaliadas!");
        }
    }

    private void validarTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new DadosInvalidosException("O texto da avaliação não pode estar vazio!");
        }
    }

    private void validarNota(int nota) {
        if (nota < 1 || nota > 5) {
            throw new DadosInvalidosException("A nota deve estar entre 1 e 5 estrelas!");
        }
    }
}