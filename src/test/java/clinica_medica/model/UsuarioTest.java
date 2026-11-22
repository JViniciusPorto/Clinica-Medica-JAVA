package clinica_medica.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clinica_medica.exceptions.DadosInvalidosException;
import clinica_medica.model.enums.Genero;
import clinica_medica.model.enums.PlanoSaude;
@DisplayName("Teste escrito para testar as regras de implementação da classe abstrata Usuario")
public class UsuarioTest {
    private Usuario usuario;

    @Test
    @DisplayName("Caso Feliz: O objeto será criado sem lançar nenhuma exceção. Além disso, os valores passados aos atributos não sofrerão alterações")
    void deveCriarUsuarioQuandoDadosValidos(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertNotNull(usuario);
        assertEquals("Arthur", usuario.getNomeUsuario());
        assertEquals("arthur@gmail.com", usuario.getEmail());
        assertEquals("123Abcd!", usuario.getSenha());
        assertEquals("52998224725", usuario.getCPF());
        assertEquals("85989860278", usuario.getTelefone());
        assertEquals(Genero.MASCULINO, usuario.getGenero());
    } 
    //testes para verificar as operações com o atributo nomeUsuario
    @Test 
    @DisplayName("Caso Feliz: O nome de usuário será alterado sem lançar nenhuma exceção")
    void deveAlterarNomeQuandoONomeForValido(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        usuario.alterarNomeUsuario("Marcelo");
        assertEquals("Marcelo", usuario.getNomeUsuario());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção foi lançada, caso o nome de usuário esteja em branco")
    void deveLancarExcecaoQuandoNomeEmBranco(){
        DadosInvalidosException excecao = assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA)
        );
        assertEquals("O nome não pode ser vazio!", excecao.getMessage());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção foi lançada, caso o nome de usuário só tenha espaços em branco")
    void deveLancarExcecaoQuandoNomeSomenteComEspacos(){
        DadosInvalidosException excecao = assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("     ", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA)
        );
        assertEquals("O nome não pode ser vazio!", excecao.getMessage());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o nome de usuário seja uma String não instanciada")
    void deveLancarExcecaoQuandoNomeForNull(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente(null, "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o nome seja apenas um tab")
    void deveLancarExcecaoQuandoNomeForSomenteTabulacao(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("\t","arthur@gmail.com","123Abcd!","52998224725","85989860278",Genero.MASCULINO,LocalDate.of(2005,05,10),PlanoSaude.HAPVIDA)
        );
    }


    @Test
    @DisplayName("Caso Limite: Verificar se a exceção é lançada, caso tente alterar o nome de usuário para um nome sem caracteres") 
    void deveLancarExcecaoQuandoNomeParaAlterarEstiverEmBranco(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertThrows(
            DadosInvalidosException.class,
            () -> usuario.alterarNomeUsuario("")
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verificar se a exceção é lançada, caso tente alterar o nome de usuário para um nome somente com espaços")
    void deveLancarExcecaoQuandoNomeParaAlterarTiverSomenteEspacos(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertThrows(
            DadosInvalidosException.class,
            () -> usuario.alterarNomeUsuario("     ")
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verificar se a exceção é lançada, caso tente alterar o nome de usuário para uma String não instanciada")
    void deveLancarExcecaoQuandoNomeParaAlterarForNull(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertThrows(
            DadosInvalidosException.class,
            () -> usuario.alterarNomeUsuario(null)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verificar se a exceção é lançada, caso tente alterar o nome de usuário para um nome com, somente, um tab")
    void deveLancarExcecaoQuandoNomeParaAlterarForUmTab(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertThrows(
            DadosInvalidosException.class,
            () -> usuario.alterarNomeUsuario("\t")
        );
    }

    @Test
    @DisplayName("Tratamento de Dados: Verifica se é retirado os espaços em branco do começo e fim do nome de usuário")
    void deveRetornarNomeSemEspacosNoComecoEFim(){
        usuario = new Paciente(" Arthur  ", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertEquals("Arthur",usuario.getNomeUsuario());
    }

    @Test
    @DisplayName("Tratamento de Dados: Verifica se nomes compostos com 'Arthur da Silva' é aceito")
    void deveAceitarNomesCompostos(){
        usuario = new Paciente("Arthur da Silva", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertEquals("Arthur da Silva", usuario.getNomeUsuario());
    }

    @Test
    @DisplayName("Tratamento de Dados: Verifica se nomes com vários espaços como 'Arthur  da\tSilva' são transformados para 'Arthur da Silva'")
    void deveTratarNomeComVariosEspacosNoInterior(){
        usuario = new Paciente("Arthur  da\tSilva", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertEquals("Arthur da Silva", usuario.getNomeUsuario());
    }

    @Test
    @DisplayName("Tratamento de Dados: Verifica se nomes com acentos como 'João' são aceitos")
    void deveAceitarNomesComAcentos(){
        usuario = new Paciente("João", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertEquals("João", usuario.getNomeUsuario());
    }

    @Test
    @DisplayName("Tratamento de Dados: Verifica se é tratado os espaços nos extremos do novo nome de usuário")
    void deveRetornarNomeAlteradoSemEspacosNosExtremos(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        usuario.alterarNomeUsuario(" Marcelo ");
        assertEquals("Marcelo", usuario.getNomeUsuario());
    }

    @Test
    @DisplayName("Tratamento de Dados: Verificar se é aceitos nomes compostos para alterar o nome de usuário") 
    void deveAceitarNomesCompostosParaAlterar(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        usuario.alterarNomeUsuario("Marcelo de Souza");
        assertEquals("Marcelo de Souza", usuario.getNomeUsuario());
    }

    @Test 
    @DisplayName("Tratamento de Dados: Verifica se é tratado nomes compostos com vários espaços para o novo nome de usuário")
    void deveTratarNomeParaAlterarComVariosEspacosNoInterior(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        usuario.alterarNomeUsuario("Marcelo   de\tSouza");
        assertEquals("Marcelo de Souza",usuario.getNomeUsuario());
    }

    @Test 
    @DisplayName("Tratamento de Dados: Verifica se é aceito nomes com acentos para alterar o nome de usuário")
    void deveAceitarNomesParaAlterarComAcentos(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        usuario.alterarNomeUsuario("João");
        assertEquals("João", usuario.getNomeUsuario());
    }

    //Testes referentes ao atributo email, não foi feito testes com o método
    //alterarEmail, pois ele faz as mesmas validações que o construtor faz
    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email seja uma String vazia")
    void deveLancarExcecaoQuandoEmailEstiverEmBranco(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class,() -> new Paciente("Arthur", "", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
        assertEquals("O email não pode ser vazio!", excecao.getMessage());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email seja uma String com vários espaços")
    void deveLancarExcecaoQuandoEmailSoTiverEspacos(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("Arthur", "    ", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email seja uma String não instanciada")
    void deveLancarExcecaoQuandoEmailForNull(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("Arthur", null, "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email tenha espaços entre as palavras") 
    void deveLancarExcecaoQuandoEmailTiverEspacosInternos(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class,() -> new Paciente("Arthur", "arthur @gmail .com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
        assertEquals("Formato de email inválido!", excecao.getMessage());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email só tenha '@...'")
    void deveLancarExcecaoSeEmailNaoTiverParteAnteriorAoArroba(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("Arthur", "@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email não tenha @")
    void deveLancarExcecaoSeEmailNaoTiverArroba(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("Arthur", "arthurgmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email não tenha um domínio")
    void deveLancarExcecaoSeEmailNaoTiverDominio(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("Arthur", "arthur@", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email não tenha um '.' depois do domínio")
    void deveLancarExcecaoSeEmailNaoTiverPontoDepoisDoDominio(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("Arthur", "arthur@gmail", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o email não tenha uma extensão de domínio")
    void deveLancarExcecaoSeEmailNaoTiverExtensaoDeDominio(){
        assertThrows(
            DadosInvalidosException.class,
            () -> new Paciente("Arthur", "arthur@gmail.", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test
    @DisplayName("Tratamento de Dados: Verifica se os espaços externos do email serão retirados ao passar para o atributo")
    void deveRetirarEspacosExternosDoEmail(){
        usuario = new Paciente("Arthur", " arthur@gmail.com ", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA);
        assertEquals("arthur@gmail.com", usuario.getEmail());
    }
    //testes para o atributo de senha, não foi testado o método alterarSenha, pois
    //ele faz as mesmas validações testadas no construtor
    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a senha seja uma String não instanciada")
    void deveLancarExcecaoQuandoSenhaForNull(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", null, "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a senha tenha menos de 8 caracteres")
    void deveLancarExcecaoQuandoSenhaTiverMenosQueOitoCaracteres(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", "123", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a senha não tenha caractere numérico")
    void deveLancarExcecaoQuandoSenhaNaoTiverNumero(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", "ABCdefg!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }
    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a senha não tenha letra maiúscula")
    void deveLancarExcecaoQuandoSenhaNaoTiverLetraMaiuscula(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", "abcd123!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a senha não tenha letra minúscula")
    void deveLancarExcecaoQuandoSenhaNaoTiverLetraMinuscula(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", "abcd123!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a senha não tenha nenhum caractere especial")
    void deveLancarExcecaoQuandoSenhaNaoTiverCaractereEspecial(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", "abcd123H", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso a senha tenha espaço ou TAB")
    void deveLancarExcecaoQuandoSenhaTiverTabOuEspacos(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", "A bcd123!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    }

    //Testes para o atributo CPF
    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o CPF seja uma String não instanciada") 
    void deveLancarExcecaoQuandoCPFForNull(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", null, "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
        assertEquals("CPF não informado!", excecao.getMessage());
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o CPF não tenha 11 dígitos")
    void deveLancarExcecaoQuandoCPFNaoTiverOnzeDigitos(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "529982247", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
        assertEquals("O CPF deve ter 11 dígitos!", excecao.getMessage());
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o CPF tenha todos os dígitos iguais")
    void deveLancarExcecaoQuandoCPFTiverTodosOsDigitosIguais(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "11111111111", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
        assertEquals("CPF inválido!", excecao.getMessage());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se exceção é lançada, caso o CPF tenha algum dos dígitos verificadores inválidos") 
    void deveLancarExcecaoQuandoCPFTiverDigitosVerificadoresInvalidos(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224724", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
        assertEquals("O CPF possui digítos verificadores inválidos", excecao.getMessage());
    }

    @Test
    @DisplayName("Tratamento de Dados: Verifica se CPFs no formato 'ddd.ddd.ddd-dd' passa pelas validações e se é tratado retirando os pontos e hífens") 
    void deveReceberCPFsComPontosEHifens(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "529.982.247-25", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 5, 10), PlanoSaude.HAPVIDA);
        assertEquals("52998224725", usuario.getCPF());
    }

    //testes para o atributo telefone, não foi feito testes para o método de alterarTelefone, pois
    //ele faz as mesmas validações feitas no construtor
    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o telefone seja uma String não instanciada") 
    void deveLancarExcecaoQuandoTelefoneForNull(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", null, Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
        assertEquals("Telefone não informado!", excecao.getMessage());
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o telefone seja uma String vazia")
    void deveLancarExcecaoQuandoTelefoneEstiverEmBranco(){
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
        assertEquals("Telefone deve possuir DDD e número válido!", excecao.getMessage());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o telefone seja vários espaços ou TAB")
    void deveLancarExcecaoQuandoTelefoneSoTiverEspacosOuTab(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "       ", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA));
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção não é lançada, quando o telefone é de um número fixo")
    void deveAceitarTelefoneFixo(){
        usuario = assertDoesNotThrow(
            () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "8589860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
        assertEquals("8589860278", usuario.getTelefone());
    }

    @Test 
    @DisplayName("Caso Limite: Verifica se a exceção não é lançada, quando o telefone é um número de celular")
    void deveAceitarTelefoneDeCelular(){
        usuario = assertDoesNotThrow(
            () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
        assertEquals("85989860278", usuario.getTelefone());
    }

    @Test 
    @DisplayName("Tratamento de Dados: Verifica se caracteres não númericos do telefone são retirados antes de ser passado ao atributo")
    void deveRetirarCaracteresNaoNumericosDoTelefone(){
        usuario = new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "(85) 98986-0278", Genero.MASCULINO, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA);
        assertEquals("85989860278", usuario.getTelefone());
    }

    @Test
    @DisplayName("Caso Limite: Verifica se a exceção é lançada, caso o gênero seja um enum Genero não instanciado") 
    void deveLancarExcecaoQuandoGeneroForNull(){
        assertThrows(
            DadosInvalidosException.class, 
            () -> new Paciente("Arthur", "arthur@gmail.com", "123Abcd!", "52998224725", "85989860278", null, LocalDate.of(2005, 05, 10), PlanoSaude.HAPVIDA)
        );
    
    }
}
