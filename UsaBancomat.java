package application;

public class UsaBancomat {

	public static void main(String[] args) {
		
		bancomatLogica b1= new bancomatLogica();
		
		//aggiungo  tre conti 
		b1.addConto(new ContoCorrente("1234", 20000));
		b1.addConto(new ContoCorrente("5678", 30000));
		b1.addConto(new ContoCorrente("9012",4000));
		
		//prelevo dai conti
		
		//conto 1  prelevo 2000
		b1.preleva("1234",2000);
		System.out.println("il saldo dopo aver prelevato 2000 é:"+b1.getSaldo("1234"));
		
		b1.versa("1234",12000);
		System.out.println("il saldo dopo aver versato 12000 é :"+b1.getSaldo("1234"));
		//conto2 prelevo 1000
		b1.preleva("5678", 1000);
		System.out.println("il saldo dopo aver prelevato 1000 é"+b1.getSaldo("5678"));
		b1.versa("5678", 11000);
		System.out.println("il saldo dopo aver versato 11000 é :"+b1.getSaldo("5678"));
		//conto3 prelevo tutto. 4000
		b1.preleva("9012", 4000);
	    System.out.println("il saldo dopo aver prelevato 4000 è "+b1.getSaldo("9012"));
	    
	    b1.versa("9012", 14000);
	    System.out.println("il saldo dopo aver versato 14000 è "+b1.getSaldo("9012"));
		
		
		
	}

}
