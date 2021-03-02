package io.swagger.api;

import io.swagger.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.customizacao.service.ClienteService;
import io.swagger.customizacao.util.RespostasUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T16:46:14.156Z")

@Controller
public class ClienteApiController implements ClienteApi {

    private static final Logger log = LoggerFactory.getLogger(ClienteApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private RespostasUtil respostaUtil;

    @org.springframework.beans.factory.annotation.Autowired
    public ClienteApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Cliente> cadastraCliente(@ApiParam(value = ""  )  @Valid @RequestBody Cliente cliente_) {
        
    	try {
            return clienteService.salva(cliente_);
         } catch (Exception e) {
          return respostaUtil.getErroInternoCliente(ClienteService.FALHA_AO_TENTAR_CADASTRAR_UM_CLIENTE);
         }
    }

	@Override
	public ResponseEntity<Cliente> consultaCliente(@ApiParam(value = "",required=true) @PathVariable("codigo") Long codigo,@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {
		String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Cliente>(objectMapper.readValue("{  \"senha\" : \"senha123\",  \"cpf\" : \"01234567890\",  \"id\" : 234,  \"contas\" : [ {    \"transacoes\" : [ {      \"tipo\" : \"debito\",      \"data\" : \"2000-01-23T04:56:07.000+00:00\",      \"valor\" : 6.027456183070403,      \"id\" : 0    }, {      \"tipo\" : \"debito\",      \"data\" : \"2000-01-23T04:56:07.000+00:00\",      \"valor\" : 6.027456183070403,      \"id\" : 0    } ],    \"numero\" : 784563,    \"saldo\" : 101.34,    \"agencia\" : 975,    \"digito\" : 7  }, {    \"transacoes\" : [ {      \"tipo\" : \"debito\",      \"data\" : \"2000-01-23T04:56:07.000+00:00\",      \"valor\" : 6.027456183070403,      \"id\" : 0    }, {      \"tipo\" : \"debito\",      \"data\" : \"2000-01-23T04:56:07.000+00:00\",      \"valor\" : 6.027456183070403,      \"id\" : 0    } ],    \"numero\" : 784563,    \"saldo\" : 101.34,    \"agencia\" : 975,    \"digito\" : 7  } ],  \"titular\" : \"Janete Silva\"}", Cliente.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Cliente>(HttpStatus.NOT_IMPLEMENTED);
	}

}