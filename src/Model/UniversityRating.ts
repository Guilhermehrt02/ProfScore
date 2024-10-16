class UniversityRating {
    private comment: string;
    private student: Student;
    private university: University;
  
    constructor(comment: string, student: Student, university: University) {
      this.comment = comment;
      this.student = student;
      this.university = university;
    }
  
    // Getter para obter o comentário
    public getcomment(): string {
      return this.comment;
    }

    public getstudent(): Student {
      return this.student;
    }

    public getuniversity(): University {
      return this.university;
    }
  
    // Setter para modificar o comentário
    public setcomment(comment: string): void {
      this.comment = comment;
    }

    public setstudent(student: Student): void {
      this.student = student;
    }

    public setuniversity(university: University): void {
      this.university = university;
    }
}
  