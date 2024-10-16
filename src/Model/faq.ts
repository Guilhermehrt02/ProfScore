class Faq {
    private title: string;
    private content: string;
  
    constructor(title: string, content: string) {
      this.title = title;
      this.content = content;
    }
  
    // Método para obter todos os dados (título e content)
    public getAll(): { title: string; content: string } {
      return {
        title: this.title,
        content: this.content
      };
    }
}
  