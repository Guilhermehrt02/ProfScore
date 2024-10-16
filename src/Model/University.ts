class University {
    
    private id: string;
    private name: string;
    private score: number;
    public city: string;
    private courses: Course[];
    private ratings: UniversityRating[];
  
    constructor(id: string, name: string, score: number, city: string) {
      this.id = id;
      this.name = name;
      this.score = score;
      this.city = city;
      this.courses = [];
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

    public getCity(): string {
      return this.city;
    }

    public getCourses(): Course[] {
      return this.courses;
    }

    public getRatings(): UniversityRating[] {
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

    public setCity(city: string): void {
      this.city = city;
    }

    public setCourses(courses: Course[]): void {
      this.courses = courses;
    }

    public setRatings(ratings: UniversityRating[]): void {
      this.ratings = ratings;
    }

}
  