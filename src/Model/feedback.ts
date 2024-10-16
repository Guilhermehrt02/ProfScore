class Feedback {
    private feedback: string;
  
    constructor(feedback: string) {
      this.feedback = feedback;
    }
  
    // Método para printar o feedback
    public printar(): void {
      console.log(`Feedback: ${this.feedback}`);
    }
}
  