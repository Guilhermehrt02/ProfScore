class Student extends User {

    private profRatings: ProfessorRating[];
    private UniRatings: UniversityRating[];
    private feedbacks: Feedback[];

    constructor(name: string, email: string, password: string) {
      super(name, email, password); // Chama o construtor da classe base (Usuario)
      this.profRatings = [];
      this.UniRatings = [];
      this.feedbacks = [];
    }
  
    public getProfRatings(): ProfessorRating[] {
      return this.profRatings;
    }

    public getUniRatings(): UniversityRating[] {
      return this.UniRatings;
    }

    public getFeedbacks(): Feedback[] {
      return this.feedbacks;
    }

    public setProfRatings(profRatings: ProfessorRating[]): void {
      this.profRatings = profRatings;
    }

    public setUniRatings(UniRatings: UniversityRating[]): void {
      this.UniRatings = UniRatings;
    }

    public setFeedbacks(feedbacks: Feedback[]): void {
      this.feedbacks = feedbacks;
    }

    public rateProf(): void {
      console.log(`${this.name} está avaliando um professor.`);

    }

    public rateUniversity(): void {
      console.log(`${this.name} está avaliando uma universidade.`);
      
    }
  
    public createFeedback(): void {
      console.log(`${this.name} está criando um feedback.`);
      
    }
}
  