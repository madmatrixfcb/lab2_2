package lab2_2;

import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Currency;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

import pl.com.bottega.ecommerce.sharedkernel.Money;

public class Money_Test {

	@Test
	public void DomyslnaWalutaTest() {
		
		BigDecimal wartosc = new BigDecimal(15);
		Money pieniadze = new Money(wartosc);

		assertThat(pieniadze.toString(), is(equalTo("15,00 EUR")));
	}
	
	@Test
	public void DomyslnaWalutaUjemnaTest() {
		
		BigDecimal wartosc = new BigDecimal(-15);
		Money pieniadze = new Money(wartosc);

		assertThat(pieniadze.toString(), is(equalTo("-15,00 EUR")));
	}
	
	@Test
	public void InnaWalutaTest() {
		
		BigDecimal wartosc = new BigDecimal(15);
		Currency waluta = Currency.getInstance("CHF");
		Money pieniadze = new Money(wartosc, waluta);
		
		assertThat(pieniadze.toString(), is(equalTo("15,00 CHF")));
	}
	
	@Test
	public void InnaWalutaUjemnaTest() {
		
		BigDecimal wartosc = new BigDecimal(-15);
		Currency waluta = Currency.getInstance("CHF");
		Money pieniadze = new Money (wartosc, waluta);
		
		assertThat(pieniadze.toString(), is(equalTo("-15,00 CHF")));
	}

}
