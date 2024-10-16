class Universidade {
    private id: string;
    private name: string;
    private avaliacao: number;
  
    constructor(id: string, name: string, avaliacao: number) {
      this.id = id;
      this.name = name;
      this.avaliacao = avaliacao;
    }
  
    // Getters
    public getId(): string {
      return this.id;
    }
  
    public getName(): string {
      return this.name;
    }
  
    public getAvaliacao(): number {
      return this.avaliacao;
    }
  
    // Setters
    public setId(id: string): void {
      this.id = id;
    }
  
    public setName(name: string): void {
      this.name = name;
    }
  
    public setAvaliacao(avaliacao: number): void {
      this.avaliacao = avaliacao;
    }
}
  