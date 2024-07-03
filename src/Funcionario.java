import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    // 2 – Criar uma classe Funcionário que herda de Pessoa e possui os atributos
    // salário e função
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }
}
