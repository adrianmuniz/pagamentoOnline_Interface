package services;

import entities.Contract;

public class ContractService {
	
	//servi�o para gerar o contrato utilizando a interface e o PayPalService
	
	//invers�o de controle: n�o deixar a classe fazer a instancia��o do que precisar. Isso deve ser feito em outra dependencia com inje��o
	
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
		}
	}
	
}
