abstract class User {

    protected id: number=1;
    protected name: string;
    protected email: string;
    protected password: string;
  
    constructor(name: string, email: string, password: string) {
      this.id += 1;
      this.name = name;
      this.email = email;
      this.password = password;
    }
  
    // Getters
    public getId(): number {
      return this.id;
    }
  
    public getName(): string {
      return this.name;
    }
  
    public getEmail(): string {
      return this.email;
    }
  
    public getPassword(): string {
      return this.password;
    }
  
    // Setters
    public setName(name: string): void {
      this.name = name;
    }
  
    public setEmail(email: string): void {
      this.email = email;
    }
  
    public setPassword(password: string): void {
      this.password = password;
    }
}
  