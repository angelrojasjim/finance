package com.banktcs.banksimulation;

import com.banktcs.banksimulation.repository.CuentaRepository;
import com.banktcs.banksimulation.repository.ClienteRepository;
import com.banktcs.banksimulation.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Date;

@SpringBootApplication
public class BankSimulationApplication implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private MovimientoRepository movimientoRepository;

	public static void main(String[] args) {

		SpringApplication.run(BankSimulationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	// Caso de Uso 1
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("Jose Lema");
		cliente1.setDireccion("Otavalo sn y principal");
		cliente1.setTelefono("098254785");
		cliente1.setContrasena("1234");
		cliente1.setEstado("True");
		cliente1.setGenero("Masculino");
		cliente1.setIdentificacion((long) 700001);

		Cliente cliente2 = new Cliente();
		cliente2.setNombre("Marianela Montalvo");
		cliente2.setDireccion("Amazonas y NNUU");
		cliente2.setTelefono("097548965");
		cliente2.setContrasena("5678");
		cliente2.setEstado("True");
		cliente2.setGenero("Femenino");
		cliente2.setIdentificacion((long) 700002);

		Cliente cliente3 = new Cliente();
		cliente3.setNombre("Juan Osorio");
		cliente3.setDireccion("13 junio y Equinoccial");
		cliente3.setTelefono("098874587");
		cliente3.setContrasena("1245");
		cliente3.setEstado("True");
		cliente3.setGenero("Masculino");
		cliente3.setIdentificacion((long) 700003);

		clienteRepository.save(cliente1);
		clienteRepository.save(cliente2);
		clienteRepository.save(cliente3);

	// Caso de Uso 2
		// Crear cuentas
		Cuenta cuenta1 = new Cuenta();
		cuenta1.setNumeroCuenta((long) 478758);
		cuenta1.setTipoCuenta("Ahorro");
		cuenta1.setSaldoInicial(2000.0);
		cuenta1.setEstado("True");
		cuenta1.setCliente(cliente1);

		Cuenta cuenta2 = new Cuenta();
		cuenta2.setNumeroCuenta((long) 225487);
		cuenta2.setTipoCuenta("Corriente");
		cuenta2.setSaldoInicial(100.0);
		cuenta2.setEstado("True");
		cuenta2.setCliente(cliente2);

		Cuenta cuenta3 = new Cuenta();
		cuenta3.setNumeroCuenta((long) 495878);
		cuenta3.setTipoCuenta("Ahorros");
		cuenta3.setSaldoInicial(0.0);
		cuenta3.setEstado("True");
		cuenta3.setCliente(cliente3);

		Cuenta cuenta4 = new Cuenta();
		cuenta4.setNumeroCuenta((long) 496825);
		cuenta4.setTipoCuenta("Ahorros");
		cuenta4.setSaldoInicial(540.0);
		cuenta4.setEstado("True");
		cuenta4.setCliente(cliente2);

		// Persistir cuentas
		cuentaRepository.save(cuenta1);
		cuentaRepository.save(cuenta2);
		cuentaRepository.save(cuenta3);
		cuentaRepository.save(cuenta4);

	// Caso de Uso 3
		Cuenta cuenta5 = new Cuenta();
		cuenta5.setNumeroCuenta((long) 585545);
		cuenta5.setTipoCuenta("Corriente");
		cuenta5.setSaldoInicial(1000);
		cuenta5.setEstado("True");
		cuenta5.setCliente(cliente1);

		cuentaRepository.save(cuenta5);

	// Case de Uso 4

		Movimiento movimiento1 = new Movimiento( new Date(), "Retiro", -575);
		Movimiento movimiento2 = new Movimiento( new Date(), "Deposito", 600);
		Movimiento movimiento3 = new Movimiento( new Date(), "Deposito", 150);
		Movimiento movimiento4 = new Movimiento( new Date(), "Retiro", -540);

		movimiento1.setCuenta(cuenta1);
		movimiento2.setCuenta(cuenta2);
		movimiento3.setCuenta(cuenta3);
		movimiento4.setCuenta(cuenta4);

		// Persistir movimientos
		movimientoRepository.save(movimiento1);
		movimientoRepository.save(movimiento2);
		movimientoRepository.save(movimiento3);
		movimientoRepository.save(movimiento4);



	}

}
