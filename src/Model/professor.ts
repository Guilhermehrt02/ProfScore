class Professor {
    private id: string;
    private name: string;
    private score: number;
    private ratings: ProfessorRating[];
  
    constructor(id: string, name: string, score: number) {
      this.id = id;
      this.name = name;
      this.score = score;
      this.ratings = [];
    }
  
    // Getters
    public getId(): string {
      return this.id;
    }
  
    public getName(): string {
      return this.name;
    }
  
    public getscore(): number {
      return this.score;
    }

    public getRatings(): ProfessorRating[] {
      return this.ratings;
    }
  
    // Setters
    public setId(id: string): void {
      this.id = id;
    }
  
    public setName(name: string): void {
      this.name = name;
    }
  
    public setscore(score: number): void {
      this.score = score;
    }

}
  