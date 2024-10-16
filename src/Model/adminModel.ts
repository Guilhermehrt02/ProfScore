class Relatorio {
    constructor(public titulo: string, public conteudo: string) {}
}
  
class Administrador extends Usuario {
    protected relatorios: Relatorio[];

    constructor(name: string, email: string, password: string) {
        super(name, email, password); // Chama o construtor da classe base (Usuario)
        this.relatorios = []; // Inicializa o array de relatórios
    }

    // Método para gerar relatórios
    public gerarRelatorios(): Relatorio[] {
        console.log(`${this.name} está gerando relatórios.`);
        // Lógica para gerar relatórios pode ser adicionada aqui
        // Exemplo simples de adicionar um relatório ao array
        const novoRelatorio = new Relatorio('Relatório Exemplo', 'Conteúdo do relatório');
        this.relatorios.push(novoRelatorio);

        return this.relatorios;
    }
}
  