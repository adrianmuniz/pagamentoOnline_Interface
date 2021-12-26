package application;

import services.OnlinePaymentService;
import services.PayPalService;

public class Program {

	public static void main(String[] args) {
		
		OnlinePaymentService ps = new PayPalService();
		
		System.out.println(ps.paymentFee(200.00));
		
	}

}
