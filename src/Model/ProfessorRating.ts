class ProfessorRating {
    private comment: string;
    private student: Student;
    private professor: Professor;
  
    constructor(comment: string, student: Student, professor: Professor) {
      this.comment = comment;
      this.student = student;
      this.professor = professor;
    }
  
    public getComment(): string {
      return this.comment;
    }

    public getStudent(): Student {
      return this.student;
    }

    public getProfessor(): Professor {
      return this.professor;
    }
  
    public setComment(comment: string): void {
      this.comment = comment;
    }

    public setStudent(student: Student): void {
      this.student = student;
    }

    public setProfessor(professor: Professor): void {
      this.professor = professor;
    }
}
  