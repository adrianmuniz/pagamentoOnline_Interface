package services;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	//serviço para gerar o contrato utilizando a interface e o PayPalService
	
	//inversão de controle: não deixar a classe fazer a instanciação do que precisar. Isso deve ser feito em outra dependencia com injeção
	
	private OnlinePaymentService paymentService;
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract(Contract contract, int months) {
		//parcela sem juros e taxa
		double basicQuota = contract.getTotalValue() / months;
		for(int i = 1; i <= months; i++) {
			//adicionado juros
			double updatedQuota = basicQuota + paymentService.interest(basicQuota, i);
			//adicionando taxa
			double fullQuota = updatedQuota + paymentService.paymentFee(updatedQuota);
			
			//pegamos a data do contrado e adicionamos i meses
			Date dueDate = addMonths(contract.getDate(), i);
			
			//vamos instanciar esse contrato no installment
			contract.getInstallments().add(new Installment(dueDate, fullQuota));
		}
	}
	
	private Date addMonths(Date date, int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, N);
		return calendar.getTime();
	}
	
}
