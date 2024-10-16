class Faq {
    private titulo: string;
    private texto: string;
  
    constructor(titulo: string, texto: string) {
      this.titulo = titulo;
      this.texto = texto;
    }
  
    // Método para obter todos os dados (título e texto)
    public getAll(): { titulo: string; texto: string } {
      return {
        titulo: this.titulo,
        texto: this.texto
      };
    }
}
  