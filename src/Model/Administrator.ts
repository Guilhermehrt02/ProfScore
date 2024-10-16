
class Administrador extends User {
    protected reports: ReportModel[];

    constructor(name: string, email: string, password: string) {
        super(name, email, password); // Chama o construtor da classe base (Usuario)
        this.reports = []; // Inicializa o array de relatórios
    }

    // Método para gerar relatórios
    public generateReports(): void {
        console.log(`${this.name} está gerando relatórios.`);
        
    }
}
  