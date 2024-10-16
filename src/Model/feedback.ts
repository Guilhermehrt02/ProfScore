class Feedback {
    private feedback: string;
    private student: Student;
  
    constructor(feedback: string, student: Student) {
      this.feedback = feedback;
      this.student = student;
    }
  
    // Método para printar o feedback
    public printar(): void {
      console.log(`Feedback: ${this.feedback}`);
    }
}
  