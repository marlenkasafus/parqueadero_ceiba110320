package parqueadero_ddd.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parqueadero_ddd.domain.Tcrm;
import parqueadero_ddd.persistencia.repositorio.TcrmRepositorio;

@RestController
@RequestMapping("/TCRMService")
@CrossOrigin
public class TasaRepresentaticaMercadoController {
	
	@Autowired
	private TcrmRepositorio tcrmRepositorio;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Tcrm> getTCRMActual() throws RemoteException{
		return new ResponseEntity<>(tcrmRepositorio.getTcrm(), HttpStatus.OK);
	}

}
