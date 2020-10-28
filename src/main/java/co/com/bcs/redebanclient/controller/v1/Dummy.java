package co.com.bcs.redebanclient.controller.v1;

public class Dummy {

	
	
//	package co.com.bcs.redebanclient.controller.v1;
//
//	import javax.validation.Valid;
//
//	import org.springframework.http.HttpStatus;
//	import org.springframework.http.ResponseEntity;
//	import org.springframework.web.bind.annotation.PathVariable;
//	import org.springframework.web.bind.annotation.PostMapping;
//	import org.springframework.web.bind.annotation.RequestBody;
//	import org.springframework.web.bind.annotation.RestController;
//
//	import co.com.bcs.redebanclient.controller.v1.entity.Anulacion;
//	import co.com.bcs.redebanclient.controller.v1.entity.Compra;
//	import co.com.bcs.redebanclient.controller.v1.entity.InfoCompra;
//	import co.com.bcs.redebanclient.mapper.ControllerMapper;
//	import co.com.bcs.redebanclient.services.AnulacionService;
//	import co.com.bcs.redebanclient.services.CompraService;
//	import co.com.bcs.redebanclient.services.CompraServiceDummy;
//	import co.com.bcs.redebanclient.services.dto.DatosAnulacion;
//	import co.com.bcs.redebanclient.services.dto.DatosCompra;
//	import co.com.bcs.redebanclient.services.dto.InformacionCompra;
//	import co.com.bcs.redebanclient.services.dto.ReversoAnulacion;
//	import co.com.bcs.redebanclient.services.dto.ReversoCompra;
//	import lombok.AllArgsConstructor;
//	import lombok.RequiredArgsConstructor;
//
//	@RestController
//	@AllArgsConstructor
//	public class ComprarController {
//
//		private ControllerMapper mapper;
//
//		private CompraServiceDummy compraService;
//
//		@PostMapping("/v1/compras/{codigoTransaccion}/comprar")
//		public ResponseEntity<InfoCompra> comprar(@Valid @RequestBody Compra compra,
//				@PathVariable("codigoTransaccion") Long codigoTransaccion) {
//			DatosCompra datosCompra = mapper.toDatosCompra(compra, codigoTransaccion);
//			InformacionCompra informacionCompra = compraService.comprar(datosCompra);
//			return new ResponseEntity<>(mapper.toInfoCompra(informacionCompra), HttpStatus.OK);
//		}
//
//		@PostMapping("/v1/compras/{codigoTransaccion}/reversar")
//		public ResponseEntity<InfoCompra> comprarReverso(@Valid @RequestBody Compra compra, String causaReverso,
//				@PathVariable("codigoTransaccion") Long codigoTransaccion) {
//			ReversoCompra reverso = mapper.toReversoCompra(compra, codigoTransaccion, causaReverso);
//			InformacionCompra informacionCompra = compraService.reversarComprar(reverso);
//			return new ResponseEntity<>(mapper.toInfoCompra(informacionCompra), HttpStatus.OK);
//		}
//
//	}
}
