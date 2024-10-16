class Course {
    private name: string;
    private semester: string;
    private university: University;
  
    constructor(name: string, semester: string, university: University) {
      this.name = name;
      this.semester = semester;
      this.university = university;
    }
  
    // Getters
    public getName(): string {
      return this.name;
    }
  
    public getsemester(): string {
      return this.semester;
    }
  
    public getUniversity(): University {
      return this.university;
    }
  
    // Setters
    public setName(name: string): void {
      this.name = name;
    }
  
    public setsemester(semester: string): void {
      this.semester = semester;
    }
  
    public setUniversity(university: University): void {
      this.university = university;
    }
}
