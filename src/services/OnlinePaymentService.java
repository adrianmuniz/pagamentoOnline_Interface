package services;

public interface OnlinePaymentService {

	//interface de pagamento onde definimos um contrato que deve ser cumprido
	
	double paymentFee(double amount);
	double interest(double amount, int months);	
}
