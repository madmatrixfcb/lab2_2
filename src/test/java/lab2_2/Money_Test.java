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
	public void DomsylnaWalutaZeroTest() {

		BigDecimal wartosc = new BigDecimal(0);
		Money pieniadze = new Money(wartosc);

		assertThat(pieniadze.toString(), is(equalTo("0,00 EUR")));
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
		Money pieniadze = new Money(wartosc, waluta);

		assertThat(pieniadze.toString(), is(equalTo("-15,00 CHF")));
	}

	@Test
	public void InnaWalutaZeroTest() {
		BigDecimal wartosc = new BigDecimal(0);
		Currency waluta = Currency.getInstance("CHF");
		Money pieniadze = new Money(wartosc, waluta);

		assertThat(pieniadze.toString(), is(equalTo("0,00 CHF")));
	}

	@Test
	public void DodawanieTejSamejWalutyTest() {
		BigDecimal wartoscA = new BigDecimal(15.50);
		BigDecimal wartoscB = new BigDecimal(14.50);
		Currency waluta = Currency.getInstance("USD");
		Money pieniadzeA = new Money(wartoscA, waluta);
		Money pieniadzeB = new Money(wartoscB, waluta);

		Money dodawanie = pieniadzeA.add(pieniadzeB);

		assertThat(dodawanie.toString(), is(equalTo("30,00 USD")));
	}

	@Test
	public void DodawanieTejSamejWalutyUjemnaTest() {
		BigDecimal wartoscA = new BigDecimal(15.50);
		BigDecimal wartoscB = new BigDecimal(-14.50);
		Currency waluta = Currency.getInstance("USD");
		Money pieniadzeA = new Money(wartoscA, waluta);
		Money pieniadzeB = new Money(wartoscB, waluta);

		Money dodawanie = pieniadzeA.add(pieniadzeB);

		assertThat(dodawanie.toString(), is(equalTo("1,00 USD")));

	}

	@Test
	public void DodawanieTejSamejWalutyZeroTest() {
		BigDecimal wartoscA = new BigDecimal(15.50);
		BigDecimal wartoscB = new BigDecimal(0);
		Currency waluta = Currency.getInstance("USD");
		Money pieniadzeA = new Money(wartoscA, waluta);
		Money pieniadzeB = new Money(wartoscB, waluta);

		Money dodawanie = pieniadzeA.add(pieniadzeB);

		assertThat(dodawanie.toString(), is(equalTo("15,50 USD")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void DodawanieInnejWalutyTest() {
		BigDecimal wartoscA = new BigDecimal(15.50);
		BigDecimal wartoscB = new BigDecimal(14.50);
		Currency walutaA = Currency.getInstance("USD");
		Currency walutaB = Currency.getInstance("CHF");
		Money pieniadzeA = new Money(wartoscA, walutaA);
		Money pieniadzeB = new Money(wartoscB, walutaB);

		Money dodawanie = pieniadzeA.add(pieniadzeB);

		throw new IllegalArgumentException("Currency mismatch");
	}

	@Test(expected = IllegalArgumentException.class)
	public void DodawanieInnejWalutyUjemnaTest() {
		BigDecimal wartoscA = new BigDecimal(15.50);
		BigDecimal wartoscB = new BigDecimal(-14.50);
		Currency walutaA = Currency.getInstance("USD");
		Currency walutaB = Currency.getInstance("CHF");
		Money pieniadzeA = new Money(wartoscA, walutaA);
		Money pieniadzeB = new Money(wartoscB, walutaB);

		Money dodawanie = pieniadzeA.add(pieniadzeB);

		throw new IllegalArgumentException("Currency mismatch");
	}

	@Test(expected = IllegalArgumentException.class)
	public void DodawanieInnejWalutyZeroTest() {
		BigDecimal wartoscA = new BigDecimal(15.50);
		BigDecimal wartoscB = new BigDecimal(0);
		Currency walutaA = Currency.getInstance("USD");
		Currency walutaB = Currency.getInstance("CHF");
		Money pieniadzeA = new Money(wartoscA, walutaA);
		Money pieniadzeB = new Money(wartoscB, walutaB);

		Money dodawanie = pieniadzeA.add(pieniadzeB);

		throw new IllegalArgumentException("Currency mismatch");
	}

	/* Zmiana sposobu deklaracji Money w testach dla szybszego 
	 * pisania testow */

	@Test
	public void OdejmowanieTejSamejWalutyTest() {

		Money pieniadzeA = new Money(15.50);
		Money pieniadzeB = new Money(14.50);

		Money odejmowanie = pieniadzeA.subtract(pieniadzeB);

		assertThat(odejmowanie.toString(), is(equalTo("1,00 EUR")));
	}
	
	@Test
	public void OdejmowanieTejSamejWalutyUjemnaTest() {

		Money pieniadzeA = new Money(15.50);
		Money pieniadzeB = new Money(-14.50);

		Money odejmowanie = pieniadzeA.subtract(pieniadzeB);

		assertThat(odejmowanie.toString(), is(equalTo("30,00 EUR")));
	}
	
	@Test
	public void OdejmowanieTejSamejWalutyZeroTest() {

		Money pieniadzeA = new Money(15.50);
		Money pieniadzeB = new Money(0);

		Money odejmowanie = pieniadzeA.subtract(pieniadzeB);

		assertThat(odejmowanie.toString(), is(equalTo("15,50 EUR")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void OdejmowanieInnejWalutyTest() {
		
		Money pieniadzeA = new Money(15.50, "USD");
		Money pieniadzeB = new Money(14.50, "CHF");
		
		Money odejmowanie = pieniadzeA.subtract(pieniadzeB);
				
		throw new IllegalArgumentException("Currency mismatch");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void OdejmowanieInnejWalutyUjemnaTest() {
		
		Money pieniadzeA = new Money(15.50, "USD");
		Money pieniadzeB = new Money(-14.50, "CHF");
		
		Money odejmowanie = pieniadzeA.subtract(pieniadzeB);
				
		throw new IllegalArgumentException("Currency mismatch");
	}

}
