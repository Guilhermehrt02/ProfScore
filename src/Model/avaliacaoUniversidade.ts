class AvaliacaoUniversidade {
    private comentario: string;
  
    constructor(comentario: string) {
      this.comentario = comentario;
    }
  
    // Getter para obter o comentário
    public getComentario(): string {
      return this.comentario;
    }
  
    // Setter para modificar o comentário
    public setComentario(comentario: string): void {
      this.comentario = comentario;
    }
  }
  