import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class App {
        public static void main(String[] args) {
                // 3.1 – Criar uma lista de funcionários
                List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"),
                                                "Operador"),
                                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"),
                                                "Operador"),
                                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"),
                                                "Coordenador"),
                                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"),
                                                "Diretor"),
                                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"),
                                                "Recepcionista"),
                                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),
                                                "Operador"),
                                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"),
                                                "Contador"),
                                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"),
                                                "Gerente"),
                                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"),
                                                "Eletricista"),
                                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"),
                                                "Gerente")));

                // 3.2 – Remover o funcionário “João” da lista.
                funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

                System.out.println("3.3 - Imprimir todos os funcionários com todas suas informações");
                // 3.3 – Imprimir todos os funcionários com todas suas informações
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

                for (Funcionario f : funcionarios) {
                        System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s%n",
                                        f.getNome(),
                                        f.getDataNascimento().format(dateFormatter),
                                        currencyFormatter.format(f.getSalario()),
                                        f.getFuncao());
                }

                System.out.println("");
                System.out.println("3.4 - Os funcionários receberam 10% de aumento de salário");
                // 3.4 – Os funcionários receberam 10% de aumento de salário
                funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

                System.out.println("");
                System.out.println("3.5 - Agrupar os funcionários por função em um MAP");
                // 3.5 – Agrupar os funcionários por função em um MAP
                Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                                .collect(Collectors.groupingBy(Funcionario::getFuncao));

                System.out.println("");
                System.out.println("3.6 - Imprimir os funcionários, agrupados por função");
                // 3.6 – Imprimir os funcionários, agrupados por função
                funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
                        System.out.println("Função: " + funcao);
                        listaFuncionarios.forEach(f -> {
                                System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s%n",
                                                f.getNome(),
                                                f.getDataNascimento().format(dateFormatter),
                                                f.getSalario().setScale(2, BigDecimal.ROUND_HALF_UP).toString()
                                                                .replace(".", ","),
                                                f.getFuncao());
                        });
                });

                System.out.println("");
                System.out.println("3.8 - Imprimir a média salarial de cada função");
                // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12
                funcionarios.stream()
                                .filter(f -> f.getDataNascimento().getMonthValue() == 10
                                                || f.getDataNascimento().getMonthValue() == 12)
                                .forEach(f -> {
                                        System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s%n",
                                                        f.getNome(),
                                                        f.getDataNascimento().format(dateFormatter),
                                                        f.getSalario().setScale(2, BigDecimal.ROUND_HALF_UP).toString()
                                                                        .replace(".", ","),
                                                        f.getFuncao());
                                });

                System.out.println("");
                System.out.println("3.9 - Imprimir a média salarial de cada função");
                // 3.9 – Imprimir o funcionário com a maior idade
                Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(f -> f.getDataNascimento()));
                System.out.printf("Funcionário mais velho: Nome: %s, Idade: %d%n",
                                maisVelho.getNome(),
                                LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear());

                System.out.println("");
                System.out.println("3.10 - Imprimir a lista de funcionários por ordem alfabética");
                // 3.10 – Imprimir a lista de funcionários por ordem alfabética
                List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
                funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
                funcionariosOrdenados.forEach(f -> {
                        System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s%n",
                                        f.getNome(),
                                        f.getDataNascimento().format(dateFormatter),
                                        f.getSalario().setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace(".",
                                                        ","),
                                        f.getFuncao());
                });

                System.out.println("");
                System.out.println("3.11 - Imprimir o total dos salários dos funcionários");
                // 3.11 – Imprimir o total dos salários dos funcionários
                BigDecimal totalSalarios = funcionarios.stream()
                                .map(Funcionario::getSalario)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                System.out.println("Total dos salários: "
                                + totalSalarios.setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace(".", ","));

                System.out.println("");
                System.out.println("3.12 - Imprimir quantos salários mínimos ganha cada funcionário");
                // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
                BigDecimal salarioMinimo = new BigDecimal("1212.00");
                funcionarios.forEach(f -> {
                        BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
                        System.out.printf("Nome: %s, Salários Mínimos: %s%n",
                                        f.getNome(),
                                        salariosMinimos.toString().replace(".", ","));
                });
        }
}
