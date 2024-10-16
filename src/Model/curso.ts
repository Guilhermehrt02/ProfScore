class Curso {
    private name: string;
    private periodo: string;
    private instituto: string;
  
    constructor(name: string, periodo: string, instituto: string) {
      this.name = name;
      this.periodo = periodo;
      this.instituto = instituto;
    }
  
    // Getters
    public getName(): string {
      return this.name;
    }
  
    public getPeriodo(): string {
      return this.periodo;
    }
  
    public getInstituto(): string {
      return this.instituto;
    }
  
    // Setters
    public setName(name: string): void {
      this.name = name;
    }
  
    public setPeriodo(periodo: string): void {
      this.periodo = periodo;
    }
  
    public setInstituto(instituto: string): void {
      this.instituto = instituto;
    }
}
