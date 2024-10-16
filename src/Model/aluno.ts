class Aluno extends Usuario {

    constructor(name: string, email: string, password: string) {
      super(name, email, password); // Chama o construtor da classe base (Usuario)
    }
  
    // Método para avaliar professor
    public avaliarProf(): void {
      console.log(`${this.name} está avaliando um professor.`);
      // Lógica de avaliação do professor pode ser adicionada aqui
    }
  
    // Método para criar feedback
    public criarFeedback(): void {
      console.log(`${this.name} está criando um feedback.`);
      // Lógica de criação de feedback pode ser adicionada aqui
    }
}
  